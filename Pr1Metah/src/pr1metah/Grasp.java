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
public class Grasp {

    float alpha;

    public int[] graspSearch(int x, int y, int mat[][], int semilla, Panel pa, String fich, int ej) {
        int solAux[], mejorSol[] = new int[x];
        int matcopia[][] = new int[y][x];
        int mejorCoste = 99999999, cosAux;
        LocalSearch localSearch;
        alpha = (float) 0.7;

        long time_start, time_end;
        time_start = System.currentTimeMillis();

        Pair cubreOrdenado[] = new Pair[x - 1];
        for (int i = 0; i < x - 1; i++) {
            cubreOrdenado[i] = new Pair(i + 1, 0);
        }
        for (int j = 1; j < x; j++) {
            for (int i = 1; i < y; i++) {
                if (mat[i][j] == 1) {
                    cubreOrdenado[j - 1].incrementaCubre();
                }
            }
        }
        MyQuickSort sorter = new MyQuickSort();
        sorter.sort(cubreOrdenado);

        int z = 0;
        while (z < 1000) {
            copiaMatriz(x, y, mat, matcopia);
            solAux = greedyRandomized(x, y, matcopia,semilla);
            localSearch = new LocalSearch();
            solAux = localSearch.busquedaLocalGrasp(solAux, mat, y, x, cubreOrdenado, 400, semilla);
            z += localSearch.getIteracionesGrasp();
            cosAux = costeSol(x, solAux, mat);
            if (cosAux < mejorCoste) {
                mejorCoste = cosAux;
                mejorSol = solAux.clone();
            }
        }

        time_end = System.currentTimeMillis();

        int coste = calculaSolucion(x, mejorSol, mat);

        pa.insertaDatos(fich, coste, (int) (time_end - time_start), ej, 4);

        return mejorSol;
    }

    public static int calculaSolucion(int x, int solucion[], int mat[][]) {
        int coste = 0;
        for (int i = 1; i < x; i++) {
            if (solucion[i] == 1) {
                coste += mat[0][i];
            }
        }
        return coste;
    }

    public void copiaMatriz(int x, int y, int origen[][], int destino[][]) {
        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                if (origen[i][j] == 1) {
                    destino[i][j] = 1;
                } else {
                    destino[i][j] = 0;
                }
            }
        }
    }

    public int costeSol(int x, int sol[], int mat[][]) {
        int cos = 0;
        for (int i = 1; i < x; i++) {
            if (sol[i] == 1) {
                cos += mat[0][i];
            }
        }
        return cos;
    }

    public void mostrarMatriz(int x, int y, int mat[][]) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public int actualizaCubre(int x, int y, int mat[][], int cubre[]) {
        for (int i = 1; i < x; i++) {
            cubre[i] = 0;
        }
        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                if (mat[i][j] == 1) {
                    ++cubre[j];
                }
            }
        }
        int mayor = cubre[1];
        for (int i = 1; i < x; i++) {
            if (mayor < cubre[i]) {
                mayor = cubre[i];
            }
        }
        return mayor;
    }

    public void cubrir(int x, int y, int mat[][], int n) {
        for (int i = 1; i < y; i++) {
            if (mat[i][n] == 1) {
                for (int j = 1; j < x; j++) {
                    mat[i][j] = 0;
                }
            }
        }
    }

    public boolean faltaPorCubir(int x, int cubre[]) {
        for (int i = 1; i < x; i++) {
            if (cubre[i] != 0) {
                return true;
            }
        }
        return false;
    }

    public int[] greedyRandomized(int x, int y, int mat[][],int semilla) {
        int cubre[] = new int[x];
        int sol[] = new int[x];
        int lrc[] = new int[x];
        int NObjNoCub, tam = 0, nRand, aux;
        float umbral;
        Random rand = new Random();
        rand.setSeed(semilla);
        for (int i = 1; i < x; i++) {
            sol[i] = 0;
        }
        NObjNoCub = actualizaCubre(x, y, mat, cubre);
        while (faltaPorCubir(x, cubre)) {
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
            cubrir(x, y, mat, aux);
            NObjNoCub = actualizaCubre(x, y, mat, cubre);
        }

        return sol;
    }
}
