/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain.Strategieclassifica.Impl;
import com.mycompany.footballroyale.domain.Strategieclassifica.CriterioOrdinamentoStrategy;
import com.mycompany.footballroyale.domain.Competizione;
import com.mycompany.footballroyale.domain.Enum.TipologiaEvento;
import com.mycompany.footballroyale.domain.EventoGara;
import com.mycompany.footballroyale.domain.Giocatore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 39327
 */
public class Goal_Giocatori implements CriterioOrdinamentoStrategy <Giocatore> {
     public Map<Giocatore,Integer> Ordina(List<Giocatore> Target, Competizione c){
            
            Map<Giocatore,Integer> ClassificaFinale= new HashMap<>();
        
            for(Giocatore g : Target){
                int i=0;
                for(EventoGara e : g.getEvento()){
                    if(e.getPartita().getCompetizione()==c & e.getTipologia()== TipologiaEvento.Goal ){
                        i++;
                    }
                    ClassificaFinale.put(g, i);
                }
            }
            
             List<Map.Entry<Giocatore, Integer>> listaDaOrdinare = new ArrayList<>(ClassificaFinale.entrySet());

    // Ordiniamo la lista (Decrescente: dal più grande al più piccolo)
    listaDaOrdinare.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

    // USIAMO LINKEDHASHMAP per "fissare" l'ordine
    Map<Giocatore, Integer> GoalOrdinato = new LinkedHashMap<>();
    
    for (Map.Entry<Giocatore, Integer> entry : listaDaOrdinare) {
        //Inseriamo gli elementi uno per uno, dal primo all'ultimo
        GoalOrdinato.put(entry.getKey(), entry.getValue());
    }

            
                return GoalOrdinato;}
    }
    

