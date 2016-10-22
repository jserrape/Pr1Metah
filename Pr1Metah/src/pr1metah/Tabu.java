/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import java.util.ArrayList;

/**
 *
 * @author Xenahort
 */

/*
- Posibles fallos:
- Hacer copia del arraylist en tabucomponent

*/

public class Tabu {
    
    private int solucion[], mejorSolucion[];
    private TabuList tabulist;
    private int mejor;
    
    //El vector solucion empieza en 1
    public int[] tabuSearch(int x, int y, int mat[][], int greedySol[], Pair pair[], LocalSearch local, int semilla) {
        mejorSolucion = solucion = greedySol.clone();
        int tamGreedy = calculaTam(solucion, y);
        mejor = objetivo(solucion, y, mat);
        tabulist = new TabuList(tamGreedy);
        int cont = 0;
        do {
            TabuList vecindario = local.busquedaLocalTabu(solucion, mat, x, y, pair, semilla);
            TabuComponent candidato = escogeVecino(vecindario, y);
            if(candidato.getCoste() < mejor) {
                mejorSolucion = calculaVecino(solucion, candidato.getEliminado(), candidato.getNuevas()).clone();
                mejor = candidato.getCoste();
            }
            solucion = calculaVecino(solucion, candidato.getEliminado(), candidato.getNuevas());
            ++cont;
        } while (cont <= 10000);
        System.out.printf("Coste: %s \n", mejor);
        return mejorSolucion;
    }
    
    public TabuComponent escogeVecino(TabuList vecindario, int y) {
        vecindario.bubble();
        int coste;
        for (int i = 0; i < vecindario.getTaml(); i++) {
            coste = vecindario.getLista()[i].getCoste();
            int eliminado = vecindario.getLista()[i].getEliminado();
            ArrayList<Integer> array = vecindario.getLista()[i].getNuevas();
            if (coste < mejor) {
                if (!tabulist.find(eliminado, array)) {
                    tabulist.addSet(eliminado, coste, array);
                    return vecindario.getComponent(i);
                } else {
                    tabulist.updatePos(i);
                    return vecindario.getComponent(i); 
                }
            } else {
                if (!tabulist.find(eliminado, array)){
                    tabulist.addSet(eliminado, coste, array);
                    return vecindario.getComponent(i);
                }
            }
        }               
        return vecindario.getComponent(0);
    }
    
    public int calculaTam(int solucion[], int tam) {
        int cont = 0;
        for (int i = 1; i < tam; i++) {
            if (solucion[1] == 1) {
                ++cont;
            }
        }
        return cont;
    }
    
    public static int objetivo(int solucionVecina[], int tam, int matriz[][]) {
        int suma = 0;
        for (int i = 1; i < tam; i++) {
            suma += solucionVecina[i] * matriz[0][i];
        }
        return suma;
    }
    
    public int[] calculaVecino(int solucion[], int eliminado, ArrayList<Integer> nuevos) {
        solucion[eliminado] = 0;
        for (int i = 0; i < nuevos.size(); i++) {
            solucion[nuevos.get(i)] = 1;
        }
        return solucion;
    }
}