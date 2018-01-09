package br.com.jsoft.centralfinanceira.mb.central;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.jsoft.centralfinanceira.bo.central.GrupoFornecedorBO;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.Empresa;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.GrupoFornecedor;
import br.com.jsoft.centralfinanceira.util.SessaoUtils;

/**
 *
 * @author KillerBeeTwo
 */
@ManagedBean
@ViewScoped
public class GrupoFornecedorMB extends AbstractBaseBean<GrupoFornecedor> implements Serializable {

    @EJB
    private GrupoFornecedorBO grupoFornecedorBO;

    @Override
    public GrupoFornecedorBO getBO() {
        return grupoFornecedorBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    @Override
    public void init() {
        Empresa empresa = grupoFornecedorBO.getDAO().getInitialized(SessaoUtils.getUser().getEmpresa());
        getEntity().setEmpresa(empresa);
    }
}
