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
public class TabuList {

    private final TabuComponent lista[];
    private final int tam;
    private int taml, pos;

    /**
     * Crea una lista tabú de un tamaño dado por cabecera usando un TSFactor de
     * 0.2
     *
     * @param greedyTam el numero de conjuntos de la solucion Greedy
     */
    public TabuList(int greedyTam) {
        tam = (int) ((0.8 * greedyTam) + 1);
        lista = new TabuComponent[tam];
        taml = pos = 0;
    }

    /**
     * Crea una lista tabú de un tamaño dado por cabecera
     *
     * @param tama
     * @param idef atributo innecesario, solo sirve para distinguirse del otro
     * constructor
     */
    public TabuList(int tama, int idef) {
        tam = tama;
        lista = new TabuComponent[tam];
        taml = pos = 0;
    }

    /**
     * Añade un movimiento tabú a nuestra lista
     *
     * @param eliminado la columna eliminada para obtener el movimiento tabú
     * @param coste el coste de la solución resultante
     * @param vecino la solución que obtenemos
     * @param nuevas las columnas nuevas que se han añadido
     */
    public void addSet(int eliminado, int coste, int vecino[], ArrayList<Integer> nuevas) {
        lista[pos] = new TabuComponent(eliminado, coste, vecino, nuevas);
        pos = (pos + 1) % tam;
        if (taml < tam) {
            ++taml;
        }
    }

    /**
     * Añade un movimiento tabú a nuestra lista
     *
     * @param tabuComponent el movimiento que queremos añadir
     */
    public void addSet(TabuComponent tabuComponent) {
        lista[pos] = tabuComponent;
        pos = (pos + 1) % tam;
        if (taml < tam) {
            ++taml;
        }
    }

    /**
     * Comprueba si un moviento tabú está en nuestra lista tabú
     *
     * @param eliminado elemento que se quiere buscar
     * @param nuevas
     * @return Devuelve true si el movimiento es tabú, false si no lo es
     */
    public boolean find(int eliminado, ArrayList<Integer> nuevas) {
        for (int i = 0; i < taml; i++) {
            if(lista[i].getEliminado() == eliminado && areEquals(lista[i].getNuevas(), nuevas)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si dos ArrayList son iguales elemento a elemento
     *
     * @param a primer ArrayList
     * @param b segundo ArrayList
     * @return Devuelve true si los dos ArrayList son idénticos, false si no lo
     * son
     */
    public boolean areEquals(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() == b.size()) {
            for (int i = 0; i < a.size(); i++) {
                if(!b.contains(a.get(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Actualiza la posición de un elemento en la lista tabu consiguiento que su
     * tenencia tabú vuelva a estar al máximo
     *
     * @param pos la posicion que se quiere actualizar
     */
    public void updatePos(int pos) {
        TabuComponent tabuComponent = lista[pos];
        for (int i = pos; i < taml - 1; i++) {
            lista[i] = lista[i + 1];
        }
        lista[taml - 1] = tabuComponent;
    }

    /**
     * @return Devuelve la lista
     */
    public TabuComponent[] getLista() {
        return lista;
    }

    /**
     * @param pos la posicion que se quiere obtener
     * @return Devuelve una posicion determinada de la lista
     */
    public TabuComponent getComponent(int pos) {
        return lista[pos];
    }

    /**
     * @return Devuelve el tamaño lógico de la lista
     */
    public int getTaml() {
        return taml;
    }

    /**
     * @return Devuelve el tamaño máximo de la lista
     */
    public int getTam() {
        return tam;
    }
}
