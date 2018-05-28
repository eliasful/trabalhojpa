package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Autor;
import model.Avaliacao;
import model.Editora;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-28T20:25:10")
@StaticMetamodel(Livro.class)
public class Livro_ { 

    public static volatile SingularAttribute<Livro, Integer> ano;
    public static volatile CollectionAttribute<Livro, Avaliacao> avaliacoes;
    public static volatile SingularAttribute<Livro, String> isbn;
    public static volatile SingularAttribute<Livro, String> titulo;
    public static volatile SingularAttribute<Livro, Editora> editora;
    public static volatile SingularAttribute<Livro, Autor> autor;

}