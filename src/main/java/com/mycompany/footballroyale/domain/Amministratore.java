
package com.mycompany.footballroyale.domain;
import java.util.Date;

public class Amministratore extends Utente {
    private String password;

    public Amministratore() { super(); }
    
    public Amministratore(String id, String nome, String cognome, String codiceFiscale, 
                          Date dataNascita, String cittaDiNascita, String telefono, 
                          String email, String password) {
        // Passa i dati comuni al costruttore della classe padre (Utente)
        super(id, nome, cognome, dataNascita, cittaDiNascita, telefono, email);
        // Inizializza il dato specifico dell'Amministratore
        this.password = password;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
public String toString() {
    return "Amministratore{" +
            super.toString() + // Chiama il toString di Utente
            ", password='****'" + // Non mostriamo la password reale per sicurezza
            '}';
}

 public void creazioneCampionato() {
        // Implementazione: Chiama il GestoreTorneo
        // Es: GestoreTorneo.getInstance().creaNuovaCompetizione();
    }

    public void creazioneSquadra() {
        // Implementazione: Chiama il GestoreCreazioneSquadra
        // Es: GestoreCreazioneSquadra.getInstance().iniziaCreazione();
    }
}

