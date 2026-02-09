/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;

import com.mycompany.footballroyale.domain.Enum.statoPrenotazione;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 39327
 */
@Entity
@Table(name = "Prenotazioni")
public class Prenotazione {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_prenotazione")
    private String Id;

    @Column(name = "data")
    private Date data;
    
    @Column(name = "orarioInizio")
    private Time orarioInizio;
    
    @Column(name = "orarioFine")
    private Time orarioFine;

    @Column(name = "stato")
    private statoPrenotazione stato;
    
    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "Campetto_Prenotazione")
    private Campetto campetto;
    
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "Prenotazione_Partita")
    private Partita partita;
    
    public Prenotazione(){}
    
    public Prenotazione(Date data, Time orarioInizio, Time orarioFine) {
        this.data = data;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
        this.stato = statoPrenotazione.PENDING; // Impostazione di default suggerita
    }

    public String getId() {
        return Id;
    }

 
    public void setId(String id) {
        this.Id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(Time orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public Time getOrarioFine() {
        return orarioFine;
    }

    public void setOrarioFine(Time orarioFine) {
        this.orarioFine = orarioFine;
    }

    public statoPrenotazione getStato() {
        return stato;
    }

    public void setStato(statoPrenotazione stato) {
        this.stato = stato;
    }
    

    public void setCampetto (Campetto u){
        this.campetto=u;
    }
    public Campetto getCampetto(){
        return campetto;
    }

    public Partita getPartita() {
        return partita;
    }

    public void setPartita(Partita partita) {
        this.partita = partita;
    }
    
    @Override
public String toString() {
    return "Prenotazione{" +
            "id='" + Id + '\'' +
            ", data=" + data +
            ", orarioInizio=" + orarioInizio +
            ", orarioFine=" + orarioFine +
            ", stato=" + stato +
            '}';
}
}

