/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.view;

import java.util.Scanner;

/**
 *
 * @author 39327
 */
public class FootballRoyaleView {
    
        private final Scanner scanner;

    public FootballRoyaleView() {
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu(){
        System.out.println("|=== FOOTBALL ROYALE ===|");
        System.out.println("|1. Crea Squadra       |");
        System.out.println("|2. Crea Campionato   |");
        System.out.println("|3. chiudi                |");
        System.out.println("|=======================|");
        System.out.print("Seleziona un'opzione:    ");

    }
    
        public String getUserInput(){
        return scanner.nextLine();
    }

    public int getUserInputInt(){
        return scanner.nextInt();
    }



    public void exitGame(){
        System.out.println("Grazie per aver usato la nostra app");
    }

    public void invalidOption(){
        System.out.println("Opzione non valida. Riprova!");
    }
    
}
