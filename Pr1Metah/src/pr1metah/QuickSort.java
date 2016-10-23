package pr1metah;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juanca
 */
public class QuickSort {
    private TabuComponent array[];
        private int length;

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
            // calculate pivot number, I am taking pivot as middle index number
            TabuComponent pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
            // Divide into two arrays
            while (i <= j) {
                /**
                 * In each iteration, we will identify a number from left side
                 * which is greater then the pivot value, and also we will
                 * identify a number from right side which is less then the
                 * pivot value. Once the search is done, then we exchange both
                 * numbers.
                 */
                while (array[i].getCoste() < pivot.getCoste()) {
                    ++i;
                }
                while (array[j].getCoste() > pivot.getCoste()) {
                    --j;
                }
                if (i <= j) {
                    exchangeNumbers(i, j);
                    //move index to next position on both sides
                    ++i;
                    --j;
                }
            }
            // call quickSort() method recursively
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
                        int a = 7;
        }
}
