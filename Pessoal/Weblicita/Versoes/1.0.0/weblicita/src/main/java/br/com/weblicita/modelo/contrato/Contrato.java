/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weblicita.modelo.contrato;

import br.com.weblicita.modelo.cadastro.Arquivo;
import br.com.weblicita.modelo.cadastro.Fornecedor;
import br.com.weblicita.modelo.cadastro.Orgao;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Juniel
 *
 */
@Entity
public class Contrato implements Serializable {

    @Id
    @SequenceGenerator(name = "Contrato", sequenceName = "contrato_id_seq")
    @GeneratedValue(generator = "Contrato")
    private Long id;

    @Size(max = 250)
    @NotBlank
    private String numeroProcessoAdministrativo;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dataCadastramento = new Date();

    @Size(max = 50)
    @NotBlank
    private String numeroContrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Fornecedor contratado;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Orgao orgao;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dataDaAssinatura;

    @Column(columnDefinition = "Text")
    private String objeto;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date vigencia;

    @Column(columnDefinition = "Text")
    private String aditivos;

    @ManyToMany(targetEntity = Arquivo.class, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Arquivo> arquivos = new ArrayList<Arquivo>();

    private boolean ativo = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroProcessoAdministrativo() {
        return numeroProcessoAdministrativo;
    }

    public void setNumeroProcessoAdministrativo(String numeroProcessoAdministrativo) {
        this.numeroProcessoAdministrativo = numeroProcessoAdministrativo;
    }

    public Date getDataCadastramento() {
        return dataCadastramento;
    }

    public void setDataCadastramento(Date dataCadastramento) {
        this.dataCadastramento = dataCadastramento;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public Fornecedor getContratado() {
        return contratado;
    }

    public void setContratado(Fornecedor contratado) {
        this.contratado = contratado;
    }

    public Date getDataDaAssinatura() {
        return dataDaAssinatura;
    }

    public void setDataDaAssinatura(Date dataDaAssinatura) {
        this.dataDaAssinatura = dataDaAssinatura;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public String getAditivos() {
        return aditivos;
    }

    public void setAditivos(String aditivos) {
        this.aditivos = aditivos;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Contrato other = (Contrato) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
