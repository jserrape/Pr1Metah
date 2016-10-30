/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Juan Carlos
 */
public class LocalSearch {

    private int terminado, numIteraciones;
    private Random aleatorio;
    private int solucionVecina[];
    private int contTabu, costeTabu;

    /**
     * Calcula una solución vecina intentando mejorar el coste de la solución Greedy
     * @param solucion vector solucion
     * @param matriz matriz con los datos del problema
     * @param x Numero de territorios +1
     * @param y Numero de comisarias +1
     * @param semilla semilla para aleatorizar los vecinos generados
     * @param pair vector de Pair para eliminar la s redundancias
     * @param pa ventana para los resultados
     * @param fich ventana para los resultados
     * @param ej ventana para los resultados
     * @return Devuelve una solución vecina
     */
    public int[] busquedaLocal(int solucion[], int matriz[][], int x, int y, Pair pair[], int semilla, Panel pa, String fich, int ej) {
        int anterior, costeVecina, costeActual, posicion;
        int solucionActual[] = solucion; // Inicializacion del Greedy
        costeActual = objetivo(solucionActual, y, matriz);
        numIteraciones = 1; //Empieza en uno ya que he llamado ya una vez a la funcion objetivo
        aleatorio = new Random();
        aleatorio.setSeed(semilla);
        ArrayList<Integer> comisarias;

        long time_start, time_end;
        time_start = System.currentTimeMillis();

        do {
            comisarias = calculaComisarias(solucionActual, y);
            do {
                posicion = Math.abs(aleatorio.nextInt() % comisarias.size());
                costeVecina = generaVecino(solucionActual, matriz, x, y, costeActual, comisarias.get(posicion), pair);
                comisarias.remove(posicion);
            } while ((costeVecina >= costeActual) &&  !comisarias.isEmpty() && numIteraciones < 10000); 
            anterior = costeActual;
            if (costeVecina < costeActual) {
                solucionActual = solucionVecina.clone();
                costeActual = costeVecina;
            }
        } while (costeVecina < anterior && numIteraciones < 10000);

        time_end = System.currentTimeMillis();
        pa.insertaDatos(fich, costeActual, (int) (time_end - time_start), ej, 2);

        return solucionActual;
    }

    /**
     * Calcula una solución vecina necesaria para el GRASP
     * @param solucion vector solucion
     * @param matriz matriz con los datos del problema
     * @param x Numero de territorios +1
     * @param y Numero de comisarias +1
     * @param semilla semilla para aleatorizar los vecinos generados
     * @param pair vector de Pair para eliminar la s redundancias
     * @return Devuelve una solución vecina
     */
    public int[] busquedaLocalGrasp(int solucion[], int matriz[][], int x, int y, Pair pair[], int semilla) {
        int anterior, costeVecina, costeActual, posicion;
        int solucionActual[] = solucion; // Inicializacion del Greedy
        costeActual = objetivo(solucionActual, y, matriz);
        numIteraciones = 1; //Empieza en uno ya que he llamado ya una vez a la funcion objetivo
        aleatorio = new Random();
        aleatorio.setSeed(semilla);
        ArrayList<Integer> comisarias;

        do {
            comisarias = calculaComisarias(solucionActual, y);
            do {
                posicion = Math.abs(aleatorio.nextInt() % comisarias.size());
                costeVecina = generaVecino(solucionActual, matriz, x, y, costeActual, comisarias.get(posicion), pair);
                comisarias.remove(posicion);
            } while ((costeVecina >= costeActual) &&  comisarias.size() > 0 && numIteraciones < 400); 
            anterior = costeActual;
            if (costeVecina < costeActual) {
                solucionActual = solucionVecina.clone();
                costeActual = costeVecina;
            }
        } while (costeVecina < anterior && numIteraciones < 400);

        return solucionActual;
    }

    /**
     * Calcula un vecindario necesiario para la búsqueda tabú
     * @param solucion vector solucion
     * @param matriz matriz con los datos del problema
     * @param x Numero de territorios +1
     * @param y Numero de comisarias +1
     * @param tabulist lista de los movimientos tabu
     * @param mejor mejor coste encontrado
     * @param coste coste de la solución base
     * @param pair vector de Pair para eliminar la s redundancias
     * @return Devuelve el vecindario de una solución dada
     */
    public ArrayList<TabuComponent> busquedaLocalTabu(int solucion[], int matriz[][], int x, int y, Pair pair[], TabuList tabulist, int mejor, int coste) {

        int solucionActual[] = solucion, posicion, cont = 0; // Inicializacion del Greedy
        costeTabu = coste;
        ArrayList<Integer> comisarias;
        comisarias = calculaComisarias(solucionActual, y);
        ArrayList<TabuComponent> vecindario = new ArrayList<>();
        
        while (!comisarias.isEmpty() && cont < 50) {
            posicion = Math.abs(aleatorio.nextInt() % comisarias.size());
            TabuComponent tabuComponent = generaVecinoTabu(solucionActual, matriz, x, y, costeTabu, comisarias.get(posicion), pair); // devuelvo el coste;
            if (!tabulist.find(tabuComponent.getEliminado(), tabuComponent.getNuevas())) {
                vecindario.add(tabuComponent);
            } else {
                if (tabuComponent.getCoste() < mejor) {
                    vecindario.add(tabuComponent);
                }
            }
            comisarias.remove(posicion);
            ++cont;
        }
        return vecindario;
    }

    /**
     * Calcula una solucion vecina para el entorno
     * @param solucionActual la solucion original (Antes de aplicar el operador vecino)
     * @param matriz matriz con los datos del problema
     * @param x Numero de territorios +1
     * @param y Numero de comisarias +1
     * @param costeActual el coste actual de la solucion
     * @param pos
     * @param pair vector de Pair para eliminar la s redundancias
     * @return Devuelve una componente tabú con los datos necesarios sobre el vecino generado
     */
    public TabuComponent generaVecinoTabu(int solucionActual[], int matriz[][], int x, int y, int costeActual, int pos, Pair pair[]) {

        int costeVecina;
        ++contTabu;
        solucionVecina = solucionActual.clone();
        solucionVecina[pos] = 0;
        costeVecina = costeActual - matriz[0][pos];
        
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
        int coste = eliminaRedundancias(y, x, matriz, pair, costeVecina);
        ArrayList<Integer> nuevos = calculaNuevos(solucionActual, solucionVecina, y);
        TabuComponent pairTabu = new TabuComponent(pos, coste, solucionVecina.clone(), nuevos);
        return pairTabu;
    }

     /**
     * Calcula una solucion vecina para el entorno
     * @param solucionActual la solucion original (Antes de aplicar el operador vecino)
     * @param matriz matriz con los datos del problema
     * @param x Numero de territorios +1
     * @param y Numero de comisarias +1
     * @param costeActual el coste actual de la solucion
     * @param pos comisaria que vamos a eliminar
     * @param pair vector de Pair para eliminar la s redundancias
     * @return Devuelve el coste del vecino generado
     */
    public int generaVecino(int solucionActual[], int matriz[][], int x, int y, int costeActual, int pos, Pair pair[]) {

        int costeVecina;
        solucionVecina = solucionActual.clone();
        ++numIteraciones; //1 factorizacion, por lo que se incrementa el contador
        solucionVecina[pos] = 0;
        costeVecina = costeActual - matriz[0][pos];
            
       

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

     /**
     * Elimina las columnas redundantes de una solucioón
     * @param x Numero de territorios +1
     * @param y Numero de comisarias +1
     * @param matriz Matriz con la informacion del problema
     * @param cubreOrdenado vector de Pair para eliminar las redundancias
     * @param costeVecina el coste del vecino
     * @return el coste factorizado de una solución vecina
     */
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

    /**
     * @param solucionVecina vector solucion
     * @param tam el tamaño del vector solucion
     * @return Devuelve el número de conjuntos que tiene una solucion
     */
    public static int calculaIteraciones(int solucionVecina[], int tam) {
        int cont = 0;
        for (int i = 1; i < tam; i++) {
            if (solucionVecina[i] == 1) {
                ++cont;
            }
        }
        return cont;
    }

    /**
     * Calcula el coste de un vector solucion
     * @param solucionVecina vector solucion
     * @param tam tamaño del vector solucion
     * @param matriz matriz con los datos del problema
     * @return Devuelve el coste de una solucion
     */
    public static int objetivo(int solucionVecina[], int tam, int matriz[][]) {
        int suma = 0;
        for (int i = 1; i < tam; i++) {
            suma += solucionVecina[i] * matriz[0][i];
        }
        return suma;
    }

    /**
     * @return Devuelve el número de iteraciones dadas
     */
    public int getIteraciones() {
        return numIteraciones;
    }
    

    /**
     * @return Devuelve el número de iteraciones dadas (GRASP)
     */
    public int getIteracionesGrasp() {
        return numIteraciones - 1;
    }


    /**
     * Calcula los subconjuntos nuevos que se han añadido a una soluion dspués
     * de generar una solución vecina
     * @param solucionOriginal la solucion original
     * @param solucionVecina la solucion vecina generada
     * @param tam el tamaño del vector solucion
     * @return Devuelve un vector con las columnas necesarias
     */
    public ArrayList<Integer> calculaNuevos(int solucionOriginal[], int solucionVecina[], int tam) {
        ArrayList<Integer> nuevos = new ArrayList<>();
        for (int i = 0; i < tam; i++) {
            if (solucionOriginal[i] == 0 && solucionVecina[i] == 1) {
                nuevos.add(i);
            }
        }
        return nuevos;
    }

    /**
     * @return Devuelve el número de iteraciones dadas en una búsqueda tabú
     */
    public int getContTabu() {
        return contTabu;
    }

    /**
     * Reinicia el contador de iteraciones para la búsqueda Tabú
     */
    public void reiniciaTabuCont() {
        contTabu = 0;
    }
    
    /**
     * @param solucion vector solución  
     * @param y tamaño del vector solución
     * @return Devuelve un ArrayList con todas las comisarias que tiene una solución
     */
    public ArrayList<Integer> calculaComisarias(int solucion[], int y){
        ArrayList<Integer> array;
        array = new ArrayList<>();
        for(int i = 1; i < y; i++){
            if(solucion[i] == 1){
                array.add(i);
            }
        }
        return array;
    }
}
