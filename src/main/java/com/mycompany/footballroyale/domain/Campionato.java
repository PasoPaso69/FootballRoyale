/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;
import java.util.*;

public class Campionato extends Competizione {
    private int anno;
    private int numeroGiornate;
     private int puntiVittoria;
    private int puntiPareggio;
    private int puntiSconfitta;

    public Campionato() { 
        super(); 
        // Default standard moderni
        this.puntiVittoria = 3;
        this.puntiPareggio = 1;
        this.puntiSconfitta = 0;
    }
    
    public Campionato(String id, String nome, Date dataInizio, int anno, int pVittoria, int pPareggio, int pSconfitta) {
        super(id, nome, dataInizio);
        this.anno = anno;
        this.puntiVittoria = pVittoria;
        this.puntiPareggio = pPareggio;
        this.puntiSconfitta = pSconfitta;
    }
    

    public int getAnno() { return anno; }
    public void setAnno(int anno) { this.anno = anno; }
    public int getNumeroGiornate() { return numeroGiornate; }
    public void setNumeroGiornate(int numeroGiornate) { this.numeroGiornate = numeroGiornate; }
    
    public int getPuntiVittoria() { return puntiVittoria; }
    public void setPuntiVittoria(int puntiVittoria) { this.puntiVittoria = puntiVittoria; }

    public int getPuntiPareggio() { return puntiPareggio; }
    public void setPuntiPareggio(int puntiPareggio) { this.puntiPareggio = puntiPareggio; }

    public int getPuntiSconfitta() { return puntiSconfitta; }
    public void setPuntiSconfitta(int puntiSconfitta) { this.puntiSconfitta = puntiSconfitta; }
    
    
    /**
     * Implementazione del metodo astratto della superclasse.
     */
    @Override
    public void generaCalendario() {
        List<Squadra> squadre = getSquadrePartecipanti();
        if (squadre.isEmpty()) return; // O lancia eccezione

        // Qui andrebbe la logica dell'algoritmo 
       
    }
    
    @Override
public String toString() {
    return "Campionato{" +
            super.toString() + // Recupera Nome, Stato, DataInizio dalla superclasse
            ", anno=" + anno +
            ", numeroGiornate=" + numeroGiornate +
            '}';
}
}
