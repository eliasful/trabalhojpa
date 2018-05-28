/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import javax.persistence.Column;
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
@Table(name = "editora")
public class Editora {
    
    @Id
    private String nome;
    @OneToMany(mappedBy = "editora", fetch = FetchType.EAGER)
    private Collection<Livro> livros;

    public Editora(String nome) {
        this.nome = nome;
    }

    public Editora() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Collection<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return nome;
    }
}
