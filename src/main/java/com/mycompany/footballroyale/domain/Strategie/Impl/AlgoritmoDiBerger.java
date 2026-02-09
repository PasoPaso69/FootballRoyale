/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain.Strategie.Impl;
import com.mycompany.footballroyale.domain.*;
import com.mycompany.footballroyale.domain.Enum.*;


import com.mycompany.footballroyale.domain.Strategie.GenerazioneCalendarioStrategy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 39327
 */
public class AlgoritmoDiBerger implements GenerazioneCalendarioStrategy {
    
    @Override
    public List<Partita> generaCalendario(List<Squadra> squadre, List<Campetto> campetti, Date dataInizio, List<GiorniSettimanali>giorni, int partitePerGiorno,List<Prenotazione> prenotazioni ) {
        List<Partita> calendarioGenerato = new ArrayList<>();

        System.out.println("Generazione calendario con Algoritmo di Belger...");
        
        // 1. Validazione input
        if (squadre == null || squadre.size() < 2 || campetti == null || campetti.isEmpty() || giorni == null || giorni.isEmpty()) {
            return calendarioGenerato;
        }
        
        // 2. Setup Squadre (Se dispari, aggiungiamo il "Riposo")
        List<Squadra> lista = new ArrayList<>(squadre);
        if (lista.size() % 2 != 0) {
            lista.add(null); 
        }

        int n = lista.size(); //numero di squadre
        int giornate = n - 1;  //numero di giornate totali
        int partitePerGiornata = n / 2; //numero di partite per terminare un turno di giornata
        
        
        // 3. Setup Temporale e Risorse
        /*
        L'algoritmo imposta la data di partenza (dataInizio).
        Il primo controllo: Se l'utente ha detto che si gioca solo di Lunedì e Giovedì,
        ma la data di inizio è un Martedì, il metodo avanzaAlProssimoGiornoValido
        sposta subito il calendario al primo Giovedì disponibile.
        */
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataInizio);
        
        // Se il giorno di inizio non è tra quelli scelti, ci spostiamo al primo giorno valido
        if (!isGiornoValido(cal, giorni)) {
            avanzaAlProssimoGiornoValido(cal, giorni);
        }

        int conteggioPartiteOggi = 0;
        int indiceCampetto = 0;
        
        
        // 4. Algoritmo di Berger (Rotazione)
        /*
        Qui entriamo nel cuore dell'algoritmo. Se hai 6 squadre,
        l'algoritmo sa che deve creare 5 turni totali affinché tutti
        incontrino tutti. Ogni giro di questo ciclo rappresenta una 
        "settimana" o un "turno" teorico del torneo.
        */
        for (int i = 0; i < giornate; i++) {
            
            /*
            In ogni giornata, l'algoritmo calcola chi deve sfidare chi.
            Usa una formula matematica (la rotazione oraria) per accoppiare 
            le squadre. Una squadra rimane fissa, mentre tutte le altre
            ruotano di posizione ad ogni giornata
            */
            for (int j = 0; j < partitePerGiornata; j++) {
                
                // Calcolo indici per la rotazione, tutta roba matematica
                int casa = (i + j) % (n - 1);
                int ospite = (n - 1 - j + i) % (n - 1);
                
                if (j == 0) {
                    ospite = n - 1;
                }

                Squadra sCasa = lista.get(casa);
                Squadra sOspite = lista.get(ospite);

                // Se non è un turno di riposo (squadra null)
                if (sCasa != null && sOspite != null) {
                    
                    // Controllo limite partite giornaliere (il tuo input partitePerGiorno)
                    if (conteggioPartiteOggi >= partitePerGiorno) {
                        avanzaAlProssimoGiornoValido(cal, giorni);
                        cal.set(cal.HOUR_OF_DAY, 8); 
                        cal.set(cal.MINUTE, 0);
                        conteggioPartiteOggi = 0;
                        indiceCampetto = 0;
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
            if (cal.get(Calendar.HOUR_OF_DAY) >= 22) {
                
                // Limite raggiunto: passiamo al prossimo giorno valido
                avanzaAlProssimoGiornoValido(cal, giorni);
                
                // RESET ORARIO: Impostiamo l'ora di inizio standard (es. le 15:00 o 18:00)
                // Altrimenti il nuovo giorno inizierebbe comunque alle 22:00!
                cal.set(Calendar.HOUR_OF_DAY, 8); 
                cal.set(Calendar.MINUTE, 0);
                conteggioPartiteOggi = 0; // Reset contatore giornaliero
                
            } else {
                // C'è ancora tempo oggi: slittiamo di 90 minuti e riproviamo il 'while'
                cal.add(Calendar.MINUTE, 90);
            }
        }
    }
    
             

                   // Campetto campettoScelto = campetti.get(indiceCampetto % campetti.size());
                   Date momentoInizio = new Date(cal.getTimeInMillis());
                   java.sql.Time oraInizio = new java.sql.Time(momentoInizio.getTime());
                   
                   // 2. Calcolo ora di fine (+1 ora)
                    long unOraInMs = 60 * 60 * 1000;
                    java.sql.Time oraFine = new java.sql.Time(momentoInizio.getTime() + unOraInMs);
                   

                   
                  
                    // Creazione della Partita
                    Partita p = new Partita();
                    p.setSquadraCasa(sCasa);
                    p.setSquadraOspite(sOspite);
                    p.setData(cal.getTime());
                    p.setOrario(oraInizio);
                    
                   Prenotazione pnuovo= new Prenotazione();
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
                    indiceCampetto++;
                }
            }
            // Dopo ogni giornata di Berger, passiamo al prossimo giorno utile
            avanzaAlProssimoGiornoValido(cal, giorni);
            cal.set(Calendar.HOUR_OF_DAY, 8); 
            cal.set(Calendar.MINUTE, 0);
            conteggioPartiteOggi = 0;
            indiceCampetto = 0;
        }
        
        
        
        
        return calendarioGenerato;
    }
    
    private void avanzaAlProssimoGiornoValido(Calendar cal, List<GiorniSettimanali> giorni) {
    do {
        cal.add(Calendar.DAY_OF_MONTH, 1);
    } while (!isGiornoValido(cal, giorni));
}

private boolean isGiornoValido(Calendar cal, List<GiorniSettimanali> giorni) {
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    for (GiorniSettimanali g : giorni) {
        // Assicurati che la tua Enum GiorniSettimanali abbia un metodo per il valore numerico
        // Domenica=1, Lunedì=2, ..., Sabato=7
        if (mappaGiorno(g) == dayOfWeek) return true;
    }
    return false;
}
    private int mappaGiorno(GiorniSettimanali g) {
    return switch (g) {
        case Lunedi    -> Calendar.MONDAY;    // Vale 2
        case Martedi   -> Calendar.TUESDAY;   // Vale 3
        case Mercoledi -> Calendar.WEDNESDAY; // Vale 4
        case Giovedi   -> Calendar.THURSDAY;  // Vale 5
        case Venerdi   -> Calendar.FRIDAY;    // Vale 6
        case Sabato    -> Calendar.SATURDAY;  // Vale 7
        case Domenica  -> Calendar.SUNDAY;    // Vale 1
    };

    }
       
    private boolean isLiberoInMemoria(Campetto c, Date inizioPartita, List<Prenotazione> prenotazioni) {
    // Usiamo Calendar per azzerare ore/minuti e confrontare solo il giorno
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(inizioPartita);
    
    for (Prenotazione pre : prenotazioni) {
        if (pre.getCampetto().getId().equals(c.getId())) {
            
           Calendar cal2 = Calendar.getInstance();
            cal2.setTime(pre.getData());

            // 1. Controlliamo se è lo stesso giorno
            if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                
                // 2. Se il giorno è lo stesso, controlliamo gli orari (java.sql.Time)
                long inPartita = cal1.get(Calendar.HOUR_OF_DAY) * 60 + cal1.get(Calendar.MINUTE);
                long finePartita = inPartita + 90; // La tua durata di 90 min

                // Trasformiamo gli orari della prenotazione in minuti totali dall'inizio del giorno
                Calendar calInizioPre = Calendar.getInstance();
                calInizioPre.setTime(pre.getOrarioInizio());
                long inPre = calInizioPre.get(Calendar.HOUR_OF_DAY) * 60 + calInizioPre.get(java.util.Calendar.MINUTE);
                
                Calendar calFinePre =  Calendar.getInstance();
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
