/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weblicita.modelo.cadastro;

import br.com.weblicita.modelo.contrato.Contrato;
import br.com.weblicita.modelo.controleacesso.Usuario;
import br.com.weblicita.modelo.licitacao.PedidoLicitacao;
import br.com.weblicita.util.Utils;
import com.xpert.audit.NotAudited;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Juniel
 */
@Entity
public class Orgao implements Serializable {

    @Id
    @SequenceGenerator(name = "Orgao", allocationSize = 1, sequenceName = "orgao_id_seq")
    @GeneratedValue(generator = "Orgao")
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String nomeDoOrgao;

    @NotBlank
    private String codigo;

    @NotBlank
    @Size(max = 20)
    private String cnpj;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Gerente gerente;

    @Email
    private String email;

    private String site;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @NotNull
    private Endereco endereco = new Endereco();

    @Column(columnDefinition = "Text")
    private String observacao;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDoCadastro = new Date();

    @ManyToMany(targetEntity = Telefone.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Telefone> telefones = new ArrayList<Telefone>();

    private boolean ativo = true;

    @NotAudited
    @OneToMany(mappedBy = "orgao")
    private List<Usuario> usuarios;

    @NotAudited
    @OneToMany(mappedBy = "orgaoSolicitante")
    private List<PedidoLicitacao> pedidosLicitacoes;

    @NotAudited
    @OneToMany(mappedBy = "orgao")
    private List<Contrato> contratos;

    @NotAudited
    @OneToMany(mappedBy = "orgao")
    private List<Cargo> cargos;

    @NotAudited
    @OneToMany(mappedBy = "orgao")
    private List<RubricaOrcamentaria> rubricas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDoOrgao() {
        return nomeDoOrgao;
    }

    public void setNomeDoOrgao(String nomeDoOrgao) {
        this.nomeDoOrgao = nomeDoOrgao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataDoCadastro() {
        return dataDoCadastro;
    }

    public void setDataDoCadastro(Date dataDoCadastro) {
        this.dataDoCadastro = dataDoCadastro;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<PedidoLicitacao> getPedidosLicitacoes() {
        return pedidosLicitacoes;
    }

    public void setPedidosLicitacoes(List<PedidoLicitacao> pedidosLicitacoes) {
        this.pedidosLicitacoes = pedidosLicitacoes;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public List<RubricaOrcamentaria> getRubricas() {
        return rubricas;
    }

    public void setRubricas(List<RubricaOrcamentaria> rubricas) {
        this.rubricas = rubricas;
    }

    @Override
    public String toString() {
        if (!Utils.isNullOrEmpty(cnpj) && !Utils.isNullOrEmpty(nomeDoOrgao)) {
            return Utils.format("##.###.###/####-##", cnpj).concat(" - ").concat(nomeDoOrgao);
        }

        return nomeDoOrgao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Orgao other = (Orgao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
