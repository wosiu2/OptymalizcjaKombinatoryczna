/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Data.Edge;
import Data.EdgeComparator;
import Data.TreeNode;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Klasa służąca do poszukiwania minimalnego drzewa rozpinajacego. Wywołanie
 * metody find MST powoduje wykonanie algorytmu poszukiwania najmniejszego
 * drzewa rozpinajacego oraz stworzenie struktury drzewiastej która moze
 * posłużyć do rozwiazania problemu komiwojażera. Algorytm oparty jest na
 * strukturze Union/Find i na algorytmie Kruskala.
 *
 * @author Włosek
 */
public class MST {

    private Edge[] listOfEdges;
    private Edge[] listOfMST;
    private double[][] graph;
    private TreeNode[] listOfNodes;
    private int lowestIndex = 0;

    /**
     * Konstruktor. Jako element wejściowy należy podać macierz sąsiedztwa.
     *
     * @param g macierz sąsiedztwa
     */
    public MST(double[][] g) {
        this.listOfEdges = new Edge[g.length * (g.length - 1) / 2];
        this.listOfMST = new Edge[g.length - 1];
        this.listOfNodes = new TreeNode[g.length];
        this.graph = g;

    }

    private void changeParent(TreeNode a) {
        int parent, index;
        if (a.getParent() == a.getIndex()) {
            return;
        }
        parent = a.getParent();
        index = a.getIndex();

        listOfNodes[parent].getChildren().remove(a);
        a.getChildren().add(listOfNodes[parent]);
        a.setParent(index);
        a.setHaveParent(false);
        listOfNodes[parent].setParent(index);
        listOfNodes[parent].setHaveParent(true);

    }

    private boolean isLower(int a, int b) {
        if (listOfNodes[a].getLevel() <= listOfNodes[b].getLevel()) {
            return false;
        }
        return true;
    }

    private void levels(TreeNode n) {

        if (n.getHaveParent() == false) {
            n.setLevel(0);
        }
        if (n.getChildren().isEmpty()) {

            return;
        }
        for (int i = 0; i < n.getChildren().size(); i++) {
            n.getChildren().get(i).setLevel(n.getLevel() + 1);
            if (isLower(n.getChildren().get(i).getIndex(), lowestIndex)) {
                lowestIndex=n.getChildren().get(i).getIndex();
            }

            levels(n.getChildren().get(i));
        }

    }

    private void moveToTop(TreeNode a) {

        if (a.getParent() == a.getIndex()) {
            return;
        }
        moveToTop(listOfNodes[a.getParent()]);
        changeParent(a);
    }

    private void createEdgeList() {
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = (i + 1); j < graph.length; j++) {
                listOfEdges[count] = new Edge(i, j, graph[i][j]);
                count++;
            }
        }
    }

    /**
     * Metoda tworząca połaczenia w strukturze TreeNode. Podobieństwo do listy
     * jednokierunkowej. Każdy element przechowuje informację o rodzicu,
     * indeksie oraz tabelę potomków.
     *
     * @param a węzeł typu TreeNode który należy połaczyc
     * @param b węzeł typu TreeNode który należy połączyć
     * @return zwraca wartość typu logicznego zaleznie czy operacja połaczenia
     * wykonana została poprawnie czy też nie
     */
    private Boolean connect(TreeNode a, TreeNode b) {

        if (!(b.getHaveParent() || a.getHaveParent())) {
            if (a.getWeightOfSubTree() >= b.getWeightOfSubTree()) {
                b.setHaveParent(true);
                b.setParent(a.getIndex());
                a.getChildren().add(b);
                // System.out.println(a.getIndex() + "->" + b.getIndex() + " :Weight " + a.getIndex() + ":" + a.getWeightOfSubTree());
                a.incWeightOfSubTree();
                return true;
            } else {
                a.setHaveParent(true);
                a.setParent(b.getIndex());
                b.getChildren().add(a);
                //  System.out.println(b.getIndex() + "->" + a.getIndex() + " :Weight " + b.getIndex() + ":" + b.getWeightOfSubTree());
                b.incWeightOfSubTree();
                return true;
            }

        }

        if (!(b.getHaveParent())) {
            b.setHaveParent(true);
            b.setParent(a.getIndex());
            a.getChildren().add(b);
            //System.out.println(a.getIndex() + "->" + b.getIndex());
            return true;
        } else if (!(a.getHaveParent())) {
            a.setHaveParent(true);
            a.setParent(b.getIndex());
            b.getChildren().add(a);
            //System.out.println(b.getIndex() + "->" + a.getIndex());
            return true;
        }

        return false;
    }

    /**
     * Metoda uruchamia wykonanie algorytmu moszukiwania MST oraz worzy
     * strukturę drzewiastą. Po wykonaniu tej metody można odczytywać krawędzie
     * drzewa rozpinajacego oraz elementy struktury drzewiastej.
     *
     */
    public void findMST() {
        int count = 0;
        int size;
        Integer rootIndex;
        LinkedList<Edge> tempList = new LinkedList<Edge>();

        this.listOfNodes = new TreeNode[graph.length];
//Tworzenie listy węzłów
        for (int i = 0; i < listOfNodes.length; i++) {

            listOfNodes[i] = new TreeNode();

            listOfNodes[i].setIndex(i);
            listOfNodes[i].setParent(i);
        }

        UF uf = new UF(graph.length);
        createEdgeList();
        Arrays.sort(listOfEdges, new EdgeComparator());

        //Blok ustawiajacy wartości tablicy indeksów
        for (int i = 0; i < listOfEdges.length; i++) {

            if ((rootIndex = uf.union(listOfEdges[i].getA(), listOfEdges[i].getB())) != -1) {
                //System.out.println("ROOT:" + rootIndex);

                //jeśli oba węzły znajduja się w osobnych podgrafach na pozycjach uniemoących
                //połączenie, krawędz odkładana jest so listy powtórnego przetworzenia
                if (connect(listOfNodes[listOfEdges[i].getA()], listOfNodes[listOfEdges[i].getB()]) == false) {

                    tempList.add(listOfEdges[i]);

                }

                listOfMST[count] = listOfEdges[i];
                count++;
                if (!(count < listOfMST.length)) {
                    //System.out.println("Wykonano:"+count);
                    break;
                    //return true;
                }
            }

        }
        size = tempList.size();
        for (int i = 0; i < size; i++) {
            int a, b;

            a = tempList.getFirst().getA();
            b = tempList.getFirst().getB();

            moveToTop(listOfNodes[a]);
            connect(listOfNodes[a], listOfNodes[b]);
            tempList.removeFirst();
        }

        levels(listOfNodes[getRootOfTree()]);
        
       moveToTop(listOfNodes[lowestIndex]);
        //return false;
    }

    /**
     * Metoda zwraca wierzchołek struktury drzewiastej
     *
     * @return zwraca indeks wierzchołka struktury drzewiastej. Wartość typu
     * int.
     */
    public int getRootOfTree() {
        int root = -1;

        for (TreeNode el : listOfNodes) {
            if (el.getIndex() == el.getParent()) {

                root = el.getIndex();
            }
        }

        return root;
    }

    /**
     * Metoda drukująca listę krawędzi w konsoli.
     *
     * @param list lista do wydrukowania
     */
    public void printList(Edge[] list) {

        for (int i = 0; i < list.length; i++) {
            System.out.println("(" + list[i].getA() + "," + list[i].getB() + ")" + ": " + list[i].getLength());
        }
    }

    /**
     * Metoda dostępowa zwracajaca listę krawędzi
     *
     * @return lista krawędzi
     */
    public Edge[] getListOfEdges() {
        return listOfEdges;
    }

    /**
     * Metoda dostępowa zwracająca listę krawędzi MST.
     *
     * @return lista krawędzi MST
     */
    public Edge[] getListOfMST() {
        return listOfMST;
    }

    /**
     * Metoda dostępowa zwracajaca strukturę drzewiastą MST
     *
     * @return tablica węzłów
     */
    public TreeNode[] getListOfNodes() {
        findMST();
        return listOfNodes;
    }

}
