package raffaele.entities;
import jakarta.persistence.*;
@Entity
@Table(name="libro")
public class Libro extends Catalogo{
    @Column(name="autore" , nullable = false)
    private String autore;
    @Column(name="genere" , nullable = false)
    private String genere;

    public Libro() {}

    public Libro(String isbn, String titolo, int annoPubblicazione, int npagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, npagine);
        this.autore = autore;
        this.genere = genere;
    }



    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

}
