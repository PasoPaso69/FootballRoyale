/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.view;

import com.mycompany.footballroyale.domain.Enum.CriteriCalendario;
import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;
import com.mycompany.footballroyale.domain.Enum.TipoCompetizione;
import com.mycompany.footballroyale.domain.Partita;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 *
 * @author 39327
 */
public class CreazioneCampionatoView {
    
        private Scanner scanner = new Scanner(System.in);
        
   public Object[] chiediDatiInizialiCampionato() {
    System.out.println("\n--- INSERIMENTO DATI NUOVA COMPETIZIONE ---");
    
    // 1. Acquisizione del Nome
    System.out.print("Inserisci il Nome del Campionato (es. Champions League): ");
    String nome = scanner.nextLine();

    // 2. Definizione dei formati disponibili
    TipoCompetizione[] formati = {
        TipoCompetizione.CAMPIONATO, 
        TipoCompetizione.ELIMINAZIONE_DIRETTA 
    };
    
    System.out.println("\n--- SELEZIONE FORMATO COMPETIZIONE ---");
    for (int i = 0; i < formati.length; i++) {
        System.out.println(" [" + (i + 1) + "] " + formati[i]);
    }

    // 3. Selezione del Formato con validazione
    int scelta = -1;
    while (scelta < 1 || scelta > formati.length) {
        System.out.print("Seleziona il numero del formato: ");
        if (scanner.hasNextInt()) {
            scelta = scanner.nextInt();
            if (scelta < 1 || scelta > formati.length) {
                System.out.println(" [!] Scelta non valida.");
            }
        } else {
            System.out.println(" [!] Errore: Inserisci un numero.");
            scanner.next(); // Pulisce l'input errato
        }
    }
    
    scanner.nextLine(); // Pulisce il buffer dopo nextInt()
    TipoCompetizione formatoScelto = formati[scelta - 1];
    
    System.out.println("\n>> Riepilogo: " + nome + " [" + formatoScelto + "]");
    
    // Restituiamo entrambi i dati in un array di Object
    return new Object[]{nome, formatoScelto};
}
   
   public Boolean mostraSquadreDisponibili(Map<Long, String> squadre) {
    System.out.println("\n" + "=".repeat(65));
    System.out.println("          SCHEDE SQUADRE DISPONIBILI          ");
    System.out.println("=".repeat(65));

    if (squadre == null || squadre.isEmpty()) {
        System.out.println(" [!] Nessuna Squadra disponibile al momento.");
        System.out.println("\nPremi INVIO per tornare al menu principale...");
        
        // Pulizia buffer e attesa tasto invio
        scanner.nextLine();
        return false;
    } else {
        squadre.forEach((id, info) -> {
            // Stampiamo l'ID in evidenza
            System.out.println(" >> ID SELEZIONE: [" + id + "]");
            
            System.out.println("    DETTAGLI: " + info);
            
            System.out.println("-".repeat(65));
        });
    }
     System.out.println("=================================================================\n");
     return true;
}
   
   public List<Long> selezionaIdSquadre() {
    List<Long> ids = new ArrayList<>();
    boolean valid = false;

    do {
        System.out.println("\n[!] ATTENZIONE: Devi selezionare almeno 10 squadre.");
        System.out.print("Inserisci gli ID separati da virgola (es: 1, 2, 10, 15, 20): ");
        
        String input = scanner.nextLine();

        try {
            ids = Arrays.stream(input.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(Long::valueOf)
                        .toList();

            if (ids.size() >= 10) {
                valid = true;
            } else {
                System.out.println(">> Errore: Hai selezionato solo " + ids.size() + " squadre.");
            }
        } catch (NumberFormatException e) {
            System.out.println(">> Errore: Hai inserito un ID non valido (usa solo numeri).");
        }
        
    } while (!valid);

    return ids;
}
      public Boolean mostraCampettiDisponibili(Map<Long, String> Campetti) {
    System.out.println("\n" + "=".repeat(65));
    System.out.println("          CAMPETTI DISPONIBILI         ");
    System.out.println("=".repeat(65));

    if (Campetti == null || Campetti.isEmpty()) {
        System.out.println(" [!] Nessun Campetto disponibile al momento.");
        System.out.println("\nPremi INVIO per tornare al menu principale...");
        
        // Pulizia buffer e attesa tasto invio
        scanner.nextLine();
        return false;
    } else {
        Campetti.forEach((id, info) -> {
            // Stampiamo l'ID in evidenza
            System.out.println(" >> ID SELEZIONE: [" + id + "]");
            
            System.out.println("    NOME: " + info);
            
            System.out.println("-".repeat(65));
        });
    }
     System.out.println("=================================================================\n");
     return true;
}
      
      public List<Long> selezionaIdCampetti() {
    List<Long> ids = new ArrayList<>();
    boolean valid = false;

    do {
        System.out.println("\n[!] ATTENZIONE: Devi selezionare almeno 1 squadre.");
        System.out.print("Inserisci gli ID separati da virgola (es: 1, 2, 10, 15, 20): ");
        
        String input = scanner.nextLine();

        try {
            ids = Arrays.stream(input.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(Long::valueOf)
                        .toList();

            if (ids.size() >= 1) {
                valid = true;
            } else {
                System.out.println(">> Errore: Hai selezionato solo " + ids.size() + " campetto.");
            }
        } catch (NumberFormatException e) {
            System.out.println(">> Errore: Hai inserito un ID non valido (usa solo numeri).");
        }
        
    } while (!valid);

    return ids;
}
      public Object[] chiediParametriCalendario() {
    System.out.println("\n" + "=".repeat(45));
    System.out.println("   CONFIGURAZIONE CALENDARIO COMPETIZIONE    ");
    System.out.println("=".repeat(45));

    // 1. Inserimento Data di Inizio
    Date dataInizio = null;
    while (dataInizio == null) {
        System.out.print(" -> Inserisci data inizio (gg/mm/aaaa): ");
        String dataStr = scanner.nextLine();
        try {
            dataInizio = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
            if (dataInizio.before(new Date())) {
                System.out.println(" [!] Avviso: La data è nel passato, sicuro?");
            }
        } catch (Exception e) {
            System.out.println(" [!] Formato data non valido. Riprova.");
        }
    }

    // 2. Partite Per Giorno (ppg)
    int ppg = -1;
    while (ppg < 1 || ppg > 10) {
        System.out.print(" -> Quante partite al giorno si giocano? (1-10): ");
        if (scanner.hasNextInt()) {
            ppg = scanner.nextInt();
        } else {
            scanner.next();
        }
        scanner.nextLine();
    }

    // 3. Selezione Criterio (Enum CriteriCalendario)
    System.out.println("\n--- Seleziona Criterio Generazione ---");
    CriteriCalendario[] criteri = CriteriCalendario.values();
    for (int i = 0; i < criteri.length; i++) {
        System.out.println(" [" + (i + 1) + "] " + criteri[i]);
    }
    int sceltaC = scanner.nextInt();
    scanner.nextLine();
    CriteriCalendario criterioScelto = criteri[sceltaC - 1];

    // 4. Selezione Giorni Settimanali (Multipla)
    List<GiorniSettimanali> giorniScelti = new ArrayList<>();
    System.out.println("\n--- Seleziona i giorni in cui si gioca (es: 1,3,5) ---");
    GiorniSettimanali[] tuttiGiorni = GiorniSettimanali.values();
    for (int i = 0; i < tuttiGiorni.length; i++) {
        System.out.println(" [" + (i + 1) + "] " + tuttiGiorni[i]);
    }
    System.out.print(" -> Inserisci i numeri separati da virgola: ");
    String inputGiorni = scanner.nextLine();
    String[] parti = inputGiorni.split(",");
    for (String p : parti) {
        int index = Integer.parseInt(p.trim()) - 1;
        if (index >= 0 && index < tuttiGiorni.length) {
            giorniScelti.add(tuttiGiorni[index]);
        }
    }

    // Restituiamo l'impacchettamento per il controller
    return new Object[]{giorniScelti, ppg, dataInizio, criterioScelto};
}


public boolean mostraCalendarioEConferma(List<Partita> calendario) {
    if (calendario == null || calendario.isEmpty()) {
        System.out.println("\n [!] Errore: Il calendario generato è vuoto.");
        return false;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    System.out.println("\n" + "=".repeat(60));
    System.out.println("            BOZZA CALENDARIO GENERATO             ");
    System.out.println("=".repeat(60));
    System.out.printf("%-12s | %-8s | %-32s\n", "DATA", "ORA", "MATCH");
    System.out.println("-".repeat(60));

    for (Partita p : calendario) {
        String dataStr = sdf.format(p.getData());
        String oraStr = (p.getOrario() != null) ? p.getOrario().toString().substring(0, 5) : "--:--";
        String matchStr = p.getSquadraCasa().getNome() + " vs " + p.getSquadraOspite().getNome();

        System.out.printf("%-12s | %-8s | %-32s\n", dataStr, oraStr, matchStr);
    }

    System.out.println("=".repeat(60));
    System.out.println("Totale partite generate: " + calendario.size());

    // Parte di conferma
    System.out.println("\n[?] Desideri salvare questo calendario e rendere ufficiale la competizione?");
    System.out.println(" [1] SI, salva tutto");
    System.out.println(" [0] NO, scarta e torna al menu");
    
    int scelta = -1;
    while (scelta != 0 && scelta != 1) {
        System.out.print(" -> Scelta: ");
        if (scanner.hasNextInt()) {
            scelta = scanner.nextInt();
        } else {
            scanner.next();
        }
        scanner.nextLine(); // Pulisce il buffer
    }

    if (scelta == 1) {
        System.out.println("\n >>> SALVATAGGIO IN CORSO... COMPETIZIONE ATTIVATA! <<<");
        return true;
    } else {
        System.out.println("\n >>> OPERAZIONE ANNULLATA. Il calendario è stato eliminato. <<<");
        return false;
    }
}
}
