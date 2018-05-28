package dao;

import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UsuarioDAO {

    public String salvar(Usuario user) throws Exception {
        try {
            if (findById(user.getId()) == null) {
                return insert(user);
            } else {
                return update(user);
            }
        } catch(Exception ex) {
            throw new Exception(ex);
        }
    }


    public Usuario findById(String userId) throws Exception {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.find(Usuario.class, userId);
        } catch(Exception ex){
            throw new Exception(ex);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    private String insert(Usuario user) throws Exception {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = ConnectionDB.newEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            em.persist(user);
            em.flush();
            transaction.commit();
            return user.getId();
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

    private String update(Usuario user) throws Exception {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = ConnectionDB.newEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Usuario userMerged = em.merge(user);
            transaction.commit();
            em.flush();
            return userMerged.getId();
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
}
