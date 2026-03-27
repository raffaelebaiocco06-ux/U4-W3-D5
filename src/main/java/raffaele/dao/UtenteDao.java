package raffaele.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import raffaele.entities.Utente;

public class UtenteDao {

    private EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }


    public void salvaU(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(utente);
            transaction.commit();
            System.out.println("Utente salvato con successo!");
    }

    public Utente cercaNTessera(String numeroTessera) {
        TypedQuery<Utente> query = em.createQuery("SELECT u FROM Utente u WHERE u.ntessera = :ntessera", Utente.class);
        query.setParameter("ntessera", numeroTessera);
        return query.getSingleResult();
    }

}
