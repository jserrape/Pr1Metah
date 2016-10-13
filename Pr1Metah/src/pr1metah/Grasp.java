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

    public void graspSearch(int x, int y, int mat[][]) {
        int solAux[], mejorSol[] = new int[x];
        int matcopia[][] = new int[y][x];
        int mejorCoste = 9999999, cosAux;
        System.out.println("\n\nComienzo la busqueda Grasp:");
        System.out.println("   x=" + x);
        System.out.println("   y=" + y);
        alpha = (float) 0.7;
        System.out.println("   alpha=" + alpha);

        long time_start, time_end;
        time_start = System.currentTimeMillis();
        
        //mostrarMatriz(x, y, mat);
        for (int z = 1; z < 1000; z++) {
            copiaMatriz(x,y,mat,matcopia);
            solAux = greedyRandomized(x, y, matcopia);
            //Busqueda local a solAux aqui
            cosAux = costeSol(x, solAux, mat);
            //System.out.println("Coste vuelta " + z + ": " + cosAux);
            //System.out.println(cosAux + " < " + mejorCoste);
            if (cosAux < mejorCoste) {
                //System.out.println("¡MEJORA!");
                mejorCoste = cosAux;
                mejorSol = solAux.clone();
            }
        }
        System.out.println("Grasp, resultado de coste: " + mejorCoste);
        time_end = System.currentTimeMillis();
        long t=( time_end - time_start );
        System.out.println("the task has taken "+ t +" milliseconds");

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
        //System.out.println("   cubre:");
        for (int i = 1; i < x; i++) {
            //System.out.print(cubre[i] + " ");
            if (mayor < cubre[i]) {
                mayor = cubre[i];
            }
        }
        //System.out.print("\n");
        //System.out.print("   NObjNoCub=" + mayor + "\n");
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

    public int[] greedyRandomized(int x, int y, int mat[][]) {
        //mostrarMatriz(x, y, mat);
        int cubre[] = new int[x];
        int sol[] = new int[x];
        int lrc[] = new int[x];
        int NObjNoCub, tam = 0, nRand, aux;
        float umbral;
        Random rand = new Random();
        for (int i = 1; i < x; i++) {
            sol[i] = 0;
        }

        NObjNoCub = actualizaCubre(x, y, mat, cubre);
        while (faltaPorCubir(x, cubre)) {
            umbral = (float) alpha * NObjNoCub;
            //System.out.println("   umbral:" + umbral);
            tam = 0;
            for (int i = 1; i < x; i++) {
                if (cubre[i] >= umbral) {
                    lrc[tam] = i;
                    ++tam;
                }
            }
            //System.out.println("   lrc:");
            for (int i = 0; i < tam; i++) {
                //System.out.print(lrc[i] + " ");
            }

            //System.out.print("\n");
            nRand = (int) (rand.nextDouble() * tam);
            aux = lrc[nRand];
            //System.out.print("   nRand:\n" + nRand + "\n");
            //System.out.print("   lrc[nRand]:\n" + lrc[nRand] + "\n");
            ++sol[aux]; //Lo pongo como solucion
            cubrir(x, y, mat, aux);
            //System.out.println("Fin de la vuelta \n\n\n\n");
            NObjNoCub = actualizaCubre(x, y, mat, cubre);
        }

        //System.out.println("   solucion final:");
        for (int i = 1; i < x; i++) {
            //System.out.print(sol[i] + " ");
        }
        //System.out.println();
        return sol;
    }
}
