package br.com.jsoft.centralfinanceira.mb.central;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.jsoft.centralfinanceira.bo.central.ConvenioRecargaBO;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.ConvenioRecarga;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class ConvenioRecargaMB extends AbstractBaseBean<ConvenioRecarga> implements Serializable {

    @EJB
    private ConvenioRecargaBO convenioRecargaBO;

    @Override
    public ConvenioRecargaBO getBO() {
        return convenioRecargaBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
