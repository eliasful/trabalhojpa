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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author elias
 */
@Entity
@Table(name = "livro")
public class Livro {
    
    @Id
    private String isbn;
    private String titulo;
    private int ano;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editora editora;
    @OneToMany(mappedBy = "livro", fetch = FetchType.EAGER)
    private Collection<Avaliacao> avaliacoes;

    public Livro() {
    }

    public Livro(String isbn, String titulo, int ano, Autor autor, Editora editora) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
        this.autor = autor;
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    
  
    @Override
    public String toString() {
        return "Livro{" + "isbn=" + isbn + ", titulo=" + titulo + ", ano=" + ano + ", autor=" + autor + ", editora=" + editora + '}';
    }
    
    
}
