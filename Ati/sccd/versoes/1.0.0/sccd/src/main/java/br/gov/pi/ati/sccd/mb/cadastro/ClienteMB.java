package br.gov.pi.ati.sccd.mb.cadastro;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.gov.pi.ati.sccd.bo.cadastro.ClienteBO;
import br.gov.pi.ati.sccd.modelo.cadastro.Cliente;
import java.util.List;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class ClienteMB extends AbstractBaseBean<Cliente> implements Serializable {

    @EJB
    private ClienteBO clienteBO;

    @Override
    public ClienteBO getBO() {
        return clienteBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    public List<Cliente> clientesAtivos() {
        return getBO().clientesAtivos();
    }

    public List<Cliente> autocompletePeloNome(String nome) {
        return getBO().clientesAtivosPeloNome(nome);
    }
}
