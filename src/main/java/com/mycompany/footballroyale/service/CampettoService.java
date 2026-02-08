/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.service;

import com.mycompany.footballroyale.TechnicalService.CampettoDao;
import com.mycompany.footballroyale.domain.Campetto;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 39327
 */
@Service
public class CampettoService {

    @Autowired
    private CampettoDao campettoDao; // Iniezione del DAO

    public List<Campetto> getCampiDisponibiliPerSlot(Date data, Time ora) {
        return campettoDao.findCampettiLiberi(data, ora);
    }
}
