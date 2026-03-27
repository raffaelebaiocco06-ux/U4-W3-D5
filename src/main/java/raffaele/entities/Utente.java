package raffaele.entities;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;



@Entity
@Table(name="utente")
public class Utente {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @Column(name="nome", nullable = false)
    private String nome;
    @Column(name="cognome", nullable = false)
    private String cognome;
    @Column(name="data_nascita", nullable = false)
    private LocalDate datanascita;
    @Column(name="numero_tessera",unique = true, nullable = false)
    private String ntessera;


    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti = new ArrayList<>();


    public Utente(){}

    public Utente( String nome, String cognome, LocalDate datanascita, String ntessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.datanascita = datanascita;
        this.ntessera = ntessera;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDatanascita() {
        return datanascita;
    }

    public String getNtessera() {
        return ntessera;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setDatanascita(LocalDate datanascita) {
        this.datanascita = datanascita;
    }

    public void setNtessera(String ntessera) {
        this.ntessera = ntessera;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", datanascita=" + datanascita +
                ", ntessera='" + ntessera + '\'' +
                '}';
    }
}
