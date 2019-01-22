/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Data.TreeNode;

/**
 *Obiekty klasy Preorder pozwalaja wykonanie przejścia przez strukture drzewiastą MST oraz przechowują wyniki obliczeń.
 * @version 1.0
 * @since 14-01-2016
 * @author Michał Woch
 */
public class PreOrderWalk {
    private TreeNode[] tree;
    private int[] path;
    private Integer j=0;
    /**
     *
     * @param tree
     */
    public PreOrderWalk(TreeNode[] tree) {
        this.tree = tree;
        this.path=new int[tree.length];
        
    }   
    
    /**
     *Metoda przechodzaca przez kolejne węzły struktury drzewiastej wierzchołków minimalnego drzewa rozpinajacego.
     * @param index numer węzła dla którego ma zostać wykonany preorde walk
     */
    public void walk(int index){
        
        path[j]=index;
       
        //System.out.println(path[j]);
         j++;
        if(tree[index].getChildren().isEmpty()) return;
        
        for (int i = 0; i < tree[index].getChildren().size(); i++) {
            
            walk(tree[index].getChildren().get(i).getIndex());
        }
    }

    /**
     * @return zwracana jest tablica kolejnych węzłów wynikających z przejscia preorder walk
     */
    public int[] getPath() {
        return path;
    }
}
