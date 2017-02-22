/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import scrabble.edd.ListaFichasJugador;
import scrabble.edd.NodoFicha;
import scrabble.edd_models.Ficha;
import scrabble.edd_models.Jugador;

/**
 *
 * @author estuardoarevalo
 */
public class ListenerDnD  implements MouseListener, MouseMotionListener {
    private Jugador jugador;
    private frmScrabbleGame frmGame;
         
    private Ficha dragFicha;
    private double dragOffsetX;
    private double dragOffsetY;


    public ListenerDnD(frmScrabbleGame _frmGame) {
                frmGame = _frmGame;
	}

    public void setJugador(Jugador _jugador){
        jugador = _jugador;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        int x = evt.getPoint().x;
        int y = evt.getPoint().y;

        System.out.println("mouseX=" + x +" - mouseY=" + y);
        
        //Recorrer la lista de fichas
        ListaFichasJugador fichasJugador = jugador.getFichas();
        NodoFicha nodo = fichasJugador.getInicio();
        
        while (nodo!=null){
            
            Ficha ficha = nodo.getFicha();
            
            if( mouseSobreFicha(ficha,x,y)){
                // calculate offset, because we do not want the drag piece
                // to jump with it's upper left corner to the current mouse
                // position
                //
                this.dragOffsetX = x - ficha.getLabel().getLocation().getX();
                this.dragOffsetY = y - ficha.getLabel().getLocation().getY();
                this.dragFicha = ficha;
                
                System.out.println("Esta intentando arrastrar la ficha \"" + ficha.getLetra() +"\"");
                
                break;
            }
            
            //movernos a la siguiente ficha
            nodo = nodo.getSiguiente();
        }  
        
        
//
//        // move drag piece to the top of the list
//        if(this.dragPiece != null){
//                this.pieces.remove( this.dragPiece );
//                this.pieces.add(this.dragPiece);
//        }
    }
    
    /**
    * check whether the mouse is currently over this piece
    * @param ficha the playing piece
    * @param x x coordinate of mouse
    * @param y y coordinate of mouse
    * @return true if mouse is over the piece
    */
    private boolean mouseSobreFicha(Ficha ficha, int x, int y) {
        
        System.out.println("-------");
        System.out.println("Ficha " + ficha.getLetra() );
        System.out.println("Bounds x(" +
                ficha.getLabel().getLocationOnScreen().getX()
                + "," +
                ficha.getLabel().getLocationOnScreen().getX() + ficha.getLabel().getWidth()
                + ")"
                );
        System.out.println("Bounds y(" +
                ficha.getLabel().getLocationOnScreen().getY()
                + "," +
                ficha.getLabel().getLocationOnScreen().getY() + ficha.getLabel().getHeight()
                + ")"
                );
        
        return ficha.getLabel().getLocationOnScreen().getX()  <= x + frmGame.getLocationOnScreen().getX()
            && ficha.getLabel().getLocationOnScreen().getX() + ficha.getLabel().getWidth() >= x + frmGame.getLocationOnScreen().getX()
            && ficha.getLabel().getLocationOnScreen().getY() <= y + frmGame.getLocationOnScreen().getY()
            && ficha.getLabel().getLocationOnScreen().getY() + ficha.getLabel().getHeight() >= y + frmGame.getLocationOnScreen().getY();
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        
        //colocar la ficha sobre el tablero correctamente
        int x = (evt.getPoint().x - (int)this.dragOffsetX) / frmScrabbleGame.FICHA_SIZE.width;
        int y = (evt.getPoint().y - (int)this.dragOffsetY) / frmScrabbleGame.FICHA_SIZE.height;
        
        this.dragFicha.getLabel().setLocation(
                    new Point(x*frmScrabbleGame.FICHA_SIZE.width,y*frmScrabbleGame.FICHA_SIZE.height));
            this.frmGame.repaint();
                
        this.dragFicha = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        if(this.dragFicha != null){
            this.dragFicha.getLabel().setLocation(
                    new Point(
                            evt.getPoint().x - (int)this.dragOffsetX,
                            evt.getPoint().y - (int)this.dragOffsetY));
            this.frmGame.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
}
