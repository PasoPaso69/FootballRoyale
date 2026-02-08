
package com.mycompany.footballroyale.TechnicalService;

import com.mycompany.footballroyale.domain.Campetto;
import org.hibernate.query.Query;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;


public class CampettoDao {
    public List<Campetto> findCampettiLiberi(Date data,Time ora) {
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

}