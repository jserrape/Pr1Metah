/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import java.util.ArrayList;

/**
 *
 * @author Juanca
 */
public class TabuComponent {
    
    private int eliminado, coste;
    private ArrayList<Integer> nuevas;
    
    public TabuComponent(int eliminado, int coste, ArrayList<Integer> nuevas) {
        this.eliminado = eliminado;
        this.coste = coste;
        this.nuevas = nuevas;
    }
    
    public TabuComponent() {}

    /**
     * @return the eliminado
     */
    public int getEliminado() {
        return eliminado;
    }

    /**
     * @return the coste
     */
    public int getCoste() {
        return coste;
    }

    /**
     * @return the nuevas
     */
    public ArrayList<Integer> getNuevas() {
        return nuevas;
    }
    
    public TabuComponent getCopy(){
        TabuComponent copy = new TabuComponent();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i : nuevas) {
            array.add(i);
        }
        copy.eliminado = this.eliminado;
        copy.coste = this.coste;
        copy.nuevas = array;
        return copy;
    }
}
