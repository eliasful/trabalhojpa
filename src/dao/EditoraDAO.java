package dao;

import java.util.List;
import model.Editora;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Autor;

public class EditoraDAO {

    public Editora salvar(Editora obj) throws Exception {
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


    public Editora findById(String id) throws Exception {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.find(Editora.class, id);
        } catch(Exception ex){
            throw new Exception(ex);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    private Editora insert(Editora obj) throws Exception {
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

    private Editora update(Editora obj) throws Exception {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = ConnectionDB.newEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Editora merged = em.merge(obj);
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
    
    public List<Editora> findAll()  {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.createQuery("from Editora e").getResultList();
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
