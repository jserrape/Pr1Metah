/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Xenahort
 *
 * ------- Errores pendientes ------
 *
 */
public class Pr1Metah {

    static int cubre[];
    static float ratio[];
    static int matriz[][];
    static int x, y;
    static int solucion[];
    static Pair cubreOrdenado[];

    public static void eliminaRedundancias() {

        MyQuickSort sorter = new MyQuickSort();
        sorter.sort(cubreOrdenado);

        /*
        System.out.println("Ordenado segun a cuantos cubre:");
        for (int i = 0; i < x - 1; i++) {
            System.out.print(cubreOrdenado[i].getLugar() + ":" + cubreOrdenado[i].getCubre() + " ");
        }
        System.out.println("\n\n");
         */
        int quito;
        int i;
        boolean columnaRedundante, sustituible;
        for (int z = 0; z < x - 1; z++) {
            if (solucion[cubreOrdenado[z].getLugar()] == 1) {
                columnaRedundante = true;
                quito = cubreOrdenado[z].getLugar();
                //System.out.println("Voy a intentar eliminar el " + quito);

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
                    //System.out.println("El " + quito + " sobra.");
                    solucion[quito] = 0;
                } else {
                    //System.out.println("El " + quito + " no sobra.");
                }

            }
        }
    }

    public static boolean faltanPorCubir() {
        for (int i = 1; i < x; i++) {
            if (cubre[i] != 0) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarSolucion() {
        System.out.println("Solucion:");
        for (int i = 1; i < x; i++) {
            if (solucion[i] == 1) {
                System.out.print(i + ":" + solucion[i] + " ");
            }
        }
        System.out.println("\n");
    }

    public static void mostrarMatrizYVector() {
        for (int i = 0; i < y; i++) { // MUESTRO LA MATRIZ
            for (int j = 0; j < x; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("\n Vector de cuantos cubre cada uno:");
        for (int i = 1; i < x; i++) {// MUESTRO a CUANTOS CUBRE
            System.out.print(i + ":" + cubre[i] + "\t");
        }
        System.out.println("\n Vector del ratio:");
        for (int i = 1; i < x; i++) { // MUESTRO EL RATIO
            System.out.print(i + ":" + ratio[i] + "\t");
        }
        System.out.print("\n");
    }

    public static void buscarMayorRatio() {

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
        //System.out.println("Aquel con mayor ratio es el numero " + mayor + "\n");
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

    public static void rellenarRatio() {
        for (int i = 1; i < x; i++) {
            //System.out.print(cubre[i]+"/"+matriz[0][i]+"  ");
            ratio[i] = (float) cubre[i] / matriz[0][i];
        }
        //System.out.print("\n");
    }

    public static void leerFichero(String fich) throws FicheroNoEncontrado {
        if (!(new File(fich)).exists()) {
            throw new FicheroNoEncontrado("Fichero no encontrado \n");
        }
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        try {
            archivo = new File(fich);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String texto;
            String[] datos;
            System.out.print("Fichero abierto correctamente\n");
            texto = br.readLine();
            datos = texto.split(" ");
            y = Integer.parseInt(datos[1]) + 1;
            x = Integer.parseInt(datos[2]) + 1;

            matriz = new int[y][x];
            cubre = new int[x];

            for (int i = 0; i < x; i++) {
                cubre[i] = 0;
            }

            for (int i = 1; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    matriz[i][j] = 0;
                }
            }
            matriz[0][0] = 0; //<---------PONER A -1

            int comisariasV = 1;
            while (x != comisariasV) {
                texto = br.readLine();
                datos = texto.split(" ");
                for (int i = 1; i < datos.length; i++) {
                    matriz[0][comisariasV] = Integer.parseInt(datos[i]);
                    ++comisariasV;
                }
            }
            int cont;
            for (int i = 1; i < y; i++) {
                texto = br.readLine();
                datos = texto.split(" ");
                cont = Integer.parseInt(datos[1]);
                while (cont != 0) {
                    texto = br.readLine();
                    datos = texto.split(" ");
                    for (int j = 1; j < datos.length; j++) {
                        matriz[i][Integer.parseInt(datos[j])] = 1;
                        ++cubre[Integer.parseInt(datos[j])];
                        --cont;
                    }
                }
            }

            solucion = new int[x];
            for (int i = 0; i < x; i++) {
                solucion[i] = 0;
            }
        } catch (IOException | NumberFormatException e) {
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                    System.out.print("Fichero cerrado correctamente\n");
                }
            } catch (Exception e2) {
            }
        }
    }

    public static void greedy() throws FicheroNoEncontrado {
        leerFichero("scpe1.txt");
        ratio = new float[x];
        cubre[0] = 0;

        cubreOrdenado = new Pair[x - 1];
        for (int i = 0; i < x - 1; i++) {
            cubreOrdenado[i] = new Pair(i + 1, cubre[i + 1]);
        }

        rellenarRatio();

        //mostrarMatrizYVector();
        //mostrarSolucion();
        buscarMayorRatio();

        while (faltanPorCubir()) {
            buscarMayorRatio();
            rellenarRatio();
            //mostrarSolucion();
        }
        eliminaRedundancias();
        mostrarSolucion();
    }
    
    

    public static void main(String[] args) throws FicheroNoEncontrado {
        greedy();
        Tabu tabu = new Tabu();
        tabu.tabuSearch(x, y, matriz, solucion);
    }

}
