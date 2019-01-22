/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Arrays;

/**
 *
 * @author DaczkusH
 */
public class LittleData {
    private double[][] graph;
    private Results results;
    
    //private double lenghtOfRoad;
    private double[][] setOfEdges;
    private double[][] antySetOfEdges;
    
    public LittleData(double[][] tab) {
        this.graph = zamienNaInf(tab);
        this.setOfEdges = new double[1][2];
        this.antySetOfEdges = new double[1][2];
    }

    
    public LittleData(LittleData obj) {
        /*double[][] temp = obj.getGraph().clone();
        this.graph = temp;
        if (temp.length > 0) {
            for (int i = 0; i < obj.getGraph().length; i++) {
                this.graph[i] = obj.getGraph()[i].clone();
            }    
        }
        temp = obj.getSetOfEdges().clone();
        this.setOfEdges = temp;
        if (temp.length > 0) {
            for (int i = 0; i < obj.getSetOfEdges().length; i++) {
                this.setOfEdges[i] = obj.getSetOfEdges()[i].clone();
            }    
        }
        temp = obj.getAntySetOfEdges().clone();
        this.antySetOfEdges = temp;
        if (temp.length > 0) {
            for (int i = 0; i < obj.getAntySetOfEdges().length; i++) {
                this.antySetOfEdges[i] = obj.getAntySetOfEdges()[i].clone();
            }    
        }*/
        this.graph = obj.getGraph().clone();
        this.setOfEdges = obj.getSetOfEdges().clone();
        this.antySetOfEdges = obj.getAntySetOfEdges().clone();
        this.results.setLengthOfRoad(obj.results.getLengthOfRoad());
    }
    
    /**
     * @return the graph
     */
    public double[][] getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(double[][] graph) {
        this.graph = graph;
    }

    /**
     * @return the setOfEdges
     */
    public double[][] getSetOfEdges() {
        return setOfEdges;
    }

    /**
     * @param setOfEdges the setOfEdges to set
     */
    public void setSetOfEdges(double[][] setOfEdges) {
        this.setOfEdges = setOfEdges;
    }
     
    /**
     * Metoda zamieniajaca macierz sasiedztwa. Dodaje 0'owy wiersz i 0'owa 
     * wypelniona liczbami 0:tabela.length oraz zamienia 0 na przekatnej na -1 
     * (nieskonczonosc)
     * @param tabela
     * @return
     */
    public double[][] zamienNaInf(double[][] tabela) {
        double[][] tempTab = new double[tabela.length+1][tabela.length+1];
        for (int i = 0; i < tabela.length+1; i++) {
            for (int j = 0; j < tabela.length+1; j++) {
                if (i == 0) {
                    tempTab[i][j] = j;
                } else if (j == 0) {
                    tempTab[i][j] = i;
                } else if (tabela[i-1][j-1] == 0) {
                    tempTab[i][j] = -1;
                } else {
                    tempTab[i][j] = tabela[i-1][j-1];
                }
            }
        }
        return tempTab;
    }
    
    public void printTab(String str) {
        System.out.println(str);
        for (int i = 0; i < graph.length; i++) {            
            System.out.println(Arrays.toString(graph[i]));
        }
    }
    
    /**
     * Metoda sprawdzajaca czy macierz nie zawiera wierszy/kolumn w calosci
     * wypelnionych -1 (nieskonczonosc)
     * @param tabela tabela do sprawdzenia
     * @return true tabela nie zawiera wierszy/kolumn z samym -1
     * false tabela nawiera wiersze/kolumny wypełnione -1
     */
    
    public boolean sprawdzWierszeKolumny() {
        boolean checkW, checkK;
        for (int i = 1; i < graph.length; i++) {
            checkW = false;
            checkK = false;
            for (int j = 1; j < graph.length; j++) {
                if (graph[i][j] != -1) {
                    checkW = true;
                }
                if (graph[j][i] != -1) {
                    checkK = true;
                }
            }
                if ((checkW == false)||(checkK == false)) {
                return false;
            }
        }
        return true;    
    }

    /**
     * @return the antySetOfEdges
     */
    public double[][] getAntySetOfEdges() {
        return antySetOfEdges;
    }

    /**
     * @param antySetOfEdges the antySetOfEdges to set
     */
    public void setAntySetOfEdges(double[][] antySetOfEdges) {
        this.antySetOfEdges = antySetOfEdges;
    }

    /**
     * @return the results
     */
    public Results getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(Results results) {
        this.results = results;
    }
}
