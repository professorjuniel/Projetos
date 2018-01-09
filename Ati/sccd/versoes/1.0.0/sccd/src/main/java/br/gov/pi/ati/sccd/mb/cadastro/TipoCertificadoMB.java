package br.gov.pi.ati.sccd.mb.cadastro;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.gov.pi.ati.sccd.bo.cadastro.TipoCertificadoBO;
import br.gov.pi.ati.sccd.modelo.cadastro.TipoCertificado;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class TipoCertificadoMB extends AbstractBaseBean<TipoCertificado> implements Serializable {

    @EJB
    private TipoCertificadoBO tipoCertificadoBO;

    @Override
    public TipoCertificadoBO getBO() {
        return tipoCertificadoBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
