/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.TechnicalService;

import com.mycompany.footballroyale.domain.Campetto;
import com.mycompany.footballroyale.domain.Foto;
import com.mycompany.footballroyale.domain.Giocatore;
import com.mycompany.footballroyale.domain.Squadra;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author 39327
 */
public class PersistentManager {
    private static PersistentManager instance;

    public static PersistentManager getInstance() {
        if (instance == null) {
            instance = new PersistentManager();
        }
        return instance;
    }
  
    public List<Campetto> getCampiDisponibiliPerSlot(Date data, Time ora) {
        return CampettoDao.findCampettiLiberi(data, ora);
    }
    
    public List<Campetto> getCampiDisponibili() {
        return CampettoDao.getCampettiDisponibili();
    }
    public Map<String,String> getIdNomeCampiDisponibili() {
        return CampettoDao.getMappaIdNomeC();
    }
        public List<Campetto> getCampettiPerID(List<String> IdSelezionati){
            return CampettoDao.getCampettiPerID(IdSelezionati);
    }
    
    public List<Squadra> getTutteLeSquadre() {
        return SquadraDao.getTutteLeSquadre();
    }
    
    public Map<String,String> getIdNomeSquadre() {
        return SquadraDao.getMappaIdNome();
    }
    
    public List<Squadra> getSquadrePerID(List<String> IdSelezionati){
            return SquadraDao.getSquadrePerID(IdSelezionati);
    }
}
