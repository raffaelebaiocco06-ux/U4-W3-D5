package raffaele.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import raffaele.entities.Catalogo;
import raffaele.entities.Libro;
import raffaele.entities.Utente;

import java.time.LocalDate;
import java.util.List;


public class CatalogoDao {
    private EntityManager em;

    public CatalogoDao(EntityManager em) {
        this.em = em;
    }
    public Catalogo salvaC(Catalogo el) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(el);
        transaction.commit();
        System.out.println("elemento salvato con successo!");
        return el;
    }
    public Catalogo cercaperisbn(String isbn){
        TypedQuery<Catalogo> query = em.createQuery("SELECT c FROM Catalogo c WHERE c.isbn = :isbn", Catalogo.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }
    public void  rimuoviperisbn(String isbn){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Catalogo elemento = cercaperisbn(isbn);

        if (elemento != null) {
            em.remove(elemento);
            System.out.println("Elemento rimosso!");
        } else {
            System.out.println("Elemento non trovato");
        }

        transaction.commit();
    }
    public List<Catalogo>  cercaperannodipubblicazione(int anno){
        TypedQuery<Catalogo> query = em.createQuery("SELECT c FROM Catalogo c WHERE c.annoPubblicazione = :anno", Catalogo.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }
    public List<Libro> cercaautore(String autore){
        TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }
    public List<Catalogo> cercaPerTitolo(String titolo) {
        TypedQuery<Catalogo> query = em.createQuery(
                "SELECT c FROM Catalogo c WHERE LOWER(c.titolo) LIKE LOWER(:titolo)",
                Catalogo.class
        );
        query.setParameter("titolo",  "%" + titolo + "%");
        return query.getResultList();
    }

}
