/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    private String nome;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private Collection<Livro> livros;

    public Autor(String nome) {
        this.nome = nome;
    }

    public Autor() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
