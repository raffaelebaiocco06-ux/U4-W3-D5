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
        TypedQuery<Catalogo> query = em.createQuery("SELECT p.elementoprestato FROM Prestito p WHERE p.utente.ntessera= :ntessera AND p.datarestituzioneeffettiva IS NULL", Catalogo.class);
        query.setParameter("ntessera", numeroTessera);
        return query.getResultList();
    }
    public List<Prestito> cercaprestitiScaduti(){
        TypedQuery<Prestito> query=em.createQuery("SELECT p FROM Prestito p WHERE p.datarestituzioneprevista < CURRENT_DATE AND p.datarestituzioneeffettiva IS NULL  ", Prestito.class);
        return query.getResultList();
    }

}
