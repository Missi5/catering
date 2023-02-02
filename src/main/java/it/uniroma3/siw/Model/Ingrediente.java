package it.uniroma3.siw.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Ingrediente(String nome, String origine, String descrizione) {
        this.nome = nome;
        this.origine = origine;
        this.descrizione = descrizione;
    }

    private String nome;


    private String origine;


    private String descrizione;
}
