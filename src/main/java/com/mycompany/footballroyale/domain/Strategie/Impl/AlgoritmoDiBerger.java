/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain.Strategie.Impl;
import com.mycompany.footballroyale.domain.*;
import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;

import com.mycompany.footballroyale.domain.Strategie.GenerazioneCalendarioStrategy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 39327
 */
public class AlgoritmoDiBerger implements GenerazioneCalendarioStrategy {
    
    @Override
    public List<Partita> generaCalendario(List<Squadra> squadre, List<Campetto> campetti, Date dataInizio, List<GiorniSettimanali>giorni, int partitePerGiorno) {
        List<Partita> calendarioGenerato = new ArrayList<>();
        
        // LOGICA DI BELGER:
        // Qui scriverai i cicli per accoppiare le squadre (Round Robin)
        // Per ogni accoppiamento, creerai un oggetto Partita:
        // Partita p = new Partita(data, squadraCasa, squadraOspite, campettoScelto);
        // calendarioGenerato.add(p);
        
        System.out.println("Generazione calendario con Algoritmo di Belger...");
        
        return calendarioGenerato;
    }
    
       
    
}
