/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import java.util.ArrayList;

/**
 *
 * @author Juan Carlos
 */
public class TabuComponent {

    private int eliminado, coste, vecino[];
    private ArrayList<Integer> nuevas;

    /**
     * Constructor parametrizado
     *
     * @param eliminado columna que se elimina para generar el vecino
     * @param coste coste del vecino
     * @param vecino vector solución del vecino
     * @param nuevas columnas que se añaden para llegar a ese vecino
     */
    public TabuComponent(int eliminado, int coste, int vecino[], ArrayList<Integer> nuevas) {
        this.eliminado = eliminado;
        this.coste = coste;
        this.nuevas = nuevas;
        this.vecino = vecino;
    }

    public TabuComponent() {
    }

    /**
     * @return Devuelve la columna que se ha elinado para generar un vecino
     */
    public int getEliminado() {
        return eliminado;
    }

    /**
     * @return Devuelve el coste de un vecino
     */
    public int getCoste() {
        return coste;
    }

    /**
     * @return Devuelve la lista de las columnas nuevas que se han añadido para
     * generar un vecino
     */
    public ArrayList<Integer> getNuevas() {
        return nuevas;
    }

    /**
     * @return Devuelve el vector solución de un vecino
     */
    public int[] getVecino() {
        return vecino;
    }
}
