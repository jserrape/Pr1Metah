/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import java.util.Random;

/**
 *
 * @author Juan Carlos
 */
public class Greedy {

    public Pair cubreOrdenado[];

    /**
     * Establece el ratio de cada comisaria
     * @param x Numero de comisarias +1 
     * @param matriz Matriz con la informacion del problema
     * @param cubre Array con el numero de territorios a los que cubre cada comisaria 
     * @param ratio Array con el ratio de cada territorio
     */
    public void rellenarRatio(int x, int matriz[][], int cubre[], float ratio[]) {
        for (int i = 1; i < x; i++) {
            ratio[i] = (float) cubre[i] / matriz[0][i];
        }
    }

    /**
     * Funcion para seleccionar la comisaria con mayor ratio, marcarla como solucion y cubir sus territorios
     * @param x Numero de comisarias +1 
     * @param y Numero de territorios +1 
     * @param ratio Array con el ratio de cada territorio
     * @param cubre Array con el numero de territorios a los que cubre cada comisaria 
     * @param solucion Array solucion
     * @param matriz Matriz con la informacion del problema
     */
    public void buscarMayorRatio(int x, int y, float ratio[], int cubre[], int solucion[], int matriz[][]) {
        int mayor = 1;
        int aux[] = new int[x];
        int n = 1;
        aux[0] = 1;
        for (int i = 2; i < x; i++) {
            if (ratio[i] >= ratio[mayor]) {
                if (ratio[i] == ratio[mayor]) { //Añado a la lista
                    aux[n] = i;
                    ++n;
                    mayor = i;
                } else { //reinicio la lista
                    aux[0] = i;
                    n = 1;
                    mayor = i;
                }
            }
        }

        Random rand = new Random();
        int nRand = (int) (rand.nextDouble() * n);
        mayor = aux[nRand];

        solucion[mayor] = 1; 
        for (int i = 1; i < y; i++) {
            if (matriz[i][mayor] == 1 && matriz[i][0] == 0) {
                ++matriz[i][0]; 
                for (int j = 1; j < x; j++) {
                    if (matriz[i][j] == 1) {
                        --cubre[j];
                    }
                }
            }
        }
    }

    /**
     * Busca en el array si faltan territorios por cubrir
     * @param x Numero de comisarias +1 
     * @param cubre Array con el numero de territorios a los que cubre cada comisaria 
     * @return Devuelve true si faltan territorios sin cubrir, false en caso contrario
     */
    public boolean faltanPorCubrir(int x, int cubre[]) {
        for (int i = 1; i < x; i++) {
            if (cubre[i] != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Funcion para eliminar redundancias en una solucion
     * @param x Numero de comisarias +1
     * @param y Numero de territorios +1
     * @param solucion Array solucion
     * @param matriz Matriz con la informacion del problema
     */
    public void eliminaRedundancias(int x, int y, int solucion[], int matriz[][]) {
        MyQuickSort sorter = new MyQuickSort();
        sorter.sort(cubreOrdenado);
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

    /**
     * Calcula el coste de un vector solucion
     *
     * @param x Numero de comisarias +1
     * @param solucion Array solucion
     * @param mat Matriz con la informacion del problema
     * @return Coste de la solucion
     */
    public static int calculaSolucion(int x, int solucion[], int mat[][]) {
        int coste = 0;
        for (int i = 1; i < x; i++) {
            if (solucion[i] == 1) {
                coste += mat[0][i];
            }
        }
        return coste;
    }

    /**
     * Funcion principal del algoritmo de busqueda greedy
     *
     * @param x Numero de comisarias +1
     * @param y Numero de territorios +1
     * @param mat Matriz con la informacion del problema
     * @param cubre Array con el numero de territorios a los que cubre cada
     * comisaria
     * @param pa objeto de la interfaz grafica
     * @param fich Fichero que ese esta ejecutando en el problema
     * @param ej Numero de ejecucion
     * @return Vector solucion
     */
    public int[] greedySearch(int x, int y, int mat[][], int cubre[], Panel pa, String fich, int ej) {
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        int solucion[] = new int[x];
        for (int i = 0; i < x; i++) {
            solucion[i] = 0;
        }
        float ratio[] = new float[x];
        cubre[0] = 0;
        cubreOrdenado = new Pair[x - 1];

        for (int i = 0; i < x - 1; i++) {
            cubreOrdenado[i] = new Pair(i + 1, cubre[i + 1]);
        }

        rellenarRatio(x, mat, cubre, ratio);
        buscarMayorRatio(x, y, ratio, cubre, solucion, mat);

        while (faltanPorCubrir(x, cubre)) {
            buscarMayorRatio(x, y, ratio, cubre, solucion, mat);
            rellenarRatio(x, mat, cubre, ratio);
        }
        eliminaRedundancias(x, y, solucion, mat);

        time_end = System.currentTimeMillis();
        int coste = calculaSolucion(x, solucion, mat);
        pa.insertaDatos(fich, coste, (int) (time_end - time_start), ej, 1);
        return solucion;
    }

    /**
     * Funcion para devolver el array cubreOrdenado
     *
     * @return Devuelve el array cubreOrdenado
     */
    public Pair[] copiaVector() {
        return cubreOrdenado;
    }

}
