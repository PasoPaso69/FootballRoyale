/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.UI.Statistiche.StrategieView.Impl;
import com.mycompany.footballroyale.UI.Statistiche.StrategieView.VisualStrategy;
import com.mycompany.footballroyale.domain.Enum.CriterioClassifica;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 39327
 */
public class GraficoBarre implements VisualStrategy{


    @Override
    public void stampa(List<Map.Entry<?, Integer>> classificaOrdinata, CriterioClassifica titoloTarget) {
        if (classificaOrdinata == null || classificaOrdinata.isEmpty()) {
            System.out.println("Nessun dato disponibile per il grafico.");
            return;
        }

        // 1. Calcolo del valore massimo per definire l'altezza dell'asse Y
        int maxPunti = classificaOrdinata.stream()
                .mapToInt(Map.Entry::getValue)
                .max()
                .orElse(0);

        System.out.println("\n--- GRAFICO: " + titoloTarget.toString().replace("_", " ").toUpperCase() + " ---");
        System.out.println();

        // 2. Disegno del corpo del grafico (dall'alto verso il basso)
        // Usiamo un passo di 1 se i punti sono pochi, o dinamico se sono molti
        int step = Math.max(1, maxPunti / 10); 

        for (int i = maxPunti; i > 0; i -= (maxPunti > 15 ? step : 1)) {
            // Asse delle ordinate (Y)
            System.out.printf("%3d |", i);

            // Disegno delle barre per ogni elemento
            for (Map.Entry<?, Integer> entry : classificaOrdinata) {
                if (entry.getValue() >= i) {
                    System.out.print("  HHH  "); 
                } else {
                    System.out.print("       "); 
                }
            }
            System.out.println();
        }

        // 3. Base del grafico (Asse X)
        System.out.print("    +");
        for (int i = 0; i < classificaOrdinata.size(); i++) {
            System.out.print("-------");
        }
        System.out.println();

        // 4. Nomi (Asse X) - Stampati in verticale per evitare sovrapposizioni
        // Troviamo la lunghezza massima dei nomi per sapere quanto scendere
        int maxNameLen = 10; // Limite per non allungare troppo
        
        for (int row = 0; row < maxNameLen; row++) {
            System.out.print("     "); // Allineamento con l'asse Y
            for (Map.Entry<?, Integer> entry : classificaOrdinata) {
                String nome = entry.getKey().toString();
                if (row < nome.length()) {
                    System.out.print("   " + nome.charAt(row) + "   ");
                } else {
                    System.out.print("       ");
                }
            }
            System.out.println();
        }
        
        System.out.println("\n(Legenda: i nomi sono scritti in verticale)");
    }
}
    


