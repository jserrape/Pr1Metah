package pr1metah;

/**
 *
 * @author Juan Carlos
 */
public class QuickSort {

    private TabuComponent array[];
    private int length;

    /**
     * Funcion para ordenar de menor a mayor una lista de vecinos en funcion
     * de su coste
     *
     * @param inputArr Array de TabuComponent
     */
    public void sort(TabuComponent[] inputArr) {
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }

    private void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        TabuComponent pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        while (i <= j) {
            while (array[i].getCoste() < pivot.getCoste()) {
                ++i;
            }
            while (array[j].getCoste() > pivot.getCoste()) {
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
        TabuComponent temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
