/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author 39327
 */
public class CreazioneSquadraView {
    
    private Scanner scanner = new Scanner(System.in);

    public String[] chiediDatiInizialiSquadra() {
        System.out.println("\n--- INSERIMENTO DATI SQUADRA ---");
        
        System.out.print("Inserisci il Nome della Squadra: ");
        String nome = scanner.nextLine();
        
        System.out.print("Colori Maglia Casa (es. Rosso-Blu): ");
        String magliaCasa = scanner.nextLine();
        
        System.out.print("Colori Maglia Trasferta (es. Bianca): ");
        String magliaTrasferta = scanner.nextLine();
        
        // Restituiamo un array con i tre dati raccolti
        return new String[]{nome, magliaCasa, magliaTrasferta};
    }
    
    public Boolean mostraGiocatoriDisponibili(Map<Long, String> giocatori) {
    System.out.println("\n" + "=".repeat(65));
    System.out.println("          SCHEDE TECNICHE GIOCATORI DISPONIBILI          ");
    System.out.println("=".repeat(65));

    if (giocatori == null || giocatori.isEmpty()) {
        System.out.println(" [!] Nessun giocatore disponibile al momento.");
        System.out.println("\nPremi INVIO per tornare al menu principale...");
        
        // Pulizia buffer e attesa tasto invio
        scanner.nextLine();
        return false;
    } else {
        giocatori.forEach((id, info) -> {
            // Stampiamo l'ID in evidenza
            System.out.println(" >> ID SELEZIONE: [" + id + "]");
            
            // Stampiamo la stringa "cucinata" dal DB
            // La rimpiazziamo leggermente per renderla più ariosa se necessario
            System.out.println("    DETTAGLI: " + info);
            
            System.out.println("-".repeat(65));
        });
    }
     System.out.println("=================================================================\n");
     return true;
}
public List<Long> selezionaIdGiocatori() {
    List<Long> ids = new ArrayList<>();
    boolean valid = false;

    do {
        System.out.println("\n[!] ATTENZIONE: Devi selezionare almeno 5 giocatori.");
        System.out.print("Inserisci gli ID separati da virgola (es: 1, 2, 10, 15, 20): ");
        
        String input = scanner.nextLine();

        try {
            ids = Arrays.stream(input.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(Long::valueOf)
                        .toList();

            if (ids.size() >= 5) {
                valid = true;
            } else {
                System.out.println(">> Errore: Hai selezionato solo " + ids.size() + " giocatori.");
            }
        } catch (NumberFormatException e) {
            System.out.println(">> Errore: Hai inserito un ID non valido (usa solo numeri).");
        }
        
    } while (!valid);

    return ids;
}
    public Boolean ConfermaInserimento(){
    int input=1;
    
      while(input != 1){     
    System.out.println("Scegli Se confermare: ");
    input = scanner.nextInt();

    }
      System.out.println("CREAZIONE SQUADRA CONFERMATA ");
   return true;
    }
}
