package br.com.jsoft.centralfinanceira.mb.central;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.jsoft.centralfinanceira.bo.central.ConvenioGasBO;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.ConvenioGas;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class ConvenioGasMB extends AbstractBaseBean<ConvenioGas> implements Serializable {

    @EJB
    private ConvenioGasBO convenioGasBO;

    @Override
    public ConvenioGasBO getBO() {
        return convenioGasBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
