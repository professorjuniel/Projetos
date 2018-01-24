/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.pi.ati.sisforms.modelo.servicos;

import br.gov.pi.ati.sisforms.modelo.enums.TipoLocal;
import com.xpert.audit.NotAudited;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Juniel
 */
@Entity
public class LocalReserva implements Serializable {
    
    @Id
    @SequenceGenerator(name = "LocalReserva", sequenceName = "seq_localreserva")
    @GeneratedValue(generator = "LocalReserva")
    private Long id;
    
    @Size(max = 255)
    @NotBlank
    private String nome;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoLocal tipo;
    
    @NotNull
    private Integer quantidadeLugares;
    
    private boolean ativo = true;
    
    @NotAudited
    @OneToMany(mappedBy = "localReserva")
    private List<ReservaLocal> reservas;
    
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
    
    public TipoLocal getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoLocal tipo) {
        this.tipo = tipo;
    }
    
    public Integer getQuantidadeLugares() {
        return quantidadeLugares;
    }
    
    public void setQuantidadeLugares(Integer quantidadeLugares) {
        this.quantidadeLugares = quantidadeLugares;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public List<ReservaLocal> getReservas() {
        return reservas;
    }
    
    public void setReservas(List<ReservaLocal> reservas) {
        this.reservas = reservas;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final LocalReserva other = (LocalReserva) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return nome.concat(" - ").concat(tipo.getDescricao());        
    }
    
}
