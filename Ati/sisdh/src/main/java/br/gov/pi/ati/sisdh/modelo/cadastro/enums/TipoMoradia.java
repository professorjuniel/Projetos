/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.pi.ati.sisdh.modelo.cadastro.enums;

/**
 *
 * @author Juniel
 */
public enum TipoMoradia {
    PROPRIA("Própria"),
    ALUGADA("Alugada"),
    CEDIDA("Cedida"),
    FINANCIADA("Financiada");
    

    private TipoMoradia(String descricao) {
        this.descricao = descricao;
    }
    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
