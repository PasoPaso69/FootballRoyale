/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;

import java.util.Date;
public class OperatorePartita extends Utente {
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
    
    @Override
public String toString() {
    return "OperatorePartita{" +
            super.toString() + // Richiama Nome, Cognome, Email dalla classe Utente
            ", password='****'" + // Oscuriamo la password per motivi di sicurezza
            '}';
}
}
