package br.com.weblicita.bo.cadastro;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.weblicita.dao.cadastro.PaisDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.weblicita.modelo.cadastro.Pais;
import br.com.weblicita.util.Utils;
import com.xpert.core.exception.UniqueFieldException;
import com.xpert.core.validation.UniqueFields;
import com.xpert.core.validation.UniqueFieldsValidation;
import com.xpert.persistence.query.Restriction;

/**
 *
 * @author Juniel
 */
@Stateless
public class PaisBO extends AbstractBusinessObject<Pais> {

    @EJB
    private PaisDAO paisDAO;

    @Override
    public PaisDAO getDAO() {
        return paisDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return null;
    }

    @Override
    public void validateUniqueFields(Pais pais) throws UniqueFieldException {
        Restriction restriction = new Restriction("UPPER(TRANSLATE(nome,'ÁÀÂÃÄáàâãäÉÈÊËéèêëÍÌÎÏíìîïÓÒÕÔÖóòôõöÚÙÛÜúùûüÇç','AAAAAaaaaaEEEEeeeeIIIIiiiiOOOOOoooooUUUUuuuuCc'))",
                Utils.removerAcentos(pais.getNome()).toUpperCase());
        UniqueField uniqueField = new UniqueField(restriction).setMessage("Já existe pais cadastrado com esse nome: ".concat(pais.getNome().toUpperCase()));

        UniqueFields uniqueFields = new UniqueFields();
        uniqueFields.add(uniqueField);

        Restriction restriction2 = new Restriction("UPPER(sigla)", pais.getSigla().toUpperCase());
        UniqueField uniqueField2 = new UniqueField(restriction2).setMessage("Já existe País cadastrado com essa sigla: ".concat(pais.getSigla().toUpperCase()));

        uniqueFields.add(uniqueField2);

        if (!Utils.isNullOrEmpty(pais.getCodigo())) {
            Restriction restriction3 = new Restriction("codigo", pais.getCodigo());
            UniqueField uniqueField3 = new UniqueField(restriction3).setMessage("Já existe País cadastrado com esse código: ".concat(pais.getCodigo()));
            uniqueFields.add(uniqueField3);
        }

        UniqueFieldsValidation.validateUniqueFields(uniqueFields, pais, getDAO());
    }

    @Override
    public void validate(Pais pais) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
