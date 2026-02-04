package com.mycompany.footballroyale.domain;

import jakarta.persistence.*; // Importante per Hibernate 6+
import java.util.Date;

@Entity
@Table(name = "giocatori")
// Collega l'ID di questa tabella a quello della tabella 'utenti'
@PrimaryKeyJoinColumn(name = "id_utente")
public class Giocatore extends Utente {
    
    @Column(name = "numero_maglia")
    private int numeroMaglia;

    @Column(name = "ruolo", length = 50)
    private String ruolo;

    // Relazione 1-a-1 con Foto (ogni giocatore ha la sua foto profilo)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_foto")
    private Foto foto;

    // Relazione molti-a-uno: molti giocatori appartengono a una squadra
    // Il nome "squadra" deve corrispondere a quello usato in mappedBy nella classe Squadra
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_squadra")
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
