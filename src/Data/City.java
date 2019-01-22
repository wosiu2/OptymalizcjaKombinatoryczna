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
public class City {
  private String cityName;
  private Coordinates coordinates;

    /**
     *Konstruktor bez parametrowy
     */
    public City() {
    }

    /**
     *Konstruktor dwu parametrowy.
     * @param cityName nazwa miasta.
     * @param coordinates współrzedne miasta przekazane przez obiekt typu
     * Coordinates.
     */
    public City(String cityName, Coordinates coordinates) {
        this.cityName = cityName;
        this.coordinates = coordinates;
    }
 
      /**
       * Getter
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Getter
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Setter
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
  
  
          
}
