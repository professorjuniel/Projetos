/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.facilpagar.modelo.dados;

import br.com.facilpagar.modelo.controleacesso.Usuario;
import br.com.facilpagar.modelo.enums.TipoPessoa;
import br.com.facilpagar.modelo.enums.TipoRepasse;
import br.com.facilpagar.util.Utils;
import com.xpert.audit.NotAudited;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Table(schema = "dados")
public class Franquia implements Serializable {

    @Id
    @SequenceGenerator(schema = "dados", allocationSize = 1, name = "Franquia", sequenceName = "dados.seq_franquia_id")
    @GeneratedValue(generator = "Franquia")
    private Long id;

    @NotNull
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipo_pessoa = TipoPessoa.PJ;

    @Size(max = 14)
    @NotBlank
    private String cpf_cnpj;

    @Size(max = 100)
    @NotBlank
    private String razao_social;

    @Size(max = 100)
    @NotBlank
    private String nome_fantasia;

    @Size(max = 15)
    @NotBlank
    private String insc_est;

    @Size(max = 100)
    private String endereco;

    @Size(max = 100)
    private String bairro;

    @Size(max = 30)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Cidade cidade;

    @Size(max = 100)
    @Email
    private String email;

    @Size(max = 100)
    private String fones;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Banco banco;

    @Size(max = 10)
    private String agencia;

    @Size(max = 10)
    private String conta_corrente;

    @Size(max = 255)
    private String mensagem;

    @Size(max = 200)
    @NotBlank
    private String localPagto = "EM QUALQUER AGENCIA BANCARIA ATÉ O VENCIMENTO";

    private BigDecimal comissao = new BigDecimal(6);

    private BigDecimal desconto = BigDecimal.ZERO;

    private BigDecimal juros = new BigDecimal(0.1);

    private BigDecimal multa = new BigDecimal(2);
    
    private boolean ativo = false;
    
    @NotAudited
    @OneToMany(mappedBy = "franquia")
    @OrderBy("nome_fantasia")
    private List<Convenio> convenios;
    
    @NotAudited
    @OneToMany(mappedBy = "franquia")
    private List<Deposito> depositos;
    
    @NotAudited
    @OneToMany(mappedBy = "franquia")
    private List<Usuario> usuarios;
    
    @Override
    public String toString() {
/*        if (!Utils.isNullOrEmpty(cpf_cnpj) && !Utils.isNullOrEmpty(nome_fantasia)) {
            String pattern;
            if (cpf_cnpj.length() > 11) {
                pattern = "##.###.###/####-##";
            } else {
                pattern = "###.###.###-##";
            }
            return Utils.format(pattern, cpf_cnpj).concat(" - ").concat(nome_fantasia);
        }
        */
        return nome_fantasia; //To change body of generated methods, choose Tools | Templates.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPessoa getTipo_pessoa() {
        return tipo_pessoa;
    }

    public void setTipo_pessoa(TipoPessoa tipo_pessoa) {
        this.tipo_pessoa = tipo_pessoa;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getInsc_est() {
        return insc_est;
    }

    public void setInsc_est(String insc_est) {
        this.insc_est = insc_est;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFones() {
        return fones;
    }

    public void setFones(String fones) {
        this.fones = fones;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta_corrente() {
        return conta_corrente;
    }

    public void setConta_corrente(String conta_corrente) {
        this.conta_corrente = conta_corrente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getLocalPagto() {
        return localPagto;
    }

    public void setLocalPagto(String localPagto) {
        this.localPagto = localPagto;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getJuros() {
        return juros;
    }

    public void setJuros(BigDecimal juros) {
        this.juros = juros;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Convenio> getConvenios() {
        return convenios;
    }

    public void setConvenios(List<Convenio> convenios) {
        this.convenios = convenios;
    }

    public List<Deposito> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<Deposito> depositos) {
        this.depositos = depositos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Franquia other = (Franquia) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
