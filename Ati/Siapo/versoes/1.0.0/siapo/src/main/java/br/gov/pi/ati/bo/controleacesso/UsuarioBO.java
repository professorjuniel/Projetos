package br.gov.pi.ati.bo.controleacesso;

import br.gov.pi.ati.util.SessaoUtils;
import br.gov.pi.ati.dao.controleacesso.HistoricoSituacaoUsuarioDAO;
import br.gov.pi.ati.dao.controleacesso.PerfilDAO;
import br.gov.pi.ati.dao.controleacesso.UsuarioDAO;
import br.gov.pi.ati.modelo.cadastro.Orgao;
import br.gov.pi.ati.modelo.cadastro.UnidadeOrcamentaria;
import br.gov.pi.ati.modelo.controleacesso.HistoricoSituacaoUsuario;
import br.gov.pi.ati.modelo.controleacesso.Perfil;
import br.gov.pi.ati.modelo.controleacesso.SituacaoUsuario;
import br.gov.pi.ati.modelo.controleacesso.TipoRecuperacaoSenha;
import br.gov.pi.ati.modelo.controleacesso.Usuario;
import br.gov.pi.ati.util.Utils;
import com.xpert.core.crud.AbstractBusinessObject;
import com.xpert.persistence.dao.BaseDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import com.xpert.core.validation.UniqueFields;
import com.xpert.persistence.query.Restrictions;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.xpert.utils.Encryption;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Ayslan
 */
@Stateless
public class UsuarioBO extends AbstractBusinessObject<Usuario> {

    private static final int TAMANHO_SENHA_ALEATORIA = 8;
    @EJB
    private UsuarioDAO usuarioDAO;
    @EJB
    private PerfilDAO perfilDAO;
    @EJB
    private HistoricoSituacaoUsuarioDAO historicoSituacaoUsuarioDAO;
    @EJB
    private SolicitacaoRecuperacaoSenhaBO solicitacaoRecuperacaoSenhaBO;

    @Override
    public BaseDAO getDAO() {
        return usuarioDAO;
    }

    /**
     * nao pode-se repetir o email , cpf e login
     *
     * @return
     */
    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields()
                .add("cpf")
                .add("userLogin")
                .add("email");
    }

    public void enviarSenhaCadastro(Usuario usuario) throws BusinessException {
        solicitacaoRecuperacaoSenhaBO.save(usuario.getEmail(), TipoRecuperacaoSenha.NOVO_USUARIO);
        usuario.setEmailCadastroEnviado(true);
        usuarioDAO.merge(usuario);
    }

    @Override
    public void save(Usuario usuario) throws BusinessException {
        boolean novo = usuario.getId() == null;

        SituacaoUsuario situacaoUsuarioAnterior = null;
        if (!novo) {
            //pegar situacao do banco
            situacaoUsuarioAnterior = (SituacaoUsuario) usuarioDAO.findAttribute("situacaoUsuario", usuario.getId());
        }

        if (novo) {
            try {
                usuario.setDataCadastro(new Date());
                //setar senha aleatoria para nao deixar campo em branco
                usuario.setUserPassword(Encryption.getSHA256(RandomStringUtils.random(10)));
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        }
        //super usuario pode remover o perfil mesmo sem te-lo
        if (SessaoUtils.getUser().isSuperUsuario() == false) {
            /*
             caso nao venha o perfil marcado e esse o usuario que estiver cadastrando nao possuir esse perfil, ele deve ser adicionado, 
             pois nesse caso o usuario logado nao tem acesso a remover o perfil q ele nao tem acesso
             */
            List<Perfil> perfisUsuarioLogado = SessaoUtils.getUser().getPerfis();
            List<Perfil> perfisNovosCadastro = usuario.getPerfis();
            if (usuario.getId() != null) {
                List<Perfil> perfisAtuaisUsuario = perfilDAO.getPerfis(usuario);
                for (Perfil perfil : perfisAtuaisUsuario) {
                    //se nao conter, mas estiver removendo
                    if (!perfisNovosCadastro.contains(perfil) && !perfisUsuarioLogado.contains(perfil) && perfisAtuaisUsuario.contains(perfil)) {
                        perfisNovosCadastro.add(perfil);
                    }
                }
            }
        }
        //salvar usuario
        super.save(usuario);

        //caso nao exista uma situacao anterior, ou ele for diferente da nova, salvar um historico
        if (situacaoUsuarioAnterior == null || !situacaoUsuarioAnterior.equals(usuario.getSituacaoUsuario())) {
            HistoricoSituacaoUsuario historicoSituacaoUsuario = new HistoricoSituacaoUsuario();
            historicoSituacaoUsuario.setDataSituacao(new Date());
            historicoSituacaoUsuario.setSituacaoUsuario(usuario.getSituacaoUsuario());
            historicoSituacaoUsuario.setUsuario(usuario);
            historicoSituacaoUsuario.setUsuarioAlteracao(SessaoUtils.getUser());
            historicoSituacaoUsuarioDAO.merge(historicoSituacaoUsuario);
            //atualizar lista do objeto usuario
            usuario.setHistoricosSituacao(usuarioDAO.getInitialized(usuario.getHistoricosSituacao()));
            if (usuario.getHistoricosSituacao() == null) {
                usuario.setHistoricosSituacao(new ArrayList<HistoricoSituacaoUsuario>());
            }
            usuario.getHistoricosSituacao().add(historicoSituacaoUsuario);
        }
    }

    public static String getSenhaAleatoria() {
        return RandomStringUtils.randomAlphanumeric(TAMANHO_SENHA_ALEATORIA);
    }

    @Override
    public void validate(Usuario usuario) throws BusinessException {

    }

    @Override
    public boolean isAudit() {
        return true;
    }

    /**
     * retorn o usuario a partir do cpf
     *
     * @param cpf
     * @return
     */
    public Usuario getUsuario(String cpf) {
        return usuarioDAO.unique("cpf", cpf);
    }
    
    public List<Usuario> usuariosPeloUnidadeOrcamentaria(UnidadeOrcamentaria unidade, String nome){
        Restrictions restrictions = new Restrictions();
        
        if(unidade==null){
            return null;
        }
        
        if(!Utils.isNullOrEmpty(nome)){
            restrictions.like("usuario.nome", nome);
        }
        
        restrictions.in("unidades", unidade);
        
        restrictions.add("usuario.homologar", true);
        
        return getDAO().getQueryBuilder().selectDistinct("usuario").from(Usuario.class, "usuario").leftJoinFetch("usuario.unidadesDeAcesso", "unidades")
                .leftJoinFetch("unidades.orgao", "orgao").add(restrictions).orderBy("usuario.nome").getResultList();
    }
}
