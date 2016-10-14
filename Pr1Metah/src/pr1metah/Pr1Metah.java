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
                    System.out.print("Fichero cerrado correctamente\n");
                }
            } catch (Exception e2) {
            }
        }
    }

    public static void main(String[] args) throws FicheroNoEncontrado {
        Panel pa = new Panel();
        pa.setVisible(true);

        Greedy greedy = new Greedy();
        Tabu tabu = new Tabu();
        LocalSearch localSearch = new LocalSearch();

        String ficheros[] = {"scpe1.txt", "scp41.txt", "scpd1.txt", "scpnrf1.txt", "scpa1.txt"}; 
        int n = 4;

        leerFichero(ficheros[4]);
        int solucion[] = greedy.greedySearch(x, y, matriz, cubre, pa, ficheros[4], 0);
        Pair pair[] = greedy.copiaVector();
   
        for (int i = 1; i < x; i++) {
            solucion[i] = 1;
        }
        
        int solucionBL[] = localSearch.busquedaLocal(solucion, matriz, y, x, pair, 77383426);
        
        /*
        int vecino[] = new int[x];
        int zonas[] = new int[y];
        int zonasPendientes = 0;

        for (int i = 1; i < x; i++) {
            vecino[i] = 0;
            if (i < y) {
                zonas[i] = 0;
            }
        }

        //Se rellena el vector de zonas, las posiciones que quedan con 0, son las que faltan por cubrir
        for (int k = 1; k < x; k++) {
            for (int j = 1; j < y; j++) {
                if (matriz[j][k] == 1 && zonas[j] == 0 && solucionBL[k] == 1) {
                    zonas[j] = 1;
                    ++zonasPendientes;
                }
            }
        }
        zonasPendientes = y - zonasPendientes - 1; 
        System.out.printf("Hay %s zonas sin cubrir \n", zonasPendientes);
        */
        int coste = 0;
        for (int i = 1; i < x; i++){
            if (solucionBL[i] == 1) {
                coste += matriz[0][i];
            }
        }
        System.out.printf("Coste total de la busqueda local: %s \n", coste);  
        tabu.tabuSearch(x, y, matriz, solucion);

    }

}
