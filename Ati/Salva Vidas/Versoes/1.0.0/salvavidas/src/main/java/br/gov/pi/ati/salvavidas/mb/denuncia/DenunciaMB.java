package br.gov.pi.ati.salvavidas.mb.denuncia;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.gov.pi.ati.salvavidas.bo.denuncia.DenunciaBO;
import br.gov.pi.ati.salvavidas.modelo.denuncia.Denuncia;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class DenunciaMB extends AbstractBaseBean<Denuncia> implements Serializable {

    @EJB
    private DenunciaBO denunciaBO;

    @Override
    public DenunciaBO getBO() {
        return denunciaBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
