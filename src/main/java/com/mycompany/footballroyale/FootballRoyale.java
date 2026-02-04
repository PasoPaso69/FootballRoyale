/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.footballroyale;

import com.mycompany.footballroyale.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FootballRoyale {

    public static void main(String[] args) {
        System.out.println("--- AVVIO TEST HIBERNATE ---");
        
        // 1. Tenta di aprire una sessione (questo inizializza il SessionFactory)
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            System.out.println(">>> Connessione stabilita con successo!");
            
            // Se arrivi qui senza errori, Hibernate ha già inviato i comandi 
            // per creare le tabelle grazie alla proprietà 'update' nel file cfg.xml
            
        } catch (Exception e) {
            System.err.println("!!! ERRORE DURANTE IL TEST !!!");
            e.printStackTrace();
        } finally {
            // Chiude il SessionFactory alla fine del test
            HibernateUtil.getSessionFactory().close();
            System.out.println("--- TEST CONCLUSO ---");
        }
    }
}