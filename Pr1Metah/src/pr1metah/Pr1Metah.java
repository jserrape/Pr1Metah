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
import java.util.Random;

/**
 *
 * @author Xenahort
 *
 * ------- Errores pendientes ------
 *  Ver si hay algun problema con la busqueda local (indices) empeazar los bucles en 1
 */
public class Pr1Metah {

    static int cubre[];
    static float ratio[];
    static int matriz[][];
    static int y, x;
    static int solucion[];
    static Pair cubreOrdenado[];
    static int base, anterior, factorizado, actual, costeVecina;
    
    public static final int SEMILLA1 = 77383426;
    public static final int SEMILLA2 = 77368737;
    public static final int SEMILLA3 = 34267738;
    public static final int SEMILLA4 = 87377736;
    public static final int SEMILLA5 = 34268737;


    public static void eliminaRedundancias() {



        int quito;
        int i;
        boolean columnaRedundante, sustituible;
        for (int z = 0; z < y - 1; z++) {
            if (solucion[cubreOrdenado[z].getLugar()] == 1) {
                columnaRedundante = true;
                quito = cubreOrdenado[z].getLugar();
                //System.out.println("Voy a intentar eliminar el " + quito);

                sustituible = false;
                for (i = 1; i < x; i++) {
                    if (matriz[i][quito] == 1) {
                        sustituible = false;
                        for (int j = 1; j < y; j++) {
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
                    solucion[quito]=0;
                } else {
                    //System.out.println("El " + quito + " no sobra.");
                }

            }
        }
    }

    public static boolean faltanPorCubir() {
        for (int i = 1; i < y; i++) {
            if (cubre[i] != 0) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarSolucion() {
        System.out.println("Solucion:");
        for (int i = 1; i < y; i++) {
            if (solucion[i] == 1){
                System.out.print(i + ":" + solucion[i] + " ");
            }
        }
        System.out.println("\n");
    }

    public static void mostrarMatrizYVector() {
        for (int i = 0; i < x; i++) { // MUESTRO LA MATRIZ
            for (int j = 0; j < y; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("\n Vector de cuantos cubre cada uno:");
        for (int i = 1; i < y; i++) {// MUESTRO a CUANTOS CUBRE
            System.out.print(i + ":" + cubre[i] + "\t");
        }
        System.out.println("\n Vector del ratio:");
        for (int i = 1; i < y; i++) { // MUESTRO EL RATIO
            System.out.print(i + ":" + ratio[i] + "\t");
        }
        System.out.print("\n");
    }

    public static void buscarMayorRatio() {

        int mayor = 1;
        for (int i = 2; i < y; i++) {
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
        System.out.println("Aquel con mayor ratio es el numero " + mayor + "\n");
        solucion[mayor] = 1; //Establezco el que tiene mas ratio como solucion

        for (int i = 1; i < x; i++) {
            if (matriz[i][mayor] == 1 && matriz[i][0] == 0) {
                ++matriz[i][0]; //DEBERA SER SIEMPRE 1, EL ++ ES PARA IR VIENDO SI ME REPITO
                for (int j = 1; j < y; j++) {
                    if (matriz[i][j] == 1) {
                        --cubre[j];
                    }
                }
            }
        }

    }

    public static void rellenarRatio() {
        for (int i = 1; i < y; i++) {
            System.out.print(cubre[i]+"/"+matriz[0][i]+"  ");
            ratio[i] = (float)cubre[i] / matriz[0][i];
        }
        System.out.print("\n");
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
            x = Integer.parseInt(datos[1]) + 1;
            y = Integer.parseInt(datos[2]) + 1;

            matriz = new int[x][y];
            cubre = new int[y];

            for (int i = 0; i < y; i++) {
                cubre[i] = 0;
            }

            for (int i = 1; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    matriz[i][j] = 0;
                }
            }
            matriz[0][0] = 0; //<---------PONER A -1

            int comisariasV = 1;
            while (y != comisariasV) {
                texto = br.readLine();
                datos = texto.split(" ");
                for (int i = 1; i < datos.length; i++) {
                    matriz[0][comisariasV] = Integer.parseInt(datos[i]);
                    ++comisariasV;
                }
            }
            int cont;
            for (int i = 1; i < x; i++) {
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

            solucion = new int[y];
            for (int i = 0; i < y; i++) {
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
    
    public static void busquedaLocal() {
        int solucionActual[] = solucion; // Inicializacion del Greedy
        int solucionVecina[] = solucion;
        int solucionAnterior[] = solucion;
        base = objetivo(solucion);
        actual = base;
        int valorMV = -1;
        anterior = 99999999;
        int valor = -1; 
        costeVecina = 1;
        int terminado;
        
        //Revisar bien los bucles, mierda de pseudocodigo
        while(costeVecina < anterior) {  //objetivo(solucionVecina) < objetivo(solucionAnterior)
            if (costeVecina < anterior) { //En este instante anterior y actual coinciden
                    solucionActual = solucionVecina;
            }
            terminado = calculaIteraciones(solucionActual);
            while( (costeVecina > valorMV) && terminado > 0) { 
                generaSolucionVecina(solucionActual, solucionVecina, SEMILLA1); // se queda colgado por la condicion del while            
                --terminado;
                if (costeVecina < valorMV) {
                    valorMV = costeVecina;
                }
            }  
            solucionAnterior = solucionActual;
            anterior = objetivo(solucionAnterior);
        }
        solucion = solucionActual;
    }   
    
    public static void generaSolucionVecina(int solucionActual[], int solucionVecina[], int semilla){
       System.out.printf("--------------------------------------------------------------------------------- \n");
       Random aleatorio = new Random(semilla);
       int pos = Math.abs((aleatorio.nextInt() % (y - 1)));
       solucionVecina = solucionActual;
       boolean parada = true;
       while (parada) {
           if (pos == 0) {
               pos++;
           }
           if (solucionVecina[pos] == 0) {
               ++pos;
               pos = (pos % (y - 1));
           } else {
               solucionVecina[pos] = 0;
               parada = false;
           }
       }
       
       //Se genera un vector con todos los candidatos que cubren alguna zona de las que me quedan por cubrir al eliminar esa ( sin incluirla )
       int vecino[] = new int[y]; 
       int zonas[] = new int[x];
       int s = 1;
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
                if(matriz[j][k] != 0 && zonas[j] == 0 && solucionVecina[k] == 1) {
                       zonas[j] = 1;
                       ++zonasPendientes;
                }
            }
        }
        zonasPendientes = x - zonasPendientes; //Ver si hay que restar 1
        //System.out.printf("Hay estas %s \n", zonasPendientes);
       
        for (int k = 1; (k < y && zonasPendientes > 0); k++) {
            for (int j = 1; j < x; j++) {
                if (k != pos) {
                    if ((matriz[j][k] == 1) && (zonas[j] == 0)) { //La zona esta sin cubrir
                        vecino[k] = 1; //Ver si se puede evitar esta asignacion continuamente
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
                }
            }
        }
        costeVecina = objetivo(solucionVecina);
        solucion = solucionVecina;
        eliminaRedundancias(); // Y ya?
    }
    
    public static int calculaIteraciones(int solucionVecina[]) {
        int cont = 0;
        for (int i = 1; i < y; i++){
            if (solucionVecina[i] == 1){
                ++cont;
            }
        }
        return cont;
    }
    
    public static int objetivo(int solucionVecina[]) {
        int suma = 0;
        for (int i = 1; i < y; i++) {
            suma += solucionVecina[i] * matriz[0][i]; 
        }
        return suma;
    }


    public static void main(String[] args) {
        String errores = "";
        try {
            leerFichero("scpe1.txt");
            ratio = new float[y];
            cubre[0] = 0;

            cubreOrdenado = new Pair[y - 1];
            for (int i = 0; i < y - 1; i++) {
                cubreOrdenado[i] = new Pair(i + 1, cubre[i + 1]);
            }
                    MyQuickSort sorter = new MyQuickSort();
        sorter.sort(cubreOrdenado);

            rellenarRatio();

            mostrarMatrizYVector();
            mostrarSolucion();

            buscarMayorRatio(); 
            
            while (faltanPorCubir()) {
                buscarMayorRatio();
                rellenarRatio();
                mostrarSolucion();
            }
            eliminaRedundancias();
            int sumar = objetivo(solucion);
            
            System.out.printf("Coste de la solucion %s \n", sumar);
            mostrarSolucion();
            
            System.out.println("-------------------------------- EMPIEZA BUSQUEDA LOCAL");
            busquedaLocal();
            sumar = objetivo(solucion);
            System.out.printf("Coste de la solucion %s \n", sumar);
            
            mostrarSolucion();

        } catch (FicheroNoEncontrado error) {
            errores = error.getMessage();
        }
        System.out.println(errores);
    }


}
