/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import Data.LittleData;
import Data.Results;
import Data.TSP;
import Tools.CreateWorld;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author DaczkusH
 */
public class Little implements TSP {
    
    private double[][] graph;
    private Results results;
    
    //private double lenghtOfRoad;
    private double[][] setOfEdges;
    private double[][] antySetOfEdges;
    
    public Little() {
        this.setOfEdges = new double[1][2];
        this.antySetOfEdges = new double[1][2];
    }
    
    public Little(Little obj) {
        this.graph = obj.getGraph().clone();
        this.setOfEdges = obj.getSetOfEdges().clone();
        this.antySetOfEdges = obj.getAntySetOfEdges().clone();
        this.results = obj.getResults();
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
     * Metoda odejmujaca od wszytskich elementow wiersza najmniejszy element w wierszu
     * @param tabela analizowana tabela
     * @return suma wszytskich najmniejszych elementow w wierszach
     */
    public void odejmijMinimumOdWierszy(Little tabela) {
        double sumaMin;
        double[] kolumnaMin = new double[tabela.getGraph().length];
        Arrays.fill(kolumnaMin, 999999999);
        kolumnaMin[0] = 0;
        for (int i = 1; i < tabela.getGraph().length; i++) {
            for (int j = 1; j < tabela.getGraph().length; j++) {
                if ((tabela.getGraph()[i][j] != -1) && (tabela.getGraph()[i][j] < kolumnaMin[i])) {
                    kolumnaMin[i] = tabela.getGraph()[i][j];
                }
            }
            if (kolumnaMin[i] == 999999999) kolumnaMin[i] = 0;
        }
        sumaMin = Arrays.stream(kolumnaMin).sum();
        //System.out.println(Arrays.toString(kolumnaMin));
        for (int i = 1; i < tabela.getGraph().length; i++) {
            for (int j = 1; j < tabela.getGraph().length; j++) {
                if (tabela.getGraph()[i][j] > 0) {
                    tabela.getGraph()[i][j] -= kolumnaMin[i];
                }
            }
        }
        sumaMin += tabela.getResults().getLengthOfRoad();
        tabela.getResults().setLengthOfRoad(sumaMin);
    }
    
    /**
     * Metoda odejmujaca od wszytskich elementow kolumny najmniejszy element w kolumnie
     * @param tabela analizowana tabela
     * @return suma wszytskich najmniejszych elementow w kolumnach     
     */
    public void odejmijMinimumOdKolumn(Little tabela) {
        double sumaMin;
        double[] wierszMin = new double[tabela.getGraph().length];
        Arrays.fill(wierszMin, 999999999);
        wierszMin[0] = 0;
        for (int i = 1; i < tabela.getGraph().length; i++) {
            for (int j = 1; j < tabela.getGraph().length; j++) {
                if ((tabela.getGraph()[j][i] != -1) && (tabela.getGraph()[j][i] < wierszMin[i])) {
                    wierszMin[i] = tabela.getGraph()[j][i];
                }
            }
            if (wierszMin[i] == 999999999) wierszMin[i] = 0;
        }
        sumaMin = Arrays.stream(wierszMin).sum();
        //System.out.println(Arrays.toString(wierszMin));
        for (int i = 1; i < tabela.getGraph().length; i++) {
            for (int j = 1; j < tabela.getGraph().length; j++) {
                if (tabela.getGraph()[j][i] > 0) {
                    tabela.getGraph()[j][i] -= wierszMin[i];
                }
            }
        }
        sumaMin += tabela.getResults().getLengthOfRoad();
        tabela.getResults().setLengthOfRoad(sumaMin);
    }

     /**
     * Metoda znajduje maxymalny element macierzy
     * @param tabela analizowana tabela
     * @return wartosc najwiekszego elementu macierzy
     */
    public double znajdzMax(double[][] tabela) {
        double temp = 0;
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela.length; j++) {
                if (tabela[i][j] > temp) {
                    temp = tabela[i][j];
                }
            }
        }
        return temp;
    }
    
    public double[][] dodajNaKoniec(double[][] tab, double[] element) {
        double[][] array = new double[tab.length+1][tab[0].length];
        double[][] tempArray = new double[1][2];
        if (tab[0][0] == 0) {
            tempArray[0] = element;
            return tempArray;
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                array[i][j] = tab[i][j];
            }
        }
        array[tab.length][0] = element[0];
        array[tab.length][1] = element[1];
        return array;   
    }
    
    /**
     * Metoda poszukujaca elementu do usuniecia z macierzy.
     * @param tabela analizowana tabela
     * @return wektor dwuarumentowy {i,j} i - wiersz, j - kolumna
     */
    public double[] znajdzElementDoUsuniecia(Little tabela) {
        double[][] tempTab = new double[tabela.getGraph().length][tabela.getGraph().length];
        double[] wierszKolumna = new double[2];
        for (double[] row: tempTab) {
            Arrays.fill(row, 0);
        }
        //System.out.println(Arrays.deepToString(tabela));
        for (int i = 1; i < tabela.getGraph().length; i++) {
            for (int j = 1; j < tabela.getGraph().length; j++) {
                if (tabela.getGraph()[i][j] == 0) {
                    double minW = 999999999, minK = 999999999;
                    for (int k = 1; k < tabela.getGraph().length; k++) {
                        if ((tabela.getGraph()[i][k] != -1) && (k != j) && (tabela.getGraph()[i][k] < minW)) {
                            minW = tabela.getGraph()[i][k];
                        }
                        if ((tabela.getGraph()[k][j] != -1) && (k != i) && (tabela.getGraph()[k][j] < minK)) {
                            minK = tabela.getGraph()[k][j];
                        }
                    }
                    tempTab[i][j] = minW + minK;
                    if ((minW == 999999999)||(minK == 999999999)) {
                        tempTab[i][j] = -1;
                    }
                }
            }
        }
        //System.out.println("TabelaKosztow" + Arrays.deepToString(tempTab));
        //System.out.println(znajdzMax(tempTab));
        for (int i = 1; i < tabela.getGraph().length; i++) {
            for (int j = 1; j < tabela.getGraph().length; j++) {
                if (tempTab[i][j] == -1) {
                    wierszKolumna[0] = tabela.getGraph()[i][0];
                    wierszKolumna[1] = tabela.getGraph()[0][j];
                    return wierszKolumna;
                }
                if (tempTab[i][j] == znajdzMax(tempTab) && (wierszKolumna[0] == 0) && (wierszKolumna[1] == 0)) {
                    wierszKolumna[0] = tabela.getGraph()[i][0];
                    wierszKolumna[1] = tabela.getGraph()[0][j];                    
                }
            }
        }        
        return wierszKolumna;
    }
    
    /**
     * Metoda usuwajaca wiersz i kolumne w macierzy. Analizuje 0'owy wiersz tabeli w
     * poszukiwaniu wk[1] oraz 0'wa kolumne w poszukiwaniu wk[0]. Ponadto jesli to 
     * mozliwe wstawia -1 w nowa tabele (np. usuwajac {2,3} boluje {3,2})
     * @param tabela analizowana tabela
     * @param wk tabela 2 elementowa 0 element okresla wiersz 1 element okresla kolumne
     * @return macierz z usunietym wierszem i kolumna
     */
    public void usunWierszKolumne(Little tabela, double[] wk) {

        double[][] tempTab = new double[tabela.getGraph().length-1][tabela.getGraph().length-1];
        if (tabela.getGraph().length > 3) {
            for (int i = 0; i < tabela.getGraph().length; i++) {
                for (int j = 0; j < tabela.getGraph().length; j++) {
                    if ((tabela.getGraph()[i][0] < wk[0]) && (tabela.getGraph()[0][j] < wk[1])) {
                        tempTab[i][j] = tabela.getGraph()[i][j];
                    }
                    if ((tabela.getGraph()[i][0] < wk[0]) && (tabela.getGraph()[0][j] > wk[1])) {
                        tempTab[i][j-1] = tabela.getGraph()[i][j];
                    }
                    if ((tabela.getGraph()[i][0] > wk[0]) && (tabela.getGraph()[0][j] < wk[1])) {
                        tempTab[i-1][j] = tabela.getGraph()[i][j];
                    }
                    if ((tabela.getGraph()[i][0] > wk[0]) && (tabela.getGraph()[0][j] > wk[1])) {
                        tempTab[i-1][j-1] = tabela.getGraph()[i][j];
                    }
                }
            }
        } else {
            for (int i = 0; i < tempTab.length; i++) {
                for (int j = 0; j < tempTab.length; j++) {
                    if ((tabela.getGraph()[0][j+1] != wk[1]) && (tabela.getGraph()[i+1][0] != wk[0])) {
                        tempTab[i][j] = tabela.getGraph()[i+1][j+1];
                        tempTab[0][1] = tabela.getGraph()[0][j+1];
                        tempTab[1][0] = tabela.getGraph()[i+1][0];
                    }
                }
            }
        }
        tabela.setSetOfEdges(dodajNaKoniec(tabela.getSetOfEdges(),wk));
        int checkW = 0;
        int checkK = 0;
        while ((checkW * checkK == 0) && (tempTab.length > 2)){
            for (int i = 1; i < tempTab.length; i++) {
                if (tempTab[0][i] == wk[0]) checkK = 1;
                if (tempTab[i][0] == wk[1]) checkW = 1;
            }
            if ((checkW == 1) && (checkK ==1)) break;
            if ((checkW == 0) || (checkK == 0)) {
                for (int j = 0; j < tabela.getSetOfEdges().length; j++) {
                    if (wk[1] == tabela.getSetOfEdges()[j][0]) wk[1] = tabela.getSetOfEdges()[j][1];
                    if (wk[0] == tabela.getSetOfEdges()[j][1]) wk[0] = tabela.getSetOfEdges()[j][0];
                }
            }            
        }
        /*for (int i = 0; i < tempTab.length; i++) {
            if (tempTab[0][i] == wk[0]) w = i;
            if (tempTab[i][0] == wk[1]) k = i;                
            if (w*k != 0) tempTab[k][w] = -1;
        }*/
        for (int i = 0; i < tempTab.length; i++) {
            for (int j = 0; j < tempTab.length; j++) {
                if ((tempTab[i][0] == wk[1]) && (tempTab[0][j] == wk[0])) {
                    tempTab[i][j] = -1;
                }
            }
        }
        //System.out.println(Arrays.deepToString(tempTab));
        tabela.setGraph(tempTab);
    }    

    /**
     * Metoda . Analizuje 0'owy wiersz tabeli w poszukiwaniu wk[1] oraz 0'wa 
     * kolumne w poszukiwaniu wk[0]. Nastepnie wstawia -1 we wskazane w wektorze
     * wk miejsce
     * @param tabela analizowana tabela
     * @param wk tabela 2 elementowa 0 element okresla wiersz 1 element okresla kolumne
     * @return macierz ze wstawionym -1
     */
    public void zaznaczBrakDrogi(Little tabela, double[] wk) {
        //double[][] tempTab = new double[tabela.length][tabela.length];
        for (int i = 0; i < tabela.getGraph().length; i++) {
            for (int j = 0; j < tabela.getGraph().length; j++) {
                //tempTab[i][j] = tabela[i][j];
                if ((tabela.getGraph()[i][0] == wk[0]) && (tabela.getGraph()[0][j] == wk[1])) {
                    tabela.getGraph()[i][j] = -1;
                }
            }
        }
        tabela.setAntySetOfEdges(dodajNaKoniec(tabela.getAntySetOfEdges(),wk));
    }
    
    /**
     * Metoda . Analizuje 0'owy wiersz tabeli w poszukiwaniu wk[1] oraz 0'wa 
     * kolumne w poszukiwaniu wk[0]. Nastepnie wstawia -1 we wskazane w wektorze
     * wk miejsce
     * @param tabela analizowana tabela
     * @param wk tabela 2 elementowa 0 element okresla wiersz 1 element okresla kolumne
     * @return macierz ze wstawionym -1
     */
    public double[][] zaznaczBrakDrogi2(double[][] tabela, double[] wk) {
        double[][] tempTab = new double[tabela.length][tabela.length];
        for (int i = 0; i < tempTab.length; i++) {
            for (int j = 0; j < tempTab.length; j++) {
                tempTab[i][j] = tabela[i][j];
                if ((tempTab[i][0] == wk[0]) && (tempTab[0][j] == wk[1])) {
                    tempTab[i][j] = -1;
                }
            }
        }
        //System.out.println(Arrays.deepToString(tempTab));
        return tempTab;
    }
    
    public void P(Little tab) {
        this.zaznaczBrakDrogi(tab, this.znajdzElementDoUsuniecia(tab));
        //tab.printTab("Graf po usunieciu drogi");
        //System.out.println("Edges " + Arrays.deepToString(tab.getSetOfEdges()));
        //System.out.println("AntyEdges " + Arrays.deepToString(tab.getAntySetOfEdges()));
        softLP(tab);
    }
    
    public void L(Little tab) {
        this.usunWierszKolumne(tab, this.znajdzElementDoUsuniecia(tab));
        //tab.printTab("Graf po usunieciu kolumn");
        //System.out.println("Edges " + Arrays.deepToString(tab.getSetOfEdges()));
        //System.out.println("AntyEdges " + Arrays.deepToString(tab.getAntySetOfEdges()));
        softLP(tab);
    }
    
    public void softLP(Little tab) {
        //tab.printTab("Graf wejsciowy");
        this.odejmijMinimumOdWierszy(tab);
        //tab.printTab("Graf po odjeciu wierszami");
        //System.out.println("Droga " + tab.getLenghtOfRoad());
        this.odejmijMinimumOdKolumn(tab);
        //tab.printTab("Graf po odjeciu kolumnami");
        //System.out.println("Droga " + tab.getLenghtOfRoad());
    }
    
    public void CzeszLittla(double[][] graph) {
        LinkedList<Little> czeszLittla = new LinkedList<>();
        Little bf = new Little();        
        bf.setGraph(zamienNaInf(graph));
        softLP(bf);
        czeszLittla.add(bf);  
        Comparator<Little> com=new Comparator<Little>() {
            @Override
            public int compare(Little o1, Little o2) {               
                if (o1.getResults().getLengthOfRoad() > o2.getResults().getLengthOfRoad()) {
                    return 1;
                } 
                else if (o1.getResults().getLengthOfRoad() < o2.getResults().getLengthOfRoad()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        };        
        while (Collections.min(czeszLittla, com).getSetOfEdges().length < graph.length) {
            Little temp=Collections.min(czeszLittla, com);
            czeszLittla.add(new Little(temp));
            czeszLittla.add(new Little(temp));
            L(czeszLittla.get(czeszLittla.size()-1));
            P(czeszLittla.get(czeszLittla.size()-2));
            for (int i = 0; i < czeszLittla.size(); i++) {
                if (!czeszLittla.get(i).sprawdzWierszeKolumny()) {
                    czeszLittla.remove(i);
                }
            }
            czeszLittla.remove(temp);
        }
        System.out.println(Collections.min(czeszLittla, com).getResults().getLengthOfRoad());
        System.out.println("Edges" + Arrays.deepToString(Collections.min(czeszLittla, com).getSetOfEdges()));
        System.out.println("Anty" + Arrays.deepToString(Collections.min(czeszLittla, com).getAntySetOfEdges()));
    }
    
    @Override
    public Results startAlgorithm(double[][] graph) {
        CzeszLittla(graph);
        return getResults();
    }

public static void main(String[] args) {
        double[][] testgraph;
        CreateWorld wo = new CreateWorld();
        wo.generate(10, 10, 10);
        testgraph = wo.getWorld().getGraphOfCities();
        Little temp = new Little();
        temp.startAlgorithm(testgraph);
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
}