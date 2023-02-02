package it.uniroma3.siw.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buffet {

    /* VARIABILI D'ISTANZA */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String nome;

    public Buffet(String nome, String descrizione, Chef chef, Set<Piatto> piatti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.chef = chef;
        this.piatti = piatti;
    }

    private String descrizione;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL})
    private Chef chef;

    @ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL})
    private Set<Piatto> piatti;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public void setPiatti(Set<Piatto> piatti) {
        this.piatti = piatti;
    }
}
