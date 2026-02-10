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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 39327
 */
@Entity
@Table(name = "campetti")
public class Campetto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <--- Fondamentale per l'autoincremento
    @Column(name = "id_campetto")
    private Long Id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING) // Salva il nome della costante, non la posizione
    @Column(name = "stato")
    private StatoCampetto stato;
    
     @Column(name = "indirizzo")
    private String indirizzo;
         
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenotazione")
    private List<Prenotazione> prenotazioni;
    
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
    
   public List<Prenotazione> getPrenotazioni() {return this.prenotazioni;}
   public void setPrenotazioni(List<Prenotazione> p) {this.prenotazioni=p;}
   
  
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
