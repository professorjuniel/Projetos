/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.weblicita.modelo.vos;

import java.io.Serializable;

/**
 *
 * @author Juniel
 */
public class RegiaoVO implements Serializable{

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}