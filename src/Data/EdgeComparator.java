/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Comparator;

/**
 *
 * @author Włosek
 */
public class EdgeComparator implements Comparator<Edge>{

    @Override
    public int compare(Edge o1, Edge o2) {
      if(o1.getLength()==o2.getLength()){return 0;}
      else if(o1.getLength()>o2.getLength()){return 1;}
      else {return -1;}
    };
    
}
