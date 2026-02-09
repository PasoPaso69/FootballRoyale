/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;
    import java.util.List;
/**
 *
 * @author 39327
 */
public class CatalogoSquadre {

    // Se non è nel DB, la lista squadre non viene gestita da Hibernate qui, 
    // ma può essere passata o recuperata al bisogno.
    
    public CatalogoSquadre() {
    }

    /**
     * Metodo Creator: crea l'oggetto Squadra e lo configura.
     */
    public Squadra creaSquadra(String nome, String magliaCasa, String magliaTrasferta, List<Giocatore> giocatori) {

        Squadra nuovaSquadra = new Squadra();
        nuovaSquadra.setNome(nome);
        nuovaSquadra.setDettaglioMagliaCasa(magliaCasa);
        nuovaSquadra.setDettaglioMagliaTrasferta(magliaTrasferta);
        nuovaSquadra.setGiocatori(giocatori);
        
        for(Giocatore g : giocatori){
        g.setDisponibilita(false);
        g.setSquadra(nuovaSquadra);
        }
       
        return nuovaSquadra;
    }
}
