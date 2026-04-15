/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;

import com.mycompany.footballroyale.domain.Enum.StatoCampetto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author 39327
 */
@Entity
@Table(name = "campetti")
public class Campetto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Permette l'autoincremento
    @Column(name = "id_campetto")
    private Long Id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private StatoCampetto stato;
    
     @Column(name = "indirizzo")
    private String indirizzo;

    public Campetto() {
    this.stato = StatoCampetto.Agibile;}

    public Campetto(Long id, String nome,String indirizzo) {
        this.Id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.stato = StatoCampetto.Agibile;
    }

    // Getter e Setter
    public Long getId() { return Id; }
    public void setId(Long Id) { this.Id = Id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public StatoCampetto getStato() { return stato; }
    public void setStato(StatoCampetto stato) { this.stato = stato; }

    @Override
public String toString() {
    return "Campetto{" +
            "id='" + Id + '\'' +
            ", nome='" + nome + '\'' +
            ", indirizzo='" + indirizzo + '\'' +
            ", stato='" + stato + '\'' +
            '}';
}
    
}
