package br.gov.pi.ati.passelivre.dao.controleacesso.impl;

import br.gov.pi.ati.passelivre.application.BaseDAOImpl;
import br.gov.pi.ati.passelivre.dao.controleacesso.UsuarioDAO;
import br.gov.pi.ati.passelivre.modelo.controleacesso.SituacaoUsuario;
import br.gov.pi.ati.passelivre.modelo.controleacesso.Usuario;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Ayslan
 */
@Stateless
public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements UsuarioDAO {

    @Override
    public Class getEntityClass() {
        return Usuario.class;
    }

    @Override
    public List<Usuario> getUsuariosAtivos() {
        return list("situacaoUsuario", SituacaoUsuario.ATIVO, "nome");
    }
    
    
}
