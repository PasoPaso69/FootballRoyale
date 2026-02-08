package com.mycompany.footballroyale.domain;

import com.mycompany.footballroyale.domain.Enum.StatoPartita;
import jakarta.persistence.*; // Necessario per Hibernate 6+
import java.sql.Time;
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
    private Time orario;

    @Column(name = "punteggio_casa")
    private int punteggioCasa;

    @Column(name = "punteggio_ospiti")
    private int punteggioOspiti;

    @Column(name = "stato")
    private StatoPartita stato;

    // Relazione verso Squadra (Casa)
    @OneToOne
    @JoinColumn(name = "id_squadra_casa")
    private Squadra squadraCasa;

    // Relazione verso Squadra (Ospite)
    @OneToOne
    @JoinColumn(name = "id_squadra_ospite")
    private Squadra squadraOspite;

    
    @ManyToOne
    @JoinColumn(name = "id_competizione")
    private Competizione competizione;
    
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "Prenotazione_Partita")
    private Prenotazione prenotazione;

    // Relazione 1 -> * con EventoGara
    // 'partita' deve essere il nome del campo nella classe EventoGara
    @OneToMany(mappedBy = "partita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoGara> eventi = new ArrayList<>();

    public Partita() {}
    
  
    public Partita(Squadra casa,Squadra ospite,Date data, Time orari ,  Competizione comp) {
        this.data = data;
        this.orario = orario;
        this.squadraCasa = casa;
        this.squadraOspite = ospite;
        this.competizione = comp;
        this.stato = StatoPartita.Programmata; // Stato di default
        this.punteggioCasa = 0;
        this.punteggioOspiti = 0;
    }
    
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public Time getOrario() { return orario; }
    public void setOrario(Time orario) { this.orario = orario; }

    public int getPunteggioCasa() { return punteggioCasa; }
    public void setPunteggioCasa(int punteggioCasa) { this.punteggioCasa = punteggioCasa; }

    public int getPunteggioOspiti() { return punteggioOspiti; }
    public void setPunteggioOspiti(int punteggioOspiti) { this.punteggioOspiti = punteggioOspiti; }

    public StatoPartita getStato() { return stato; }
    public void setStato(StatoPartita stato) { this.stato = stato; }

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
