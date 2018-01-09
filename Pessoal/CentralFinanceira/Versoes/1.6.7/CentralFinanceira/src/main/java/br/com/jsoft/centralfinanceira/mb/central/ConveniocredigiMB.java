package br.com.jsoft.centralfinanceira.mb.central;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.jsoft.centralfinanceira.bo.central.ConveniocredigiBO;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.Conveniocredigi;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class ConveniocredigiMB extends AbstractBaseBean<Conveniocredigi> implements Serializable {

    @EJB
    private ConveniocredigiBO conveniocredigiBO;

    @Override
    public ConveniocredigiBO getBO() {
        return conveniocredigiBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
