/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;



/**
 *
 * @author Włosek
 */
public class Edge {
    private Integer a,b;
    private Double length;

    /**
     *
     * @param a wierzchołek 
     * @param b wierzchołek
     * @param length długość krawędzi pomiędzy wierzchołkami
     */
    public Edge(Integer a, Integer b, Double length) {
        this.a = a;
        this.b = b;
        this.length = length;
    } 

    /**
     * @return the a
     */
    public Integer getA() {
        return a;
    }

    /**
     * @return the b
     */
    public Integer getB() {
        return b;
    }

    /**
     * @return the length
     */
    public Double getLength() {
        return length;
    }
    
    
}
