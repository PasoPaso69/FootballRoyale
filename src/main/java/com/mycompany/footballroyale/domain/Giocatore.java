package com.mycompany.footballroyale.domain;

import jakarta.persistence.*; // Importante per Hibernate 6+
import java.util.Date;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "giocatori")

@PrimaryKeyJoinColumn(name = "id_utente")
public class Giocatore extends Utente {
    
    
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    
    @Column(name = "numero_maglia")
    private int numeroMaglia;

    @Column(name = "ruolo", length = 50)
    private String ruolo;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_foto")
    private Foto foto;
    
    @Column(name= "disponibilita")
    private Boolean disponibilita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_squadra")
    private Squadra squadra;

    @OneToMany(mappedBy = "protagonista", fetch = FetchType.EAGER)
    private List<EventoGara> Eventi;
    
    
    public Giocatore() { super(); }
    
 
     public Giocatore(Long id, String nome, String cognome,  
                     Date dataNascita, String cittaDiNascita, String telefono, String email,
                     int numeroMaglia, String ruolo, Foto foto) {
        
        // Passa i dati comuni al costruttore della superclasse Utente
        super(id, nome, cognome, dataNascita, cittaDiNascita, telefono, email);
        
        // Inizializza i dati specifici del Giocatore
        this.numeroMaglia = numeroMaglia;
        this.ruolo = ruolo;
        this.foto = foto;
    }
     
    @Override
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
   
    
    public String getRuolo() { return ruolo; }
    public void setruolo(String ruolo) { this.ruolo = ruolo; }
    
    public List<EventoGara> getEvento(){return this.Eventi;}
    public void setEventi(List<EventoGara> e ){this.Eventi = e; }

    public int getNumeroMaglia() { return numeroMaglia; }
    public void setNumeroMaglia(int numeroMaglia) { this.numeroMaglia = numeroMaglia; }

    public Foto getFoto() { return foto; }
    public void setFoto(Foto foto) { this.foto = foto; }
    
    public Squadra getSquadra() { return squadra; }
    public void setSquadra(Squadra squadra) { this.squadra = squadra; }
    
    public Boolean getDisponibilita(){return disponibilita;}
    public void setDisponibilita(Boolean Disponibilità) {this.disponibilita = Disponibilità;}
    
    @Override
public String toString() {
    return  super.toString();
}
}
