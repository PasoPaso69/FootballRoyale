package com.mycompany.footballroyale.domain;

import jakarta.persistence.*; // Necessario per Hibernate 6+
import java.util.*;

@Entity
@Table(name = "partite")
public class Partita {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Genera automaticamente una stringa univoca
    @Column(name = "id_partita")
    private String Id;

    @Temporal(TemporalType.DATE) // Specifica che salviamo solo la data
    @Column(name = "data_gara")
    private Date data;

    @Column(name = "orario")
    private String orario;

    @Column(name = "punteggio_casa")
    private int punteggioCasa;

    @Column(name = "punteggio_ospiti")
    private int punteggioOspiti;

    @Column(name = "stato")
    private String stato;

    // Relazione verso Squadra (Casa)
    @ManyToOne
    @JoinColumn(name = "id_squadra_casa")
    private Squadra squadraCasa;

    // Relazione verso Squadra (Ospite)
    @ManyToOne
    @JoinColumn(name = "id_squadra_ospite")
    private Squadra squadraOspite;

    // Relazione verso Competizione
    @ManyToOne
    @JoinColumn(name = "id_competizione")
    private Competizione competizione;

    // Relazione 1 -> * con EventoGara
    // 'partita' deve essere il nome del campo nella classe EventoGara
    @OneToMany(mappedBy = "partita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoGara> eventi = new ArrayList<>();

    public Partita() {}
    
  
    public Partita(Date data, String orario, Squadra casa, Squadra ospite, Competizione comp) {
        this.data = data;
        this.orario = orario;
        this.squadraCasa = casa;
        this.squadraOspite = ospite;
        this.competizione = comp;
        this.stato = "Programmata"; // Stato di default
        this.punteggioCasa = 0;
        this.punteggioOspiti = 0;
    }
    
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getOrario() { return orario; }
    public void setOrario(String orario) { this.orario = orario; }

    public int getPunteggioCasa() { return punteggioCasa; }
    public void setPunteggioCasa(int punteggioCasa) { this.punteggioCasa = punteggioCasa; }

    public int getPunteggioOspiti() { return punteggioOspiti; }
    public void setPunteggioOspiti(int punteggioOspiti) { this.punteggioOspiti = punteggioOspiti; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    public Squadra getSquadraCasa() { return squadraCasa; }
    public void setSquadraCasa(Squadra s) { this.squadraCasa = s; }

    public Squadra getSquadraOspite() { return squadraOspite; }
    public void setSquadraOspite(Squadra s) { this.squadraOspite = s; }
    
    public Competizione getCompetizione() { return competizione; }
    public void setCompetizione(Competizione competizione) { this.competizione = competizione; }

    public List<EventoGara> getEventi() { return eventi; }
    public void setEventi(List<EventoGara> e) { this.eventi = e; }
    
    @Override
public String toString() {
    return "Partita{" +
            "Id='" + Id + '\'' +
            ", data=" + data +
            ", orario='" + orario + '\'' +
            ", match='" + (squadraCasa != null ? squadraCasa.getNome() : "N/A") + " " + punteggioCasa + 
            " - " + punteggioOspiti + " " + (squadraOspite != null ? squadraOspite.getNome() : "N/A") + '\'' +
            ", stato='" + stato + '\'' +
            ", numeroEventi=" + (eventi != null ? eventi.size() : 0) +
            '}';
}
}
