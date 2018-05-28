package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Livro;
import model.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-28T20:25:10")
@StaticMetamodel(Avaliacao.class)
public class Avaliacao_ { 

    public static volatile SingularAttribute<Avaliacao, Livro> livro;
    public static volatile SingularAttribute<Avaliacao, Usuario> usuario;
    public static volatile SingularAttribute<Avaliacao, Integer> id;
    public static volatile SingularAttribute<Avaliacao, Double> nota;

}