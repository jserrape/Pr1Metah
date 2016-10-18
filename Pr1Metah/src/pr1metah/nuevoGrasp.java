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
        int matcopia[][] = new int[y][x];
        int mejorCoste = 99999999, cosAux;
        int cubre[] = new int[x];
        float alpha = (float) 0.7;
        LocalSearch localSearch;

        long time_start, time_end;
        time_start = System.currentTimeMillis();

        cubrirArray(x, y, mat, cubre);

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
            solAux = greedyRandomized(x, y, mat, alpha, cubre.clone());
            eliminaRedundancias(x,y,solAux,mat,cubreOrdenado);
            localSearch = new LocalSearch();
            solAux = localSearch.busquedaLocalGrasp(solAux, mat, y, x, cubreOrdenado, 400, semilla);
            z += localSearch.getIteracionesGrasp();
            cosAux = calculaSolucion(x, solAux, mat);
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

    /*
        int solAux[], mejorSol[] = new int[x];
        int cubre[] = new int[x];
        float alpha = (float) 0.7;

        cubrirArray(x, y, mat, cubre);
        solAux = greedyRandomized(x, y, mat, alpha, cubre);
     */
    public int[] greedyRandomized(int x, int y, int mat[][], float alpha, int cubre[]) {
        //cubrirArray(x, y, mat, cubre);
        //solAux = greedyRandomized(x, y, mat, alpha, cubre);
        //System.out.println("Comienzo el greedy random");

        int sol[] = new int[x];
        int lrc[] = new int[x];
        int NObjNoCub, tam = 0, nRand, aux;
        float umbral;
        Random rand = new Random();
        for (int i = 1; i < x; i++) {
            sol[i] = 0;
        }
        for (int i = 1; i < y; i++) {
            mat[i][0] = 0;
        }
        NObjNoCub = buscaMayor(x, cubre);

        //mostrarCosas(x, y, mat, cubre);  //MOSTRAR POR PANTALLA
        //System.out.println("NObjNoCub:" + NObjNoCub);
        while (faltaPorCubir(x, cubre)) {
            //System.out.println("COMIENZA UNA VUELTA!!!");
            umbral = (float) alpha * NObjNoCub;
            //System.out.println("umbral:" + umbral);
            tam = 0;
            for (int i = 1; i < x; i++) {
                if (cubre[i] >= umbral) {
                    lrc[tam] = i;
                    ++tam;
                }
            }
            //mostrarUmbral(tam, lrc, umbral);//MOSTRAR POR PANTALLA

            nRand = (int) (rand.nextDouble() * tam);
            aux = lrc[nRand];
            //System.out.println("Selecciono como solucion el " + aux);
            //mostrarSol(x, sol);
            ++sol[aux];
            //mostrarSol(x, sol);
            marcarCubierto(x, y, mat, aux, cubre);

            //mostrarCosas(x, y, mat, cubre);  //MOSTRAR POR PANTALLA
            NObjNoCub = buscaMayor(x, cubre);
            //System.out.print("\n\n\n\n\n\n");
        }
        /*
        System.out.println("Fin del bucle");
        for (int i = 1; i < x; i++) {
            if (sol[i] == 1) {
                System.out.print(i + ": " + sol[i] + "  ");
            }
        }*/
        //System.out.print("\n");
        //Elimino redundancias aqui
        return sol;
    }

    public void mostrarSol(int x, int sol[]) {
        System.out.println("Solucion:");
        for (int i = 1; i < x; i++) {
            System.out.print(sol[i] + " ");
        }
        System.out.println("\n");
    }

    public void mostrarUmbral(int tam, int lrc[], float umbral) {
        System.out.println("Elementos del umbral:");
        for (int i = 0; i < tam; i++) {
            System.out.print(lrc[i] + " ");
        }
        System.out.println("");
    }

    public void mostrarCosas(int x, int y, int mat[][], int cubre[]) {
        for (int i = 1; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        for (int i = 0; i < x; i++) {
            System.out.print(cubre[i] + " ");
        }
        System.out.print("\n");
    }

    /**
     * Busca en el array si faltan territorios por cubrir
     *
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
    public void cubrirArray(int x, int y, int mat[][], int cubre[]) {
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

    public void marcarCubierto(int x, int y, int mat[][], int n, int cubre[]) {
        for (int i = 1; i < y; i++) {
            //System.out.println("mat[" + i + "[0] == 0 && mat[" + i + "][" + n + "] == 1");
            if (mat[i][0] == 0 && mat[i][n] == 1) {
                //System.out.println("Entre");
                for (int j = 1; j < x; j++) {
                    if (mat[i][j] == 1) {
                        --cubre[j];
                    }
                }
                ++mat[i][0];
            }
        }
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

    public void eliminaRedundancias(int x, int y, int solucion[], int matriz[][],Pair cubreOrdenado[]) {
        int quito;
        int i;
        boolean columnaRedundante, sustituible;
        for (int z = 0; z < x - 1; z++) {
            if (solucion[cubreOrdenado[z].getLugar()] == 1) {
                columnaRedundante = true;
                quito = cubreOrdenado[z].getLugar();
                sustituible = false;
                for (i = 1; i < y; i++) {
                    if (matriz[i][quito] == 1) {
                        sustituible = false;
                        for (int j = 1; j < x; j++) {
                            if (matriz[i][j] == 1 && solucion[j] == 1 && quito != j) {
                                sustituible = true;
                            }
                        }
                        if (!sustituible) {
                            columnaRedundante = false;
                        }
                    }
                }
                if (columnaRedundante) {
                    solucion[quito] = 0;
                }
            }
        }
    }

}
