/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author Włosek
 */
public class CopyTable {

    public int[][] copyInt(int[][] t) {
        int[][] cp = new int[t.length][t[0].length];

        for (int i = 0; i < t.length; i++) {

            for (int j = 0; j < t[i].length; j++) {
                cp[i][j] = t[i][j];
            }
        }
        return cp;
    }

    public int[] copyIntArray(int[] t) {
        int[] cp = new int[t.length];

        for (int i = 0; i < t.length; i++) {

            cp[i] = t[i];

        }
        return cp;
    }

    public double[][] copyDouble(double[][] t) {
        double[][] cp = new double[t.length][t[0].length];

        for (int i = 0; i < t.length; i++) {

            for (int j = 0; j < t[i].length; j++) {
                cp[i][j] = t[i][j];
            }
        }
        return cp;
    }

    public int[] subArrayInt(int[] t, int index) {
        int[] cp = new int[t.length - 1];
        int row = 0;

        for (int i = 0; i < t.length; i++) {
            if (i == index) {
                continue;
            }
            cp[row] = t[i];
            row++;
        }

        return cp;
    }

    public double[][] subMatrixDouble(double[][] t, int rowIndex, int colIndex) {
        double[][] cp = new double[t.length - 1][t[0].length - 1];
        int row = 0;
        int col;
        for (int i = 0; i < t.length; i++) {
            if (i == rowIndex) {
                continue;
            }
            col=0;
            for (int j = 0; j < t[0].length; j++) {
                if (j == colIndex) {
                    continue;
                }
                cp[row][col] = t[i][j];
                col++;
            }

            row++;
        }

        return cp;
    }

}
