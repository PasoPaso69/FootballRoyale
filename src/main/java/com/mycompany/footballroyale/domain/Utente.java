package com.mycompany.footballroyale.domain;

import jakarta.persistence.*; // Importante: Hibernate 6 usa jakarta
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utenti") // Nome della tabella su XAMPP
@Inheritance(strategy = InheritanceType.JOINED) // Crea una tabella base e tabelle separate per i figli
public abstract class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <--- Fondamentale per l'autoincremento
    @Column(name = "id_utente")
    private Long id; // Se vuoi l'auto-incremento, servirebbe un int/long, altrimenti lo gestisci a mano

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "cognome", nullable = false, length = 50)
    private String cognome;

    @Column(name = "citta_nascita")
    private String cittaDiNascita;

    @Temporal(TemporalType.DATE) // Specifica che nel DB salva solo la data (senza orario)
    @Column(name = "data_nascita")
    private Date dataNascita;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", unique = true) // L'email non può essere duplicata
    private String email;
    

    public Utente() {}

    public Utente(Long id, String nome, String cognome, 
                  Date dataNascita, String cittaDiNascita, String telefono, String email) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.cittaDiNascita = cittaDiNascita;
        this.telefono = telefono;
        this.email = email;
    }

    // Getter e Setter rimangono uguali...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getCittaDiNascita() { return cittaDiNascita; }
    public void setCittaDiNascita(String cittaDiNascita) { this.cittaDiNascita = cittaDiNascita; }

    public Date getDataNascita() { return dataNascita; }
    public void setDataNascita(Date dataNascita) { this.dataNascita = dataNascita; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    
    public abstract String getPassword();
    
    @Override
    public String toString() {
        return "Id='" + id + '\'' +
               ", nome='" + nome + '\'' +
               ", cognome='" + cognome + '\'' +
               ", citta di nascita='" + cittaDiNascita + '\'' +
               ", email='" + email + '\'';
    }
}