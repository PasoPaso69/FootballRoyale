package com.mycompany.footballroyale.domain;

import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;
import com.mycompany.footballroyale.domain.Enum.StatoCompetizione;
import com.mycompany.footballroyale.domain.Strategie.GenerazioneCalendarioStrategy;
import jakarta.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competizioni")
@Inheritance(strategy = InheritanceType.JOINED) 
public abstract class Competizione {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_competizione")
    private String Id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "stato")
    protected StatoCompetizione stato;
    
    @Transient
    protected GenerazioneCalendarioStrategy strategia;


    @Temporal(TemporalType.DATE)
    @Column(name = "data_inizio")
    private Date dataInizio;

    // Relazione Molti-a-Molti: Una competizione ha molte squadre, una squadra partecipa a molte competizioni
    @ManyToMany
    @JoinTable(
        name = "partecipazioni", // Nome della tabella di giunzione su XAMPP
        joinColumns = @JoinColumn(name = "id_competizione"),
        inverseJoinColumns = @JoinColumn(name = "id_squadra")
    )
    protected List<Squadra> squadrePartecipanti = new ArrayList<>();
    
        @ManyToMany
    @JoinTable(
        name = "DisponibilitàCampetti", 
        joinColumns = @JoinColumn(name = "id_competizione"),
        inverseJoinColumns = @JoinColumn(name = "id_campetto")
    )
    protected List<Campetto> campettiDisponibili = new ArrayList<>();

    // Relazione Uno-a-Molti: Una competizione ha molte partite (il calendario)
    @OneToMany(mappedBy = "competizione", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Partita> calendario = new ArrayList<>();
    
    public Competizione() {}

    public Competizione(String id, String nome, Date dataInizio) {
        this.Id = id;
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.stato = StatoCompetizione.InConfigurazione;
    }

    // Getter e Setter
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public StatoCompetizione getStato() { return stato; }
    public void setStato(StatoCompetizione stato) { this.stato = stato; }

    public Date getDataInizio() { return dataInizio; }
    public void setDataInizio(Date dataInizio) { this.dataInizio = dataInizio; }

    public List<Squadra> getSquadrePartecipanti() { return squadrePartecipanti; }
    public void setSquadrePartecipanti(List<Squadra> s) { this.squadrePartecipanti = s; }

    public List<Partita> getCalendario() { return calendario; }
    public void setCalendario(List<Partita> c) { this.calendario = c; }
    
    public List<Campetto> getCampetti() { return campettiDisponibili; }
    public void setcampetto(List<Campetto> c) { this.campettiDisponibili = c; }
    
        public void setStrategia(GenerazioneCalendarioStrategy s){this.strategia= s;}
    

    public abstract List<Partita> generaCalendario(List<GiorniSettimanali> giorni,int ppg,Date DataInizio,List<Prenotazione> prenotazioni);

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
               ", stato='" + stato + '\'' +
               ", dataInizio=" + dataInizio +
               ", numeroSquadre=" + (squadrePartecipanti != null ? squadrePartecipanti.size() : 0);
    }
}