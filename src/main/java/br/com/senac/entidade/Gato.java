/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Artemisia Dutra
 */
@Entity
@Table(name="gato")
@PrimaryKeyJoinColumn(name = "Id_animal")
public class Gato extends Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private boolean fiv;

    @Column(nullable = false)
    private boolean felv;

    public Gato() {
    }


    public Gato(boolean fiv, boolean felv, String nome, String nascimento, Double peso, String sexo, String observacao, Comportamento comportamento) {
        super(nome, nascimento, peso, sexo, observacao, comportamento);
        this.fiv = fiv;
        this.felv = felv;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gato)) {
            return false;
        }
        Gato other = (Gato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.senac.entidade.Gato[ id=" + id + " ]";
    }
    
}
