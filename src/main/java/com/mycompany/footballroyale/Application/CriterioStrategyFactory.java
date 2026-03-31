/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Application;

import com.mycompany.footballroyale.domain.Enum.CriteriCalendario;
import static com.mycompany.footballroyale.domain.Enum.CriteriCalendario.ALGORITMO_DI_BERGER;
import static com.mycompany.footballroyale.domain.Enum.CriteriCalendario.SISTEMA_SVIZZERO;
import com.mycompany.footballroyale.domain.Enum.CriterioClassifica;
import com.mycompany.footballroyale.domain.StrategieCalendario.GenerazioneCalendarioStrategy;
import com.mycompany.footballroyale.domain.StrategieCalendario.Impl.AlgoritmoDiBerger;
import com.mycompany.footballroyale.domain.StrategieCalendario.Impl.SistemaSvizzero;
import com.mycompany.footballroyale.domain.Strategieclassifica.CriterioOrdinamentoStrategy;
import com.mycompany.footballroyale.domain.Strategieclassifica.Impl.Cartellini_Giocatori;
import com.mycompany.footballroyale.domain.Strategieclassifica.Impl.DifferenzaReti_Squadra;
import com.mycompany.footballroyale.domain.Strategieclassifica.Impl.Goal_Giocatori;
import com.mycompany.footballroyale.domain.Strategieclassifica.Impl.Punti_Squadra;

/**
 *
 * @author 39327
 */
public class CriterioStrategyFactory {
    public static CriterioOrdinamentoStrategy getStrategy(CriterioClassifica criterio) {
        if (criterio == null) {
            throw new IllegalArgumentException("Il criterio di generazione non può essere nullo");
        }

        return switch (criterio) {
            case Goal_Giocatori -> new Goal_Giocatori();
            case Punti_Squadre -> new Punti_Squadra(); 
            case DifferenzaReti_Squadre -> new DifferenzaReti_Squadra();
            case Cartellini_Giocatori -> new Cartellini_Giocatori();
            default -> throw new IllegalArgumentException("Algoritmo non riconosciuto: " + criterio);
        };
    }
    
}
