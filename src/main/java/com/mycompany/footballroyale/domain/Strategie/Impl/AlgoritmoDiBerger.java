/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain.Strategie.Impl;
import com.mycompany.footballroyale.domain.*;
import com.mycompany.footballroyale.domain.Enum.*;


import com.mycompany.footballroyale.domain.Strategie.GenerazioneCalendarioStrategy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 39327
 */
public class AlgoritmoDiBerger implements GenerazioneCalendarioStrategy {
    
    @Override
    public List<Partita> generaCalendario(List<Squadra> squadre, List<Campetto> campetti, Date dataInizio, List<GiorniSettimanali>giorni, int partitePerGiorno) {
        List<Partita> calendarioGenerato = new ArrayList<>();
        
        // LOGICA DI BELGER:SI
        // Qui scriverai i cicli per accoppiare le squadre (Round Robin)
        // Per ogni accoppiamento, creerai un oggetto Partita:
        // Partita p = new Partita(data, squadraCasa, squadraOspite, campettoScelto);
        // calendarioGenerato.add(p);
        
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
        java.util.Calendar cal = java.util.Calendar.getInstance();
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
                        conteggioPartiteOggi = 0;
                        indiceCampetto = 0;
                    }

                    // Assegnazione campetto
                    /*
                    Se hai 3 campetti, la prima partita va sul Campo 1, 
                    la seconda sul Campo 2, ecc. Quando finiscono i campi, 
                    ricomincia dal primo (indiceCampetto % campetti.size()).
                    */
                    Campetto campettoScelto = campetti.get(indiceCampetto % campetti.size());
                    
                    // Creazione della Partita
                    Partita p = new Partita();
                    p.setSquadraCasa(sCasa);
                    p.setSquadraOspite(sOspite);
                    p.setData(cal.getTime());
                    //qui manca la relazione
                   // p.setCampetto(campettoScelto);
                    
                    calendarioGenerato.add(p);

                    conteggioPartiteOggi++;
                    indiceCampetto++;
                }
            }
            // Dopo ogni giornata di Berger, passiamo al prossimo giorno utile
            avanzaAlProssimoGiornoValido(cal, giorni);
            conteggioPartiteOggi = 0;
            indiceCampetto = 0;
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
    // Se la tua Enum non ha valori, usiamo uno switch rapido
    return switch (g) {
        
        case Lunedi -> 1;
        case Martedi -> 2;
        case Mercoledi -> 3;
        case Giovedi -> 4;
        case Venerdi -> 5;
        default -> -1;
    };
    }
       
    
}
