/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.pi.ati.sisrh.modelo.cadastro.enums;

/**
 *
 * @author Juniel
 */
public enum TipoDeServicoLgbt {

    P_AC("P-Ac"),
    AC("Ac"),
    AT("At"),
    EN("En"),
    MO("Mo"),
    MC("Mc"),
    VI("Vi"),
    VD("Vd");

    private TipoDeServicoLgbt(String descricao) {
        this.descricao = descricao;
    }
    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}