/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.footballroyale.domain.Strategieclassifica;

import com.mycompany.footballroyale.domain.Competizione;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 39327
 */
/**
 * Interfaccia per definire l'algoritmo di ordinamento (Strategy Pattern).
 * 
 * @param <T> Il tipo di entità da ordinare 
 */
public interface CriterioOrdinamentoStrategy<T> {
    Map<T,Integer> Ordina(List<T> Target, Competizione c) ;
    
}
