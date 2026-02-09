
package com.mycompany.footballroyale.TechnicalService;

import com.mycompany.footballroyale.domain.Campetto;
import com.mycompany.footballroyale.domain.Enum.StatoCampetto;
import com.mycompany.footballroyale.domain.Squadra;
import org.hibernate.query.Query;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;


public class CampettoDao {
    public static List<Campetto> findCampettiLiberi(Date data,Time ora) {
        List<Campetto> risultati = new ArrayList<>();
        
        // Usiamo il Singleton HibernateService per ottenere la SessionFactory
        try (Session session = HibernateService.getInstance().getSessionFactory().openSession()) {
            
            String hql = "SELECT c FROM Campetto c WHERE c.id NOT IN (" +
                         "  SELECT p.campetto.id FROM Prenotazione p " +
                         "  WHERE p.data = :dataScelta " +
                         "  AND p.orario = :oraScelta" +
                         ")";
            
            Query<Campetto> query = session.createQuery(hql, Campetto.class);
            query.setParameter("dataScelta", data);
            query.setParameter("oraScelta", ora);
            
            risultati = query.list();
            
        } catch (Exception e) {
            System.err.println("Errore nel DAO Campetto durante la ricerca disponibilità:");
            e.printStackTrace();
        }
        
        return risultati;
    }
    
    public static List<Campetto> getCampettiDisponibili(){
        List<Campetto> CampettiDisponibili = EntityManager.getInstance().findListByAttribute(Campetto.class, "stato" , StatoCampetto.Agibile);
       return CampettiDisponibili; 
    }
    public static Map<String, String> getMappaIdNomeC() {
        Map<String, String> mappaC = new HashMap<>();
        
        // Usiamo il nostro HibernateService per aprire la sessione
        try (Session session = HibernateService.getInstance().getSessionFactory().openSession()) {
            
            // Query HQL: selezioniamo solo id e nome (non l'intero oggetto)
            // Hibernate restituirà una lista di array di oggetti: List<Object[]>
            String hql = "SELECT c.id, c.nome FROM Campetto c WHERE c.stato = : statoC";
            
            List<Object[]> risultati = session.createQuery(hql, Object[].class).setParameter("statoC", StatoCampetto.Agibile).list();

            // Cicliamo sui risultati e riempiamo la mappa
            for (Object[] riga : risultati) {
                String id = (String) riga[0];
                String nome = (String) riga[1];
                mappaC.put(id, nome);
            }
            
        } catch (Exception e) {
            System.err.println("Errore nel recupero della mappa campetti:");
            e.printStackTrace();
        }
        
        return mappaC;
    }
     public static List<Campetto> getCampettiPerID(List<String> IdSelezionati){
        List<Campetto> campetto = new ArrayList<>();
    for (String id : IdSelezionati) {
        // Usiamo l'EntityManager per prendere l'oggetto intero
        Campetto s = EntityManager.getInstance().findById(Campetto.class, id);
        if (s != null) campetto.add(s);
    }
    return campetto;
    }
     
     public boolean isCampettoLibero(String idCampetto, Date oraInizioPartita) {
    // Calcoliamo la fine della partita (90 minuti dopo)
    long novantaMinutiInMs = 90 * 60 * 1000; 
    Date oraFinePartita = new Date(oraInizioPartita.getTime() + novantaMinutiInMs);

    
    Session session = HibernateService.getInstance().getSessionFactory().openSession();
    
    // Query JPQL per trovare sovrapposizioni
    String jpql = "SELECT COUNT(p) FROM Prenotazione p " +
                  "WHERE p.campetto.id = :idCampetto " +
                  "AND p.oraInizio < :finePartita " +
                  "AND p.oraFine > :inizioPartita";

    Long coincidenze = session.createQuery(jpql, Long.class)
            .setParameter("idCampetto", idCampetto)
            .setParameter("finePartita", oraFinePartita)
            .setParameter("inizioPartita", oraInizioPartita)
            .getSingleResult();

    // Se il conteggio è 0, il campo è libero
    return coincidenze == 0;
}
    

}