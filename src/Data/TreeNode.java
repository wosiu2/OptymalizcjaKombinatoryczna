/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.LinkedList;

/**
 *
 * @author Włosek
 */
public class TreeNode {
 private int index;
 private int parent;
 private int weightOfSubTree=0;
 private LinkedList<TreeNode> children;
 private Boolean haveParent;
 private int level;

    public TreeNode() {
        
        this.children=new LinkedList<TreeNode>();
        this.haveParent=false;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the parent
     */
    public int getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(int parent) {
        this.parent = parent;
    }

    /**
     * @return the childs
     */
    public LinkedList<TreeNode> getChildren() {
        return children;
    }

    /**
     * @return the isConnected
     */
    public Boolean getHaveParent() {
        return haveParent;
    }

    /**
     * @param isConnected the isConnected to set
     */
    public void setHaveParent(Boolean isConnected) {
        this.haveParent = isConnected;
    }

    /**
     * @return the weightOfSubTree
     */
    public int getWeightOfSubTree() {
        return weightOfSubTree;
    }

    /**
     * 
     */
    public void incWeightOfSubTree() {
        this.weightOfSubTree++;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

}
