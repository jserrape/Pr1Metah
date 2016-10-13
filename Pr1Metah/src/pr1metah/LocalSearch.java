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

    static int anterior, costeVecina, costeActual, factorizacion;
    static int terminado;
    static Random aleatorio;
    
    public static final int SEMILLA1 = 77383426;
    public static final int SEMILLA2 = 77368737;
    public static final int SEMILLA3 = 34267738;
    public static final int SEMILLA4 = 87377736;
    public static final int SEMILLA5 = 34268737;
    
    public int[] busquedaLocal(int solucion[], int matriz[][], int x, int y, Greedy greedy) {
        
        int solucionActual[] = solucion.clone(); // Inicializacion del Greedy
        int solucionVecina[] = solucion.clone();
        int solucionAnterior[];
        costeActual = objetivo(solucionActual, y, matriz);
        int pos;
        
        do {
            terminado = calculaIteraciones(solucionActual, y);
            aleatorio = new Random(SEMILLA3);
            pos = Math.abs((aleatorio.nextInt() % (y - 1)));
            do {
                generaSolucionVecina(solucionActual, solucionVecina, matriz, x, y, greedy, pos);
                --terminado;
            } while ((costeVecina >= costeActual) && terminado != 0); 
            solucionAnterior = solucionActual.clone();
            anterior = costeActual;
            if (costeVecina < costeActual) {
                solucionActual = solucionVecina.clone();
                costeActual = costeVecina;
            }
        } while (costeVecina < anterior);
        
        return solucionActual;
    }

    public void generaSolucionVecina(int solucionActual[], int solucionVecina[], int matriz[][], int x, int y, Greedy greedy, int pos) {
        solucionVecina = solucionActual.clone();
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
                factorizacion = costeActual - matriz[0][pos];
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
                    factorizacion += matriz[0][i];
                }
            }
        }

        greedy.eliminaRedundancias(y, x, solucionVecina, matriz, factorizacion);
        costeVecina = factorizacion;
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

    public static int objetivo(int solucionVecina[], int tam, int matriz [][]) {
        int suma = 0;
        for (int i = 1; i < tam; i++) {
            suma += solucionVecina[i] * matriz[0][i];
        }
        return suma;
    }

}
