/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.pi.ati.sisforms.modelo.enums;

/**
 *
 * @author Juniel
 */
public enum Religiao {
    CATOLICA("Católica"),
    EVANGELICA("Evangélica"),
    CULTOS_AFROS("Cultos Afros"),
    ESPIRITA_KARDENAQUIANA("Espírita Kardequiana"),
    OUTROS("Outros");
    

    private Religiao(String descricao) {
        this.descricao = descricao;
    }
    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
