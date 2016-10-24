/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

import java.util.ArrayList;

/**
 *
 * @author Juan Carlos
 */

public class Tabu {

    private int solucion[], mejorSolucion[];
    private TabuList tabulist;
    private int mejor, coste;

    /**
     * Calcula una solución a nuestro problema usando la búsqueda Tabú partiendo
     * de una solución Greedy inicial
     * 
     * @param x número total de zonas (incrementado en 1)
     * @param y número total de comisarías (incrementado en 1)
     * @param mat la matriz del problema que contiene los datos
     * @param greedySol la solución Greedy inicial
     * @param pair vector de Pair para eliminar las redundancias
     * @param local objeto para llamar a los métodos de la búsqueda local
     * @param semilla semilla que se va a utilizar en la instancia
     * @param pa ventana para los resultados
     * @param fich ventana para los resultados
     * @param ej ventana para los resultados
     * @return Devuelve la mejor solución encontrada
     */
    public int[] tabuSearch(int x, int y, int mat[][], int greedySol[], Pair pair[], LocalSearch local, int semilla, Panel pa, String fich, int ej) {
        mejorSolucion = solucion = greedySol.clone();
        int tamGreedy = calcGreedySets(solucion, y);
        coste = mejor = objetivo(solucion, y, mat);
        tabulist = new TabuList(tamGreedy);

        long time_start, time_end;
        time_start = System.currentTimeMillis();
        local.reiniciaTabuCont();
        do {
            TabuList vecindario = local.busquedaLocalTabu(solucion, mat, x, y, pair, coste, semilla); //Vecindario del entorno actual
            TabuComponent candidato = escogeVecino(vecindario);
            if (candidato == null) {
                time_end = System.currentTimeMillis();
                pa.insertaDatos(fich, mejor, (int) (time_end - time_start), ej, 3);
                return mejorSolucion;
            }
            solucion = candidato.getVecino();
            coste = candidato.getCoste();
            if (candidato.getCoste() < mejor) {
                mejorSolucion = solucion.clone();
                mejor = candidato.getCoste();
            }
        } while (local.getContTabu() <= 10000);
        time_end = System.currentTimeMillis();
        pa.insertaDatos(fich, mejor, (int) (time_end - time_start), ej, 3);
        return mejorSolucion;
    }

    /**
     * Calcula el mejor vecino siguiendo los criterios tabú, se escoge el vecino
     * de menor coste que no esté incluido en la lista tabú o si se da el caso
     * el vecino que mejore la mejor solución encontrada aún siendo tabú
     * 
     * @param vecindario el vecindario que se quiere evaluar
     * @return Devuelve el mejor vecino
     */
    public TabuComponent escogeVecino(TabuList vecindario) {
        QuickSort sorter = new QuickSort();
        sorter.sort(vecindario.getLista());
        int coste;
        for (int i = 0; i < vecindario.getTaml(); i++) {
            coste = vecindario.getLista()[i].getCoste();
            int eliminado = vecindario.getLista()[i].getEliminado();
            int vecino[] = vecindario.getLista()[i].getVecino();
            ArrayList<Integer> array = vecindario.getLista()[i].getNuevas();
            if (coste < mejor) {
                if (!tabulist.find(eliminado, array)) {
                    tabulist.addSet(eliminado, coste, vecino, array);
                    return vecindario.getComponent(i);
                } else {
                    tabulist.updatePos(i);
                    return vecindario.getComponent(i);
                }
            } else if (!tabulist.find(eliminado, array)) {
                tabulist.addSet(eliminado, coste, vecino, array);
                return vecindario.getComponent(i);
            }
        }
        return null;
    }

    /**
     * Calcula el numero de conjuntos que tiene una solucion Greedy
     * @param solucion el vector de la solucion greedy
     * @param tam tamaño del vector solucion
     * @return Devuelve el número de conjuntos que tiene una solución greedy
     */
    public int calcGreedySets(int solucion[], int tam) {
        int cont = 0;
        for (int i = 1; i < tam; i++) {
            if (solucion[i] == 1) {
                ++cont;
            }
        }
        return cont;
    }

    /**
     * Calcula el coste de un vector solucion
     * @param solucionVecina vector solucion que se quiere evaluar
     * @param tam tamaño del vector solucion
     * @param matriz matriz de costes del problema
     * @return Devuelve el coste del vector solucion
     */
    public static int objetivo(int solucionVecina[], int tam, int matriz[][]) {
        int suma = 0;
        for (int i = 1; i < tam; i++) {
            suma += solucionVecina[i] * matriz[0][i];
        }
        return suma;
    }
}
