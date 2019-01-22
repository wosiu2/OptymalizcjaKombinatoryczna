package Tools;

import Tools.Exceptions.ParameterOutOfRangeInUFException;

/**
 * Klasa Union/Find pozawala na kontrolę czy przypisywane połacznia wierzchołków
 * nie powodują powstania cyklu. Może zostać wykorzystane przy implemantacji
 * algorytmu Kruskala poszukującego najmniejsze drzewo rozpinajace. Algorytm
 * pozwala łaczyć wierzchołki w podzbiory oraz kontrolę czy dana para
 * wierzchołków należy to danego podzbiou. Jeżeli dwa wierzchołki znajdują sie w
 * podzbiorze świadczy o powstaniu cyklu, a co z tym idzie połączenie takie jest
 * niedozwolone.
 *
 * @version 1.0
 * @since 2016-01-10
 * @author Michał Woch
 */
public class UF {

    private int noOfTrees;
    private int weight[];
    private int root[];

    /**
     * Konstruktor klasy UF (Union/Find)
     *
     * @param noOfNodes ilość węzłów grafu
     */
    public UF(int noOfNodes) {
        try {
            downValidation(noOfNodes);
        } catch (ParameterOutOfRangeInUFException e) {
            System.out.println("Błąd: błędny wynik walidacji wejścia do konstruktora");
        }
        this.noOfTrees = noOfNodes;
        weight = new int[noOfNodes];
        root = new int[noOfNodes];

        for (int i = 0; i < noOfNodes; i++) {
            root[i] = i;
            weight[i] = 0;
        }
    }
//konstruktor kopiujacy
    public UF(UF u) {

        CopyTable cp = new CopyTable();
        this.noOfTrees = u.getNoOfNodes();
        this.root =cp.copyIntArray(u.getRoot());
        this.weight=cp.copyIntArray(u.getWeight());
    }

    /**
     * Metoda poszukująca węzeł nadrzędny
     *
     * @param a numer węzła dla którego szukamy węzła nadrzędnego
     * @return zwraca wartość węzła nadrzędnego
     */
    public int find(int a) {
        try {
            validation(a);
        } catch (ParameterOutOfRangeInUFException e) {
            System.out.println("Błąd: błędny wynik walidacji wejścia do metody find");
        }
        while (a != getRoot()[a]) {
            root[a] = getRoot()[getRoot()[a]];
            a = getRoot()[a];
        }

        return a;
    }

    /**
     * Metoda łącząca węzły.
     *
     * @param a węzeł grafu
     * @param b węzeł grafu
     * @return zwraca wartość logiczna zależnie od tego czy udało się połączyć
     * węzły czy nie. Jeśli udało się połaczyć węzły w podzbiór metoda zwrac
     * true. Jeśli operacja się nie powiodła zwraca false.
     */
    public int union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        int returnRoot;

        if (rootA == rootB) {
            return -1;
        }

        if (getWeight()[rootB] < getWeight()[rootA]) {
            root[rootB] = rootA;
            returnRoot = rootA;
        } else if (getWeight()[rootB] > getWeight()[rootA]) {
            root[rootA] = rootB;
            returnRoot = rootB;
        } else {
            root[rootA] = rootB;
            returnRoot = rootB;
            getWeight()[rootB]++;
        }

        noOfTrees--;

        return returnRoot;
    }

    /**
     * Metoda sprawdza czy węzły znajduja się w jednym podzbiorze.
     *
     * @param a węzeł grafu
     * @param b węzeł grafu
     * @return zwraca wartość logiczna zależnie czy węzły znajduja sie w
     * podzbiorze czy nie.
     */
    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    private void validation(int a) throws ParameterOutOfRangeInUFException {

        if (a < 0 || a >= getRoot().length) {
            throw new ParameterOutOfRangeInUFException();

        }
    }

    private void downValidation(int a) throws ParameterOutOfRangeInUFException {
        if (a < 0) {
            throw new ParameterOutOfRangeInUFException();
        }
    }

    /**
     * Metoda zwraca ilość podzbiorów skaładowych grafu.
     *
     * @return ilość podzbiorów zbioru węzłów (wierzchołków) grafu
     */
    public int getNoOfNodes() {
        return noOfTrees;
    }

    /**
     * Metoda wypisuje kolejne wartoście tablicy węzłów nadrzędnych dla
     * poszczegolnych węzłów grafu.
     */
    public void printRootTable() {
        System.out.println("............................");
        System.out.println("Elementy tablicy węzłów nadrzędnych:");
        for (int i = 0; i < getRoot().length; i++) {
            System.out.println("Element " + i + ": " + getRoot()[i]);
        }
    }

    /**
     * Metoda wypisuje kolejne wartości wag kolejnych wierzchołków grafu.
     */
    public void printWeightTable() {
        System.out.println("............................");
        System.out.println("Elementy tablicy wagi drzewa:");
        for (int i = 0; i < getWeight().length; i++) {
            System.out.println("Element " + i + ": " + getWeight()[i]);
        }
    }

    /**
     * @return the weight
     */
    public int[] getWeight() {
        return weight;
    }

    /**
     * @return the root
     */
    public int[] getRoot() {
        return root;
    }

}
