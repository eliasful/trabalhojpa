package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Livro;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-28T20:25:10")
@StaticMetamodel(Autor.class)
public class Autor_ { 

    public static volatile CollectionAttribute<Autor, Livro> livros;
    public static volatile SingularAttribute<Autor, String> nome;

}