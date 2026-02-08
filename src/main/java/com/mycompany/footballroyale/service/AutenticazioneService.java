/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.service;

/**
 *
 * @author 39327
 */  

import com.mycompany.footballroyale.TechnicalService.EntityManager;
import com.mycompany.footballroyale.domain.*;
import com.mycompany.footballroyale.TechnicalService.HibernateService;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;
public class AutenticazioneService {

    public Utente login(String email, String passwordInserita) {
        // 1. Cerchiamo l'utente tramite l'EntityManager
        Utente utente = EntityManager.getInstance().findByAttribute(Utente.class, "email", email);

        if (utente == null) {
            return null;
        }

        // 2. Grazie al polimorfismo, chiamiamo getPassword() direttamente!
        // Hibernate sa già se deve andare a prenderla nella tabella Admin o Giocatore.
        String hashDalDb = utente.getPassword();

        // 3. Confronto con BCrypt
        if (BCrypt.checkpw(passwordInserita, hashDalDb)) {
            System.out.println("Login riuscito per: " + utente.getClass().getSimpleName());
            return utente;
        }

        return null;
    }
}

