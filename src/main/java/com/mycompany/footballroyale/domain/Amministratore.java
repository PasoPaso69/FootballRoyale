package com.mycompany.footballroyale.domain;

import jakarta.persistence.*;
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt; // Per la gestione sicura della password

@Entity
@Table(name = "amministratori")
@PrimaryKeyJoinColumn(name = "id_utente") // Lega l'ID alla tabella 'utenti'
public class Amministratore extends Utente {
    

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    public Amministratore() { 
        super(); 
    }
    
    public Amministratore(Long id, String nome, String cognome, 
                          Date dataNascita, String cittaDiNascita, String telefono, 
                          String email, String password) {
        super(id, nome, cognome, dataNascita, cittaDiNascita, telefono, email);
        this.password = password;
    }

    // Hash automatico della password prima del salvataggio su XAMPP
    @PrePersist
    @PreUpdate
    private void hashPassword() {
        if (this.password != null && !this.password.startsWith("$2a$")) {
            this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
        }
    }
    
    
    @Override
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public String toString() {
        return "Amministratore{" +
                super.toString() + 
                ", password='****'" + 
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

