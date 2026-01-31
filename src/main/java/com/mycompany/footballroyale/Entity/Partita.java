/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Entity;
import java.util.Date;
import java.util.List;

public class Partita {
    private Date data;
    private String orario;
    private int punteggioCasa;
    private int punteggioOspiti;
    private String stato;
    private Squadra squadraCasa;
    private Squadra squadraOspite;
    
    // Relazione con gli eventi
    private List<EventoGara> eventi;

    public Partita() {}

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getOrario() { return orario; }
    public void setOrario(String orario) { this.orario = orario; }

    public int getPunteggioCasa() { return punteggioCasa; }
    public void setPunteggioCasa(int punteggioCasa) { this.punteggioCasa = punteggioCasa; }

    public int getPunteggioOspiti() { return punteggioOspiti; }
    public void setPunteggioOspiti(int punteggioOspiti) { this.punteggioOspiti = punteggioOspiti; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    public Squadra getSquadraCasa() { return squadraCasa; }
    public void setSquadraCasa(Squadra s) { this.squadraCasa = s; }

    public Squadra getSquadraOspite() { return squadraOspite; }
    public void setSquadraOspite(Squadra s) { this.squadraOspite = s; }

    public List<EventoGara> getEventi() { return eventi; }
    public void setEventi(List<EventoGara> e) { this.eventi = e; }
}
