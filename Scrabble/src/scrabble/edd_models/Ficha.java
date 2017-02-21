/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.edd_models;

import java.awt.Image;
import java.awt.Point;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author estuardoarevalo
 */
public class Ficha {
    private String letra;
    private int puntos;
    
    /**
     * Propiedades para manejo visual de las fichas
     */
    private ImageIcon imageIcon;
    private Point coords;
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
        URL urlPieceImg = getClass().getResource("/scrabble/images/" + letra + ".png");
        imageIcon = new ImageIcon(urlPieceImg);
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
     * @return the image
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * @return the coords
     */
    public Point getCoords() {
        return coords;
    }

    /**
     * @param coords the coords to set
     */
    public void setCoords(Point coords) {
        this.coords = coords;
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
    
    
    
}
