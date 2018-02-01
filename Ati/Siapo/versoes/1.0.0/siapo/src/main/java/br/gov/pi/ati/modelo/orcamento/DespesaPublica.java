/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.pi.ati.modelo.orcamento;

import br.gov.pi.ati.modelo.cadastro.AcaoOrcamentaria;
import br.gov.pi.ati.modelo.cadastro.FonteDeRecurso;
import br.gov.pi.ati.modelo.cadastro.NaturezaDeDespesa;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Juniel
 */
@Entity
public class DespesaPublica implements Serializable {

    @Id
    @SequenceGenerator(name = "DespesaPublica", sequenceName = "despesaPublica_id_seq")
    @GeneratedValue(generator = "DespesaPublica")
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String numeroProcesso;

    @NotBlank
    @Column(columnDefinition = "Text")
    private String descricaoDespesa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private AcaoOrcamentaria acaoOrcamentaria;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private FonteDeRecurso fonteDeRecurso;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private NaturezaDeDespesa naturezaDaDespesa;

//    @ManyToMany(targetEntity = ProgramacaoFinanceira.class, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    private List<ProgramacaoFinanceira> programacaoFinanceira = new ArrayList<ProgramacaoFinanceira>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private MetaPPA produtoLDO;

    private boolean geraQuantificador = false;
    
    
}
