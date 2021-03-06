package br.com.weblicita.bo.contrato;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.weblicita.dao.contrato.ContratoDAO;
import br.com.weblicita.modelo.cadastro.Fornecedor;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.weblicita.modelo.contrato.Contrato;
import br.com.weblicita.modelo.controleacesso.Usuario;
import br.com.weblicita.util.SessaoUtils;
import br.com.weblicita.util.Utils;
import com.xpert.persistence.query.Restrictions;
import java.util.Date;

/**
 *
 * @author Juniel
 */
@Stateless
public class ContratoBO extends AbstractBusinessObject<Contrato> {

    @EJB
    private ContratoDAO contratoDAO;

    @Override
    public ContratoDAO getDAO() {
        return contratoDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return null;
    }

    @Override
    public void validate(Contrato contrato) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

    public String gerarNumeroContrato() {
        Usuario usuarioAtual = SessaoUtils.getUser();

        String codigo = "";

        String anoAtual = Utils.getAno(new Date());

        Long id = (Long) getDAO().getQueryBuilder().from(Contrato.class).add("orgao", usuarioAtual.getOrgao()).max("id");

        Contrato contrato = getDAO().unique("id", id);

        if (contrato != null) {
            

            if (Utils.isNullOrEmpty(contrato.getNumeroContrato())) {
                codigo = codigo.concat(Utils.inserirZeroDireita("1", 6)).concat("-").concat(anoAtual);
            } else {
                String[] codigoTemp = new String[2];
                
                codigoTemp = contrato.getNumeroContrato().split("\\-");

                String anoContrato = codigoTemp[1];

                Integer sequencial = new Integer(codigoTemp[0]);

                if (anoAtual.equals(anoContrato)) {
                    sequencial++;
                    codigo = codigo.concat(Utils.inserirZeroDireita(sequencial.toString(), 6)).concat("-").concat(anoContrato);
                } else {
                    codigo = codigo.concat(Utils.inserirZeroDireita("1", 6)).concat("-").concat(anoAtual);
                }
            }

        } else {
            codigo = codigo.concat(Utils.inserirZeroDireita("1", 6)).concat("-").concat(anoAtual);
        }

        return codigo;
    }

    public Contrato buscarContatoPeloNumero(String numero) {
        Usuario usuarioAtual = SessaoUtils.getUser();
        Restrictions restrictions = new Restrictions();
        restrictions.add("orgao", usuarioAtual.getOrgao());
        restrictions.add("numeroContrato", numero);
        return getDAO().unique(restrictions);
    }
}
