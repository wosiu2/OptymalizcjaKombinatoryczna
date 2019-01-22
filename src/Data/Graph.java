/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Wosiu
 */
public class Graph {

    private LinkedList<City> cities = new LinkedList<City>();
    private int err = 0;
    private double[][] graphOfCities;
    private String[] namesOfCities;
    private Coordinates[] coordinatesOfCities;

    /**
     *Konstruktor bezparametrowy
     */
    public Graph() {

    }

    /**
     *Konstruktor jenoparametrowy. Pozwala na przekazanie do parametu tablicę miast.
     * @param tableOfCities tablica miast.
     */
    public Graph(City[] tableOfCities) {

        cities.addAll(Arrays.asList(tableOfCities));
    }

    /**
     *Metoda pozwalająca na dodawanie miast 
     * @param data miasto.
     */
    public void addCity(City data) {
        cities.add(data);
    }

    /**
     *Metoda sprawdzajaca czy dwa różne miasta nie są na tych samych współrzędnych.
     * Analizuje pojedynczą tablicą jednowymiarową.
     * jeśli metoda na trafi na taki obiekt zmienia jego na wartość 1 oraz zwiększa licznik błędów.
     * @param singleRow tablica jednowymiarowa (wiersz).
     * @param rowNumber numer wiersza.
     */
    public void makeCorrectionsInRow(double[] singleRow, int rowNumber) {
        for (int i=0;i<singleRow.length;i++) {
            if ((i != rowNumber) && (singleRow[i] == 0)) {
                err++;
                singleRow[i] = 1;
            }
        }
    }

    /**
     *Metoda sprawdza tablicę 2D w poszukiwaniu pokrywajacych się miast. Błędne 
     * miasto zostaje odsunięte o 1.
     * @param graph macierz sąsiedztwa.
     */
    public void checkIfCorrect(double[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            makeCorrectionsInRow(graph[i], i);
        }
    }

    /**
     * Metoda wyciagajaca wartości z listy wiązanej i przekazuje je do odpowiednich tablic.
     */
    public void extractList() {

        namesOfCities = new String[cities.size()];
        coordinatesOfCities = new Coordinates[cities.size()];
        int i = 0;
        for (Iterator<City> iterator = cities.iterator(); iterator.hasNext();) {
            City next = iterator.next();
            namesOfCities[i] = next.getCityName();
            coordinatesOfCities[i] = next.getCoordinates();
            i++;
        }

    }

    /**
     * Metoda generujaca macierz sąsiedztwa na podstawie wprowadzonych miast.
     */
    public void generateGraph() {
        graphOfCities = new double[cities.size()][cities.size()];
        double length;
        double deltaX;
        double deltaY;
        double deltaZ;

        extractList();

        for (int i = 0; i < cities.size(); i++) {
            for (int j = i; j < cities.size(); j++) {

                deltaX = Math.pow(coordinatesOfCities[i].getX() - coordinatesOfCities[j].getX(), 2);
                deltaY = Math.pow(coordinatesOfCities[i].getY() - coordinatesOfCities[j].getY(), 2);
                deltaZ = Math.pow(coordinatesOfCities[i].getZ() - coordinatesOfCities[j].getZ(), 2);

                length = Math.pow(deltaX+deltaY+deltaZ, 0.5);
                
                graphOfCities[i][j]=length;
                graphOfCities[j][i]=length;
            }

        }
        checkIfCorrect(graphOfCities);
    }

    /**
     * Getter zwracajacy listę wiazana miast.
     * @return the cities
     */
    public LinkedList<City> getCities() {

        return cities;
    }

    /**
     * 
     * Getter zwracajacy ilość błędnych miast.
     * @return the err
     */
    public int getErr() {
        return err;
    }

    /**
     * Getter macierzy sąsiedztwa. Należy wcześniej wygenerować 
     * macierz sąsiedztwa za pomocą metody generatGraph.
     * @return the graphOfCities
     */
    public double[][] getGraphOfCities() {
        return graphOfCities;
    }

    /**
     * Getter tablicy przechowującej nazwy miast.
     * @return the namesOfCities
     */
    public String[] getNamesOfCities() {
        return namesOfCities;
    }

}
