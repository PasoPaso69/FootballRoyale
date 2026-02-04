/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.service;

/**
 *
 * @author 39327
 */  

import com.mycompany.footballroyale.domain.*;
import com.mycompany.footballroyale.util.HibernateUtil;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;
public class AutenticazioneService {




    public Utente login(String email, String passwordInserita) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            // 1. Cerchiamo l'utente per email
            // Hibernate caricherà automaticamente la sottoclasse corretta (Admin o Operatore)
            String hql = "FROM Utente u WHERE u.email = :email";
            Utente utente = session.createQuery(hql, Utente.class)
                                   .setParameter("email", email)
                                   .uniqueResult();

            if (utente == null) {
                System.out.println("Utente non trovato.");
                return null;
            }

            // 2. Verifichiamo la password
            String hashDalDb = "";
            
            // Dobbiamo capire se è un Admin o un Operatore per prendere la password
            if (utente instanceof Amministratore) {
                hashDalDb = ((Amministratore) utente).getPassword();
            } else if (utente instanceof OperatorePartita) {
                hashDalDb = ((OperatorePartita) utente).getPassword();
            }

            // 3. Confronto sicuro con BCrypt
            if (BCrypt.checkpw(passwordInserita, hashDalDb)) {
                return utente; // Login successo!
            } else {
                System.out.println("Password errata.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
