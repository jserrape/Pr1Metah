/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import java.util.Random;

/**
 *
 * @author Xenahort
 */
public class nuevoGrasp {

    public int[] graspSearch(int x, int y, int mat[][], int semilla, Panel pa, String fich, int ej) {
        int solAux[], mejorSol[] = new int[x];
        int cubre[] = new int[x];
        float alpha = (float) 0.7;

        cubrir(x, y, mat, cubre);
        //solAux = greedyRandomized(x, y, mat,alpha);

        return mejorSol;
    }

    public int[] greedyRandomized(int x, int y, int mat[][], float alpha) {
        int cubre[] = new int[x];
        int sol[] = new int[x];
        int lrc[] = new int[x];
        int NObjNoCub, tam = 0, nRand, aux;
        float umbral;
        Random rand = new Random();
        for (int i = 1; i < x; i++) {
            sol[i] = 0;
        }
        NObjNoCub = buscaMayor(x, cubre);
        //Inserte bucle aqui

        umbral = (float) alpha * NObjNoCub;
        tam = 0;
        for (int i = 1; i < x; i++) {
            if (cubre[i] >= umbral) {
                lrc[tam] = i;
                ++tam;
            }
        }

        nRand = (int) (rand.nextDouble() * tam);
        aux = lrc[nRand];
        ++sol[aux];
        //Cubro aqui
        
        //Acabo bucle aqui
        
        //Elimino redundancias aqui

        return sol;
    }
    
    /**
     * Busca en el array si faltan territorios por cubrir
     * @param x
     * @param cubre
     * @return 
     */
    public boolean faltaPorCubir(int x, int cubre[]) {
        for (int i = 1; i < x; i++) {
            if (cubre[i] != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca el mayor de un array
     *
     * @param x
     * @param cubre
     * @return
     */
    public int buscaMayor(int x, int cubre[]) {
        int mayor = cubre[1];
        for (int i = 1; i < x; i++) {
            if (mayor < cubre[i]) {
                mayor = cubre[i];
            }
        }
        return mayor;
    }

    /**
     * Rellena el array cubre con en numero de territorios que cubre cada
     * comisaria
     *
     * @param x
     * @param y
     * @param mat
     * @param cubre
     */
    public void cubrir(int x, int y, int mat[][], int cubre[]) {
        int aux;
        for (int j = 1; j < x; j++) {
            aux = 0;
            for (int i = 1; i < y; i++) {
                if (mat[i][j] == 1) {
                    ++aux;
                }
            }
            cubre[j] = aux;
        }
    }
}
