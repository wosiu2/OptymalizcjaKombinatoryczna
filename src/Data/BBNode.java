/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import ControlPackage.BB;
import Tools.CopyTable;
import Tools.UF;

/**
 *
 * @author Włosek
 */
public class BBNode {

    private BBNode leftNode;
    private BBNode rightNode;
    private final Matrix mainGraph;
    private double LB = 0;
    private final double INF;
    private final int NEGLECT;
    private UF uf;
    private Edge[] list;
    private MatrixCoordinates maxPenelty;

    public BBNode(Matrix g) {

        this.INF = Double.MAX_VALUE;
        this.NEGLECT = -1;
        this.mainGraph = g;
        this.uf = new UF(g.getMatrix().length);

    }

    private boolean isLess(double a, double b) {
        return a < b;
    }

    private boolean isMore(double a, double b) {
        return a > b;
    }

    private double findMinInRow(double[][] g, int index, int neglectedElement) {
        double min = getINF();
        for (int j = 0; j < g[index].length; j++) {

            if (j == neglectedElement) {
                continue;
            }

            if (isLess(g[index][j], min)) {
                min = g[index][j];
            }
        }
        return min;
    }

    private double findMinInCol(double[][] g, int index, int neglectedElement) {
        double min = getINF();
        for (int j = 0; j < g.length; j++) {

            if (j == neglectedElement) {
                continue;
            }

            if (isLess(g[j][index], min)) {
                min = g[j][index];
            }
        }
        return min;
    }

    private double subRows(double[][] g) {
        double sum = 0;
        double min;
        for (int i = 0; i < g.length; i++) {
            min = findMinInRow(g, i, getNEGLECT());
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] == getINF()) {
                    continue;
                }
                g[i][j] = g[i][j] - min;
            }
            sum += min;
        }
        return sum;
    }

    private double subCol(double[][] g) {
        double sum = 0;
        double min;
        for (int i = 0; i < g[0].length; i++) {
            min = findMinInCol(g, i, getNEGLECT());
            for (int j = 0; j < g.length; j++) {

                if (g[j][i] == getINF()) {
                    continue;
                }
                g[j][i] = g[j][i] - min;
            }
            sum += min;
        }
        return sum;

    }

    private double getPenalty(double[][] g, int row, int col) {

        double penalty;

        penalty = findMinInRow(g, row, col) + findMinInCol(g, col, row);

        return penalty;
    }

    private MatrixCoordinates findMaxPenalty(double[][] g) {

        MatrixCoordinates e = new MatrixCoordinates(0, 0);
        e.setPenalty(getNEGLECT());

        double temp = 0;
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] != 0) {
                    continue;
                }
                if (isMore(temp = getPenalty(g, i, j), e.getPenalty())) {
                    e.setRow(i);
                    e.setCol(j);
                    e.setPenalty(temp);
                }
            }
        }

        return e;
    }

    private double findRightLB(double[][] g) {
        double lb = 0;
        double[][] temp;
        CopyTable cp = new CopyTable();
        temp = cp.copyDouble(g);
        lb += subRows(temp);
        lb += subCol(temp);
        return lb;
    }

    private void blockConnection(Matrix m) {
        for (int i = 0; i < m.getRows().length; i++) {

            for (int j = 0; j < m.getColumns().length; j++) {

                if (getUf().isConnected(m.getRows()[i], m.getColumns()[j])) {
                    m.getMatrix()[i][j] = INF;
                }
            }
        }
    }

    private Matrix reduceMatrix(Matrix m, int row, int col) {
        CopyTable cp = new CopyTable();
        Matrix reduced = new Matrix();

        reduced.setMatrix(cp.subMatrixDouble(m.getMatrix(), row, col));
        reduced.setRows(cp.subArrayInt(m.getRows(), row));
        reduced.setColumns(cp.subArrayInt(m.getColumns(), col));

        return reduced;
    }

    private void calculateLeafs() {

        Matrix tmp,right;
        CopyTable cp = new CopyTable();

        tmp = getMainGraph().copy();
        setList(new Edge[getMainGraph().getMatrix().length]);

        setLB(getLB() + subRows(tmp.getMatrix()));
        setLB(getLB() + subCol(tmp.getMatrix()));

        maxPenelty = findMaxPenalty(tmp.getMatrix());
        right = tmp.copy();
        //WYPEŁNIANIE LEWEGO LISCIA

        //blokowanie drogi powrotnej w lewym liściu
        tmp.getMatrix()[maxPenelty.getCol()][maxPenelty.getRow()] = INF;
        //zapisanie macierzy kosztów do lewego liścia oraz zwiekszenie LB o karny koszt nie wykożystanej scieżki
        leftNode = new BBNode(tmp);
        leftNode.setLB(getLB() + maxPenelty.getPenalty());
        leftNode.setUf(new UF(uf));

        //WYPEŁNIANIE PRAWEGO LIŚCIA
        uf.union(right.getRows()[maxPenelty.getRow()], right.getColumns()[maxPenelty.getCol()]);
        blockConnection(right);
        rightNode = new BBNode(reduceMatrix(right, maxPenelty.getRow(), maxPenelty.getCol()));
        rightNode.setUf(new UF(uf));
        rightNode.setLB(LB);

    }

    /*
    TODO

    - tworzenie lewej macierzy
    - tworzenie prawej macierzy
    - blokowanie przebiegu

    

    - 
     */
    public static void main(String[] args) {
        double[][] g = {{0, 10, 8, 9, 7}, {10, 0, 10, 5, 6}, {8, 10, 0, 8, 9}, {9, 5, 8, 0, 6}, {7, 6, 9, 6, 0}};

        BB alg = new BB();

        alg.startAlgorithm(g);

        int i = 1;
    }

    /**
     * @return the leftNode
     */
    public BBNode getLeftNode() {
        if (leftNode == null) {
            calculateLeafs();
            return leftNode;
        }

        return leftNode;

    }

    /**
     * @param leftNode the leftNode to set
     */
    public void setLeftNode(BBNode leftNode) {

        this.leftNode = leftNode;
    }

    /**
     * @return the rightNode
     */
    public BBNode getRightNode() {
        if (rightNode == null) {
            calculateLeafs();
            return rightNode;
        }

        return rightNode;
    }

    /**
     * @param rightNode the rightNode to set
     */
    public void setRightNode(BBNode rightNode) {

        this.rightNode = rightNode;
    }

    /**
     * @return the mainGraph
     */
    public Matrix getMainGraph() {
        return mainGraph;
    }

    /**
     * @return the INF
     */
    public double getINF() {
        return INF;
    }

    /**
     * @return the NEGLECT
     */
    public int getNEGLECT() {
        return NEGLECT;
    }

    /**
     * @return the LB
     */
    public double getLB() {
        return LB;
    }

    /**
     * @param LB the LB to set
     */
    public void setLB(double LB) {
        this.LB = LB;
    }

    /**
     * @return the uf
     */
    public UF getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(UF uf) {
        this.uf = uf;
    }

    /**
     * @return the list
     */
    public Edge[] getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(Edge[] list) {
        this.list = list;
    }
}
