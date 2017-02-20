/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.xml_models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 *
 * @author estuardoarevalo
 */

@XStreamAlias("diccionario")
public class Diccionario {
    
    @XStreamImplicit(itemFieldName="palabra")
    private String[] palabras;

    /**
     * @return the palabras
     */
    public String[] getPalabras() {
        return palabras;
    }

    /**
     * @param palabras the palabras to set
     */
    public void setPalabras(String[] palabras) {
        this.palabras = palabras;
    }
}
