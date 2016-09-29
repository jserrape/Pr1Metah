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
import java.util.Arrays;

/**
 *
 * @author Xenahort
 */
public class Pr1Metah {

    static int matriz[][];
    static int x,y;

    public static void leerFichero() {
        String fich = "scpe1.txt";
        if (!(new File(fich)).exists()) {
            System.out.print("Fichero no encontrado\n");
            return;
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
            System.out.print("Vamoh a leer\n");
            texto = br.readLine();
            datos = texto.split(" ");
            System.out.print(texto+"\n");
            x=Integer.parseInt(datos[1])+1;
            y=Integer.parseInt(datos[2])+1;
            
            matriz = new int[x][y];
            
            
            for (int i = 0; i < x; i++) {   //SOBRA
                for (int j = 0; j < y; j++) {
                    matriz[i][j] = -2;
                }
            }
            
            
            for(int i=0;i<y;i++){
                matriz[0][i] = -1;
            }
            for(int j=0;j<x;j++){
                matriz[j][0] = 0;
            }
            
            
            

            int comisariasV=1;
            System.out.print("Y vale: "+y+"  comisariasV vale: "+comisariasV+" \n");
            while(y!=comisariasV){
                if((texto = br.readLine()) != null){
                    datos = texto.split(" ");
                    for(int i=1;i<datos.length;i++){
                        matriz[0][comisariasV] = Integer.parseInt(datos[i]);
                        ++comisariasV;
                        System.out.print("Y vale: "+y+"  comisariasV vale: "+comisariasV+" \n");
                    }
                }
            }
            System.out.print(texto = br.readLine()+"\n"); //<---------------------- SOBRA URGENTEMENTE
            
            
            for (int i = 0; i < x; i++) {    //SOBRA
                for (int j = 0; j < y; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.print("\n");
            }
            
            /*
            while ((texto = br.readLine()) != null) {
                System.out.print(texto + "\n");
            }
            */
        } catch (IOException | NumberFormatException e) {
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
            }
        }

        System.out.print("Sali bien\n");
    }

    public static void main(String[] args) {
        leerFichero();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
  //              System.out.print(matriz[i][j] + " ");
            }
            //System.out.print("\n");
        }
    }

}
