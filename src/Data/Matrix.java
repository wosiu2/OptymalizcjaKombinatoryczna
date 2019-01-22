/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Tools.CopyTable;

/**
 *
 * @author Włosek
 */
public class Matrix {

    private double[][] matrix;
    private int[] rows;
    private int[] columns;

    /**
     * @return the matrix
     */
    public double[][] getMatrix() {
        return matrix;
    }

    public Matrix() {
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        this.rows = new int[matrix.length];
        this.columns = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            rows[i] = i;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            columns[i] = i;
        }

    }

    public void matrixINF() {

        double INF = Double.MAX_VALUE;
        int NEGLECT = -1;


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == j) {
                    matrix[i][j] = INF;
                    continue;
                }
                //matrix[i][j] = g[i][j];
            }
        }
    }

    public Matrix copy() {
        CopyTable cp = new CopyTable();
        Matrix tmp = new Matrix();

        tmp.setMatrix(cp.copyDouble(this.getMatrix()));
        tmp.setColumns(cp.copyIntArray(this.getColumns()));
        tmp.setRows(cp.copyIntArray(this.getRows()));
        
        return tmp;
    }

    /**
     * @param matrix the matrix to set
     */
    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * @return the rows
     */
    public int[] getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int[] rows) {
        this.rows = rows;
    }

    /**
     * @return the columns
     */
    public int[] getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(int[] columns) {
        this.columns = columns;
    }

}
