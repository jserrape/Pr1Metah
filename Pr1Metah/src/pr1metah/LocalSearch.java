/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import java.util.Random;

/**
 *
 * @author Juanca
 */
public class LocalSearch {

    static int terminado;
    static Random aleatorio;
    static int numIteraciones;
    static int solucionVecina[];

    public int[] busquedaLocal(int solucion[], int matriz[][], int x, int y, Pair pair[], int maxIteraciones, int semilla) {
        int anterior, costeVecina, costeActual;
        int solucionActual[] = solucion.clone(); // Inicializacion del Greedy
        //int solucionAnterior[];
        costeActual = objetivo(solucionActual, y, matriz);
        numIteraciones = 1; //Empieza en uno ya que he llamado ya una vez a la funcion objetivo
        aleatorio = new Random();
        aleatorio.setSeed(semilla);

        do {
            terminado = calculaIteraciones(solucionActual, y);
            do {
                costeVecina = generaSolucionVecina(solucionActual, matriz, x, y, costeActual, pair);
                --terminado;
            } while ((costeVecina >= costeActual) && terminado != 0 && numIteraciones < maxIteraciones); //Objetivo mejor vecino ¿?
            //solucionAnterior = solucionActual.clone();
            anterior = costeActual;
            if (costeVecina < costeActual) {
                solucionActual = solucionVecina.clone();
                costeActual = costeVecina;
            }
        } while (costeVecina < anterior && numIteraciones < maxIteraciones);

        return solucionActual;
    }

    public int generaSolucionVecina(int solucionActual[], int matriz[][], int x, int y, int costeActual, Pair pair[]) {

        int costeVecina = 0;
        int pos = Math.abs((aleatorio.nextInt() % (y - 1)));
        solucionVecina = solucionActual.clone();
        ++numIteraciones; //1 factorizacion, por lo que se incrementa el contador
        boolean parada = true;
        while (parada) {
            if (pos == 0) {
                ++pos;
            }
            if (solucionVecina[pos] == 0) {
                ++pos;
                pos = (pos % (y - 1));
            } else {
                solucionVecina[pos] = 0;
                costeVecina = costeActual - matriz[0][pos];
                parada = false;
            }
        }

        //Se genera un vector con todos los candidatos que cubren alguna zona de las que me quedan por cubrir al eliminar esa ( sin incluirla )
        int vecino[] = new int[y];
        int zonas[] = new int[x];
        int zonasPendientes = 0;

        for (int i = 1; i < y; i++) {
            vecino[i] = 0;
            if (i < x) {
                zonas[i] = 0;
            }
        }

        //Se rellena el vector de zonas, las posiciones que quedan con 0, son las que faltan por cubrir
        for (int k = 1; k < y; k++) {
            for (int j = 1; j < x; j++) {
                if (matriz[j][k] == 1 && zonas[j] == 0 && solucionVecina[k] == 1) {
                    zonas[j] = 1;
                    ++zonasPendientes;
                }
            }
        }
        zonasPendientes = x - zonasPendientes - 1;
        for (int k = 1; (k < y && zonasPendientes > 0); k++) {
            for (int j = 1; j < x; j++) {
                if (k != pos) {
                    if ((matriz[j][k] == 1) && (zonas[j] == 0)) { //La zona esta sin cubrir
                        vecino[k] = 1;
                        zonas[j] = 1;
                        --zonasPendientes;
                    }
                }
            }
        }

        for (int i = 1; i < y; i++) {
            if (solucionVecina[i] == 0 && vecino[i] == 1) {
                if (i != pos) {
                    solucionVecina[i] = 1;
                    costeVecina += matriz[0][i];
                }
            }
        }
        costeVecina = eliminaRedundancias(y, x, matriz, pair, costeVecina);
        return costeVecina;
    }

    public int eliminaRedundancias(int x, int y, int matriz[][], Pair cubreOrdenado[], int costeVecina) {
        int factorizacion = costeVecina;
        MyQuickSort sorter = new MyQuickSort();
        sorter.sort(cubreOrdenado);
        int quito;
        int i;
        boolean columnaRedundante, sustituible;
        for (int z = 0; z < x - 1; z++) {
            if (solucionVecina[cubreOrdenado[z].getLugar()] == 1) {
                columnaRedundante = true;
                quito = cubreOrdenado[z].getLugar();
                sustituible = false;
                for (i = 1; i < y; i++) {
                    if (matriz[i][quito] == 1) {
                        sustituible = false;
                        for (int j = 1; j < x; j++) {
                            if (matriz[i][j] == 1 && solucionVecina[j] == 1 && quito != j) {
                                sustituible = true;
                            }
                        }
                        if (!sustituible) {
                            columnaRedundante = false;
                        }
                    }
                }
                if (columnaRedundante) {
                    solucionVecina[quito] = 0;
                    factorizacion = factorizacion - matriz[0][quito];
                }
            }
        }
        return factorizacion;
    }

    public static int calculaIteraciones(int solucionVecina[], int tam) {
        int cont = 0;
        for (int i = 1; i < tam; i++) {
            if (solucionVecina[i] == 1) {
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

    public int getIteraciones() {
        return numIteraciones;
    }

    public int getIteracionesGrasp() {
        return numIteraciones - 1;
    }

    public static int mostrarSolucion(int x, int solucion[], int mat[][]) {
        System.out.println("Solucion Busqueda local:");
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
}
