/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.service;

import com.mycompany.footballroyale.TechnicalService.EntityManager;
import com.mycompany.footballroyale.TechnicalService.PersistentManager;
import com.mycompany.footballroyale.domain.Campetto;
import com.mycompany.footballroyale.domain.Campionato;
import com.mycompany.footballroyale.domain.Competizione;
import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;
import com.mycompany.footballroyale.domain.Enum.TipoCompetizione;
import com.mycompany.footballroyale.domain.Enum.CriteriCalendario;
import com.mycompany.footballroyale.domain.Squadra;
import com.mycompany.footballroyale.domain.Strategie.GenerazioneCalendarioStrategy;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class GestoreTorneo { 
    private Competizione competizionecorrente;


    
     public Map<String,String> selezionaFormato(TipoCompetizione Competizione){
       
     Competizione nuovaCompetizione = switch (Competizione) {
            case CAMPIONATO -> new Campionato();
            case ELIMINAZIONE_DIRETTA -> {
                System.out.println("Inizializzazione formato Eliminazione Diretta...");
                yield null; // Placeholder finché non viene creata la classe
            }
            default -> throw new IllegalArgumentException("Tipo competizione non supportato: " + Competizione);
        };
         competizionecorrente = nuovaCompetizione;
         
         Map<String,String> TutteLesquadre = PersistentManager.getInstance().getIdNomeSquadre();
       
        return TutteLesquadre;
    }
     
     public Map<String,String> SelezionaSquadre(List<String> IdSquadre){
         List<Squadra> SquadreSelezionate= PersistentManager.getInstance().getSquadrePerID(IdSquadre);
         competizionecorrente.setSquadrePartecipanti(SquadreSelezionate);
         
         Map<String,String> campiDisp = PersistentManager.getInstance().getIdNomeCampiDisponibili();
         
         return campiDisp;
         
     }
     
    public Boolean SelezionaCampetti(List<String> IdCampetti){
         List<Campetto> CampettiSelezionati= PersistentManager.getInstance().getCampettiPerID(IdCampetti);
         competizionecorrente.setcampetto(CampettiSelezionati);
         
         return true;
         
     }
    
    public Boolean impostaParametriCalendario(List<GiorniSettimanali> giorni, int ppg, Date dataInizio, CriteriCalendario criterio)
    {
        
        GenerazioneCalendarioStrategy algoritmoScelto = FootballRoyaleBusinessFactory.getStrategy(criterio);
        competizionecorrente.setStrategia(algoritmoScelto);
        competizionecorrente.generaCalendario(giorni,ppg,dataInizio);
  
        return true;
    }
    
    public Boolean ConfermaCampionato()  {
         EntityManager.getInstance().save(competizionecorrente);
         return true;
    }
     
     








}

