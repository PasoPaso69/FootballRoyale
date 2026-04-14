/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.UI.Statistiche;

import com.mycompany.footballroyale.UI.Statistiche.StrategieView.VisualStrategy;
import com.mycompany.footballroyale.UI.Statistiche.VisualFactory;
import com.mycompany.footballroyale.domain.Competizione;
import com.mycompany.footballroyale.domain.Enum.CriterioClassifica;
import com.mycompany.footballroyale.domain.Enum.CriterioVisual;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author 39327
 */
public class StatisticheFrame {
    
            private Scanner scanner = new Scanner(System.in);

    
    public Competizione mostraSchermataSelezioneCompetizione(List<Competizione> competizioni) {
    System.out.println("\n" + "=".repeat(50));
    System.out.println("       SELEZIONE DELLA COMPETIZIONE");
    System.out.println("=".repeat(50));
    
    // Mostriamo la lista numerata
    for (int i = 0; i < competizioni.size(); i++) {
        Competizione c = competizioni.get(i);
        // Assumiamo che Competizione abbia un metodo getNome()
        System.out.printf("[%d] %-30s%n", (i + 1), c.getNome());
    }
    
    System.out.println("-".repeat(50));
    
    int scelta = -1;
    while (scelta < 1 || scelta > competizioni.size()) {
        System.out.print("Digita il numero della competizione: ");
        try {
            // Usiamo scanner.nextLine() e Integer.parseInt per evitare bug del buffer
            scelta = Integer.parseInt(scanner.nextLine());
            
            if (scelta < 1 || scelta > competizioni.size()) {
                System.out.println("️ Scelta non valida. Riprova.");
            }
        } catch (NumberFormatException e) {
            System.out.println("️ Inserisci un numero valido.");
        }
    }

    // Restituiamo l'oggetto Competizione corrispondente alla scelta
    Competizione sceltaFinale = competizioni.get(scelta - 1);
    System.out.println("Hai selezionato: " + sceltaFinale.getNome());
    return sceltaFinale;
}
    
    public String mostraSchermataSelezioneTarget() {
    System.out.println("\n" + "=".repeat(50));
    System.out.println("       COSA VUOI ANALIZZARE?");
    System.out.println("=".repeat(50));
    System.out.println("[1] Squadre");
    System.out.println("[2] Giocatori");
    System.out.println("-".repeat(50));

    int scelta = -1;
    while (scelta != 1 && scelta != 2) {
        System.out.print("Seleziona il target (1 o 2): ");
        try {
            scelta = Integer.parseInt(scanner.nextLine());
            if (scelta != 1 && scelta != 2) {
                System.out.println("️ Scelta non valida. Inserisci 1 per Squadre o 2 per Giocatori.");
            }
        } catch (NumberFormatException e) {
            System.out.println("️ Errore: inserisci un numero (1 o 2).");
        }
    }

    // Ritorniamo la stringa che il Gestore userà nel suo switch-case
    String target = (scelta == 1) ? "Squadra" : "Giocatore";
    System.out.println(" Target selezionato: " + target);
    return target;
}
    
    public CriterioClassifica mostraSchermataSelezioneCriterio(String target) {
    System.out.println("\n" + "=".repeat(50));
    System.out.printf("       CRITERI DISPONIBILI (%s)%n", target.toUpperCase());
    System.out.println("=".repeat(50));

    CriterioClassifica criterioScelto = null;

    if (target.equalsIgnoreCase("Squadra")) {
        System.out.println("[1] Punti Classifica (Punti_Squadre)");
        System.out.println("[2] Differenza Reti (DifferenzaReti_Squadre)");
        
        int scelta = chiediNumero(1, 2);
        criterioScelto = (scelta == 1) ? CriterioClassifica.Punti_Squadre : CriterioClassifica.DifferenzaReti_Squadre;
    } else {
        System.out.println("[1] Classifica Marcatori (Goal_Giocatori)");
        System.out.println("[2] Classifica Disciplina (Cartellini_Giocatori)");
        
        int scelta = chiediNumero(1, 2);
        criterioScelto = (scelta == 1) ? CriterioClassifica.Goal_Giocatori : CriterioClassifica.Cartellini_Giocatori;
    }

    System.out.println("Criterio selezionato: " + criterioScelto.name());
    return criterioScelto;
}


    public Boolean SelezionaMetodo(CriterioVisual criterio,List<Map.Entry<?,Integer>> classificaOrdinata, CriterioClassifica Criterio){
        
       
        VisualStrategy criterioScelto = VisualFactory.getStrategy(criterio);
        criterioScelto.stampa(classificaOrdinata, Criterio);
    return true;}
    
   // Utility interna per validare l'input numerico
private int chiediNumero(int min, int max) {
    int n = -1;
    while (n < min || n > max) {
        System.out.print("Scegli un'opzione: ");
        try {
            n = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("️ Inserisci un numero valido.");
        }
    }
    return n;
}
}
