/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.facilpagar.modelo.dados;

import com.xpert.audit.NotAudited;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author juniel
 */
@Entity
@Table(schema = "dados")
public class Cidade implements Serializable {

    @Id
    @SequenceGenerator(schema ="dados", allocationSize = 1, name = "Cidade", sequenceName = "dados.seq_cidade_id")
    @GeneratedValue(generator = "Cidade")
    private Long id;

    @Size(max = 50)
    @NotBlank
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    private Uf uf;
    
    @NotAudited
    @OrderBy("nome")
    @OneToMany(mappedBy = "cidade")
    private List<Convenio> convenio;
    
    @NotAudited
    @OrderBy("nome")
    @OneToMany(mappedBy = "cidade")
    private List<Cliente> clientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public List<Convenio> getConvenio() {
        return convenio;
    }

    public void setConvenio(List<Convenio> convenio) {
        this.convenio = convenio;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Cidade other = (Cidade) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
