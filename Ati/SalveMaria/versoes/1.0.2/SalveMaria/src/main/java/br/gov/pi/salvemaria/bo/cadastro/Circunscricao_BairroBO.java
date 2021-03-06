package br.gov.pi.salvemaria.bo.cadastro;

import com.xpert.core.crud.AbstractBusinessObject;
import br.gov.pi.salvemaria.dao.cadastro.Circunscricao_BairroDAO;
import br.gov.pi.salvemaria.modelo.cadastro.Bairro;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.gov.pi.salvemaria.modelo.cadastro.CircunscricaoBairro;
import com.xpert.core.validation.UniqueFields;
import com.xpert.faces.utils.FacesMessageUtils;

/**
 *
 * @author Juniel
 */
@Stateless
public class Circunscricao_BairroBO extends AbstractBusinessObject<CircunscricaoBairro> {

    @EJB
    private Circunscricao_BairroDAO circunscricao_BairroDAO;

    @Override
    public Circunscricao_BairroDAO getDAO() {
        return circunscricao_BairroDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add("nome", "cidade");
    }

    @Override
    public void validate(CircunscricaoBairro circunscricao_Bairro) throws BusinessException {
        List<Bairro> bairros = getDAO().getInitialized(circunscricao_Bairro.getBairros());
        if (bairros.size() < 1) {
            throw new BusinessException("É necessário ao menos um bairro para o cadastro de uma Circunscrição!");
        }
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
