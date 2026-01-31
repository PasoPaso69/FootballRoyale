/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Entity;

public class Campionato extends Competizione {
    private int anno;
    private int numeroGiornate;

    public Campionato() { super(); }

    public int getAnno() { return anno; }
    public void setAnno(int anno) { this.anno = anno; }
    public int getNumeroGiornate() { return numeroGiornate; }
    public void setNumeroGiornate(int numeroGiornate) { this.numeroGiornate = numeroGiornate; }
}
