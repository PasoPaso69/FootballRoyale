/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain.Strategie.Impl;

import com.mycompany.footballroyale.domain.Campetto;
import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;
import static com.mycompany.footballroyale.domain.Enum.GiorniSettimanali.Domenica;
import static com.mycompany.footballroyale.domain.Enum.GiorniSettimanali.Giovedi;
import static com.mycompany.footballroyale.domain.Enum.GiorniSettimanali.Lunedi;
import static com.mycompany.footballroyale.domain.Enum.GiorniSettimanali.Martedi;
import static com.mycompany.footballroyale.domain.Enum.GiorniSettimanali.Mercoledi;
import static com.mycompany.footballroyale.domain.Enum.GiorniSettimanali.Sabato;
import static com.mycompany.footballroyale.domain.Enum.GiorniSettimanali.Venerdi;
import com.mycompany.footballroyale.domain.Enum.statoPrenotazione;
import com.mycompany.footballroyale.domain.Partita;
import com.mycompany.footballroyale.domain.Prenotazione;
import com.mycompany.footballroyale.domain.Squadra;
import com.mycompany.footballroyale.domain.Strategie.GenerazioneCalendarioStrategy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 39327
 */
public class SistemaSvizzero implements GenerazioneCalendarioStrategy {
    
@Override
    public List<Partita> generaCalendario(List<Squadra> squadre, List<Campetto> campetti, Date dataInizio, List<GiorniSettimanali> giorni, int ppg,List<Prenotazione> prenotazioni) {
        List<Partita> calendarioGenerato = new ArrayList<>();
       
        System.out.println("Generazione calendario con Sistema Svizzero ...");
        
        // 1. Ordinamento squadre per Overall (Decrescente: dal più forte al meno forte)
        List<Squadra> listaOrdinata = new ArrayList<>(squadre);
        listaOrdinata.sort((s1, s2) -> Double.compare(s2.getOverall(), s1.getOverall()));
        
        System.out.println("------ RANKING INIZIALE SQUADRE ------");
        for (int i = 0; i < listaOrdinata.size(); i++) {
        Squadra s = listaOrdinata.get(i);
    // Usiamo String.format per vedere solo 2 decimali
         System.out.println((i + 1) + "° Posto: " + s.getNome() + " - Overall: " + String.format("%.2f", s.getOverall()));
    }
        System.out.println("--------------------------------------");
        
        
        // Gestione riposo se dispari
        if (listaOrdinata.size() % 2 != 0) {
            listaOrdinata.add(null);
        }
        
        int n = listaOrdinata.size();
        int giornate = n - 1;
        int partitePerGiornata = n / 2;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataInizio);
        cal.set(java.util.Calendar.HOUR_OF_DAY, 8); 
        cal.set(java.util.Calendar.MINUTE, 0);
        if (!isGiornoValido(cal, giorni)) avanzaAlProssimoGiornoValido(cal, giorni);
        
        int conteggioPartiteOggi = 0;
        
        // 2. Logica di Accoppiamento "Svizzero-Berger"
        // Usiamo la rotazione di Berger sulla lista ORDINATA per forza.
        // Questo garantisce che nella prima giornata 1-2, 3-4, ecc. (vicini di ranking)
        for (int i = 0; i < giornate; i++) {
            for (int j = 0; j < partitePerGiornata; j++) {
                
        int casaIdx = (j * 2 + i) % n;
        int ospiteIdx = (j * 2 + 1 + i) % n;

        // Invece della rotazione fissa dell'ultimo, usiamo uno shift fluido
        Squadra sCasa = listaOrdinata.get(casaIdx);
        Squadra sOspite = listaOrdinata.get(ospiteIdx);



                // Se non è un turno di riposo (squadra null)
                if (sCasa != null && sOspite != null) {
                    
                    // Controllo limite partite giornaliere (il tuo input partitePerGiorno)
                    if (conteggioPartiteOggi >= ppg) {
                        avanzaAlProssimoGiornoValido(cal, giorni);
                        cal.set(Calendar.HOUR_OF_DAY, 8); 
                        cal.set(Calendar.MINUTE, 0);
                        conteggioPartiteOggi = 0;
                        
                    }
                    
                    // Assegnazione campetto
                    Campetto campettoScelto = null;

    // Entriamo in un ciclo che cerca uno slot finché non lo trova
    while (campettoScelto == null) {
        
        //  Proviamo a vedere se uno dei campetti è libero all'orario attuale
        for (Campetto c : campetti) {
            if (isLiberoInMemoria(c, cal.getTime(), prenotazioni)) {
                campettoScelto = c;
                break; // Trovato! Usciamo dal for
            }
        }

        // 2. Se dopo aver controllato tutti i campetti non abbiamo trovato nulla...
        if (campettoScelto == null) {
            
            // Controlliamo se siamo arrivati al limite delle 22:00
            // (Usiamo >= 22 perché se la partita inizia alle 22 finirebbe troppo tardi)
            if (cal.get(java.util.Calendar.HOUR_OF_DAY) >= 22) {
                
                // Limite raggiunto: passiamo al prossimo giorno valido
                avanzaAlProssimoGiornoValido(cal, giorni);
                
                // RESET ORARIO: Impostiamo l'ora di inizio standard (es. le 15:00 o 18:00)
                // Altrimenti il nuovo giorno inizierebbe comunque alle 22:00!
                cal.set(java.util.Calendar.HOUR_OF_DAY, 8); 
                cal.set(java.util.Calendar.MINUTE, 0);
                conteggioPartiteOggi = 0; // Reset contatore giornaliero
                
            } else {
                // C'è ancora tempo oggi: slittiamo di 90 minuti e riproviamo il 'while'
                cal.add(java.util.Calendar.MINUTE, 90);
            }
        }
    }
                    // --- CREAZIONE DATI ---
                    Date momentoInizio = new Date(cal.getTimeInMillis());
                    java.sql.Time oraInizio = new java.sql.Time(momentoInizio.getTime());
                    java.sql.Time oraFine = new java.sql.Time(momentoInizio.getTime() + (60 * 60 * 1000));

                    Partita p = new Partita();
                    p.setSquadraCasa(sCasa);
                    p.setSquadraOspite(sOspite);
                    p.setData(momentoInizio);
                    p.setOrario(oraInizio);

                    Prenotazione pnuovo = new Prenotazione();
                    pnuovo.setData(momentoInizio);
                    pnuovo.setCampetto(campettoScelto);
                    pnuovo.setStato(statoPrenotazione.COMPLETATA);
                    pnuovo.setOrarioInizio(oraInizio);
                    pnuovo.setOrarioFine(oraFine);
                    pnuovo.setPartita(p);

                    p.setPrenotazione(pnuovo);
                    prenotazioni.add(pnuovo);
                    calendarioGenerato.add(p);
                    conteggioPartiteOggi++;
                }
            }
            // Fine giornata: reset temporale per la prossima giornata di campionato
            avanzaAlProssimoGiornoValido(cal, giorni);
            cal.set(Calendar.HOUR_OF_DAY, 8);
            cal.set(Calendar.MINUTE, 0);
            conteggioPartiteOggi = 0;
        }
        return calendarioGenerato;
    }        
    
       private void avanzaAlProssimoGiornoValido(java.util.Calendar cal, List<GiorniSettimanali> giorni) {
    do {
        cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
    } while (!isGiornoValido(cal, giorni));
}

private boolean isGiornoValido(java.util.Calendar cal, List<GiorniSettimanali> giorni) {
    int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
    for (GiorniSettimanali g : giorni) {
        // Assicurati che la tua Enum GiorniSettimanali abbia un metodo per il valore numerico
        // Domenica=1, Lunedì=2, ..., Sabato=7
        if (mappaGiorno(g) == dayOfWeek) return true;
    }
    return false;
}
    private int mappaGiorno(GiorniSettimanali g) {
    return switch (g) {
        case Lunedi    -> java.util.Calendar.MONDAY;    // Vale 2
        case Martedi   -> java.util.Calendar.TUESDAY;   // Vale 3
        case Mercoledi -> java.util.Calendar.WEDNESDAY; // Vale 4
        case Giovedi   -> java.util.Calendar.THURSDAY;  // Vale 5
        case Venerdi   -> java.util.Calendar.FRIDAY;    // Vale 6
        case Sabato    -> java.util.Calendar.SATURDAY;  // Vale 7
        case Domenica  -> java.util.Calendar.SUNDAY;    // Vale 1
    };

    }
       
    private boolean isLiberoInMemoria(Campetto c, Date inizioPartita, List<Prenotazione> prenotazioni) {
    // Usiamo Calendar per azzerare ore/minuti e confrontare solo il giorno
    java.util.Calendar cal1 = java.util.Calendar.getInstance();
    cal1.setTime(inizioPartita);
    
    for (Prenotazione pre : prenotazioni) {
        if (pre.getCampetto().getId().equals(c.getId())) {
            
            java.util.Calendar cal2 = java.util.Calendar.getInstance();
            cal2.setTime(pre.getData());

            // 1. Controlliamo se è lo stesso giorno
            if (cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR) &&
                cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR)) {
                
                // 2. Se il giorno è lo stesso, controlliamo gli orari (java.sql.Time)
                long inPartita = cal1.get(java.util.Calendar.HOUR_OF_DAY) * 60 + cal1.get(java.util.Calendar.MINUTE);
                long finePartita = inPartita + 90; // La tua durata di 90 min

                // Trasformiamo gli orari della prenotazione in minuti totali dall'inizio del giorno
                java.util.Calendar calInizioPre = java.util.Calendar.getInstance();
                calInizioPre.setTime(pre.getOrarioInizio());
                long inPre = calInizioPre.get(java.util.Calendar.HOUR_OF_DAY) * 60 + calInizioPre.get(java.util.Calendar.MINUTE);
                
                java.util.Calendar calFinePre = java.util.Calendar.getInstance();
                calFinePre.setTime(pre.getOrarioFine());
                long fPre = calFinePre.get(java.util.Calendar.HOUR_OF_DAY) * 60 + calFinePre.get(java.util.Calendar.MINUTE);

                // Sovrapposizione: (Inizio1 < Fine2) && (Fine1 > Inizio2)
                if (inPartita < fPre && finePartita > inPre) {
                    return false;
                }
            }
        }
    }
    return true;
}
        }

    

