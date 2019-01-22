/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Wosiu
 */
public class Coordinates {
    private double x,y,z;

    /**
     *Konstruktor bez parametrowy
     */
    public Coordinates() {
    }

    /**
     *Konstruktor dwu parametrowy. Należy podać współrzędne x i y natomiast
     * współrzędna z jest domyślnie 0.
     * @param x współrzędna x;
     * @param y współrzędna y;
     */
    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    /**
     *Konstruktor trój parametrowy. .
     * @param x współrzędna x;
     * @param y współrzędna y;
     * @param z współrzędna z;
     */
    public Coordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Getter współrzędnej x.
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Setter współrzędnej x.
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter wspórzędnej y.
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Setter współrzednej y.
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Getter współrzędnej z.
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /**
     * Setter współrzędnej z.
     * @param z the z to set
     */
    public void setZ(double z) {
        this.z = z;
    }
    
    

    
    
}
