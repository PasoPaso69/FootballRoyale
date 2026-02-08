package com.mycompany.footballroyale;

import com.mycompany.footballroyale.TechnicalService.HibernateService;
import com.mycompany.footballroyale.TechnicalService.EntityManager;
import com.mycompany.footballroyale.domain.Campetto;
import java.util.List;

public class FootballRoyale {

    public static void main(String[] args) {
        System.out.println("--- [SISTEMA FOOTBALL ROYALE] AVVIO ---");
        
        try {
            // 1. Inizializziamo Hibernate tramite il nostro Service Singleton
            // Al primo richiamo, Hibernate legge il cfg.xml e crea le tabelle
            System.out.println(">>> Collegamento al database in corso...");
            HibernateService.getInstance().getSessionFactory(); 
            System.out.println(">>> Connessione stabilita e tabelle verificate!");

            // 2. Testiamo l'EntityManager
            // Proviamo a vedere quanti campetti ci sono nel DB (all'inizio sarà 0)
            System.out.println(">>> Controllo dati esistenti...");
            List<Campetto> listaCampetti = EntityManager.getInstance().findAll(Campetto.class);
            
            System.out.println(">>> Test completato! Campetti registrati nel sistema: " + listaCampetti.size());

        } catch (Exception e) {
            System.err.println("!!! ERRORE CRITICO DURANTE L'AVVIO DEL DB !!!");
            System.err.println("Assicurati che MySQL sia attivo e che il database 'FootballRoyale' esista.");
            e.printStackTrace();
        } 
        
        // NOTA: Non chiudere la SessionFactory qui se questa è l'app principale,
        // altrimenti il database diventerà irraggiungibile per il resto del programma!
    }
}