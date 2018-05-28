package dao;

import java.util.List;
import model.Autor;
import model.Editora;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Livro;

public class AutorDAO {

    public Autor salvar(Autor obj) throws Exception {
        try {
            if (findById(obj.getNome()) == null) {
                return insert(obj);
            } else {
                return update(obj);
            }
        } catch(Exception ex) {
            throw new Exception(ex);
        }
    }


    public Autor findById(String id) throws Exception {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.find(Autor.class, id);
        } catch(Exception ex){
            throw new Exception(ex);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    private Autor insert(Autor obj) throws Exception {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = ConnectionDB.newEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            em.persist(obj);
            em.flush();
            transaction.commit();
            return obj;
        } catch (Exception ex){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new Exception(ex);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    private Autor update(Autor obj) throws Exception {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = ConnectionDB.newEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Autor merged = em.merge(obj);
            transaction.commit();
            return merged;
        } catch (Exception ex){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new Exception(ex);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }
    
    public List<Autor> findAll()  {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.createQuery("from Autor a").getResultList();
        } catch(Exception ex){
            System.out.println(ex);
            return null;
        } finally {
            if (em != null){
                em.close();
            }
        }
    }
}
