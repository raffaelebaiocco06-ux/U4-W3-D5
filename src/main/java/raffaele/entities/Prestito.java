package raffaele.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestito")
public class Prestito {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;


    @ManyToOne
    @JoinColumn(name = "catalogo_id", nullable = false)
    private Catalogo elementoprestato;

    @Column(name = "data_inizio_prestito", nullable = false)
    private LocalDate datainizioprestito;

    @Column(name = "data_restituzione_prevista", nullable = false)
    private LocalDate datarestituzioneprevista;

    @Column(name = "data_restituzione_effettiva")
    private LocalDate datarestituzioneeffettiva;

    public Prestito() {}

    public Prestito(Utente utente, Catalogo elementoprestato, LocalDate datainizioprestito) {
        this.utente = utente;
        this.elementoprestato = elementoprestato;
        this.datainizioprestito = datainizioprestito;
        this.datarestituzioneprevista = datainizioprestito.plusDays(30);
    }

    public Long getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public Catalogo getElementoPrestato() {
        return elementoprestato;
    }

    public LocalDate getDataInizioPrestito() {
        return datainizioprestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return datarestituzioneprevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return datarestituzioneeffettiva;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setElementoPrestato(Catalogo elementoPrestato) {
        this.elementoprestato = elementoPrestato;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.datainizioprestito = dataInizioPrestito;
        this.datarestituzioneprevista = dataInizioPrestito.plusDays(30);
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.datarestituzioneeffettiva = dataRestituzioneEffettiva;
    }
}
