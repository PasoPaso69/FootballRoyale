/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.domain;

/**
 *
 * @author 39327
 */


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catalogo_squadre")
public class CatalogoSquadre {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_catalogo")
    private String Id;

    // Relazione Uno-a-Molti: Il catalogo contiene molte squadre
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_catalogo")
    private List<Squadra> listaSquadre = new ArrayList<>();

    public CatalogoSquadre() {}
    
    public CatalogoSquadre(String id,List<Squadra> listaSquadre) {
        this.Id = id;
        this.listaSquadre= listaSquadre;
    }

    // Metodi dal diagramma UML
    public void creaSquadra(String nomeSquadra, Foto logo, String colori, List<Giocatore> listaGiocatori) {
        // Qui andrebbe la logica per istanziare una nuova Squadra e aggiungerla alla lista
    }

    public Squadra getsquadra(String idSquadra) {
        return listaSquadre.stream()
                .filter(s -> s.getId().equals(idSquadra))
                .findFirst()
                .orElse(null);
    }

    public List<Squadra> getTutteLeSquadre() {
        return listaSquadre;
    }

    // Getter e Setter necessari per Hibernate
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public List<Squadra> getListaSquadre() {
        return listaSquadre;
    }

    public void setListaSquadre(List<Squadra> listaSquadre) {
        this.listaSquadre = listaSquadre;
    }
}

