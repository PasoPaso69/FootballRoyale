/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Application;

import com.mycompany.footballroyale.domain.Enum.CriteriCalendario;
import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;
import com.mycompany.footballroyale.domain.Enum.StatoCompetizione;
import com.mycompany.footballroyale.domain.Enum.TipoCompetizione;
import com.mycompany.footballroyale.domain.Partita;
import com.mycompany.footballroyale.UI.Creation.CreazioneCampionatoView;
import com.mycompany.footballroyale.UI.Creation.CreazioneSquadraView;
import com.mycompany.footballroyale.UI.FootballRoyaleView;
import com.mycompany.footballroyale.UI.Statistiche.StatisticheFrame;
import com.mycompany.footballroyale.domain.Competizione;
import com.mycompany.footballroyale.domain.Enum.CriterioClassifica;
import com.mycompany.footballroyale.domain.Enum.CriterioVisual;
import java.util.ArrayList;
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
    private final GestoreClassifica gestoreclassifica;
    private final StatisticheFrame statisticheframe;
    
    public FootballRoyaleController() {
        this.gestoreTorneo = new GestoreTorneo();
        this.creazioneSquadra = new GestoreCreazioneSquadra();
        this.CampionatoView = new CreazioneCampionatoView();
        this.SquadraView = new CreazioneSquadraView();
        this.footballroyaleview = new FootballRoyaleView();
        this.gestoreclassifica=new GestoreClassifica();
        this.statisticheframe=new StatisticheFrame();
   
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
                avviaGenerazioneStatistiche();
                break;
            case 4:
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
    
    public void avviaGenerazioneStatistiche() {
        
        //try {
          
            // Il gestore prepara la classifica e recupera le competizioni dal DB
            List<Competizione> competizioniDisponibili = gestoreclassifica.AvviaVisualizzazioneStatistiche();
            
            if (competizioniDisponibili == null || competizioniDisponibili.isEmpty()) {
                System.out.println("Nessuna competizione trovata nel sistema.");
                return; // Interrompe il flusso se non c'è nulla da elaborare
            }

            // Il frame mostra le competizioni e l'utente sceglie l'oggetto
            Competizione competizioneScelta = statisticheframe.mostraSchermataSelezioneCompetizione(competizioniDisponibili);
            
            // Il controller passa la scelta al gestore
            gestoreclassifica.SelezionaCompetizione(competizioneScelta);



            // Il frame chiede Squadre o Giocatori
            String targetScelto = statisticheframe.mostraSchermataSelezioneTarget();
            
            // Il controller passa la stringa al gestore
            gestoreclassifica.SelezionaTarget(targetScelto);


            // Il frame mostra i criteri in base al target e restituisce l'Enum
            CriterioClassifica criterioScelto = statisticheframe.mostraSchermataSelezioneCriterio(targetScelto);
            
            // Il gestore calcola la classifica usando la Strategy corretta e restituisce la Mappa
            Map<?, Integer> mappaClassifica = gestoreclassifica.SelezionaCriterio(criterioScelto);



            // Trasformiamo la mappa del dominio in una lista ordinata per la UI
            List<Map.Entry<?, Integer>> listaPerStampa = new ArrayList<>(mappaClassifica.entrySet());

            // Infine, diciamo al frame di stampare. 
            // Qui passo "STAMPA_VIDEO" come esempio, assumendo che tu abbia l'Enum CriterioVisual
            statisticheframe.SelezionaMetodo(CriterioVisual.stampa_a_video, listaPerStampa, criterioScelto);

        }// catch (Exception e) {
            // Una gestione basica degli errori per evitare che un problema blocchi l'app
            //System.out.println("\n⚠️ Si è verificato un errore durante la generazione delle statistiche: " + e.getMessage());
        //}
    //}
}
    
    
    

