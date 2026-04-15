/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain.Strategieclassifica.Impl;
import com.mycompany.footballroyale.domain.Strategieclassifica.CriterioOrdinamentoStrategy;
import com.mycompany.footballroyale.domain.Competizione;
import com.mycompany.footballroyale.domain.Giocatore;
import com.mycompany.footballroyale.domain.Partita;
import com.mycompany.footballroyale.domain.Squadra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author 39327
 */
public class DifferenzaReti_Squadra implements CriterioOrdinamentoStrategy<Squadra>{  
    
   @Override
    public Map<Squadra, Integer> Ordina(List<Squadra> Target, Competizione c) {
        // Creiamo una mappa per memorizzare la differenza reti di ogni squadra
        Map<Squadra, Integer> differenzaRetiMap = new HashMap<>();

        // Calcoliamo la differenza reti per ogni squadra nel Target
        for (Squadra squadra : Target) {
            int golFatti = 0;
            int golSubiti = 0;

            // Iteriamo su tutte le partite della competizione data
            for (Partita p : c.getCalendario() ) { 
                if (p.getSquadraCasa().equals(squadra)) {
                    golFatti += p.getPunteggioCasa();
                    golSubiti += p.getPunteggioOspiti();
                } else if (p.getSquadraOspite().equals(squadra)) {
                    golFatti += p.getPunteggioOspiti();
                    golSubiti += p.getPunteggioCasa();
                }
            }
            differenzaRetiMap.put(squadra, golFatti - golSubiti);
        }
        // Creiamo una lista dalle entry della mappa per poterle ordinare
    List<Map.Entry<Squadra, Integer>> listaDaOrdinare = new ArrayList<>(differenzaRetiMap.entrySet());

    // Ordiniamo la lista (Decrescente: dal più grande al più piccolo)
    listaDaOrdinare.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

    // USIAMO LINKEDHASHMAP per "fissare" l'ordine
    Map<Squadra, Integer> differenzaretiOrdinato = new LinkedHashMap<>();
    
    for (Map.Entry<Squadra, Integer> entry : listaDaOrdinare) {
        // Inseriamo gli elementi uno per uno, dal primo all'ultimo
        differenzaretiOrdinato.put(entry.getKey(), entry.getValue());
    }

    return differenzaretiOrdinato;
    }
}
