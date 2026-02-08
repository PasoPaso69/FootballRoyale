/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_campetto")
    private String Id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "stato")
    private String stato;
    
     @Column(name = "indirizzo")
    private String indirizzo;
         
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenotazione")
    private List<Prenotazione> prenotazioni;
    
    public Campetto() {}

    public Campetto(String id, String nome,String indirizzo) {
        this.Id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.stato = "attivo";
    }

    // Getter e Setter
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }
    
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
