/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.pi.ati.modelo.orcamento;

import br.gov.pi.ati.modelo.cadastro.AcaoEstrategica;
import br.gov.pi.ati.modelo.cadastro.ProgramaPPA;
import com.xpert.audit.NotAudited;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juniel
 */
@Entity
public class MetaAcaoEstrategica implements Serializable {
    
    @Id
    @SequenceGenerator(name = "MetaAcaoEstrategica", sequenceName = "metaAcaoEstrategica_id_seq")
    @GeneratedValue(generator = "MetaAcaoEstrategica")
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private AcaoEstrategica acaoEstrategica;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private ProgramaPPA programaPPA;
    
    private BigDecimal receitasEstaduaisAplicacaoDespesaCorrente;
    
    private BigDecimal receitasEstaduaisAplicacaoDespesasCapital;
    
    private BigDecimal outrasReceitasAplicacaoDespesaCorrente;
    
    private BigDecimal outrasReceitasAplicacaoDespesaCapital;
    
    public BigDecimal getReceitasCorrente() {
        BigDecimal valor = BigDecimal.ZERO;
        
        if (receitasEstaduaisAplicacaoDespesaCorrente != null) {
            valor = valor.add(receitasEstaduaisAplicacaoDespesaCorrente);
        }
        
        if (outrasReceitasAplicacaoDespesaCorrente != null) {
            valor = valor.add(outrasReceitasAplicacaoDespesaCorrente);
        }
        
        return valor;
    }
    
    public BigDecimal getReceitasCapital() {
        BigDecimal valor = BigDecimal.ZERO;
        
        if (receitasEstaduaisAplicacaoDespesasCapital != null) {
            valor = valor.add(receitasEstaduaisAplicacaoDespesasCapital);
        }
        
        if (outrasReceitasAplicacaoDespesaCapital != null) {
            valor = valor.add(outrasReceitasAplicacaoDespesaCapital);
        }
        
        return valor;
    }
    
    public BigDecimal getMetaFinanceiraTotal() {
        
        BigDecimal valor = BigDecimal.ZERO;
        
        if (getReceitasCorrente() != null) {
            valor = valor.add(getReceitasCorrente());
        }
        
        if (getReceitasCapital() != null) {
            valor = valor.add(getReceitasCapital());
        }
        
        return valor;
    }

//    @ManyToMany(targetEntity = ReceitaMetaAcaoEstrategica.class, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    private List<ReceitaMetaAcaoEstrategica> receitas = new ArrayList<ReceitaMetaAcaoEstrategica>();
    @OneToMany(mappedBy = "metaAcao")
    @NotAudited
    private List<MetaProduto> mestasProduto;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public AcaoEstrategica getAcaoEstrategica() {
        return acaoEstrategica;
    }
    
    public void setAcaoEstrategica(AcaoEstrategica acaoEstrategica) {
        this.acaoEstrategica = acaoEstrategica;
    }
    
    public ProgramaPPA getProgramaPPA() {
        return programaPPA;
    }
    
    public void setProgramaPPA(ProgramaPPA programaPPA) {
        this.programaPPA = programaPPA;
    }

    public BigDecimal getReceitasEstaduaisAplicacaoDespesaCorrente() {
        return receitasEstaduaisAplicacaoDespesaCorrente;
    }

    public void setReceitasEstaduaisAplicacaoDespesaCorrente(BigDecimal receitasEstaduaisAplicacaoDespesaCorrente) {
        this.receitasEstaduaisAplicacaoDespesaCorrente = receitasEstaduaisAplicacaoDespesaCorrente;
    }

    public BigDecimal getReceitasEstaduaisAplicacaoDespesasCapital() {
        return receitasEstaduaisAplicacaoDespesasCapital;
    }

    public void setReceitasEstaduaisAplicacaoDespesasCapital(BigDecimal receitasEstaduaisAplicacaoDespesasCapital) {
        this.receitasEstaduaisAplicacaoDespesasCapital = receitasEstaduaisAplicacaoDespesasCapital;
    }

    public BigDecimal getOutrasReceitasAplicacaoDespesaCorrente() {
        return outrasReceitasAplicacaoDespesaCorrente;
    }

    public void setOutrasReceitasAplicacaoDespesaCorrente(BigDecimal outrasReceitasAplicacaoDespesaCorrente) {
        this.outrasReceitasAplicacaoDespesaCorrente = outrasReceitasAplicacaoDespesaCorrente;
    }

    public BigDecimal getOutrasReceitasAplicacaoDespesaCapital() {
        return outrasReceitasAplicacaoDespesaCapital;
    }

    public void setOutrasReceitasAplicacaoDespesaCapital(BigDecimal outrasReceitasAplicacaoDespesaCapital) {
        this.outrasReceitasAplicacaoDespesaCapital = outrasReceitasAplicacaoDespesaCapital;
    }
        
    public List<MetaProduto> getMestasProduto() {
        return mestasProduto;
    }
    
    public void setMestasProduto(List<MetaProduto> mestasProduto) {
        this.mestasProduto = mestasProduto;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final MetaAcaoEstrategica other = (MetaAcaoEstrategica) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
