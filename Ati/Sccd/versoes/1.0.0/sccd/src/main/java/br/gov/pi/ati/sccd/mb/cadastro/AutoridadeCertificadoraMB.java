package br.gov.pi.ati.sccd.mb.cadastro;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.gov.pi.ati.sccd.bo.cadastro.AutoridadeCertificadoraBO;
import br.gov.pi.ati.sccd.modelo.cadastro.AutoridadeCertificadora;
import com.xpert.persistence.query.JoinBuilder;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class AutoridadeCertificadoraMB extends AbstractBaseBean<AutoridadeCertificadora> implements Serializable {

    @EJB
    private AutoridadeCertificadoraBO autoridadeCertificadoraBO;

    @Override
    public AutoridadeCertificadoraBO getBO() {
        return autoridadeCertificadoraBO;
    }

    @Override
    public String getDataModelOrder() {
        return "fornecedor, autoridade.nome";
    }
    
    
    @Override
    public JoinBuilder getDataModelJoinBuilder() {
        return new JoinBuilder("autoridade")
                .leftJoinFetch("autoridade.fornecedor", "fornecedor");
    }
}
