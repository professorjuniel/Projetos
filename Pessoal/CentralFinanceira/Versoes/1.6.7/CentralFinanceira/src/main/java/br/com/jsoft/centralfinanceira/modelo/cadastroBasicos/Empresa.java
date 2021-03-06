/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsoft.centralfinanceira.modelo.cadastroBasicos;

import br.com.jsoft.centralfinanceira.modelo.controleacesso.Usuario;
import br.com.jsoft.centralfinanceira.modelo.financeiro.CentroResultados;
import br.com.jsoft.centralfinanceira.modelo.financeiro.ContasAPagar;
import br.com.jsoft.centralfinanceira.modelo.financeiro.ContasAPagarVencimentos;
import br.com.jsoft.centralfinanceira.modelo.financeiro.ContasAPagarVencimentosBaixas;
import br.com.jsoft.centralfinanceira.modelo.financeiro.Lancamentos;
import br.com.jsoft.centralfinanceira.modelo.financeiro.ReceitaDespesa;
import com.xpert.audit.NotAudited;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * dbo.EMP
 *
 * @author Juniel
 */
@Entity
@Table(schema = "cadastro")
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @NotBlank
    private String nome;

    @Size(max = 20)
    private String apelido;

    private String logomarca;

    @Size(max = 20)
    private String im;

    private String cnpj;

    private boolean inss = false;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<GrupoFornecedor> gruposForn;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<Fornecedor> fornecedores;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<GrupoCliente> grupoClientes;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<Representante> representantes;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<ContaFinanceira> contaFinanceira;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<GrupoContaFinanceira> GruposContaFinanceira;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<AgenteCobrador> agentes;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<InstrucaoCobranca> instrucoes;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<Transportadora> transportadoras;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<ContasAPagar> contasAPaga;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<HistoricoPadrao> historicos;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<TipoDocumento> tipoDocumentos;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<CentroResultados> centroResultados;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<Estabelecimento> estabelecimento;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<ReceitaDespesa> receitasDespesas;

    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<GrupoReceitaDespesa> gruposReceitaDespesas;
    
    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<ContasAPagarVencimentos> contasAPagarVencimentos;
    
    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios;
    
    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<Lancamentos> lancamentos;
    
    @NotAudited
    @OneToMany(mappedBy = "empresa")
    private List<ContasAPagarVencimentosBaixas> vencimentoBaixas;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getLogomarca() {
        return logomarca;
    }

    public void setLogomarca(String logomarca) {
        this.logomarca = logomarca;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean isInss() {
        return inss;
    }

    public void setInss(boolean inss) {
        this.inss = inss;
    }

    public List<GrupoFornecedor> getGruposForn() {
        return gruposForn;
    }

    public void setGruposForn(List<GrupoFornecedor> gruposForn) {
        this.gruposForn = gruposForn;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<GrupoCliente> getGrupoClientes() {
        return grupoClientes;
    }

    public void setGrupoClientes(List<GrupoCliente> grupoClientes) {
        this.grupoClientes = grupoClientes;
    }

    public List<Representante> getRepresentantes() {
        return representantes;
    }

    public void setRepresentantes(List<Representante> representantes) {
        this.representantes = representantes;
    }

    public List<ContaFinanceira> getContaFinanceira() {
        return contaFinanceira;
    }

    public void setContaFinanceira(List<ContaFinanceira> contaFinanceira) {
        this.contaFinanceira = contaFinanceira;
    }

    public List<GrupoContaFinanceira> getGruposContaFinanceira() {
        return GruposContaFinanceira;
    }

    public void setGruposContaFinanceira(List<GrupoContaFinanceira> GruposContaFinanceira) {
        this.GruposContaFinanceira = GruposContaFinanceira;
    }

    public List<AgenteCobrador> getAgentes() {
        return agentes;
    }

    public void setAgentes(List<AgenteCobrador> agentes) {
        this.agentes = agentes;
    }

    public List<InstrucaoCobranca> getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(List<InstrucaoCobranca> instrucoes) {
        this.instrucoes = instrucoes;
    }

    public List<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    public List<ContasAPagar> getContasAPaga() {
        return contasAPaga;
    }

    public void setContasAPaga(List<ContasAPagar> contasAPaga) {
        this.contasAPaga = contasAPaga;
    }

    public List<HistoricoPadrao> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<HistoricoPadrao> historicos) {
        this.historicos = historicos;
    }

    public List<TipoDocumento> getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public List<CentroResultados> getCentroResultados() {
        return centroResultados;
    }

    public void setCentroResultados(List<CentroResultados> centroResultados) {
        this.centroResultados = centroResultados;
    }

    public List<Estabelecimento> getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(List<Estabelecimento> estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<ReceitaDespesa> getReceitasDespesas() {
        return receitasDespesas;
    }

    public void setReceitasDespesas(List<ReceitaDespesa> receitasDespesas) {
        this.receitasDespesas = receitasDespesas;
    }

    public List<GrupoReceitaDespesa> getGruposReceitaDespesas() {
        return gruposReceitaDespesas;
    }

    public void setGruposReceitaDespesas(List<GrupoReceitaDespesa> gruposReceitaDespesas) {
        this.gruposReceitaDespesas = gruposReceitaDespesas;
    }

    public List<ContasAPagarVencimentos> getContasAPagarVencimentos() {
        return contasAPagarVencimentos;
    }

    public void setContasAPagarVencimentos(List<ContasAPagarVencimentos> contasAPagarVencimentos) {
        this.contasAPagarVencimentos = contasAPagarVencimentos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Lancamentos> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamentos> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public List<ContasAPagarVencimentosBaixas> getVencimentoBaixas() {
        return vencimentoBaixas;
    }

    public void setVencimentoBaixas(List<ContasAPagarVencimentosBaixas> vencimentoBaixas) {
        this.vencimentoBaixas = vencimentoBaixas;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empresa other = (Empresa) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
