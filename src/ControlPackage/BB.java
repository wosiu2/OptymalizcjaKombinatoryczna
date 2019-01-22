/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import Data.BBNode;
import Data.Matrix;
import Data.Results;
import Data.TSP;

/**
 *
 * @author Włosek
 */
public class BB implements TSP {

    private Results result = new Results();
    private BBNode leftLowestLB;

    public void rightSearch(BBNode n) {

        BBNode[] lista = new BBNode[3];
        int stack = 0;
        lista[0] = n.getRightNode();
        lista[1] = n.getLeftNode();
        while (true) {

            if (leftLowestLB == null) {
                leftLowestLB = n.getLeftNode();
            }

            if (lista[1].getLB() < leftLowestLB.getLB()) {
                leftLowestLB = lista[1];
            }
            if (lista[0].getMainGraph().getMatrix().length == 2) {
                result.setLengthOfRoad(n.getLB());
                break;
            }
            lista[0] = lista[0].getRightNode();
            lista[1] = lista[0].getLeftNode();
        }

        /* if (leftLowestLB == null) {
            leftLowestLB = n.getLeftNode();
        }
        if (n.getLeftNode().getLB() < leftLowestLB.getLB()) {
            leftLowestLB = n.getLeftNode();
        }

        if (n.getRightNode().getMainGraph().getMatrix().length == 2) {
            result=new Results();
            result.setLengthOfRoad(n.getLB());
            return;
        } else {
            rightSearch(n.getRightNode());
            
        }*/
    }

    public void BBSearch(BBNode n) {
        rightSearch(n);
        if (leftLowestLB.getLB() < result.getLengthOfRoad()) {
            BBSearch(leftLowestLB);
        } else {
            return;
        }

    }

    @Override
    public Results startAlgorithm(double[][] graph) {
        Matrix m = new Matrix(graph);
        m.matrixINF();

        BBSearch(new BBNode(m));

        return result;
    }

}
