/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.TechnicalService;

import com.mycompany.footballroyale.domain.Squadra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.Session;

/**
 *
 * @author 39327
 */
public class SquadraDao {
    
    
    public static List<Squadra> getTutteLeSquadre(){
        return EntityManager.getInstance().findAll(Squadra.class);
    }
    
public static Map<Long, String> getMappaIdNome() {
        Map<Long, String> mappa = new HashMap<>();
        
        // Usiamo il nostro HibernateService per aprire la sessione
        try (Session session = HibernateService.getInstance().getSessionFactory().openSession()) {
            
            // Query HQL: selezioniamo solo id e nome (non l'intero oggetto)
            // Hibernate restituirà una lista di array di oggetti: List<Object[]>
            String hql = "SELECT s.id, s.nome FROM Squadra s";
            List<Object[]> risultati = session.createQuery(hql, Object[].class).list();

            // Cicliamo sui risultati e riempiamo la mappa
            for (Object[] riga : risultati) {
                Long id = (Long) riga[0];
                String nome = (String) riga[1];
                mappa.put(id, nome);
            }
            
        } catch (Exception e) {
            System.err.println("Errore nel recupero della mappa squadre:");
            e.printStackTrace();
        }
        
        return mappa;
    }

    public static List<Squadra> getSquadrePerID(List<Long> IdSelezionati){
        List<Squadra> squadre = new ArrayList<>();
    for (Long id : IdSelezionati) {
        // Usiamo l'EntityManager per prendere l'oggetto intero
        Squadra s = EntityManager.getInstance().findById(Squadra.class, id);
        if (s != null) squadre.add(s);
    }
    return squadre;
    }
}
