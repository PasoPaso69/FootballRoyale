/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Application;

import com.mycompany.footballroyale.TechnicalService.PersistentManager;
import com.mycompany.footballroyale.domain.Classifica;
import com.mycompany.footballroyale.domain.Competizione;
import com.mycompany.footballroyale.domain.Enum.CriteriCalendario;
import com.mycompany.footballroyale.domain.Enum.CriterioClassifica;
import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;
import com.mycompany.footballroyale.domain.Giocatore;
import com.mycompany.footballroyale.domain.Partita;
import com.mycompany.footballroyale.domain.Prenotazione;
import com.mycompany.footballroyale.domain.Squadra;
import com.mycompany.footballroyale.domain.StrategieCalendario.GenerazioneCalendarioStrategy;
import com.mycompany.footballroyale.domain.Strategieclassifica.CriterioOrdinamentoStrategy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 39327
 */
public class GestoreClassifica {
     private Classifica classifica;
     private String targetSelezionato;
     
     public List<Competizione> AvviaVisualizzazioneStatistiche(){
         Classifica nuovaClassifica = new Classifica();
         this.classifica=nuovaClassifica;
         List<Competizione> listaCompetizioni = PersistentManager.getInstance().getTutteLeCompetizioni();
         return listaCompetizioni;
     
     }
     
     public Boolean SelezionaCompetizione (Competizione C){
         classifica.setCompetizione(C);
         return true;
     }
     public Boolean SelezionaTarget(String TipoTarget){
             
         targetSelezionato=TipoTarget;
         return true;
     }
     
         public Map<?,Integer> SelezionaCriterio(CriterioClassifica criterio)
    {
        CriterioOrdinamentoStrategy algoritmoScelto = CriterioStrategyFactory.getStrategy(criterio); 
        List<?> targetEstratti = null;
        switch (targetSelezionato.toLowerCase()) {
        
        case "squadra":
            // Information Expert: chiedo direttamente alla competizione le sue squadre
           targetEstratti = classifica.getCompetizione().getSquadrePartecipanti(); 
            break;
            
        case "giocatore":
            // Navigo le associazioni: Competizione -> Squadra -> Giocatore
            List<Giocatore> tuttiGiocatori = new ArrayList<>();
            
            // Per ogni squadra della competizione, estraggo i giocatori
            for (Squadra sq : classifica.getCompetizione().getSquadrePartecipanti()) {
                tuttiGiocatori.addAll(sq.getGiocatori());
            }
            targetEstratti = tuttiGiocatori;
            break;
            
        default:
            throw new IllegalArgumentException("Target non valido");
    }
        classifica.setCriterio(algoritmoScelto);
        Map<?,Integer> ClassificaFinale= classifica.Ordina(targetEstratti);
  
        return ClassificaFinale;
    }
}
