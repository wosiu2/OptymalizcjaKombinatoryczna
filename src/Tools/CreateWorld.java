/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Data.City;
import Data.Coordinates;
import Data.Graph;
import java.util.Random;

/**
 *
 * @author Włosek
 */
public class CreateWorld {
    private Graph world;

    /**
     *Konstruktor 
     */
    public CreateWorld() {
        this.world=new Graph();
    }
    
    /**
     * metoda generuje miasta zgodnie z podanymi parametrami. Miasta otrzymują nazwę "Miasto i"
     * @param amount ilość miast
     * @param X zakres po kierunku X
     * @param Y zakres po kierunku Y
     */
    public void generate(int amount,double X,double Y){
        Random r=new Random();
        double x,y;
        int j=0;
        
        for (int i = 0; i < amount; i++) {
            x=r.nextDouble()*X;
            y=r.nextDouble()*Y;
            
            getWorld().addCity(new City("Miasto "+j, new Coordinates(x, y)));
            j++;
            
        }
        getWorld().generateGraph();
        
    }

    /**
     * Metoda dostępowa do wygenerowanego świata. Wartości pojawia się po zastosowaniu metody generate
     * @return Zwraca losowy układ miast
     */
    public Graph getWorld() {
        return world;
    }
}
