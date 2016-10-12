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

    public void mostrarMatriz(int x, int y, int mat[][]) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void graspSearch(int x, int y, int mat[][]) { //LA MATRIZ LA MACHACO!!!
        System.out.println("\n\nComienzo la busqueda Grasp:");
        System.out.println("   x=" + x);
        System.out.println("   y=" + y);
        alpha = (float) 0.6;
        System.out.println("   alpha=" + alpha);

        greedyRandomized(x, y, mat.clone());
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
        System.out.println("   cubre:");
        for (int i = 1; i < x; i++) {
            System.out.print(cubre[i] + " ");
            if (mayor < cubre[i]) {
                mayor = cubre[i];
            }
        }
        System.out.print("\n");
        System.out.print("   NObjNoCub=" + mayor + "\n");
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
        return true;
    }

    public void greedyRandomized(int x, int y, int mat[][]) {
        int cubre[] = new int[x];
        int sol[] = new int[x];
        int lrc[] = new int[x];
        int NObjNoCub, tam = 0, nRand;
        float umbral;
        Random rand = new Random();
        for (int i = 1; i < x; i++) {
            sol[i] = 0;
        }

        while (faltaPorCubir(x, cubre)) {
            NObjNoCub = actualizaCubre(x, y, mat, cubre);
            umbral = (float) alpha * NObjNoCub;
            System.out.println("   umbral:" + umbral);
            tam = 0;
            for (int i = 1; i < x; i++) {
                if (cubre[i] >= umbral) {
                    lrc[tam] = i;
                    ++tam;
                }
            }
            System.out.println("   lrc:");
            for (int i = 0; i < tam; i++) {
                System.out.print(lrc[i] + " ");
            }
            System.out.print("\n");
            nRand = (int) (rand.nextDouble() * tam);
            System.out.print("   nRand:\n" + nRand + "\n");
            ++sol[nRand]; //Lo pongo como solucion
            cubrir(x, y, mat, nRand);
            System.out.println("Fin de la vuelta");
        }
    }
}
