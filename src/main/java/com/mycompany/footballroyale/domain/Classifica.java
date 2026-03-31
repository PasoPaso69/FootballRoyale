/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;

import com.mycompany.footballroyale.domain.Enum.CriterioClassifica;
import com.mycompany.footballroyale.domain.Strategieclassifica.CriterioOrdinamentoStrategy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 39327
 */
@Entity
@Table(name = "classifica")
public class Classifica<T> {
    
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Permette l'autoincremento
    @Column(name = "id_classifica")
    private Long Id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competizione")
    private Competizione competizione;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipologiaclassifica")
    private CriterioOrdinamentoStrategy TipoClassifica;
    
     @Column(name = "indirizzo")
    private Map<T,Integer> ListaTargetPunteggi;
         
    //@OneToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id_prenotazione")
    //private List<Prenotazione> prenotazioni;
    
    public Classifica() {}

    public Classifica(Long id, Competizione competizione,CriterioOrdinamentoStrategy TipoClassifica) {
        this.Id = id;
        this.competizione = competizione;
        this.TipoClassifica = TipoClassifica;
    }

    // Getter e Setter
    public Long getId() { return Id; }
    public void setId(Long Id) { this.Id = Id; }

    public Competizione getCompetizione() { return competizione; }
    public void setCompetizione(Competizione competizione) { this.competizione = competizione; }

    public CriterioOrdinamentoStrategy getCriterio() { return TipoClassifica; }
    public void setCriterio(CriterioOrdinamentoStrategy Tipologia) { this.TipoClassifica = Tipologia; }
    
    public Map<T,Integer> getClassifica(){return ListaTargetPunteggi;}
    public void setClassifica(Map<T,Integer> Punteggi){this.ListaTargetPunteggi= Punteggi;}
    
   //public List<Prenotazione> getPrenotazioni() {return this.prenotazioni;}
   //public void setPrenotazioni(List<Prenotazione> p) {this.prenotazioni=p;}
    
    
   public Map<T,Integer> Ordina (List<T> targetEstratti){
       return this.TipoClassifica.Ordina(targetEstratti, competizione);
   }
  
    @Override
public String toString() {
    return "Campetto{" +
            "id='" + Id + '\'' +
            ", competizione='" + competizione
            + '\'' +
            ", Tipologia Classifica='" + TipoClassifica + '\'' +
            ", Classifica='" + ListaTargetPunteggi + '\'' +
            '}';
}
    
}
