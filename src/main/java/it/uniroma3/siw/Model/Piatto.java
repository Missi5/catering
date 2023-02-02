package it.uniroma3.siw.Model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Piatto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String nome;

    public Piatto(String nome, String descrizione, List<Ingrediente> ingredienti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
    }

    private String descrizione;

    @ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL})
    private List<Ingrediente> ingredienti;
}
