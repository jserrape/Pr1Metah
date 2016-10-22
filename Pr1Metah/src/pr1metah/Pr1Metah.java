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
 * ------- Errores pendientes ------ HAY QUE PONERLE EL RANDOM AL GREEDY
 *
 */
public class Pr1Metah {

    static int cubre[];
    static int matriz[][];
    static int x, y;

    public static final int SEMILLA1 = 77383426;
    public static final int SEMILLA2 = 77368737;
    public static final int SEMILLA3 = 34267738;
    public static final int SEMILLA4 = 87377736;
    public static final int SEMILLA5 = 34268737;

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
            //System.out.print("Fichero abierto correctamente\n");
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
            matriz[0][0] = 0;
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
        } catch (IOException | NumberFormatException e) {
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                    //System.out.print("Fichero cerrado correctamente\n");
                }
            } catch (Exception e2) {
            }
        }
    }

    public static int calculaCoste(int x, int sol[], int mat[][]) {
        int coste = 0;
        for (int i = 1; i < x; i++) {
            if (sol[i] == 1) {
                coste += mat[0][i];
            }
        }
        return coste;
    }



    public static void main(String[] args) throws FicheroNoEncontrado, InterruptedException {
        Panel pa = new Panel();
        pa.setVisible(true);

        int solGreedy[], solLocal[], solTabu[], solGrasp[];
        int semillas[] = new int[5];
        semillas[0] = SEMILLA1;
        semillas[1] = SEMILLA2;
        semillas[2] = SEMILLA3;
        semillas[3] = SEMILLA4;
        semillas[4] = SEMILLA5;

        Greedy greedy = new Greedy();
        Tabu tabu = new Tabu();
        LocalSearch localSearch = new LocalSearch();
        LocalSearch localSearchTabu = new LocalSearch();
        Grasp grasp2 = new Grasp();

        String ficheros[] = {"scpe1.txt", "scp41.txt", "scpd1.txt", "scpnrf1.txt", "scpa1.txt"};
        int n = 5;

        for (int i = 0; i < n; i++) {
            for (int ejecucion = 0; ejecucion < 5; ejecucion++) {
                leerFichero(ficheros[i]);
                
                //GREEDY
                solGreedy = greedy.greedySearch(x, y, matriz, cubre, pa, ficheros[i], ejecucion);

                //BUSQUEDA LOCAL
                Pair pair[] = greedy.copiaVector();
                solLocal = localSearch.busquedaLocal(solGreedy, matriz, y, x, pair, 10000, semillas[ejecucion], pa, ficheros[i], ejecucion);

                //TABU
                solTabu = tabu.tabuSearch(y, x, matriz, solGreedy, pair, localSearch, semillas[ejecucion]);
                //GRASP
                solGrasp = grasp2.graspSearch(x, y, matriz, semillas[ejecucion], pa, ficheros[i], ejecucion);

            }
        }
        pa.insertarEstadisticas();
    }

}
