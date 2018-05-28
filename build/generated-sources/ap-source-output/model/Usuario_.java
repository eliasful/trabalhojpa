package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Avaliacao;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-28T20:25:10")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> idade;
    public static volatile CollectionAttribute<Usuario, Avaliacao> avaliacoes;
    public static volatile SingularAttribute<Usuario, String> id;
    public static volatile SingularAttribute<Usuario, String> local;

}