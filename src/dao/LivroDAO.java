package dao;

import java.util.List;
import model.Editora;
import model.Livro;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LivroDAO {

    public Livro salvar(Livro obj) throws Exception {
        try {
            if (findById(obj.getIsbn()) == null) {
                return insert(obj);
            } else {
                return update(obj);
            }
        } catch(Exception ex) {
            throw new Exception(ex);
        }
    }


    public Livro findById(String id) throws Exception {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.find(Livro.class, id);
        } catch(Exception ex){
            throw new Exception(ex);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    private Livro insert(Livro obj) throws Exception {
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

    private Livro update(Livro obj) throws Exception {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = ConnectionDB.newEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Livro merged = em.merge(obj);
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

    public List<Livro> findAll()  {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.createQuery("from Livro l").getResultList();
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
