package br.com.weblicita.bo.cadastro;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.weblicita.dao.cadastro.ItemLicitacaoDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.weblicita.modelo.cadastro.Item;

/**
 *
 * @author Juniel
 */
@Stateless
public class ItemLicitacaoBO extends AbstractBusinessObject<Item> {

    @EJB
    private ItemLicitacaoDAO itemLicitacaoDAO;
    
    @Override
    public ItemLicitacaoDAO getDAO() {
        return itemLicitacaoDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return null;
    }

    @Override
    public void validate(Item itemLicitacao) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
