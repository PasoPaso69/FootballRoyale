
package com.mycompany.footballroyale.Entity;

public class Giocatore extends Utente {
    private String Id;
    private String cittaNascita;
    private int numeroMaglia;
    private Foto foto;
    private String ruolo;
    private Squadra squadra;

    public Giocatore() { super(); }
    
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public String getCittaNascita() { return cittaNascita; }
    public void setCittaNascita(String cittaNascita) { this.cittaNascita = cittaNascita; }
    
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
            ", Id='" + Id + '\'' +
            ", cittaNascita='" + cittaNascita + '\'' +
            ", numeroMaglia=" + numeroMaglia +
            ", ruolo='" + ruolo + '\'' +
            ", foto=" + (foto != null ? foto.getFileName() : "Nessuna") +
            ", squadra=" + (squadra != null ? squadra.getNome() : "Svincolato") +
            '}';
}
}
