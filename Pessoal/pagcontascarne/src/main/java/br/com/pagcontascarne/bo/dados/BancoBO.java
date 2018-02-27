package br.com.pagcontascarne.bo.dados;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.pagcontascarne.dao.dados.BancoDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.pagcontascarne.modelo.dados.Banco;
import br.com.pagcontascarne.util.Utils;
import com.xpert.core.validation.UniqueFields;
import com.xpert.persistence.query.Restrictions;

/**
 *
 * @author Juniel
 */
@Stateless
public class BancoBO extends AbstractBusinessObject<Banco> {

    @EJB
    private BancoDAO bancoDAO;

    @Override
    public BancoDAO getDAO() {
        return bancoDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add("sigla").add("nome").add("codigo");
    }

    @Override
    public void validate(Banco banco) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

    public List<Banco> listarCodigoOuNome(String nome) {
        Restrictions restrictions = new Restrictions();

        if (!Utils.isNullOrEmpty(nome)) {
            if (Utils.ehInteiro(nome)) {
                restrictions.like("codigo", nome);
            } else {
                restrictions.like("nome", nome);
            }
        }

        return getDAO().list(restrictions, "nome");
    }

}