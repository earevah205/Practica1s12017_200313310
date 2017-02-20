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
@XStreamAlias("scrabble")
public class Scrabble {
    
    @XStreamAlias("dimension")
    private int dimension;
    
    @XStreamAlias("dobles")
    private Doble dobles;
    
    @XStreamAlias("triples")
    private Triple triples;
    
    @XStreamAlias("diccionario")
    private Diccionario diccionario;

    /**
     * @return the dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * @param dimension the dimension to set
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     * @return the dobles
     */
    public Doble getDobles() {
        return dobles;
    }

    /**
     * @param dobles the dobles to set
     */
    public void setDobles(Doble dobles) {
        this.dobles = dobles;
    }

    /**
     * @return the triples
     */
    public Triple getTriples() {
        return triples;
    }

    /**
     * @param triples the triples to set
     */
    public void setTriples(Triple triples) {
        this.triples = triples;
    }

    /**
     * @return the diccionario
     */
    public Diccionario getDiccionario() {
        return diccionario;
    }

    /**
     * @param diccionario the diccionario to set
     */
    public void setDiccionario(Diccionario diccionario) {
        this.diccionario = diccionario;
    }

}
