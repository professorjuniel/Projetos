package geradorne.geradorne.mb.controleacesso;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import geradorne.geradorne.bo.controleacesso.SolicitacaoRecuperacaoSenhaBO;
import geradorne.geradorne.modelo.controleacesso.SolicitacaoRecuperacaoSenha;

/**
 *
 * @author ayslan
 */
@ManagedBean
@ViewScoped
public class SolicitacaoRecuperacaoSenhaMB extends AbstractBaseBean<SolicitacaoRecuperacaoSenha> implements Serializable {

    @EJB
    private SolicitacaoRecuperacaoSenhaBO solicitacaoRecuperacaoSenhaBO;

    @Override
    public SolicitacaoRecuperacaoSenhaBO getBO() {
        return solicitacaoRecuperacaoSenhaBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id DESC";
    }

    @Override
    public boolean isLoadEntityOnPostConstruct() {
        return false;
    }
    
    
}
