package br.com.weblicita.mb.licitacao;

import br.com.weblicita.bo.cadastro.OrgaoBO;
import br.com.weblicita.bo.cadastro.RubricaOrcamentariaBO;
import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.weblicita.bo.licitacao.PedidoLicitacaoBO;
import br.com.weblicita.modelo.cadastro.Documento;
import br.com.weblicita.modelo.cadastro.Item;
import br.com.weblicita.modelo.cadastro.RubricaOrcamentaria;
import br.com.weblicita.modelo.controleacesso.Usuario;
import br.com.weblicita.modelo.licitacao.ItemLicitacao;
import br.com.weblicita.modelo.licitacao.PedidoLicitacao;
import br.com.weblicita.util.SessaoUtils;
import br.com.weblicita.util.Utils;
import com.xpert.faces.utils.FacesMessageUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.apache.commons.io.FilenameUtils.getExtension;
import org.bouncycastle.util.encoders.Base64;
import org.hibernate.proxy.HibernateProxy;
import org.primefaces.context.RequestContext;
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
public class PedidoLicitacaoMB extends AbstractBaseBean<PedidoLicitacao> implements Serializable {

    @EJB
    private PedidoLicitacaoBO pedidoLicitacaoBO;

    @EJB
    private RubricaOrcamentariaBO rubricaBO;

    private Usuario usuarioAtual;

    private ItemLicitacao itemAdd;

    private List<ItemLicitacao> itens;

    private List<Documento> documentos;

    private boolean renderizarCampo;

    private boolean somenteLeitura;

    private boolean renderizarFormulario;

    private String numeroProcessoAdministrativo = "";

    @Override
    public PedidoLicitacaoBO getBO() {
        return pedidoLicitacaoBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    @Override
    public void init() {
        itemAdd = new ItemLicitacao();
        usuarioAtual = SessaoUtils.getUser();

        renderizarCampo = false;
        somenteLeitura = false;
        renderizarFormulario = false;
        itens = new ArrayList<ItemLicitacao>();
        documentos = new ArrayList<Documento>();

    }

    @Override
    public void save() {

        getEntity().setItens(itens);
        getEntity().setDocumentos(documentos);

        if (getEntity().getId() != null) {
            getEntity().setNumeroLicitacao(getBO().gerarCodigoPedido());
        }

        super.save();
    }

    public void addItem() {
        if (itemAdd.getItem() != null) {
            if (verificarItemJahAdicionado(itemAdd.getItem())) {
                FacesMessageUtils.error("Item já foi adicionado!");
            } else {
                if (itemAdd.getQuantidade() != null) {
                    if (itemAdd.getUnidade() != null) {
                        itens.add(itemAdd);
                        itemAdd = new ItemLicitacao();
                    } else {
                        FacesMessageUtils.error("Unidade de medida é obrigatória!");
                    }
                } else {
                    FacesMessageUtils.error("Quantidade é obrigatória!");
                }
            }
        } else {
            FacesMessageUtils.error("Item é obrigatório!");
        }
    }

    public void removerItem(ItemLicitacao item) {
        itens.remove(item);
    }

    public boolean verificarItemJahAdicionado(Item itemAdicionado) {
        for (ItemLicitacao item : itens) {
            if (item.getItem().equals(itemAdicionado)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void postSave() {
        renderizarCampo = false;
        somenteLeitura = false;
        renderizarFormulario = false;
        setEntity(new PedidoLicitacao());
    }

    public void novo() {
        setEntity(new PedidoLicitacao());
        getEntity().setUsuario(usuarioAtual);
        getEntity().setOrgaoSolicitante(usuarioAtual.getOrgao());
        getEntity().setNumeroLicitacao(getBO().gerarCodigoPedido());
        getEntity().setDataSolicitacao(new Date());
        getEntity().setDataAlteracao(new Date());

        renderizarCampo = true;
        somenteLeitura = false;
        renderizarFormulario = true;
    }

    public void desativar() {
        if (getEntity().getId() != null) {
            getEntity().setAtivo(false);
            super.save();
            renderizarCampo = false;
            somenteLeitura = false;
            renderizarFormulario = false;
        } else {
            FacesMessageUtils.error("Informe o Pedido Licitação a ser desativado!");
        }
    }

    public void editar() {
        if (getEntity().getId() != null) {
            itens = getDAO().getInitialized(getEntity().getItens());
            documentos = getDAO().getInitialized(getEntity().getDocumentos());
            renderizarCampo = true;
            somenteLeitura = false;
            renderizarFormulario = true;
        } else {
            FacesMessageUtils.error("Informe o Pedido Licitação a ser editado!");
        }
    }

    public void buscar() {
        setEntity(new PedidoLicitacao());
        if (!Utils.isNullOrEmpty(numeroProcessoAdministrativo)) {
            PedidoLicitacao pedido = getBO().pedidoPeloProcessoAdministrativo(numeroProcessoAdministrativo);
            if (pedido != null) {
                renderizarCampo = false;
                somenteLeitura = true;
                renderizarFormulario = true;
                setEntity(pedido);
                itens = getDAO().getInitialized(pedido.getItens());
                documentos = getDAO().getInitialized(pedido.getDocumentos());

            } else {
                renderizarCampo = false;
                somenteLeitura = true;
                renderizarFormulario = false;
                FacesMessageUtils.error("Pedido Licitação não encontrado!");
            }
        } else {
            renderizarCampo = false;
            somenteLeitura = true;
            renderizarFormulario = false;
            FacesMessageUtils.error("Informe o Número do Processo Administrativo para busca!");
        }
    }

    public StreamedContent download(Documento arquivo) throws IOException {

        if (arquivo instanceof HibernateProxy) {
            HibernateProxy proxy = (HibernateProxy) arquivo;
            arquivo = (Documento) proxy.getHibernateLazyInitializer().getImplementation();
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

    public void upload(FileUploadEvent event) throws FileNotFoundException, IOException {
        Documento arquivo = new Documento();
        UploadedFile uploadedFile = event.getFile();
        arquivo.setNome(uploadedFile.getFileName());
        arquivo.setExtensao(getExtension(uploadedFile.getFileName()));
        String base64AsString = new String(Base64.encode(uploadedFile.getContents()));
        arquivo.setConteudo(base64AsString);
        documentos.add(arquivo);
    }

    public void abrirTelaItem() {

        RequestContext context = RequestContext.getCurrentInstance();

        context.execute("PF('widgetListarItem').show();");
    }

    public List<PedidoLicitacao> autocomplete(String codigo) {
        return getBO().listaPeloNumProcessoAdmin(codigo);
    }

    public void removerArquivo(Documento arquivo) {
        documentos.remove(arquivo);
    }

    public ItemLicitacao getItemAdd() {
        return itemAdd;
    }

    public void setItemAdd(ItemLicitacao itemAdd) {
        this.itemAdd = itemAdd;
    }

    public List<ItemLicitacao> getItens() {
        return itens;
    }

    public void setItens(List<ItemLicitacao> itens) {
        this.itens = itens;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
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

    public String getNumeroProcessoAdministrativo() {
        return numeroProcessoAdministrativo;
    }

    public void setNumeroProcessoAdministrativo(String numeroProcessoAdministrativo) {
        this.numeroProcessoAdministrativo = numeroProcessoAdministrativo;
    }

    public boolean processoAdmin() {
        if (getEntity().getId() != null && somenteLeitura) {
            return true;
        }

        return false;
    }

    public List<RubricaOrcamentaria> autocompleteRubricas(String nome) {
        return rubricaBO.rubricasPelaLegendaEOrgao(getEntity().getOrgaoSolicitante(), nome);
    }
}
