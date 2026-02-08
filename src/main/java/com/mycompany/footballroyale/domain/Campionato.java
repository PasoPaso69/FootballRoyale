package com.mycompany.footballroyale.domain;

import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;
import com.mycompany.footballroyale.domain.Enum.StatoCompetizione;
import com.mycompany.footballroyale.domain.Strategie.GenerazioneCalendarioStrategy;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "campionati")
@PrimaryKeyJoinColumn(name = "id_competizione") // Collega l'ID a quello della tabella Competizione
public class Campionato extends Competizione {

    @Column(name = "anno")
    private int anno;
    

    @Column(name = "numero_giornate")
    private int numeroGiornate;

    @Column(name = "punti_vittoria")
    private int puntiVittoria;

    @Column(name = "punti_pareggio")
    private int puntiPareggio;

    @Column(name = "punti_sconfitta")
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

    // Getter e Setter rimangono uguali
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
    

    
    @Override
    public List<Partita> generaCalendario(List<GiorniSettimanali> giorni,int ppg,Date DataInizio) {
        List<Partita> partiteGenerate = strategia.generaCalendario(
            this.squadrePartecipanti, 
            this.campettiDisponibili, 
            DataInizio, 
            giorni, 
            ppg
        );
        for (Partita p : partiteGenerate){
            this.calendario.add(p);
    }
        this.setStato(StatoCompetizione.DaIniziare);
        return calendario;
    }
    
    @Override
    public String toString() {
        return "Campionato{" +
                super.toString() + 
                ", anno=" + anno +
                ", numeroGiornate=" + numeroGiornate +
                '}';
    }
}