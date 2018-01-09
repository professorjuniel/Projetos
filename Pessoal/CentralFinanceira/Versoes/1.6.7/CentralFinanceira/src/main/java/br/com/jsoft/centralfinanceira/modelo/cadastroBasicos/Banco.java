/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsoft.centralfinanceira.modelo.cadastroBasicos;

import br.com.jsoft.centralfinanceira.modelo.financeiro.ContasAPagarVencimentos;
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
 * dbo.BAN
 *
 * @author Juniel
 */
@Entity
@Table(schema = "cadastro")
public class Banco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @NotNull
//    private Empresa empresa;
    @Size(max = 40)
    @NotBlank
    private String nome;

    @Size(max = 3)
    private String numero;

    @Size(max = 1)
    private String dv;

    @NotAudited
    @OneToMany(mappedBy = "banco")
    private List<Fornecedor> fornecedores;

    @NotAudited
    @OneToMany(mappedBy = "banco")
    private List<ContaFinanceira> contaFinanceira;

    @NotAudited
    @OneToMany(mappedBy = "banco")
    private List<AgenteCobrador> agentes;

    @NotAudited
    @OneToMany(mappedBy = "banco")
    private List<InstrucaoCobranca> instrucoes;

    @NotAudited
    @OneToMany(mappedBy = "banco")
    private List<Cliente> clientes;

    @NotAudited
    @OneToMany(mappedBy = "banco")
    private List<ContasAPagarVencimentos> contasAPagarVencimentos;

    @NotAudited
    @OneToMany(mappedBy = "banco")
    private List<GrupoLoja> gruposLoja;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Empresa getEmpresa() {
//        return empresa;
//    }
//
//    public void setEmpresa(Empresa empresa) {
//        this.empresa = empresa;
//    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<ContaFinanceira> getContaFinanceira() {
        return contaFinanceira;
    }

    public void setContaFinanceira(List<ContaFinanceira> contaFinanceira) {
        this.contaFinanceira = contaFinanceira;
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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<ContasAPagarVencimentos> getContasAPagarVencimentos() {
        return contasAPagarVencimentos;
    }

    public void setContasAPagarVencimentos(List<ContasAPagarVencimentos> contasAPagarVencimentos) {
        this.contasAPagarVencimentos = contasAPagarVencimentos;
    }

    public List<GrupoLoja> getGruposLoja() {
        return gruposLoja;
    }

    public void setGruposLoja(List<GrupoLoja> gruposLoja) {
        this.gruposLoja = gruposLoja;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Banco other = (Banco) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
