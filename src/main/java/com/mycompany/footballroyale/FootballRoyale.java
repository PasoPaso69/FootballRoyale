package com.mycompany.footballroyale;

import com.mycompany.footballroyale.TechnicalService.HibernateService;
import com.mycompany.footballroyale.TechnicalService.EntityManager;
import com.mycompany.footballroyale.domain.Campetto;
import com.mycompany.footballroyale.service.FootballRoyaleController;
import java.util.List;

public class FootballRoyale {

    public static void main(String[] args) {
        System.out.println("--- [SISTEMA FOOTBALL ROYALE] AVVIO ---");
        
        try {
            // 1. Inizializzazione Database
            System.out.println(">>> Collegamento al database in corso...");
            HibernateService.getInstance().getSessionFactory(); 
            System.out.println(">>> Connessione stabilita!");

            // 2. AVVIO DELL'APPLICAZIONE
            // Creiamo l'istanza del controller (quello nel package .service)
            FootballRoyaleController app = new FootballRoyaleController();
            
            // Facciamo partire il loop del menu principale
            app.StartApp();

        } catch (Exception e) {
            System.err.println("!!! ERRORE CRITICO DURANTE L'AVVIO !!!");
            e.printStackTrace();
        } finally {
            // 3. Chiusura pulita quando l'utente esce dal programma
            System.out.println(">>> Chiusura sessioni in corso...");
            HibernateService.getInstance().getSessionFactory().close();
            System.out.println("--- APPLICAZIONE CHIUSA ---");
        }
    }
}
