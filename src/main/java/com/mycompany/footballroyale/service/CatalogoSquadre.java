/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.service;

/**
 *
 * @author 39327
 */


import com.mycompany.footballroyale.domain.Foto;
import com.mycompany.footballroyale.domain.Giocatore;
import com.mycompany.footballroyale.domain.Squadra;
import java.util.ArrayList;
import java.util.List;


public class CatalogoSquadre {

    private List<Squadra> listaSquadre = new ArrayList<>();

    public CatalogoSquadre() {}
    
    public CatalogoSquadre(List<Squadra> listaSquadre) {
        this.listaSquadre= listaSquadre;
    }

    // Metodi dal diagramma UML
    public void creaSquadra(String nomeSquadra, Foto logo, String colori, List<Giocatore> listaGiocatori) {
        // Qui andrebbe la logica per istanziare una nuova Squadra e aggiungerla alla lista
    }

    public Squadra getsquadra(String idSquadra) {
        return listaSquadre.stream()
                .filter(s -> s.getId().equals(idSquadra))
                .findFirst()
                .orElse(null);
    }

    public List<Squadra> getTutteLeSquadre() {
        return listaSquadre;
    }
}

