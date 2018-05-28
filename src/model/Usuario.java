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
@Table(name = "usuario")
public class Usuario {
    
    @Id
    private String id;
    private String local;
    private int idade;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Collection<Avaliacao> avaliacoes;

    public Usuario() {
    }

    public Usuario(String id, String local, int idade) {
        this.id = id;
        this.local = local;
        this.idade = idade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", local=" + local + ", idade=" + idade + '}';
    }
}
