/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;
import java.util.List;
import java.util.ArrayList;

public class Squadra {
    private String Id;
    private String nome;
    private Foto logo;
    private String dettaglioMagliaCasa;
    private String dettaglioMagliaTrasferta;
    private int vittorie;
    private int pareggi;
    private int sconfitte;
    
    // Relazione 1 -> 5..* con Giocatore
    private List<Giocatore> giocatori = new ArrayList<>();

    public Squadra() {}
    
     public Squadra(String id, String nome, Foto logo, String magliaCasa, String magliaTrasferta) {
        this.Id = id;
        this.nome = nome;
        this.logo = logo;
        this.dettaglioMagliaCasa = magliaCasa;
        this.dettaglioMagliaTrasferta = magliaTrasferta;
        // I contatori partono da 0
        this.vittorie = 0;
        this.pareggi = 0;
        this.sconfitte = 0;
    }
    
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Foto getLogo() { return logo; }
    public void setLogo(Foto logo) { this.logo = logo; }

    public String getDettaglioMagliaCasa() { return dettaglioMagliaCasa; }
    public void setDettaglioMagliaCasa(String d) { this.dettaglioMagliaCasa = d; }

    public String getDettaglioMagliaTrasferta() { return dettaglioMagliaTrasferta; }
    public void setDettaglioMagliaTrasferta(String d) { this.dettaglioMagliaTrasferta = d; }
    
    public int getVittorie() { return vittorie; }
    public void setVittorie(int vittorie) { this.vittorie = vittorie; }

    public int getPareggi() { return pareggi; }
    public void setPareggi(int pareggi) { this.pareggi = pareggi; }

    public int getSconfitte() { return sconfitte; }
    public void setSconfitte(int sconfitte) { this.sconfitte = sconfitte; }

    public List<Giocatore> getGiocatori() { return giocatori; }
    public void setGiocatori(List<Giocatore> g) { this.giocatori = g; }
    
    @Override
public String toString() {
    return "Squadra{" +
            "Id='" + Id + '\'' +
            ", nome='" + nome + '\'' +
            ", logo=" + (logo != null ? logo.getFileName() : "N/A") +
            ", magliaCasa='" + dettaglioMagliaCasa + '\'' +
            ", magliaTrasferta='" + dettaglioMagliaTrasferta + '\'' +
            ", numeroGiocatori=" + (giocatori != null ? giocatori.size() : 0) +
            '}';
}
}
