/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import Tools.GraphTool;
import Data.Results;
import Data.TSP;

/**
 *
 * @author Włosek
 */
public class Greed implements TSP {

    private Results results;

    /**
     * Konstruktor bez parametrowy
     */
    public Greed() {

        results = new Results();
    }

    /**
     * Metoda kasująca połaczenia w macierzy sąsiedztwa. Należy podać
     * wierzchołek który zostanie skasowany, a co za tym idzie skasowane zostaną
     * wszystkie połaczenia z tym wierzchołkiem.
     *
     * @param table macierz sąsiedztwa.
     * @param index wierzchołek do skasaowania.
     */
    public void eraseConections(double[][] table, int index) {

        for (int i = 0; i < table.length; i++) {
            table[i][index] = 0;
            table[index][i] = 0;
        }
    }

    /**
     * Metoda poszukująca najbliższego sąsiada do wierzchołka o zadanym
     * indeksie.
     *
     * @param table macierz sąsiedztwa.
     * @param index indeks wierzchołka bazowego.
     * @return Metoda zwraca indeks najbliższego sąsiada
     */
    public int findMinimalNeighbor(double[][] table, int index) {
        double minimalValue = 0;
        int indexOfMinimal = -1;
        for (int i = 0; i < table.length; i++) {
            if (minimalValue == 0) {
                minimalValue = table[index][i];
            }
            if ((table[index][i] == 0) || (minimalValue < table[index][i])) {
                //System.out.println(i);
            } else {
                minimalValue = table[index][i];
                indexOfMinimal = i;
            }
        }
        return indexOfMinimal;
    }

    /**
     * Metoda przechodzi zachłannie powierzchołkach poczawszy od wierzchołka o
     * zadanym indeksie.
     *
     * @param table macierz sąsiedztwa.
     * @param index indeks wierzchołka wyjściowego.
     * @return Zwraca tablicę typu int przechowującą kolejne wierzchołki cyklu.
     */
    public int[] singleTour(double[][] table, int index) {
        GraphTool pr = new GraphTool();

        double[][] tableClone = pr.copyOfGraph(table);
        int[] tableOfNodes = new int[table.length];
        int node = index;

        for (int i = 0; i < tableClone.length; i++) {
            tableOfNodes[i] = node;
            node = findMinimalNeighbor(tableClone, tableOfNodes[i]);
            eraseConections(tableClone, tableOfNodes[i]);
        }
        return tableOfNodes;
    }

    /**
     * Metoda genetrująca tablicę cykli dal kolejnych indeksów startowych
     * korzystajac z metody singleTour.
     *
     * @param table macierz sąsiedztwa.
     * @return zwraca tablicę 2D przechowującą cykle.
     */
    public int[][] globalTour(double[][] table) {
        int[][] tableOfTours = new int[table.length][];
        for (int i = 0; i < table.length; i++) {
            tableOfTours[i] = singleTour(table, i);
        }
        return tableOfTours;
    }

    /**
     * Metoda główna analizująca macierz sąsiedztwa.
     *
     * @param graph maciewrz sąsiedztwa.
     */
    public void GreadSearch(double[][] graph) {

        /* przeszukiwanie wygenerowanej tablicy dróg dzięki zaszytej metodzie w obiekcie przechowującej wyniki*/
        getResults().analizeResaults(graph, globalTour(graph));
    }

    /**
     * Metoda pozwalajaca na uruchomienie algorytmu przez obiekt testujący.
     *
     * @param graph macierz sąsiedztwa.
     */
    @Override
    public Results startAlgorithm(double[][] graph) {
        GreadSearch(graph);
        
        return results;
    }

    /**
     * Getter do obiektu przechowującego wyniki.
     *
     * @return the results
     */
    public Results getResults() {
        return results;
    }

}
