/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.text.DecimalFormat;

/**
 *
 * @author Wosiu
 */
public class GraphTool {

    /**
     *Metoda wypisująca liczby typu double w zadanym formacie.
     * 
     * @param pattern wzorzec formatowania liczby double.
     * @param value wartość double która zosyanie wypisana w zadanym formacie
     */
    public void formatedDoublePrinter(String pattern, double value) {
        DecimalFormat form = new DecimalFormat(pattern);
        String output = form.format(value).replace(",",".");
        System.out.print(output);

    }

    /**
     *
     * Metoda wypisuje zadaną tablelę na konsoli.
     * @param table tabela do wypisania na konsoli.
     */
    public void printTable(int[] table) {

        for (Integer u : table) {
            System.out.print(u);

        }
        System.out.println("");

    }

    /**
     *Metoda wypisuje zadana tablicę 2D. Wartosci z tablicy wypisuje 
     * na konsoli w formacie #.##.
     * @param table tablica typu double do wypisania na ekranie .
     */
    public void printGraph(double[][] table) {

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                formatedDoublePrinter("#0.00", table[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    /**
     *Metoda wypisuje na konsoli zadana tablicę typu int.
     * @param table tablica typu int do wypisaniana konsoli.
     */
    public void printTableOfTour(int[][] table) {

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     *Metoda kopiująca tablicę 2D.
     * @param table tablica 2D do przekopiowania.
     * @return Zwraca kopię tablicy przekazanej do metody.
     */
    public double[][] copyOfGraph(double[][] table) {
        double[][] copy = new double[table.length][];

        for (int i = 0; i < table.length; i++) {

            copy[i] = table[i].clone();

        }

        return copy;
    }

}
