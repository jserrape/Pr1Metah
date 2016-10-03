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

    public static void mostrarMatrizYVector() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n\n\n");
        for (int i = 0; i < x; i++) {
            System.out.print(cubre[i] + ".0 ");
        }
        System.out.print("\n\n");
        for (int i = 0; i < x; i++) {
            System.out.print(ratio[i] + " ");
        }
        System.out.print("\n\n\n\n");
    }
    
    public static int buscarMayorRatio(){
    
        int mayor=1;
        for(int i=2;i<x;i++){
            if(ratio[i]>=ratio[mayor]){
                if(ratio[i]==ratio[mayor]){
                    if(cubre[i]<cubre[mayor]){
                        break;
                    }
                }
                mayor=i;
            }
        }
        System.out.println("Aquel con mayor ratio es el numero "+mayor);
        return mayor;
    }
    

    public static void rellenarRatio(){
        ratio = new float[x];
        cubre[0]=0;
        for (int i = 1; i < x; i++) {
            ratio[i]=cubre[i]/matriz[0][i];
        }
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
                for (int j = 1; j < x; j++) {
                    matriz[i][j] = 0;
                }
            }

            for (int j = 0; j < y; j++) {
                matriz[j][0] = -1;
            }
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

    public static void main(String[] args) {
        String errores = "";
        try {
            
            leerFichero("scpe1.txt");
            rellenarRatio();
            mostrarMatrizYVector();
            int mayor=buscarMayorRatio();
            
        } catch (FicheroNoEncontrado error) {
            errores = error.getMessage();
        }
        System.out.println(errores);
    }

}
