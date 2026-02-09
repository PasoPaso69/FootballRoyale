/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.service;

import com.mycompany.footballroyale.TechnicalService.EntityManager;
import com.mycompany.footballroyale.TechnicalService.PersistentManager;
import com.mycompany.footballroyale.domain.CatalogoSquadre;
import com.mycompany.footballroyale.domain.Giocatore;
import com.mycompany.footballroyale.domain.Squadra;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class GestoreCreazioneSquadra {
    CatalogoSquadre catalogoSquadra;
    private String TempNome;
    private String TempDettaglioMagliaCasa;
    private String TempDettaglioMagliaTrasferta;
    private List<Giocatore> giocatoriAssegnati;
   public Map<String,String> InserisciDati(String nome, String DettaglioMagliaCasa, String DettaglioMagliaTrasferta){
       TempNome=nome;
       TempDettaglioMagliaCasa=DettaglioMagliaCasa;
       TempDettaglioMagliaTrasferta=DettaglioMagliaTrasferta;
       Map<String,String> ListaGiocatori = PersistentManager.getInstance().getIdNomiGiocatori();
       return ListaGiocatori;
      
   }
   
   public Boolean AssegnaGiocatori(List<String> idSelezionati){
       List<Giocatore> ListaGiocatoriSelezionati = PersistentManager.getInstance().getGiocatorePerId(idSelezionati);
       giocatoriAssegnati = ListaGiocatoriSelezionati;
       return true;
   }
   
   public Boolean ConfermaInserimento(){
       
       Squadra s= catalogoSquadra.creaSquadra(TempNome,TempDettaglioMagliaCasa, TempDettaglioMagliaTrasferta, giocatoriAssegnati);
       EntityManager.getInstance().save(s);
       return true;
       
   
   }
}
