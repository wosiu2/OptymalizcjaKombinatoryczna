
package ControlPackage;


import Data.Results;
import Data.TSP;
/**
 *Klasa implementująca algorytm BruteForce poszukiwania najkrótszego cyklu.
 * 
 * @author Wosiu
 */
public class BruteForceMod implements TSP{

    private Results results;

    /**
     *Konstruktor bez parametrowy.
     */
    public BruteForceMod() {
        results = new Results();
    }

    /**
     * Getter wyników otrzymanych na skutek działania algorytmu BF.
     * @return Metoda zwraca obiekt przechowujący wyniki
     */
    public Results getResaults() {
        return results;
    }

    /**
     *Metoda generująca tablicę wypełnioną kolejnymi cyframi. Do metody 
     * przekazać należy wielkość tablicy typu int.
     * @param n ilość elementów tablicy {0..n-1}
     * @return Tablica  n elementowa kolejnych liczb całkowitych.
     */
    public int[] generateTable(int n) {
        int[] table = new int[n];
        for (int i = 0; i < n; i++) {
            table[i] = i;
        }
        return table;
    }

    /**
     *Metoda do zamiany miejscami elementów tablicy.
     * @param table tablica elementów
     * @param i indeks elementu z tablicy table który zostanie zamieniony 
     * z elementem o indeksie k.
     * @param k indeks elementu z tablicy table który zostanie zamieniony
     * z elementem o indeksie i.
     */
    public void swap(int[] table, int i, int k) {
        int temp;
        temp = table[i];
        table[i] = table[k];
        table[k] = temp;
        
    }

    /**
     *Metoda jest generatorem permutacji i jednocześnie sprawdza czy kolejna 
     * permutacja wierzchołków nie daje najkrótszego cyklu. 
     * @param graph macierz sąsiedztwa.
     * @param table tablica kolejnych liczb będąca bazą do generowania permutacji.
     * @param index indeks ostatniego elementu tablicy który będzie podlegał permutacjom.
     */
    public void findShortest(double[][] graph, int[] table, int index) {

        if (index == 0) {

            if (results.getLengthOfRoad() > results.sumOfElements(graph, table)) {
                results.setLengthOfRoad(results.sumOfElements(graph, table));

                results.setListOfStops(table.clone());
            }

        } else {
            for (int i = 1; i <= index; i++) {
                swap(table, i, index);
                findShortest(graph, table, index - 1);
                swap(table, i, index);
            }
        }
    }

    /**
     *Metoda główna analizujaca macierz sąsiedztwa.
     * @param graph macierz sąsiedztwa do przeanalizaowania.
     */
    public void BFSearch(double[][] graph) {

        /*Pierwsze wstawienie długości różnej od zera*/
        results.setLengthOfRoad(results.sumOfElements(graph, generateTable(graph.length)));
        
        /* Rekurencyjna funkcja tworząca permutacje w poszukiwaniu wartości optymalnej*/
        findShortest(graph, generateTable(graph.length), generateTable(graph.length).length - 1);

    }
/**
 * Metoda uruchamiajaca działanie algorytmu stosowana przez tester. 
 * 
 * @param graph macierz sąsiedztwa do analizy
 */
    @Override
    public Results startAlgorithm(double[][] graph) {
        BFSearch(graph);
        return results;
    }

}
