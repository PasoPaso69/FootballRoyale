/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.TechnicalService;

/**
 *
 * @author 39327
 */

 

import com.mycompany.footballroyale.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateService {
    private static HibernateService instance;
    private SessionFactory sessionFactory;

    private HibernateService() {
        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Campetto.class)
                .addAnnotatedClass(Partita.class)
                .addAnnotatedClass(Squadra.class)
                .addAnnotatedClass(Competizione.class)
                .addAnnotatedClass(Campionato.class)
                .addAnnotatedClass(EventoGara.class)
                .addAnnotatedClass(Foto.class)
                .addAnnotatedClass(Giocatore.class)
                .addAnnotatedClass(OperatorePartita.class)
                .addAnnotatedClass(Prenotazione.class)
                .addAnnotatedClass(Amministratore.class)
                .addAnnotatedClass(Utente.class)
                .buildSessionFactory();
    }

    public static HibernateService getInstance() {
        if (instance == null) {
            instance = new HibernateService();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}   

