/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.UI.Statistiche;

import com.mycompany.footballroyale.UI.Statistiche.StrategieView.Impl.GraficoBarre;
import com.mycompany.footballroyale.UI.Statistiche.StrategieView.Impl.StampaVideo;
import com.mycompany.footballroyale.UI.Statistiche.StrategieView.VisualStrategy;
import com.mycompany.footballroyale.domain.Enum.CriterioVisual;


/**
 *
 * @author 39327
 */
public class VisualFactory {
    
        public static VisualStrategy getStrategy(CriterioVisual criterio) {
        if (criterio == null) {
            throw new IllegalArgumentException("Il criterio di generazione non può essere nullo");
        }

        return switch (criterio) {
            case stampa_a_video -> new StampaVideo();
            case grafico_a_barre -> new GraficoBarre();
            
            default -> throw new IllegalArgumentException("Algoritmo non riconosciuto: " + criterio);
        };
    }
    
}
