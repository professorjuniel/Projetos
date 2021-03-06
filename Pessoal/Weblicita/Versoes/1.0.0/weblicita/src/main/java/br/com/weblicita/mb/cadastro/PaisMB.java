package br.com.weblicita.mb.cadastro;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.weblicita.bo.cadastro.PaisBO;
import br.com.weblicita.modelo.cadastro.Pais;
import br.com.weblicita.util.Utils;
import com.xpert.faces.utils.FacesMessageUtils;
import com.xpert.persistence.query.Restrictions;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class PaisMB extends AbstractBaseBean<Pais> implements Serializable {

    @EJB
    private PaisBO paisBO;

    private boolean renderizarCampo;

    private boolean somenteLeitura;

    private boolean renderizarFormulario;

    private String nome = "";

    @Override
    public PaisBO getBO() {
        return paisBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    @Override
    public void init() {
        renderizarCampo = false;
        somenteLeitura = false;
        renderizarFormulario = false;
    }

    @Override
    public void postSave() {
        renderizarCampo = false;
        somenteLeitura = false;
        renderizarFormulario = false;
        setEntity(new Pais());
    }

    @Override
    public void save() {
        super.save();
    }

    public void novo() {
        setEntity(new Pais());
        renderizarCampo = true;
        somenteLeitura = false;
        renderizarFormulario = true;
    }

    public void excluir() {
        if (getEntity().getId() != null) {
            remove();
            setEntity(new Pais());
            renderizarCampo = false;
            somenteLeitura = false;
            renderizarFormulario = false;
        } else {
            FacesMessageUtils.error("Informe o País a ser desativado!");
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
            FacesMessageUtils.error("Carregue o Pais que será desativado!!");
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
            FacesMessageUtils.error("Carregue o Pais que será ativado!!");
        }
    }

    public void editar() {
        if (getEntity().getId() != null) {
            renderizarCampo = true;
            somenteLeitura = false;
            renderizarFormulario = true;
        } else {
            FacesMessageUtils.error("Informe o País a ser editado!");
        }
    }

    public void buscar() {
        setEntity(new Pais());
        if (!Utils.isNullOrEmpty(nome)) {
            Restrictions restrictions = new Restrictions();
            restrictions.equals("UPPER(TRANSLATE(nome,'ÁÀÂÃÄáàâãäÉÈÊËéèêëÍÌÎÏíìîïÓÒÕÔÖóòôõöÚÙÛÜúùûüÇç','AAAAAaaaaaEEEEeeeeIIIIiiiiOOOOOoooooUUUUuuuuCc'))",
                    Utils.removerAcentos(nome).toUpperCase());
            Pais paisTemp = getDAO().unique(restrictions);
            if (paisTemp != null) {
                renderizarCampo = false;
                somenteLeitura = true;
                renderizarFormulario = true;
                setEntity(paisTemp);
            } else {
                renderizarCampo = false;
                somenteLeitura = true;
                renderizarFormulario = false;
                FacesMessageUtils.error("País não encontrado!");
            }
        } else {
            renderizarCampo = false;
            somenteLeitura = true;
            renderizarFormulario = false;
            FacesMessageUtils.error("Informe o nome para busca!");
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean habilitarBotaoDesativar() {
        return getEntity().getId() != null && getEntity().isAtivo();
    }

    public boolean habilitarBotaoAtivar() {
        return getEntity().getId() != null && !getEntity().isAtivo();
    }
}
