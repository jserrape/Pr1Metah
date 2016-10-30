package pr1metah;

import java.util.ArrayList;

/**
 *
 * @author Juan Carlos
 */
public class QuickSort {

    private ArrayList<TabuComponent> array;
    private int length;

    /**
     * Funcion para ordenar de menor a mayor una lista de vecinos en funcion
     * de su coste
     *
     * @param inputArr Array de TabuComponent
     */
    public void sort(ArrayList<TabuComponent> inputArr) {
        if (inputArr == null || inputArr.isEmpty()) {
            return;
        }
        this.array = inputArr;
        length = inputArr.size();
        quickSort(0, length - 1);
    }

    private void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        TabuComponent pivot = array.get(lowerIndex + (higherIndex - lowerIndex) / 2);
        while (i <= j) {
            while (array.get(i).getCoste() < pivot.getCoste()) {
                ++i;
            }
            while (array.get(j).getCoste() > pivot.getCoste()) {
                --j;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                ++i;
                --j;
            }
        }
        if (lowerIndex < j) {
            quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex);
        }
    }

    private void exchangeNumbers(int i, int j) {
        TabuComponent temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
