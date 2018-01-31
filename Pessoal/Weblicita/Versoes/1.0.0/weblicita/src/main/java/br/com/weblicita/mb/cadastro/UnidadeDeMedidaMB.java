package br.com.weblicita.mb.cadastro;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.weblicita.bo.cadastro.UnidadeDeMedidaBO;
import br.com.weblicita.modelo.cadastro.UnidadeDeMedida;
import br.com.weblicita.util.Utils;
import com.xpert.faces.utils.FacesMessageUtils;
import java.util.List;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class UnidadeDeMedidaMB extends AbstractBaseBean<UnidadeDeMedida> implements Serializable {

    @EJB
    private UnidadeDeMedidaBO unidadeDeMedidaBO;

    private boolean renderizarCampo;

    private boolean somenteLeitura;

    private boolean renderizarFormulario;

    private String nome = "";

    @Override
    public UnidadeDeMedidaBO getBO() {
        return unidadeDeMedidaBO;
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
        setEntity(new UnidadeDeMedida());
    }

    @Override
    public void save() {
        super.save();
    }

    public void novo() {
        setEntity(new UnidadeDeMedida());
        renderizarCampo = true;
        somenteLeitura = false;
        renderizarFormulario = true;
    }

    public void desativar() {
        if (getEntity().getId() != null) {
            getEntity().setAtivo(false);
            renderizarCampo = false;
            somenteLeitura = false;
            renderizarFormulario = false;
            super.save();
        } else {
            FacesMessageUtils.error("Informe o País a ser desativado!");
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
            FacesMessageUtils.error("Informe o País a ser desativado!");
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
        if (!Utils.isNullOrEmpty(nome)) {
            UnidadeDeMedida unidade = getDAO().unique("nome", nome);
            if (unidade != null) {
                renderizarCampo = false;
                somenteLeitura = true;
                renderizarFormulario = true;
                setEntity(unidade);
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
    
    public List<UnidadeDeMedida> autocomplete(String nome){
        return getBO().unidadePeloNome(nome);
    }
    
    public boolean habilitarBotaoDesativar() {
        return getEntity().getId() != null && getEntity().isAtivo();
    }

    public boolean habilitarBotaoAtivar() {
        return getEntity().getId() != null && !getEntity().isAtivo();
    }
}