package raffaele.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import raffaele.entities.Catalogo;
import raffaele.entities.Prestito;
import raffaele.entities.Utente;

import java.util.List;

public class PrestitoDao {
    private EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public void salvaprestito(Prestito prestito){
        EntityTransaction transaction= em.getTransaction();
        transaction.begin();
        em.persist(prestito);
        transaction.commit();
        System.out.println("Prestito salvato con successo!");
    }
    public List<Catalogo> cercaprestitiPerTessera(String numeroTessera){
        TypedQuery<Catalogo> query = em.createQuery("SELECT p.elementoprestato FROM PRESTITO p", Catalogo.class);
        query.setParameter("ntessera", numeroTessera);
        return query.getResultList();
    }

}
