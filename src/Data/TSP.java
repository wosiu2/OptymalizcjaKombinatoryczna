
package Data;

/**
 *
 * @author Włosek
 */
public interface TSP {
 
    /**
     *Interface pozwalający na obsługą różnych algorytmów przez klasę testującą.
     * @param graph macierz sąsiedztwa.
     * @return 
     */
    public Results startAlgorithm(double[][] graph); 
    
}
