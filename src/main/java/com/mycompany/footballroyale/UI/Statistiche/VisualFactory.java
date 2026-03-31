/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.UI.Statistiche;

import com.mycompany.footballroyale.UI.Statistiche.StrategieView.Impl.StampaVideo;
import com.mycompany.footballroyale.UI.Statistiche.StrategieView.VisualStrategy;
import com.mycompany.footballroyale.domain.Enum.CriteriCalendario;
import static com.mycompany.footballroyale.domain.Enum.CriteriCalendario.ALGORITMO_DI_BERGER;
import static com.mycompany.footballroyale.domain.Enum.CriteriCalendario.SISTEMA_SVIZZERO;
import com.mycompany.footballroyale.domain.Enum.CriterioVisual;
import com.mycompany.footballroyale.domain.StrategieCalendario.GenerazioneCalendarioStrategy;
import com.mycompany.footballroyale.domain.StrategieCalendario.Impl.AlgoritmoDiBerger;
import com.mycompany.footballroyale.domain.StrategieCalendario.Impl.SistemaSvizzero;

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
            
            default -> throw new IllegalArgumentException("Algoritmo non riconosciuto: " + criterio);
        };
    }
    
}
