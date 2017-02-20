/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author estuardoarevalo
 */
@XStreamAlias("casilla")
public class Casilla {
    
    @XStreamAlias("x")
    private int x;
    
    @XStreamAlias("y")
    private int y;

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}
