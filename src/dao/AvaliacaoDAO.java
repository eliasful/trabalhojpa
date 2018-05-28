package dao;

import java.util.List;
import model.Avaliacao;
import model.Editora;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Livro;

public class AvaliacaoDAO {

    public Avaliacao salvar(Avaliacao obj) throws Exception {
        try {
            if (findById(obj.getId()) == null) {
                return insert(obj);
            } else {
                return update(obj);
            }
        } catch(Exception ex) {
            throw new Exception(ex);
        }
    }


    public Avaliacao findById(int id) throws Exception {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.find(Avaliacao.class, id);
        } catch(Exception ex){
            throw new Exception(ex);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    private Avaliacao insert(Avaliacao obj) throws Exception {
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

    private Avaliacao update(Avaliacao obj) throws Exception {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = ConnectionDB.newEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Avaliacao merged = em.merge(obj);
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
    
    public List<Avaliacao> findByLocal(String local)  {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.createQuery("from Avaliacao a "
                    + " join a.usuario u "
                    + " where u.local = :local")
                    .setParameter("local", local)
                    .getResultList();
        } catch(Exception ex){
            System.out.println(ex);
            return null;
        } finally {
            if (em != null){
                em.close();
            }
        }
    }
    
    public List<Avaliacao> findByAvaliacao(int ano)  {
        EntityManager em = null;
        try {
            em = ConnectionDB.newEntityManager();
            return em.createQuery("from Avaliacao a "
                    + " join a.livro l "
                    + " where l.ano = :ano")
                    .setParameter("ano", ano)
                    .getResultList();
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
