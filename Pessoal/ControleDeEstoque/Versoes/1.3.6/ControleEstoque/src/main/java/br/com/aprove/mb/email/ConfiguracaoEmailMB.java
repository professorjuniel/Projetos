package br.com.aprove.mb.email;

import br.com.aprove.bo.email.ConfiguracaoEmailBO;
import br.com.aprove.bo.email.EmailBO;
import br.com.aprove.modelo.email.ConfiguracaoEmail;
import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.xpert.core.exception.BusinessException;
import com.xpert.faces.utils.FacesMessageUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ayslan
 */
@ManagedBean
@ViewScoped
public class ConfiguracaoEmailMB extends AbstractBaseBean<ConfiguracaoEmail> implements Serializable {
    
    @EJB
    private ConfiguracaoEmailBO configuracaoEmailBO;
    @EJB
    private EmailBO emailBO;
    
    @NotBlank(message="required.mensagem")
    private String mensagem;
    
    @NotBlank(message="required.email")
    @Email(message="business.emailInvalido")
    private String email;
    
    public void enviarEmail() {
        try {
            emailBO.enviar("Mensagem de Teste", mensagem, getEntity(), email);
            FacesMessageUtils.sucess();
        } catch (BusinessException ex) {
            FacesMessageUtils.error(ex);
        }
    }
    
    @Override
    public ConfiguracaoEmailBO getBO() {
        return configuracaoEmailBO;
    }
    
    @Override
    public String getDataModelOrder() {
        return "email";
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
