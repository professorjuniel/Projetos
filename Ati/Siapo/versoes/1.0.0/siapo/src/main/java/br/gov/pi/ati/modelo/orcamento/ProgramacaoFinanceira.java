/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.pi.ati.modelo.orcamento;

import br.gov.pi.ati.modelo.cadastro.FonteDeRecurso;
import br.gov.pi.ati.modelo.cadastro.NaturezaDeDespesa;
import br.gov.pi.ati.modelo.cadastro.enums.Meses;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Juniel
 */
@Entity
public class ProgramacaoFinanceira implements Serializable {

    @Id
    @SequenceGenerator(name = "ProgramacaoFinanceira", sequenceName = "programacaoFinanceira_id_seq")
    @GeneratedValue(generator = "ProgramacaoFinanceira")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private FonteDeRecurso fonteDeRecurso;

    @ManyToOne(fetch = FetchType.LAZY)
    private NaturezaDeDespesa naturezaDaDespesa;

    @Enumerated(EnumType.STRING)
    private Meses mes;

    private Integer ano;

    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Meses getMes() {
        return mes;
    }

    public void setMes(Meses mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return mes.getDescricao().concat(" - ").concat(ano.toString()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ProgramacaoFinanceira other = (ProgramacaoFinanceira) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}