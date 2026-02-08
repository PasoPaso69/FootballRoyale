package com.mycompany.footballroyale.domain;

import com.mycompany.footballroyale.domain.Enum.TipologiaEvento;
import jakarta.persistence.*; // Necessario per Hibernate 6+

@Entity
@Table(name = "eventi_gara")
public class EventoGara {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Coerente con l'uso delle String per gli ID
    @Column(name = "id_evento")
    private String Id;

    @Column(name = "minuto", nullable = false)
    private int minuto;

    @Column(name = "tipologia", length = 50) // Gol, Ammonizione, Espulsione
    private TipologiaEvento tipologia;

    // Relazione: Molti eventi appartengono a una sola partita
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partita", nullable = false)
    private Partita partita;

    // Relazione: Molti eventi possono essere inseriti dallo stesso operatore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operatore")
    private OperatorePartita operatore;

    // Relazione: Molti eventi possono avere lo stesso giocatore protagonista (es. doppietta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_giocatore_protagonista")
    private Giocatore protagonista;
    

    // Costruttore vuoto obbligatorio per Hibernate
    public EventoGara() {}

    public EventoGara(int minuto, Giocatore protagonista, 
                      Partita partita, OperatorePartita operatore) {
        this.minuto = minuto;
        this.protagonista = protagonista;
        this.partita = partita;
        this.operatore = operatore;
    }

  
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public int getMinuto() { return minuto; }
    public void setMinuto(int minuto) { this.minuto = minuto; }

    public TipologiaEvento getTipologia() { return tipologia; }
    public void setTipologia(TipologiaEvento tipologia) { this.tipologia = tipologia; }

    public Giocatore getProtagonista() { return protagonista; }
    public void setProtagonista(Giocatore p) { this.protagonista = p; }
    
    public Partita getPartita() { return partita; }
    public void setPartita(Partita partita) { this.partita = partita; }

    public OperatorePartita getOperatore() { return operatore; }
    public void setOperatore(OperatorePartita operatore) { this.operatore = operatore; }

    @Override
    public String toString() {
        return "EventoGara{" +
                "Id='" + Id + '\'' +
                ", minuto=" + minuto +
                ", tipologia='" + tipologia + '\'' +
                ", protagonista=" + (protagonista != null ? protagonista.getCognome() + " (" + protagonista.getNumeroMaglia() + ")" : "N/A") +
                '}';
    }
}