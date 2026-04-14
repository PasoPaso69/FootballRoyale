package com.mycompany.footballroyale.UI.Statistiche.StrategieView.Impl;

import com.mycompany.footballroyale.UI.Statistiche.StrategieView.VisualStrategy;
import com.mycompany.footballroyale.domain.Enum.CriterioClassifica;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 39327
 */
public class StampaVideo implements VisualStrategy {
    
    @Override
    public void stampa(List<Map.Entry<?, Integer>> classificaOrdinata, CriterioClassifica titoloTarget) {
        String border = "==========================================================";
        String separator = "----------------------------------------------------------";

        System.out.println("\n+" + border + "+");
        System.out.printf("| %-56s |%n", "CLASSIFICA: " + titoloTarget.toString().replace("_", " ").toUpperCase());
        System.out.println("+" + border + "+");
        
        // --- INIZIO LOGICA TITOLO DINAMICO ---
        String intestazioneEntita = "ENTITÀ"; // Valore di default
        
        // Controlliamo che la classifica non sia vuota per evitare crash
        if (classificaOrdinata != null && !classificaOrdinata.isEmpty()) {
            Object primoElemento = classificaOrdinata.get(0).getKey();
            if (primoElemento != null) {
                // Prende il nome esatto della classe Java (es. "Squadra" o "Giocatore") e lo fa maiuscolo
                intestazioneEntita = primoElemento.getClass().getSimpleName().toUpperCase();
            }
        }
        // --- FINE LOGICA ---

        // Stampiamo l'intestazione usando la nostra nuova variabile dinamica
        System.out.printf("| %-5s | %-32s | %-12s |%n", "POS", intestazioneEntita, "PUNTI");
        System.out.println("+" + separator + "+");

        int rank = 1;
        for (Map.Entry<?, Integer> entry : classificaOrdinata) {
            Object entita = entry.getKey();
            String nomeRappresentativo = entita.toString(); 

            String rankStr = String.format("%02d.", rank);

            System.out.printf("| %-5s | %-32s | %-12d |%n", 
                rankStr, 
                truncate(nomeRappresentativo, 32), 
                entry.getValue());
            
            rank++;
        }

        System.out.println("+" + border + "+");
    }

    private String truncate(String text, int length) {
        if (text == null) return "N/A";
        return (text.length() <= length) ? text : text.substring(0, length - 3) + "...";
    }
}