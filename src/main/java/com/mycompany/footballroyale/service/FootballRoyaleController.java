/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.service;

import com.mycompany.footballroyale.domain.Enum.CriteriCalendario;
import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;
import com.mycompany.footballroyale.domain.Enum.StatoCompetizione;
import com.mycompany.footballroyale.domain.Enum.TipoCompetizione;
import com.mycompany.footballroyale.domain.Partita;
import com.mycompany.footballroyale.view.CreazioneCampionatoView;
import com.mycompany.footballroyale.view.CreazioneSquadraView;
import com.mycompany.footballroyale.view.FootballRoyaleView;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 39327
 */
public class FootballRoyaleController {
    private final GestoreTorneo gestoreTorneo;
    private final GestoreCreazioneSquadra creazioneSquadra;
    private final CreazioneCampionatoView CampionatoView;
    private final CreazioneSquadraView SquadraView;
    private final FootballRoyaleView footballroyaleview;
    
    public FootballRoyaleController() {
        this.gestoreTorneo = new GestoreTorneo();
        this.creazioneSquadra = new GestoreCreazioneSquadra();
        this.CampionatoView = new CreazioneCampionatoView();
        this.SquadraView = new CreazioneSquadraView();
        this.footballroyaleview = new FootballRoyaleView();
   
    }
    
   public void StartApp() {
    boolean running = true;

    while (running) {
        this.footballroyaleview.showMainMenu();
        int choice = this.footballroyaleview.getUserInputInt();

        switch (choice) {
            case 1:
                avviaModuloCreazioneSquadra();
                break;
            case 2:
                avviaModuloCreazioneCampionato();
                break;
            case 3:
                footballroyaleview.exitGame();
                running = false; // Ferma il ciclo e chiude l'app
                break;
            default:
                footballroyaleview.invalidOption();
                break;
        }
    }
   }
    private void avviaModuloCreazioneSquadra() {
    // 1. Chiedi i dati alla View
    String[] datiIniziali = this.SquadraView.chiediDatiInizialiSquadra();
    
    // 2. Invia i dati al Gestore e ricevi la mappa dei giocatori disponibili
    // datiIniziali[0] = Nome, [1] = Casa, [2] = Trasferta
    Map<Long, String> giocatoriDisp = this.creazioneSquadra.InserisciDati(
        datiIniziali[0], 
        datiIniziali[1], 
        datiIniziali[2]
    );
    
    // 3. Mostra all'utente la lista dei giocatori ricevuta
     Boolean Ackmostragiocatori = this.SquadraView.mostraGiocatoriDisponibili(giocatoriDisp);
         // SE ackMostraGiocatori è false (lista vuota), interrompo tutto e torno al menu
    if (!Ackmostragiocatori) {
        return;
        
    }
    // seleziona giocatori
     List<Long> IdSelezionati = this.SquadraView.selezionaIdGiocatori();
     
     //assegna giocatori
     this.creazioneSquadra.AssegnaGiocatori(IdSelezionati);

     //conferma inserimento
    Boolean ackConferma = this.SquadraView.ConfermaInserimento();
        if (ackConferma) { this.creazioneSquadra.ConfermaInserimento();
        
        }
}
    
    public void avviaModuloCreazioneCampionato(){
        
       Object[] datiIniziali = this.CampionatoView.chiediDatiInizialiCampionato();
       
       String nomeCampionato = (String) datiIniziali[0];
        TipoCompetizione formato = (TipoCompetizione) datiIniziali[1];
       
       Map<Long,String> SquadreDisp = this.gestoreTorneo.selezionaFormato(nomeCampionato, formato);
       
       Boolean ackmostrasquadre = this.CampionatoView.mostraSquadreDisponibili(SquadreDisp);
           if (!ackmostrasquadre) {
        return;
        }
       List<Long> IdSelezionati = this.CampionatoView.selezionaIdSquadre();
        
       Map<Long,String> CampettiDisp = this.gestoreTorneo.SelezionaSquadre(IdSelezionati);
       
       Boolean ackmostracampetti = this.CampionatoView.mostraCampettiDisponibili(CampettiDisp); 
          if (!ackmostracampetti) {
        return;
        }
       List<Long> IdCampettiSelezionati = this.CampionatoView.selezionaIdCampetti();
       
       this.gestoreTorneo.SelezionaCampetti(IdCampettiSelezionati);
       
       Object[] parametricalendario = this.CampionatoView.chiediParametriCalendario();
       
       List<GiorniSettimanali> giorni = (List<GiorniSettimanali>) parametricalendario[0];
       int ppg = (int) parametricalendario[1];
       Date dataInizio = (Date) parametricalendario[2];
       CriteriCalendario criterio = (CriteriCalendario) parametricalendario[3];
       
       List<Partita> calendario = this.gestoreTorneo.impostaParametriCalendario(giorni, ppg, dataInizio, criterio);
       
       Boolean ackConfermaCampionato = this.CampionatoView.mostraCalendarioEConferma(calendario);
       
               if (ackConfermaCampionato) { this.gestoreTorneo.ConfermaCampionato();}
    }
}
    
    
    

