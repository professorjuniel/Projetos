package br.com.weblicita.bo.cadastro;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.weblicita.dao.cadastro.UnidadeDeMedidaDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.weblicita.modelo.cadastro.UnidadeDeMedida;
import br.com.weblicita.util.Utils;
import com.xpert.core.exception.UniqueFieldException;
import com.xpert.core.validation.UniqueFields;
import com.xpert.core.validation.UniqueFieldsValidation;
import com.xpert.persistence.query.Restriction;
import com.xpert.persistence.query.Restrictions;

/**
 *
 * @author Juniel
 */
@Stateless
public class UnidadeDeMedidaBO extends AbstractBusinessObject<UnidadeDeMedida> {

    @EJB
    private UnidadeDeMedidaDAO unidadeDeMedidaDAO;

    @Override
    public UnidadeDeMedidaDAO getDAO() {
        return unidadeDeMedidaDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
//        return new UniqueFields().add("nome");
        return null;
    }

    @Override
    public void validateUniqueFields(UnidadeDeMedida unidade) throws UniqueFieldException {
        UniqueFields uniqueFields = new UniqueFields();
        Restriction restriction = new Restriction("UPPER(TRANSLATE(nome,'ÁÀÂÃÄáàâãäÉÈÊËéèêëÍÌÎÏíìîïÓÒÕÔÖóòôõöÚÙÛÜúùûüÇç','AAAAAaaaaaEEEEeeeeIIIIiiiiOOOOOoooooUUUUuuuuCc'))",
                Utils.removerAcentos(unidade.getNome()).toUpperCase());
        UniqueField uniqueField = new UniqueField(restriction).setMessage("Já existe unidade cadastrada com esse nome: ".concat(unidade.getNome().toUpperCase()));

        Restriction restriction2 = new Restriction("UPPER(sigla)", unidade.getSigla().toUpperCase());
        UniqueField uniqueField2 = new UniqueField(restriction2).setMessage("Já existe unidade cadastrada com essa sigla: ".concat(unidade.getSigla().toUpperCase()));

        uniqueFields.add(uniqueField);
        uniqueFields.add(uniqueField2);
        UniqueFieldsValidation.validateUniqueFields(uniqueFields, unidade, getDAO());
    }

    @Override
    public void validate(UnidadeDeMedida unidadeDeMedida) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

    public List<UnidadeDeMedida> unidadePeloNome(String nome) {
        Restrictions restrictions = new Restrictions();

        restrictions.add("ativo", true);

        if (!Utils.isNullOrEmpty(nome)) {
            restrictions.like("nome", nome);
        }

        return getDAO().list(restrictions, "nome");
    }

    public List<UnidadeDeMedida> unidadeAtidas() {
        Restrictions restrictions = new Restrictions();

        restrictions.add("ativo", true);

        return getDAO().list(restrictions, "nome");
    }

}
