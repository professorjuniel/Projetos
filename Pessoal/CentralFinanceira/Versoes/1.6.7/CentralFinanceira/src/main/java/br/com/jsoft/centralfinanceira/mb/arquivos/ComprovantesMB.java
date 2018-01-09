/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsoft.centralfinanceira.mb.arquivos;

import br.com.jsoft.centralfinanceira.modelo.arquivo.FiltrosDeBusca;
import br.com.jsoft.centralfinanceira.bo.central.ConvenioBoletoBO;
import br.com.jsoft.centralfinanceira.constante.ConstantesURL;
import br.com.jsoft.centralfinanceira.modelo.cadastroBasicos.ConvenioBoleto;
import br.com.jsoft.centralfinanceira.vo.ArquivoVO;
import br.com.jsoft.centralfinanceira.vo.ArquivoViewVO;
import com.xpert.core.crud.AbstractBaseBean;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Juniel
 */
@ManagedBean
@ViewScoped
public class ComprovantesMB extends AbstractBaseBean<ConvenioBoleto> implements Serializable {

    @EJB
    private ConvenioBoletoBO convenioBoletoBO;

    private FiltrosDeBusca filtros;

    private int indentLevel = -1;

    private String caminho = ConstantesURL.URL_FTP_COMPROVANTES;

    private NtlmPasswordAuthentication mycreds = new NtlmPasswordAuthentication("pagcontas.com.br", "ti", "pag2014");

    private List<ArquivoViewVO> arquivos;

    private List<ArquivoVO> comprovantes;

    private List<String> pai;

    private List<String> filho;

    public List<String> getPai() {
        return pai;
    }

    public void setPai(List<String> pai) {
        this.pai = pai;
    }

    public List<String> getFilho() {
        return filho;
    }

    public void setFilho(List<String> filho) {
        this.filho = filho;
    }

    @Override
    public ConvenioBoletoBO getBO() {
        return convenioBoletoBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    @Override
    public void init() {
        filtros = new FiltrosDeBusca();
        try {
            pai = listDiretoriosPai();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ComprovantesDespesasMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmbException ex) {
            Logger.getLogger(ComprovantesDespesasMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void listPathFile(File path) throws ParseException {
        File files[];

        indentLevel++;

        files = path.listFiles();

        Arrays.sort(files);

        for (int i = 0, n = files.length; i < n; i++) {

            if (files[i].isFile()) {
                ArquivoViewVO arquivo = new ArquivoViewVO();
                String nomeArquivo = files[i].getName();
                arquivo.setNome(getExpressao(nomeArquivo, 0));
                arquivo.setEmpresa(getExpressao(nomeArquivo, 1));

                arquivo.setDataPublicacao(getExpressao(nomeArquivo, 2));

                arquivo.setValor(getExpressao(nomeArquivo, 3));
                arquivo.setExtensao(getExtension(nomeArquivo));
                arquivo.setPath(files[i].getPath());

                arquivos.add(arquivo);

            }
            if (files[i].isDirectory()) {
                listPathFile(files[i]);
            }
        }
        indentLevel--;

    }

    private void listPathSmb(SmbFile path) throws ParseException, SmbException {
        SmbFile files[];

        indentLevel++;

        files = path.listFiles();

        for (int i = 0, n = files.length; i < n; i++) {

            if (files[i].isFile()) {
                ArquivoViewVO arquivo = new ArquivoViewVO();
                String nomeArquivo = files[i].getName();
                if (!getExpressaoPdf(files[i].getName()).equals("")) {
                    if (getExpressao(nomeArquivo, 0).equals("")) {
                        arquivo.setNome(nomeArquivo);
                        arquivo.setExtensao(getExtension(nomeArquivo));
                        arquivo.setPath(files[i].getPath());

                        arquivos.add(arquivo);
                    } else {
                        arquivo.setNome(getExpressao(nomeArquivo, 0));
                        arquivo.setEmpresa(getExpressao(nomeArquivo, 1));

                        arquivo.setDataPublicacao(getExpressao(nomeArquivo, 2));

                        arquivo.setValor(getExpressao(nomeArquivo, 3));
                        arquivo.setExtensao(getExtension(nomeArquivo));
                        arquivo.setPath(files[i].getPath());

                        arquivos.add(arquivo);
                    }
                }

            }
            if (files[i].isDirectory()) {
                listPathSmb(files[i]);
            }
        }
        indentLevel--;

    }

    static String getExtension(String name) {
        return name.replaceAll(".+\\.(.+)", "$1");
    }

    private String getExpressao(String nome, int op) {
        String retorno = "";
        // novo  ^(.+) (\\d{6}) (\\d+,\\d{1,2})\\.pdf$
        Pattern pattern = Pattern.compile("^(.+) (\\d{6}) (\\d+,\\d{1,2})\\.pdf$");
        Matcher matcher = pattern.matcher(nome);
        if (matcher.find()) {
            retorno = matcher.group(op);
        }
        return retorno;
    }

    private String getExpressaoPdf(String nome) {
        String retorno = "";
        // novo  ^(.+) (\\d{6}) (\\d+,\\d{1,2})\\.pdf$
        Pattern pattern = Pattern.compile("\\.pdf$");
        Matcher matcher = pattern.matcher(nome);
        if (matcher.find()) {
            retorno = matcher.group(0);
        }
        return retorno;
    }

    public void carregarArquivosList() throws ParseException {

        String diretorio = "";

        diretorio = (filtros.getDiretorioPai() != null ? filtros.getDiretorioPai() : "")
                .concat(filtros.getDiretorioFilho() != null ? filtros.getDiretorioFilho() : "");
        comprovantes = new ArrayList<ArquivoVO>();
        ArquivoVO comprovante;

        if (filtros.getDataInicial() == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date dataConverted = sdf.parse("01/01/2000");
            filtros.setDataInicial(dataConverted);
        }

        if (filtros.getDataFinal() == null) {
            filtros.setDataFinal(new Date());
        }

        for (ArquivoViewVO arquivo : arquivos) {
            if (!arquivo.getNome().equals("")) {
                comprovante = new ArquivoVO();

                comprovante.setEmpresa(arquivo.getEmpresa());
                comprovante.setExtensao(arquivo.getExtensao());
                comprovante.setNomeArquivo(diretorio.concat(arquivo.getNome()));
                comprovante.setPath(arquivo.getPath());
                comprovante.setPublicacao(convertStringParaData(arquivo.getDataPublicacao()));
                comprovante.setValor(arquivo.getValor() != null ? new BigDecimal(arquivo.getValor().replaceAll(",", ".")) : BigDecimal.ZERO);

                if (filtros.getNomeArquivo().equals("")) {
                    if (comprovante.getPublicacao() != null) {
                        if (between(comprovante.getPublicacao(), filtros.getDataInicial(), filtros.getDataFinal())) {
                            comprovantes.add(comprovante);
                        }
                    } else {
                        comprovantes.add(comprovante);

                    }

                } else {
                    if (findMe(arquivo.getNome().toUpperCase(), filtros.getNomeArquivo().toUpperCase())) {
                        if (comprovante.getPublicacao() != null) {
                            if (between(comprovante.getPublicacao(), filtros.getDataInicial(), filtros.getDataFinal())) {
                                comprovantes.add(comprovante);
                            }
                        } else {
                            comprovantes.add(comprovante);

                        }

                    }
                }
            }
        }
    }

    public void buscar() throws ParseException, MalformedURLException, SmbException {
        String diretorioFilho = "";
        diretorioFilho = filtros.getDiretorioFilho() != null ? filtros.getDiretorioFilho() : "";
        SmbFile diretorio = new SmbFile(caminho.concat(filtros.getDiretorioPai().concat(diretorioFilho)), mycreds);
        arquivos = new ArrayList<ArquivoViewVO>();
        listPathSmb(diretorio);
        carregarArquivosList();
    }

    public List<ArquivoVO> getComprovantes() {
        return comprovantes;
    }

    public void setComprovantes(List<ArquivoVO> comprovantes) {
        this.comprovantes = comprovantes;
    }

    public StreamedContent prepDownload(String path, String nomeArquivo) throws Exception {
        SmbFile diretorio = new SmbFile(path, mycreds);

        StreamedContent download = new DefaultStreamedContent();
//        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        download = new DefaultStreamedContent(diretorio.getInputStream(), externalContext.getMimeType(nomeArquivo), nomeArquivo);
        return download;
    }

    public FiltrosDeBusca getFiltros() {
        return filtros;
    }

    public void setFiltros(FiltrosDeBusca filtros) {
        this.filtros = filtros;
    }

    private boolean findMe(String searchMe, String findMe) {

        int searchMeLength = searchMe.length();
        int findMeLength = findMe.length();
        for (int i = 0;
                i <= (searchMeLength - findMeLength);
                i++) {
            if (searchMe.regionMatches(i, findMe, 0, findMeLength)) {
                return true;

            }
        }
//        if (!foundIt) {
//            System.out.println("No match found.");
//        }
        return false;
    }

    private Date convertStringParaData(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");

        sdf.setLenient(false);

        Date dataConverted = null;

        if (data != null) {
            dataConverted = sdf.parse(data);
        }

        return dataConverted;

    }

    private boolean between(Date data, Date min, Date max) {
        return (data.equals(min) || data.after(min)) && (data.equals(max) || data.before(max));
    }

    public List<String> listDiretoriosPai() throws MalformedURLException, SmbException {
        List<String> lista = new ArrayList<String>();
        filtros.setDiretorioFilho(null);
        SmbFile smbFile = new SmbFile(caminho, mycreds);

        SmbFile files[];

        files = smbFile.listFiles();

        for (SmbFile file : files) {
            if (file.isDirectory()) {
                lista.add(file.getName());
                System.out.println(file.getName());
            }

        }

        return lista;
    }

    public void listDiretoriosFilho() throws MalformedURLException, SmbException {
        List<String> lista = new ArrayList<String>();
        if (filtros.getDiretorioPai() != null) {
            SmbFile smbFile = new SmbFile(caminho.concat(filtros.getDiretorioPai()), mycreds);

            SmbFile files[];

            files = smbFile.listFiles();

            for (SmbFile file : files) {
                if (file.isDirectory()) {
                    lista.add(file.getName());
                }
            }
        }
        filho = lista;
    }

}
