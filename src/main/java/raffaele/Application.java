package raffaele;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import raffaele.dao.CatalogoDao;
import raffaele.dao.PrestitoDao;
import raffaele.dao.UtenteDao;
import raffaele.en.Periodicita;
import raffaele.entities.Catalogo;
import raffaele.entities.Libro;
import raffaele.entities.Prestito;
import raffaele.entities.Rivista;
import raffaele.entities.Utente;

import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-w3-d5");
        EntityManager em = emf.createEntityManager();

        CatalogoDao catalogoDao = new CatalogoDao(em);
        UtenteDao utenteDAO = new UtenteDao(em);
        PrestitoDao prestitoDao = new PrestitoDao(em);

        try {

            Libro libro1 = new Libro("ISBN001", "Il Signore degli Anelli", 1954, 1200, "Tolkien", "Fantasy");
            Libro libro2 = new Libro("ISBN002", "Harry Potter e la Pietra Filosofale", 1997, 350, "Rowling", "Fantasy");
            Rivista rivista1 = new Rivista("ISBN003", "Focus", 2024, 100, Periodicita.MENSILE);

          //  catalogoDao.salvaC(libro1);
          //  catalogoDao.salvaC(libro2);
           // catalogoDao.salvaC(rivista1);

            Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 10), "TESS001");
           // utenteDAO.salvaU(utente1);

            Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.now().minusDays(40)); // scaduto
            Prestito prestito2 = new Prestito(utente1, rivista1, LocalDate.now().minusDays(10));

           // prestitoDao.salvaprestito(prestito1);
           // prestitoDao.salvaprestito(prestito2);


            System.out.println(" cerca per isbn ");
            Catalogo trovatoIsbn = catalogoDao.cercaperisbn("ISBN001");
            System.out.println(trovatoIsbn);

            System.out.println(" cerca per anno di pubblicazione ");
            List<Catalogo> trovatiAnno = catalogoDao.cercaperannodipubblicazione(1997);
            trovatiAnno.forEach(System.out::println);


            System.out.println("caerca autore");
            List<Libro> trovatiAutore = catalogoDao.cercaautore("Rowling");
            trovatiAutore.forEach(System.out::println);


            System.out.println("cerca  parte titolo");
            List<Catalogo> trovatiTitolo = catalogoDao.cercaPerTitolo("Harry");
            trovatiTitolo.forEach(System.out::println);


            System.out.println("prestiti attivi ");
            List<Catalogo> prestitiAttivi = prestitoDao.cercaprestitiPerTessera("TESS001");
            prestitiAttivi.forEach(System.out::println);


            System.out.println("prestiti scaduti");
            List<Prestito> prestitiScaduti = prestitoDao.cercaprestitiScaduti();
            prestitiScaduti.forEach(p -> System.out.println(
                    "Prestito ID: " + p.getId() +
                            ", elemento: " + p.getElementoPrestato().getTitolo() +
                            ", utente: " + p.getUtente().getNome()
            ));


            System.out.println("rimuovi per isbn");
            catalogoDao.rimuoviperisbn("ISBN002");

            System.out.println("riprova");//vediamo se funge
            Catalogo dopoRimozione = catalogoDao.cercaperisbn("ISBN002");
            System.out.println(dopoRimozione);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
