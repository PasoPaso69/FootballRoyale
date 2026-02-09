package com.mycompany.footballroyale.domain;


import jakarta.persistence.*; // Importante per Hibernate 6+
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "squadre")
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <--- Fondamentale per l'autoincremento
    @Column(name = "id_squadra")
    private Long Id; 

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_foto_logo")
    private Foto logo;

    @Column(name = "maglia_casa")
    private String dettaglioMagliaCasa;

    @Column(name = "maglia_trasferta")
    private String dettaglioMagliaTrasferta;

    @Column(name = "vittorie", columnDefinition = "int default 0")
    private int vittorie;

    @Column(name = "pareggi", columnDefinition = "int default 0")
    private int pareggi;

    @Column(name = "sconfitte", columnDefinition = "int default 0")
    private int sconfitte;
    
    @OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Giocatore> giocatori = new ArrayList<>();

    // Costruttore vuoto obbligatorio
    public Squadra() {}
    
     public Squadra(Long id, String nome,Foto logo, 
                  String DettaglioMagliaCasa, String DettaglioMagliaTrasferta,int vittorie, int pareggi , int sconfitte) {
        this.Id = id;
        this.nome = nome;
        this.logo = logo;
        this.dettaglioMagliaCasa = DettaglioMagliaCasa;
        this.dettaglioMagliaTrasferta = DettaglioMagliaTrasferta;
        this.vittorie = vittorie;
        this.pareggi = pareggi;
        this.sconfitte = sconfitte;
     }
    
     public Long getId() { return Id; }

    public void setId(Long Id) { this.Id = Id; }



    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }



    public Foto getLogo() { return logo; }

    public void setLogo(Foto logo) { this.logo = logo; }



    public String getDettaglioMagliaCasa() { return dettaglioMagliaCasa; }

    public void setDettaglioMagliaCasa(String d) { this.dettaglioMagliaCasa = d; }



    public String getDettaglioMagliaTrasferta() { return dettaglioMagliaTrasferta; }

    public void setDettaglioMagliaTrasferta(String d) { this.dettaglioMagliaTrasferta = d; }

    

    public int getVittorie() { return vittorie; }

    public void setVittorie(int vittorie) { this.vittorie = vittorie; }



    public int getPareggi() { return pareggi; }

    public void setPareggi(int pareggi) { this.pareggi = pareggi; }



    public int getSconfitte() { return sconfitte; }

    public void setSconfitte(int sconfitte) { this.sconfitte = sconfitte; }



    public List<Giocatore> getGiocatori() { return giocatori; }

    public void setGiocatori(List<Giocatore> g) { this.giocatori = g; }
    
    public double getOverall(){
        
     
    int partiteGiocate = vittorie + pareggi + sconfitte;

        // Caso di default: nessuna partita giocata
        if (partiteGiocate == 0) {
            return 50.0;
        }

        // Calcolo punteggio ottenuto
        double puntiOttenuti = (vittorie * 3.0) + (pareggi * 1.0);
        
        // Calcolo punteggio massimo potenziale
        double puntiMassimiPossibili = partiteGiocate * 3.0;

        // Calcolo percentuale (0.0 a 1.0) e trasformazione in scala 0-100
        double overall = (puntiOttenuti / puntiMassimiPossibili) * 100;
        return overall;
    }
    

}