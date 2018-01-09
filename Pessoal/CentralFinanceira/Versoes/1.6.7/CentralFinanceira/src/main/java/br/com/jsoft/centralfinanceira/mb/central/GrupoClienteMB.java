package br.com.jsoft.centralfinanceira.mb.central;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.jsoft.centralfinanceira.bo.central.GrupoClienteBO;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.Empresa;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.GrupoCliente;
import br.com.jsoft.centralfinanceira.util.SessaoUtils;

/**
 *
 * @author KillerBeeTwo
 */
@ManagedBean
@ViewScoped
public class GrupoClienteMB extends AbstractBaseBean<GrupoCliente> implements Serializable {

    @EJB
    private GrupoClienteBO grupoClienteBO;

    @Override
    public GrupoClienteBO getBO() {
        return grupoClienteBO;
    }

    @Override
    public String getDataModelOrder() {
        return "nome";
    }

    @Override
    public void init() {
        Empresa empresa = grupoClienteBO.getDAO().getInitialized(SessaoUtils.getUser().getEmpresa());
        getEntity().setEmpresa(empresa);

    }
}
