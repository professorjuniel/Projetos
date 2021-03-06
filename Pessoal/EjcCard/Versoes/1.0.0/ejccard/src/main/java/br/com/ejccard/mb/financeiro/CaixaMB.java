package br.com.ejccard.mb.financeiro;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.ejccard.bo.financeiro.CaixaBO;
import br.com.ejccard.modelo.financeiro.Caixa;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class CaixaMB extends AbstractBaseBean<Caixa> implements Serializable {

    @EJB
    private CaixaBO caixaBO;

    @Override
    public CaixaBO getBO() {
        return caixaBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
