/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.TechnicalService;

import com.mycompany.footballroyale.domain.Campetto;
import com.mycompany.footballroyale.domain.Enum.StatoCampetto;
import com.mycompany.footballroyale.domain.Prenotazione;
import java.util.List;

/**
 *
 * @author 39327
 */
public class PrenotazioneDao {
    
        public static List<Prenotazione> getPrenotazioni(){
        List<Prenotazione> prenotazioni = EntityManager.getInstance().findAll(Prenotazione.class);
       return prenotazioni; 
    }
    
}
