/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

/**
 *
 * @author alumno
 */
public class Greedy {
    
    public Pair cubreOrdenado[];

    public void rellenarRatio(int x, int matriz[][], int cubre[], float ratio[]) {
        for (int i = 1; i < x; i++) {
            ratio[i] = (float) cubre[i] / matriz[0][i];
        }
    }

    public void buscarMayorRatio(int x, int y, float ratio[], int cubre[], int solucion[], int matriz[][]) {
        int mayor = 1;
        for (int i = 2; i < x; i++) {
            if (ratio[i] >= ratio[mayor]) {
                if (ratio[i] == ratio[mayor]) {
                    if (cubre[i] < cubre[mayor]) {
                        break;
                    }
                } else {
                    mayor = i;
                }
            }
        }
        solucion[mayor] = 1; //Establezco el que tiene mas ratio como solucion
        for (int i = 1; i < y; i++) {
            if (matriz[i][mayor] == 1 && matriz[i][0] == 0) {
                ++matriz[i][0]; //DEBERA SER SIEMPRE 1, EL ++ ES PARA IR VIENDO SI ME REPITO
                for (int j = 1; j < x; j++) {
                    if (matriz[i][j] == 1) {
                        --cubre[j];
                    }
                }
            }
        }
    }

    public boolean faltanPorCubrir(int x, int cubre[]) {
        for (int i = 1; i < x; i++) {
            if (cubre[i] != 0) {
                return true;
            }
        }
        return false;
    }

    public void eliminaRedundancias(int x, int y, int solucion[], int matriz[][], int factorizacion) {
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
                    factorizacion = factorizacion - matriz[0][quito];
                }
            }
        }
    }

    public static int mostrarSolucion(int x, int solucion[], int mat[][]) {
        System.out.println("Solucion:");
        int coste = 0;
        for (int i = 1; i < x; i++) {
            if (solucion[i] == 1) {
                System.out.print(i + ":" + solucion[i] + " ");
                coste += mat[0][i];
            }
        }
        System.out.print("Coste: " + coste + "\n");

        return coste;
    }

    public int[] greedySearch(int x, int y, int mat[][], int cubre[], Panel pa, String fich, int ej) {
        System.out.println("\n\nComienzo la busqueda greedy:");
        System.out.println("   x=" + x);
        System.out.println("   y=" + y);

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
        eliminaRedundancias(x, y, solucion, mat, 0);
        int coste = mostrarSolucion(x, solucion, mat);

        time_end = System.currentTimeMillis();
        long t=( time_end - time_start );
        System.out.println("the task has taken "+ t +" milliseconds");
        pa.insertaDatos(fich, coste, t, ej);
        return solucion;
    }
    
    public Pair[] copiaVector() {
        return cubreOrdenado;
    }

}
