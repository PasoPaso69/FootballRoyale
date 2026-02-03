
package com.mycompany.footballroyale.domain;
import java.util.Date;

public class Giocatore extends Utente {
    
    private int numeroMaglia;
    private Foto foto;
    private String ruolo;
    private Squadra squadra;

    public Giocatore() { super(); }
    
 
     public Giocatore(String id, String nome, String cognome,  
                     Date dataNascita, String cittaDiNascita, String telefono, String email,
                     int numeroMaglia, String ruolo, Foto foto) {
        
        // Passa i dati comuni al costruttore della superclasse Utente
        super(id, nome, cognome, dataNascita, cittaDiNascita, telefono, email);
        
        // Inizializza i dati specifici del Giocatore
        this.numeroMaglia = numeroMaglia;
        this.ruolo = ruolo;
        this.foto = foto;
    }
   
    
    public String getRuolo() { return ruolo; }
    public void setruolo(String ruolo) { this.ruolo = ruolo; }

    public int getNumeroMaglia() { return numeroMaglia; }
    public void setNumeroMaglia(int numeroMaglia) { this.numeroMaglia = numeroMaglia; }

    public Foto getFoto() { return foto; }
    public void setFoto(Foto foto) { this.foto = foto; }
    
    @Override
public String toString() {
    return "Giocatore{" +
            super.toString() + // Recupera Nome, Cognome, Email da Utente
            
            ", numeroMaglia=" + numeroMaglia +
            ", ruolo='" + ruolo + '\'' +
            ", foto=" + (foto != null ? foto.getFileName() : "Nessuna") +
            ", squadra=" + (squadra != null ? squadra.getNome() : "Svincolato") +
            '}';
}
}
