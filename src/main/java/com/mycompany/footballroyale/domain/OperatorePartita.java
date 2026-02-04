/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;

import jakarta.persistence.*; // Necessario per Hibernate 6+
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "operatori_partita")
@PrimaryKeyJoinColumn(name = "id_utente") 

public class OperatorePartita extends Utente {

    @Column(name = "password", nullable = false)
    private String password;
    public OperatorePartita() { super(); }
    
    public OperatorePartita(String id, String nome, String cognome,
                            Date dataNascita, String cittaDiNascita, String telefono, 
                            String email, String password) {
        super(id, nome, cognome, dataNascita, cittaDiNascita, telefono, email);
        this.password = password;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
        // Hash automatico della password prima del salvataggio su XAMPP
    @PrePersist
    @PreUpdate
    private void hashPassword() {
        if (this.password != null && !this.password.startsWith("$2a$")) {
            this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
        }
    }
    
    @Override
public String toString() {
    return "OperatorePartita{" +
            super.toString() + // Richiama Nome, Cognome, Email dalla classe Utente
            ", password='****'" + // Oscuriamo la password per motivi di sicurezza
            '}';
}
}
