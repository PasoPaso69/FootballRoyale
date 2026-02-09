/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.TechnicalService;

import com.mycompany.footballroyale.domain.Campetto;
import com.mycompany.footballroyale.domain.Enum.StatoCampetto;
import com.mycompany.footballroyale.domain.Giocatore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author 39327
 */
public class GiocatoreDao {
 
    
    public static Map<String, String> getMappaIdNomeG() {
        Map<String, String> mappaC = new HashMap<>();
        
        // Usiamo il nostro HibernateService per aprire la sessione
        try (Session session = HibernateService.getInstance().getSessionFactory().openSession()) {
            
            // Query HQL: selezioniamo solo id e nome (non l'intero oggetto)
            // Hibernate restituirà una lista di array di oggetti: List<Object[]>
            String hql = "SELECT g.id, g.nome FROM Giocatore g WHERE g.disponibilita = : disponibilita";
            
            List<Object[]> risultati = session.createQuery(hql, Object[].class).setParameter("disponibilita", true).list();

            // Cicliamo sui risultati e riempiamo la mappa
            for (Object[] riga : risultati) {
                String id = (String) riga[0];
                String nome = (String) riga[1];
                mappaC.put(id, nome);
            }
            
        } catch (Exception e) {
            System.err.println("Errore nel recupero della mappa Giocaotori:");
            e.printStackTrace();
        }
        
        return mappaC;
    }
    
    public static List<Giocatore> getGiocatorePerID(List<String> IdSelezionati){
        List<Giocatore> giocatore = new ArrayList<>();
    for (String id : IdSelezionati) {
        // Usiamo l'EntityManager per prendere l'oggetto intero
        Giocatore s = EntityManager.getInstance().findById(Giocatore.class, id);
        if (s != null) giocatore.add(s);
    }
    return giocatore;
    }
    
}
