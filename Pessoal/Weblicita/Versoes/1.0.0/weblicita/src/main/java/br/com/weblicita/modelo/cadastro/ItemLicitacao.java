/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weblicita.modelo.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juniel
 */
//@Entity
public class ItemLicitacao implements Serializable {

    @Id
    @SequenceGenerator(name = "ItemLicitacaoAdd", allocationSize = 1, sequenceName = "itemLicitacaoAdd_id_seq")
    @GeneratedValue(generator = "ItemLicitacaoAdd")
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
    
    private BigDecimal quantidade;
    
    private BigDecimal preco;
    
    
}
