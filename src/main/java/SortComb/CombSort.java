package SortComb;

public class CombSort {
    private static int iterations = 0; // Счётчик итераций
    private static int swaps = 0;     // Счётчик обменов

    public static void sort(int[] arr) {
        iterations = 0;
        swaps = 0;

        int gap = arr.length;
        boolean swapped = true;

        while (gap > 1 || swapped) {
            gap = Math.max(1, (int)(gap / 1.3)); // Уменьшаем gap
            swapped = false;

            for (int i = 0; i < arr.length - gap; i++) {
                iterations++;
                if (arr[i] > arr[i + gap]) {
                    swap(arr, i, i + gap);
                    swapped = true;
                    swaps++;
                }
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {  //пузырьковая сортировка
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int getIterations() { return iterations; }
    public static int getSwaps() { return swaps; }
}