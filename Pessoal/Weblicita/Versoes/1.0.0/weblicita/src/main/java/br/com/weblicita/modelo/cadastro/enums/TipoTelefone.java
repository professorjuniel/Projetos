/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weblicita.modelo.cadastro.enums;

/**
 *
 * @author Juniel
 */
public enum TipoTelefone {
    COMERCIAL(1, "Comercial"),
    RESIDENCIAL(2, "Residêncial"),
    CELULAR(3, "Celular"),
    PUBLICO(4, "Público");

    private TipoTelefone(int num, String descricao) {
        this.descricao = descricao;
        this.num = num;
    }
    private String descricao;
    private int num;

    public String getDescricao() {
        return descricao;
    }

    public int getNum() {
        return num;
    }
    
}
