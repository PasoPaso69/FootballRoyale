/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;
import java.util.Date;
import java.util.*;

public abstract class Competizione {
    private String Id;
    private String nome;
    private String stato;
    private Date dataInizio;
    
   private List<Squadra> squadrePartecipanti = new ArrayList<>();
    private List<Partita> calendario = new ArrayList<>();


    public Competizione() {}
    
      public Competizione(String id, String nome, Date dataInizio) {
        this.Id = id;
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.stato = "In configurazione"; // Stato iniziale di default
    }
    
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    // Getter e Setter
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    public Date getDataInizio() { return dataInizio; }
    public void setDataInizio(Date dataInizio) { this.dataInizio = dataInizio; }

    public List<Squadra> getSquadrePartecipanti() { return squadrePartecipanti; }
    public void setSquadrePartecipanti(List<Squadra> s) { this.squadrePartecipanti = s; }

    public List<Partita> getCalendario() { return calendario; }
    public void setCalendario(List<Partita> c) { this.calendario = c; }
    
    //metodo astratto per forzare la generazione alle sottoclassi
    public abstract void generaCalendario();
    
@Override
public String toString() {
    return "nome='" + nome + '\'' +
           ", stato='" + stato + '\'' +
           ", dataInizio=" + dataInizio +
           ", numeroSquadre=" + (squadrePartecipanti != null ? squadrePartecipanti.size() : 0);
}
}
