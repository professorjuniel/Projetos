package br.gov.pi.ati.sccd.bo.cadastro;

import com.xpert.core.crud.AbstractBusinessObject;
import br.gov.pi.ati.sccd.dao.cadastro.ClienteDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.gov.pi.ati.sccd.modelo.cadastro.Cliente;
import br.gov.pi.ati.sccd.util.Utils;
import com.xpert.core.validation.UniqueFields;
import com.xpert.persistence.query.Restrictions;

/**
 *
 * @author Juniel
 */
@Stateless
public class ClienteBO extends AbstractBusinessObject<Cliente> {

    @EJB
    private ClienteDAO clienteDAO;

    @Override
    public ClienteDAO getDAO() {
        return clienteDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add("nome").add("cpfCnpj").add("codigo");
    }

    @Override
    public void validate(Cliente cliente) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

    public List<Cliente> clientesAtivosPeloNome(String nome) {
        Restrictions restrictions = new Restrictions();
        if (!Utils.isNullOrEmpty(nome)) {
            restrictions.like("cliente.nome", nome);
        }

        restrictions.add("cliente.ativo", true);

        return getDAO().getQueryBuilder().from(Cliente.class, "cliente").add(restrictions).orderBy("cliente.nome").getResultList();
    }

    public List<Cliente> clientesAtivos() {
        Restrictions restrictions = new Restrictions();
        restrictions.add("cliente.ativo", true);
        return getDAO().getQueryBuilder().from(Cliente.class, "cliente").add(restrictions).orderBy("cliente.nome").getResultList();
    }
}
