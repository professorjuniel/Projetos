package br.com.weblicita.mb.cadastro;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.weblicita.bo.cadastro.OrgaoBO;
import br.com.weblicita.modelo.cadastro.Orgao;
import br.com.weblicita.modelo.cadastro.Telefone;
import br.com.weblicita.util.Utils;
import com.xpert.faces.utils.FacesMessageUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class OrgaoMB extends AbstractBaseBean<Orgao> implements Serializable {

    @EJB
    private OrgaoBO orgaoBO;

    private Telefone telefoneAdd;

    private List<Telefone> telefones = new ArrayList<Telefone>();

    private String cnpj;

    private boolean renderizarCampo;

    private boolean somenteLeitura;

    private boolean renderizarFormulario;

    @Override
    public OrgaoBO getBO() {
        return orgaoBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    @Override
    public void init() {
        telefoneAdd = new Telefone();
        telefones = new ArrayList<Telefone>();
        if (getEntity().getId() == null) {
            getEntity().setCodigo(getBO().gerarCodigo());
        }
        renderizarCampo = false;
        somenteLeitura = false;
        renderizarFormulario = false;

    }

    @Override
    public void save() {
        if (getEntity().getId() != null) {
            getEntity().setCodigo(orgaoBO.gerarCodigo());
        } else {
            getEntity().setCodigo(getBO().gerarCodigo());

        }
        getEntity().setTelefones(telefones);
        super.save();

    }

    @Override
    public void postSave() {
        renderizarCampo = false;
        somenteLeitura = false;
        renderizarFormulario = false;

        setEntity(new Orgao());
    }

    public Telefone getTelefoneAdd() {
        return telefoneAdd;
    }

    public void setTelefoneAdd(Telefone telefoneAdd) {
        this.telefoneAdd = telefoneAdd;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void addTelefone() {
        if (Utils.isNullOrEmpty(telefoneAdd.getNumero())) {
            FacesMessageUtils.error("O número do telefone é obrigatório!");
        } else {
            if (telefoneAdd.getTipo() != null) {
                telefones.add(telefoneAdd);
                telefoneAdd = new Telefone();
            } else {
                FacesMessageUtils.error("Tipo do telefone é obrigatório!");
            }

        }
    }

    public void removerTelefone(Telefone telefone) {
        telefones.remove(telefone);
    }

    public void buscar() {
        setEntity(new Orgao());
        if (!Utils.isNullOrEmpty(cnpj)) {
            Orgao orgao = orgaoBO.getDAO().unique("cnpj", cnpj);
            if (orgao != null) {
                setEntity(orgao);
                telefones = getBO().getDAO().getInitialized(orgao.getTelefones());
                renderizarCampo = false;
                somenteLeitura = true;
                renderizarFormulario = true;
            } else {
                renderizarCampo = false;
                somenteLeitura = true;
                renderizarFormulario = false;
                FacesMessageUtils.error("Orgão não encontrado!!");
            }
        } else {
            renderizarCampo = false;
            somenteLeitura = true;
            renderizarFormulario = false;
            FacesMessageUtils.error("CNPJ da busca é obrigatatório!");
        }

    }

    public void novo() {
        setEntity(new Orgao());
        getEntity().setCodigo(orgaoBO.gerarCodigo());
        telefoneAdd = new Telefone();

        telefones = new ArrayList<Telefone>();
        renderizarCampo = true;
        somenteLeitura = false;
        renderizarFormulario = true;

    }

    public void editar() {
        telefoneAdd = new Telefone();
        if (getEntity().getId() != null) {
            renderizarCampo = true;
            somenteLeitura = false;
            renderizarFormulario = true;
        } else {
            FacesMessageUtils.error("Carregue o Órgão que será editado!!");
        }
    }

    public void desativar() {
        if (getEntity().getId() != null) {
            getEntity().setAtivo(false);
            renderizarCampo = false;
            somenteLeitura = false;
            renderizarFormulario = false;
            super.save();

        } else {
            FacesMessageUtils.error("Carregue o Órgão que será desativado!!");
        }
    }

    public void ativar() {
        if (getEntity().getId() != null) {
            getEntity().setAtivo(true);
            renderizarCampo = false;
            somenteLeitura = false;
            renderizarFormulario = false;
            super.save();

        } else {
            FacesMessageUtils.error("Carregue o Órgão que será ativado!!");
        }
    }

    public boolean isRenderizarCampo() {
        return renderizarCampo;
    }

    public void setRenderizarCampo(boolean renderizarCampo) {
        this.renderizarCampo = renderizarCampo;
    }

    public boolean isSomenteLeitura() {
        return somenteLeitura;
    }

    public void setSomenteLeitura(boolean somenteLeitura) {
        this.somenteLeitura = somenteLeitura;
    }

    public boolean isRenderizarFormulario() {
        return renderizarFormulario;
    }

    public void setRenderizarFormulario(boolean renderizarFormulario) {
        this.renderizarFormulario = renderizarFormulario;
    }

    public List<Orgao> autocomplete(String nome) {
        return getBO().orgaoPeloNome(nome);
    }

    public boolean habilitarBotaoDesativar() {
        return getEntity().getId() != null && getEntity().isAtivo();
    }

    public boolean habilitarBotaoAtivar() {
        return getEntity().getId() != null && !getEntity().isAtivo();
    }
}
