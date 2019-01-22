/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Włosek
 */
public class Results {
    
    private double lengthOfRoad;
    private int[] listOfStops;
    
    public Results () {
        this.lengthOfRoad = 0;
    }

    /**
     *Metoda zwraca długość cyklu opartego na wierzchołkach umieszczonych 
     * w tablicy permutations. Ostatni wierzchołek w tablicy jest domyślnie 
     * połączony z pierwszym wierzchołkiem w tablicy.
     * @param graph macierz sąsiedztwa wypełniona odległościami 
     * pomiędzy wierzchołkami. 
     * @param permutations tablica kolejno odwiedzonych wierzchołków. 
     * @return Długość cyklu powstałego z przejścia kolejno po wierzchołkach 
     * zawartych w tablicy premutations. 
     */
    public double sumOfElements(double[][] graph, int[] permutations) {
        double length = 0;
        for (int i = 0; i < permutations.length; i++) {

            if (i == permutations.length - 1) {
                length += graph[permutations[i]][permutations[0]];
            } else {
                length += graph[permutations[i]][permutations[i + 1]];
            }
        }
        return length;
    }

    /**
     *Metoda analizuje wyniki poszukiwania najkrótszego cyklu przechowywane
     * w tablicy permutations.  
     * @param graph macierz sąsiedztwa
     * @param permutations tablica rozwiązań
     */
    public void analizeResaults(double[][] graph, int[][] permutations){
            lengthOfRoad=0;
            for (int i = 0; i < graph.length; i++) {
                if(lengthOfRoad==0){
                    lengthOfRoad=sumOfElements(graph, permutations[i]);
                    listOfStops=permutations[i];
                }
                if (lengthOfRoad>sumOfElements(graph, permutations[i])) {
                  lengthOfRoad=sumOfElements(graph, permutations[i]);
                  listOfStops=permutations[i];
                }
            }
            
        }
    /**
     * @return the lengthOfRoad
     */
    public double getLengthOfRoad() {
        return lengthOfRoad;
    }

    /**
     * @param lengthOfRoad the lengthOfRoad to set
     */
    public void setLengthOfRoad(double lengthOfRoad) {
        this.lengthOfRoad = lengthOfRoad;
    }

    /**
     * @return the listOfStops
     */
    public int[] getListOfStops() {
        return listOfStops;
    }

    /**
     * @param listOfStops the listOfStops to set
     */
    public void setListOfStops(int[] listOfStops) {
        this.listOfStops = listOfStops;
    }

   

 
 
}
