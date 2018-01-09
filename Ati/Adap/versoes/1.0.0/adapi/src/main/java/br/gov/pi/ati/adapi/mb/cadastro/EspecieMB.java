package br.gov.pi.ati.adapi.mb.cadastro;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.gov.pi.ati.adapi.bo.cadastro.EspecieBO;
import br.gov.pi.ati.adapi.modelo.cadastro.Especie;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class EspecieMB extends AbstractBaseBean<Especie> implements Serializable {

    @EJB
    private EspecieBO especieBO;

    @Override
    public EspecieBO getBO() {
        return especieBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
