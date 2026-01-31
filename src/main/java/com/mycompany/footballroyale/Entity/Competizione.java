/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Entity;
import java.util.Date;
import java.util.List;

public abstract class Competizione {
    private String nome;
    private String stato;
    private Date dataInizio;
    
    private List<Squadra> squadrePartecipanti;
    private List<Partita> calendario;


    public Competizione() {}

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

}
