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

@XStreamAlias("triples")
public class Triple {
    
    @XStreamImplicit(itemFieldName="casilla")
    private Casilla[] casillas;

    /**
     * @return the casillas
     */
    public Casilla[] getCasillas() {
        return casillas;
    }

    /**
     * @param casillas the casillas to set
     */
    public void setCasillas(Casilla[] casillas) {
        this.casillas = casillas;
    }

}
