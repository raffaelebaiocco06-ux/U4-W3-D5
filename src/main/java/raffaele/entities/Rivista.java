package raffaele.entities;
import jakarta.persistence.*;
import raffaele.en.Periodicita;
@Entity
@Table(name="rivista")
public class Rivista extends Catalogo {
  @Enumerated(EnumType.STRING)
    @Column(name = "periodicita",nullable = false)
    private Periodicita periodicita;

    public Rivista() {}

    public Rivista(String isbn, String titolo, int annoPubblicazione, int npagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, npagine);

        this.periodicita = periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

}
