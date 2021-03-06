/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.pi.ati.modelo.cadastro.vos;

import br.gov.pi.ati.modelo.cadastro.AcaoEstrategica;
import br.gov.pi.ati.modelo.cadastro.AcaoOrcamentaria;
import br.gov.pi.ati.modelo.cadastro.FonteDeRecurso;
import br.gov.pi.ati.modelo.cadastro.NaturezaDeDespesa;
import br.gov.pi.ati.modelo.cadastro.Produto;
import br.gov.pi.ati.modelo.cadastro.ProgramaDeGoverno;
import br.gov.pi.ati.modelo.cadastro.UnidadeOrcamentaria;
import br.gov.pi.ati.modelo.controleacesso.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Juniel
 */
public class Filtros implements Serializable {

    private List<UnidadeOrcamentaria> unidadesOrcamentaria = new ArrayList<UnidadeOrcamentaria>();

    private UnidadeOrcamentaria unidadeOrcamentaria;

    private AcaoEstrategica acaoEstrategica;
    
    private AcaoOrcamentaria acaoOrcamentaria;

    private ProgramaDeGoverno programaDeGoverno;
    
    private FonteDeRecurso fonteDeRecurso;
    
    private NaturezaDeDespesa naturezaDespesa;
    
    private Usuario usuario;

    private Produto produto;

    private Date dataInicial;

    private Date dataFinal;
    
    private Date dataInicial2;

    private Date dataFinal2;

    private String nome;

    private String codigo;
    
    private String numProcesso;
    
    private String subElemento;
    
    private Boolean ativo;
    
    private Boolean ativo2;
    
    private Integer ano;

    public List<UnidadeOrcamentaria> getUnidadesOrcamentaria() {
        return unidadesOrcamentaria;
    }

    public void setUnidadesOrcamentaria(List<UnidadeOrcamentaria> unidadesOrcamentaria) {
        this.unidadesOrcamentaria = unidadesOrcamentaria;
    }

    public AcaoEstrategica getAcaoEstrategica() {
        return acaoEstrategica;
    }

    public void setAcaoEstrategica(AcaoEstrategica acaoEstrategica) {
        this.acaoEstrategica = acaoEstrategica;
    }

    public ProgramaDeGoverno getProgramaDeGoverno() {
        return programaDeGoverno;
    }

    public void setProgramaDeGoverno(ProgramaDeGoverno programaDeGoverno) {
        this.programaDeGoverno = programaDeGoverno;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public UnidadeOrcamentaria getUnidadeOrcamentaria() {
        return unidadeOrcamentaria;
    }

    public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
        this.unidadeOrcamentaria = unidadeOrcamentaria;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public AcaoOrcamentaria getAcaoOrcamentaria() {
        return acaoOrcamentaria;
    }

    public void setAcaoOrcamentaria(AcaoOrcamentaria acaoOrcamentaria) {
        this.acaoOrcamentaria = acaoOrcamentaria;
    }

    public FonteDeRecurso getFonteDeRecurso() {
        return fonteDeRecurso;
    }

    public void setFonteDeRecurso(FonteDeRecurso fonteDeRecurso) {
        this.fonteDeRecurso = fonteDeRecurso;
    }

    public NaturezaDeDespesa getNaturezaDespesa() {
        return naturezaDespesa;
    }

    public void setNaturezaDespesa(NaturezaDeDespesa naturezaDespesa) {
        this.naturezaDespesa = naturezaDespesa;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(String numProcesso) {
        this.numProcesso = numProcesso;
    }

    public Date getDataInicial2() {
        return dataInicial2;
    }

    public void setDataInicial2(Date dataInicial2) {
        this.dataInicial2 = dataInicial2;
    }

    public Date getDataFinal2() {
        return dataFinal2;
    }

    public void setDataFinal2(Date dataFinal2) {
        this.dataFinal2 = dataFinal2;
    }

    public String getSubElemento() {
        return subElemento;
    }

    public void setSubElemento(String subElemento) {
        this.subElemento = subElemento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getAtivo2() {
        return ativo2;
    }

    public void setAtivo2(Boolean ativo2) {
        this.ativo2 = ativo2;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

}
