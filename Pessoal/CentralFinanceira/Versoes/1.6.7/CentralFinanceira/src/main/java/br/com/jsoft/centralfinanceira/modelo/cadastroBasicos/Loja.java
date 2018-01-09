/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsoft.centralfinanceira.modelo.cadastroBasicos;

import br.com.jsoft.centralfinanceira.modelo.central.DespesaLoja;
import br.com.jsoft.centralfinanceira.modelo.central.FatosBoletoSite;
import br.com.jsoft.centralfinanceira.modelo.central.FatosBoletos;
import br.com.jsoft.centralfinanceira.modelo.central.FatosCreditos;
import br.com.jsoft.centralfinanceira.modelo.central.FatosRecarga;
import br.com.jsoft.centralfinanceira.modelo.central.FatosValeGas;
import br.com.jsoft.centralfinanceira.modelo.central.Fatosops;
import br.com.jsoft.centralfinanceira.modelo.contrato.Contrato;
import com.xpert.audit.NotAudited;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Juniel
 */
@Entity
@Table(schema = "cadastro")
public class Loja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @NotBlank
    private String nome;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private GrupoLoja grupoLoja;

    @Size(max = 50)
    @NotBlank
    private String razaosocial;

    @Size(max = 16)
    @NotBlank
    private String cnpj;

    @Size(max = 14)
    private String inscricaoEstadual;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Segmento segmento;

    @Size(max = 50)
    private String endereco;

    @Size(max = 25)
    private String bairro;

    @Size(max = 8)
    private String cep;

    private String latitude;

    private String longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    private TipoLoja tipoLoja;

    private boolean ativo = true;
    
    private boolean valida = true;

    @NotAudited
    @OneToMany(mappedBy = "loja")
    private List<FatosBoletos> fatosboletos;

    @NotAudited
    @OneToMany(mappedBy = "loja")
    private List<FatosBoletoSite> fatosboletoSite;

    @NotAudited
    @OneToMany(mappedBy = "loja")
    private List<FatosCreditos> fatosCreditos;

    @NotAudited
    @OneToMany(mappedBy = "loja")
    private List<Fatosops> fatosOs;

    @NotAudited
    @OneToMany(mappedBy = "loja")
    private List<FatosValeGas> fatosValeGas;

    @NotAudited
    @OneToMany(mappedBy = "loja")
    private List<FatosRecarga> fatosRecarga;

    @NotAudited
    @OneToMany(mappedBy = "loja")
    private List<Contrato> contratos;

    @NotAudited
    @OneToMany(mappedBy = "loja")
    private List<DespesaLoja> depesas;

    @NotAudited
    @OneToMany(mappedBy = "listaLoja")
    private List<ListaLoja_Loja> listasLojas;
    
    public List<DespesaLoja> getDepesas() {
        return depesas;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<FatosRecarga> getFatosRecarga() {
        return fatosRecarga;
    }

    public void setFatosRecarga(List<FatosRecarga> fatosRecarga) {
        this.fatosRecarga = fatosRecarga;
    }

    public List<FatosBoletos> getFatosboletos() {
        return fatosboletos;
    }

    public void setFatosboletos(List<FatosBoletos> fatosboletos) {
        this.fatosboletos = fatosboletos;
    }

    public List<FatosBoletoSite> getFatosboletoSite() {
        return fatosboletoSite;
    }

    public void setFatosboletoSite(List<FatosBoletoSite> fatosboletoSite) {
        this.fatosboletoSite = fatosboletoSite;
    }

    public List<FatosCreditos> getFatosCreditos() {
        return fatosCreditos;
    }

    public void setFatosCreditos(List<FatosCreditos> fatosCreditos) {
        this.fatosCreditos = fatosCreditos;
    }

    public List<Fatosops> getFatosOs() {
        return fatosOs;
    }

    public void setFatosOs(List<Fatosops> fatosOs) {
        this.fatosOs = fatosOs;
    }

    public List<FatosValeGas> getFatosValeGas() {
        return fatosValeGas;
    }

    public void setFatosValeGas(List<FatosValeGas> fatosValeGas) {
        this.fatosValeGas = fatosValeGas;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public TipoLoja getTipoLoja() {
        return tipoLoja;
    }

    public void setTipoLoja(TipoLoja tipoLoja) {
        this.tipoLoja = tipoLoja;
    }

    public GrupoLoja getGrupoLoja() {
        return grupoLoja;
    }

    public void setGrupoLoja(GrupoLoja grupoLoja) {
        this.grupoLoja = grupoLoja;
    }

    public List<ListaLoja_Loja> getListasLojas() {
        return listasLojas;
    }

    public void setListasLojas(List<ListaLoja_Loja> listasLojas) {
        this.listasLojas = listasLojas;
    }

    public boolean isValida() {
        return valida;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Loja other = (Loja) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
