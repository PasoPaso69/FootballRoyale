/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.TechnicalService;

import com.mycompany.footballroyale.domain.Competizione;
import com.mycompany.footballroyale.domain.Squadra;
import java.util.List;

/**
 *
 * @author 39327
 */
public class CompetizioneDao {
    
    public static List<Competizione> getTutteLeCompetizioni(){
     return EntityManager.getInstance().findAll(Competizione.class);
    }   
}
