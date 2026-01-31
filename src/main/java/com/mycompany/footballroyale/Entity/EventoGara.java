/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Entity;

public class EventoGara {
    private int minuto;
    private String tipologia; // Gol, Ammonizione, Espulsione
    
    private Giocatore protagonista; // Il giocatore che ha fatto l'evento

    public EventoGara() {}

    public int getMinuto() { return minuto; }
    public void setMinuto(int minuto) { this.minuto = minuto; }

    public String getTipologia() { return tipologia; }
    public void setTipologia(String tipologia) { this.tipologia = tipologia; }

    public Giocatore getProtagonista() { return protagonista; }
    public void setProtagonista(Giocatore p) { this.protagonista = p; }
}
