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
        tam = (int) ((0.2 * greedyTam) + 1 );
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
        TabuComponent tabuComponent = lista[pos];
        for (int i = pos; i < taml - 1; i++) {
            lista[i] = lista[i + 1];
        }
        lista[taml - 1] = tabuComponent;
    }
    
    public TabuComponent[] getLista() {return lista;}
    public int getTaml() {return taml;}
    public int getTam() {return tam;}
    public TabuComponent getComponent(int pos) {return lista[pos];}
}
