/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poligono;

import java.io.Serializable;

/**
 *
 * @author Juniel
 */
public class PontoValor implements Serializable {

    private Long id;

    private double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public PontoValor(double valor) {
        this.valor = valor;
    }

    public PontoValor() {
    }

}
