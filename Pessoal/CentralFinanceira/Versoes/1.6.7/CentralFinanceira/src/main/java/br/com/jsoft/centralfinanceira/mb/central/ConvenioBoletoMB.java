package br.com.jsoft.centralfinanceira.mb.central;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.jsoft.centralfinanceira.bo.central.ConvenioBoletoBO;
import br.com.jsoft.centralfinanceira.dao.central.ConvenioBoletoDAO;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.ConvenioBoleto;
import java.util.List;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class ConvenioBoletoMB extends AbstractBaseBean<ConvenioBoleto> implements Serializable {

    @EJB
    private ConvenioBoletoBO convenioBoletoBO;

    @EJB
    private ConvenioBoletoDAO convenioDao;

    @Override
    public ConvenioBoletoBO getBO() {
        return convenioBoletoBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    public List<ConvenioBoleto> consultarConvenioBoleto(String nome) {

        if (nome != null && !nome.isEmpty()) {

            return convenioDao.getConvenioPorNome(nome);

        }

        return null;

    }
}
