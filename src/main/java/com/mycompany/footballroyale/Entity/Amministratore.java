
package com.mycompany.footballroyale.Entity;

public class Amministratore extends Utente {
    private String password;

    public Amministratore() { super(); }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
public String toString() {
    return "Amministratore{" +
            super.toString() + // Chiama il toString di Utente
            ", password='****'" + // Non mostriamo la password reale per sicurezza
            '}';
}
}
