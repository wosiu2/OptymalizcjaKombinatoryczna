/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import Data.Results;
import Data.TSP;
import Tools.MST;
import Tools.PreOrderWalk;

/**
 *
 * @author Włosek
 */
public class MSTAproximation implements TSP{

    private Results results;

    public MSTAproximation() {
        this.results = new Results();
    }

    public void aproxSearch(double[][] graph) {
        //utworzenie obieku MST za pomocą którego poszukuje sie minimalnego drzewa rozpinajacego
        MST tree = new MST(graph);

        /*Utworzenie obiektu PreOrderWalk za pomocą którego otrzymany zostanie wektor wierzchołków drzewa 
        w kolejności preorder.
         */
        PreOrderWalk walk = new PreOrderWalk(tree.getListOfNodes());
        
        walk.walk(tree.getRootOfTree());
        //Zapisuje wynik przejścia preorder w obiekcie results
        getResults().setListOfStops(walk.getPath());
        //zapisanie długości drogi do ubiektu results zwykorzystaniem funkcji w niej zaszytych
        getResults().setLengthOfRoad(getResults().sumOfElements(graph, getResults().getListOfStops()));

    }

    /**
     * metoda dostępowa do wyników. Aby otrzymać wyniki należy chociaż raz uruchomić
     * metodę aproxSearch()
     *
     * @return the results
     */
    public Results getResults() {
        return results;
    }


    @Override
    public Results startAlgorithm(double[][] graph) {
        aproxSearch(graph);
        return results;
    }

}
