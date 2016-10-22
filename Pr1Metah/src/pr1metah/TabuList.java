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
public class TabuList {
    
    private TabuComponent lista[];
    private int tam, taml, pos;
    
    public TabuList(int greedyTam) { 
        tam = (int) ((0.2 * (greedyTam + 1)) + 1 ); //Para evitar que la lista sea solo de 1 posicion
        lista = new TabuComponent[tam];
        taml = pos = 0;
    }
    
    public TabuList(int tama, int idef) {
        tam = tama; 
        lista = new TabuComponent[tam];
        taml = pos = 0;
    }
    
    public void addSet(int eliminado, int coste, ArrayList<Integer> nuevas) {
        lista[pos] = new TabuComponent(eliminado, coste, nuevas);
        pos = (pos + 1) % tam;
        if (taml < tam) {
            ++taml;
        }
    }
    
    public void addSet(TabuComponent tabuComponent) {
        lista[pos] = tabuComponent;
        pos = (pos + 1) % tam;
        if (taml < tam) {
            ++taml;
        }
    }
    
    public boolean find(int eliminado, ArrayList<Integer> nuevas) {
        for (int i = 0; i < taml; i++) {
            if(lista[i].getEliminado() == eliminado && areEquals(lista[i].getNuevas(), nuevas)) {
                return true;
            }
        }
        return false;
    }
    
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
    
    public void updatePos(int pos) {
        TabuComponent tabuComponent = lista[pos].getCopy();
        for (int i = pos; i < taml - 1; i++) {
            lista[i] = lista[i + 1];
        }
        lista[taml - 1] = tabuComponent;
    }
    
    public void bubble() {
        TabuComponent aux;
        for (int i = 0; i < taml; i++) {
            for (int j = 0; j < taml - i - 1; j++) {
                if (lista[j].getCoste() > lista[j + 1].getCoste()) {
                    aux = lista[j].getCopy();
                    lista[j] = lista[j + 1].getCopy();
                    lista[j + 1] = aux.getCopy();
                }
            }
        }
    }
    
    public TabuComponent[] getLista() {return lista;}
    public int getTaml() {return taml;}
    public TabuComponent getComponent(int pos) {return lista[pos];}
}
