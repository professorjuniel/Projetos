package br.gov.pi.ati.agendacultural.mb.cadastro;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.gov.pi.ati.agendacultural.bo.cadastro.SetorBO;
import br.gov.pi.ati.agendacultural.modelo.cadastro.Setor;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class SetorMB extends AbstractBaseBean<Setor> implements Serializable {

    @EJB
    private SetorBO setorBO;

    @Override
    public SetorBO getBO() {
        return setorBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
