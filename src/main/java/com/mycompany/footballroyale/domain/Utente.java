/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;
import java.util.Date;

public abstract class Utente {
    private String id;
    private String nome;
    private String cognome;
    private String cittaDiNascita;
    private Date dataNascita;
    private String telefono;
    private String email;

    public Utente() {}
    
    public Utente(String id, String nome, String cognome, 
                  Date dataNascita, String cittaDiNascita, String telefono, String email) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.cittaDiNascita = cittaDiNascita;
        this.telefono = telefono;
        this.email = email;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getCodiceFiscale() { return cittaDiNascita; }
    public void setCodiceFiscale(String cittaDiNascita) { this.cittaDiNascita = cittaDiNascita; }

    public Date getDataNascita() { return dataNascita; }
    public void setDataNascita(Date dataNascita) { this.dataNascita = dataNascita; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
public String toString() {
    return "Id='" + id + '\'' +
           ", nome='" + nome + '\'' +
           ", cognome='" + cognome + '\'' +
           ", citta di nascita='" + cittaDiNascita + '\'' +
           ", email='" + email + '\'';
}
}
