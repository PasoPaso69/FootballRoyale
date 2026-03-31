/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Application;

import com.mycompany.footballroyale.domain.Enum.CriteriCalendario;
import com.mycompany.footballroyale.domain.StrategieCalendario.GenerazioneCalendarioStrategy;
import com.mycompany.footballroyale.domain.StrategieCalendario.Impl.AlgoritmoDiBerger;
import com.mycompany.footballroyale.domain.StrategieCalendario.Impl.SistemaSvizzero;

/**
 *
 * @author Lenovo
 */
public class FootballRoyaleBusinessFactory {
    
    
    public static GenerazioneCalendarioStrategy getStrategy(CriteriCalendario criterio) {
        if (criterio == null) {
            throw new IllegalArgumentException("Il criterio di generazione non può essere nullo");
        }

        return switch (criterio) {
            case SISTEMA_SVIZZERO -> new SistemaSvizzero();
            case ALGORITMO_DI_BERGER -> new AlgoritmoDiBerger();
            default -> throw new IllegalArgumentException("Algoritmo non riconosciuto: " + criterio);
        };
    }
}
