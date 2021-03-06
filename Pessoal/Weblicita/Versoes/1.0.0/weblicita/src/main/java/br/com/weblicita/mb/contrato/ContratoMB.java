package br.com.weblicita.mb.contrato;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.weblicita.bo.contrato.ContratoBO;
import br.com.weblicita.modelo.cadastro.Arquivo;
import br.com.weblicita.modelo.cadastro.enums.TipoArquivoContrato;
import br.com.weblicita.modelo.contrato.Contrato;
import br.com.weblicita.modelo.controleacesso.Usuario;
import br.com.weblicita.util.SessaoUtils;
import br.com.weblicita.util.Utils;
import com.xpert.faces.utils.FacesMessageUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.io.FilenameUtils.getExtension;
import org.bouncycastle.util.encoders.Base64;
import org.hibernate.proxy.HibernateProxy;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class ContratoMB extends AbstractBaseBean<Contrato> implements Serializable {
    
    @EJB
    private ContratoBO contratoBO;
    
    private List<Arquivo> arquivos;
    
    Usuario usuarioAtual = SessaoUtils.getUser();
    
    private boolean renderizarCampo;
    
    private boolean somenteLeitura;
    
    private boolean renderizarFormulario;
    
    private String numeroContrato = "";
    
    @Override
    public ContratoBO getBO() {
        return contratoBO;
    }
    
    @Override
    public String getDataModelOrder() {
        return "id";
    }
    
    @Override
    public void init() {
        renderizarCampo = false;
        somenteLeitura = false;
        renderizarFormulario = false;
    }
    
    @Override
    public void postSave() {
        renderizarCampo = false;
        somenteLeitura = false;
        renderizarFormulario = false;
        setEntity(new Contrato());
    }
    
    @Override
    public void save() {
        getEntity().setArquivos(arquivos);
        super.save();
    }
    
    public void novo() {
        setEntity(new Contrato());
        getEntity().setOrgao(usuarioAtual.getOrgao());
        getEntity().setNumeroContrato(getBO().gerarNumeroContrato());
        arquivos = new ArrayList<Arquivo>();
        renderizarCampo = true;
        somenteLeitura = false;
        renderizarFormulario = true;
    }
    
    public void excluir() {
        if (getEntity().getId() != null) {
            remove();
            setEntity(new Contrato());
            renderizarCampo = false;
            somenteLeitura = false;
            renderizarFormulario = false;
        } else {
            FacesMessageUtils.error("Informe o Contrato a ser desativado!");
        }
    }
    
    public void editar() {
        if (getEntity().getId() != null) {
            renderizarCampo = true;
            somenteLeitura = false;
            renderizarFormulario = true;
            arquivos = getDAO().getInitialized(getEntity().getArquivos());
        } else {
            FacesMessageUtils.error("Informe o Contrato a ser editado!");
        }
    }
    
    public void buscar() {
        setEntity(new Contrato());
        
        arquivos = new ArrayList<Arquivo>();
        if (!Utils.isNullOrEmpty(numeroContrato)) {
            Contrato contratoTemp = getBO().buscarContatoPeloNumero(numeroContrato);
            if (contratoTemp != null) {
                renderizarCampo = false;
                somenteLeitura = true;
                renderizarFormulario = true;
                setEntity(contratoTemp);
            } else {
                renderizarCampo = false;
                somenteLeitura = true;
                renderizarFormulario = false;
                FacesMessageUtils.error("Contrato não encontrado!");
            }
        } else {
            renderizarCampo = false;
            somenteLeitura = true;
            renderizarFormulario = false;
            FacesMessageUtils.error("Informe o número de contrato para busca!");
        }
    }
    
    public void uploadAnexoContrato(FileUploadEvent event) throws FileNotFoundException, IOException {
        Arquivo arquivo = new Arquivo();
        UploadedFile uploadedFile = event.getFile();
        arquivo.setNome(uploadedFile.getFileName());
        arquivo.setExtensao(getExtension(uploadedFile.getFileName()));
        arquivo.setTipo(TipoArquivoContrato.CONTRATO);
        String base64AsString = new String(Base64.encode(uploadedFile.getContents()));
        arquivo.setConteudo(base64AsString);
        arquivos.add(arquivo);
    }
    
    public void uploadPublicacaoDoContrato(FileUploadEvent event) throws FileNotFoundException, IOException {
        Arquivo arquivo = new Arquivo();
        UploadedFile uploadedFile = event.getFile();
        arquivo.setNome(uploadedFile.getFileName());
        arquivo.setExtensao(getExtension(uploadedFile.getFileName()));
        arquivo.setTipo(TipoArquivoContrato.PUBLICACAO);
        String base64AsString = new String(Base64.encode(uploadedFile.getContents()));
        arquivo.setConteudo(base64AsString);
        arquivos.add(arquivo);
    }
    
    public void uploadAditivoDoContrato(FileUploadEvent event) throws FileNotFoundException, IOException {
        Arquivo arquivo = new Arquivo();
        UploadedFile uploadedFile = event.getFile();
        arquivo.setNome(uploadedFile.getFileName());
        arquivo.setExtensao(getExtension(uploadedFile.getFileName()));
        arquivo.setTipo(TipoArquivoContrato.ADITIVO);
        String base64AsString = new String(Base64.encode(uploadedFile.getContents()));
        arquivo.setConteudo(base64AsString);
        arquivos.add(arquivo);
    }
    
    public StreamedContent download(Arquivo arquivo) throws IOException {
        
        if (arquivo instanceof HibernateProxy) {
            HibernateProxy proxy = (HibernateProxy) arquivo;
            arquivo = (Arquivo) proxy.getHibernateLazyInitializer().getImplementation();
        }
        
        String nomeArquivo = arquivo.getNome();
        String extensaoArquivo = arquivo.getExtensao();
        
        File file = File.createTempFile(nomeArquivo, extensaoArquivo);
        
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(Base64.decode(arquivo.getConteudo()));
        outputStream.flush();
        outputStream.close();
        
        return new DefaultStreamedContent(new FileInputStream(file), extensaoArquivo, nomeArquivo);
    }
    
    public void removerArquivo(Arquivo arquivo) {
        arquivos.remove(arquivo);
    }
    
    public boolean isRenderizarCampo() {
        return renderizarCampo;
    }
    
    public void setRenderizarCampo(boolean renderizarCampo) {
        this.renderizarCampo = renderizarCampo;
    }
    
    public boolean isSomenteLeitura() {
        return somenteLeitura;
    }
    
    public void setSomenteLeitura(boolean somenteLeitura) {
        this.somenteLeitura = somenteLeitura;
    }
    
    public boolean isRenderizarFormulario() {
        return renderizarFormulario;
    }
    
    public void setRenderizarFormulario(boolean renderizarFormulario) {
        this.renderizarFormulario = renderizarFormulario;
    }
    
    public String getNumeroContrato() {
        return numeroContrato;
    }
    
    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }
    
    public boolean habilitarBotaoDesativar() {
        return getEntity().getId() != null && getEntity().isAtivo();
    }
    
    public boolean habilitarBotaoAtivar() {
        return getEntity().getId() != null && !getEntity().isAtivo();
    }
}
