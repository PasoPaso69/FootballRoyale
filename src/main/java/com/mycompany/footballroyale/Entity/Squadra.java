/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Entity;
import java.util.List;
import java.util.ArrayList;

public class Squadra {
    private String nome;
    private Foto logo;
    private String dettaglioMagliaCasa;
    private String dettaglioMagliaTrasferta;
    
    // Relazione 1 -> 5..* con Giocatore
    private List<Giocatore> giocatori = new ArrayList<>();

    public Squadra() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Foto getLogo() { return logo; }
    public void setLogo(Foto logo) { this.logo = logo; }

    public String getDettaglioMagliaCasa() { return dettaglioMagliaCasa; }
    public void setDettaglioMagliaCasa(String d) { this.dettaglioMagliaCasa = d; }

    public String getDettaglioMagliaTrasferta() { return dettaglioMagliaTrasferta; }
    public void setDettaglioMagliaTrasferta(String d) { this.dettaglioMagliaTrasferta = d; }

    public List<Giocatore> getGiocatori() { return giocatori; }
    public void setGiocatori(List<Giocatore> g) { this.giocatori = g; }
}
