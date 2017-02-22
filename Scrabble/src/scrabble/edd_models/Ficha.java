/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd_models;

import java.awt.Point;
import javax.swing.JLabel;

/**
 *
 * @author estuardoarevalo
 */
public class Ficha {
    private String letra;
    private int puntos;
    private Point posicionEnTablero;
    private boolean ocupada = false;
    
    /**
     * Propiedades para manejo visual de las fichas
     */
    private JLabel label = new JLabel();
    
    
    /**
     * @return the letra
     */
    public String getLetra() {
        return letra;
    }

    /**
     * @param letra the letra to set
     */
    public void setLetra(String letra) {
        this.letra = letra;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the label
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }

    /**
     * @return the posicionEnTablero
     */
    public Point getPosicionEnTablero() {
        return posicionEnTablero;
    }

    /**
     * @param posicionEnTablero the posicionEnTablero to set
     */
    public void setPosicionEnTablero(Point posicionEnTablero) {
        this.posicionEnTablero = posicionEnTablero;
    }

    /**
     * @return the ocupada
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * @param ocupada the ocupada to set
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    
    
    
}
