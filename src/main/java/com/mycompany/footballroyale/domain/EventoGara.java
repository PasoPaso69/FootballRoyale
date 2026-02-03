/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;

public class EventoGara {
    private String Id;
    private int minuto;
    private String tipologia; // Gol, Ammonizione, Espulsione
    
        //  In quale partita è successo
    private Partita partita;
    
    //  Chi ha inserito il dato
    private OperatorePartita operatore;

    
    private Giocatore protagonista; // Il giocatore che ha fatto l'evento

    public EventoGara() {}
    
    public EventoGara(int minuto, String tipologia, Giocatore protagonista, 
                      Partita partita, OperatorePartita operatore) {
        this.minuto = minuto;
        this.tipologia = tipologia;
        this.protagonista = protagonista;
        this.partita = partita;
        this.operatore = operatore;
    }
    
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public int getMinuto() { return minuto; }
    public void setMinuto(int minuto) { this.minuto = minuto; }

    public String getTipologia() { return tipologia; }
    public void setTipologia(String tipologia) { this.tipologia = tipologia; }

    public Giocatore getProtagonista() { return protagonista; }
    public void setProtagonista(Giocatore p) { this.protagonista = p; }
    
    
    public Partita getPartita() { return partita; }
    public void setPartita(Partita partita) { this.partita = partita; }

    public OperatorePartita getOperatore() { return operatore; }
    public void setOperatore(OperatorePartita operatore) { this.operatore = operatore; }
    
    @Override
public String toString() {
    return "EventoGara{" +
            "Id='" + Id + '\'' +
            ", minuto=" + minuto +
            ", tipologia='" + tipologia + '\'' +
            ", protagonista=" + (protagonista != null ? protagonista.getCognome() + " (" + protagonista.getNumeroMaglia() + ")" : "N/A") +
            '}';
}
}
