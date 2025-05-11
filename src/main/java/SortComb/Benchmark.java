package SortComb;

import java.io.FileWriter;
import java.io.IOException;

public class Benchmark {
    private static int[] generateTestSizes() {
        int[] sizes = new int[55];
        int index = 0;
        int[] baseSizes = {100, 200, 300, 400, 500, 1000, 2000, 3000, 5000, 8000, 10000};


        for (int size : baseSizes) {
            sizes[index++] = size;
            sizes[index++] = (int)(size * 0.9);
            sizes[index++] = (int)(size * 1.1);
            sizes[index++] = size + 50;
            sizes[index++] = size - 50;
        }

        return sizes;
    }

    public static void main(String[] args) {
        int[] sizes = generateTestSizes();

        // Создаём CSV-файл
        try (FileWriter writer = new FileWriter("results/data.csv")) {
            writer.write("size,type,time_ns,iterations,swaps\n");


            for (int size : sizes) {
                if (size < 50) continue;

                testSort(writer, size, "random");
                testSort(writer, size, "sorted");
                testSort(writer, size, "reversed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Тесты завершены! Сгенерировано " + (sizes.length*3) + " наборов данных.");
    }

    private static void testSort(FileWriter writer, int size, String type) throws IOException {
        int[] arr = generateArray(size, type);
        int[] arrCopy = arr.clone();

        long startTime = System.nanoTime();
        CombSort.sort(arrCopy);
        long endTime = System.nanoTime();

        writer.write(String.format("%d,%s,%d,%d,%d\n",
                size, type,
                (endTime - startTime),
                CombSort.getIterations(),
                CombSort.getSwaps()
        ));
    }

    private static int[] generateArray(int size, String type) {
        switch (type) {
            case "random": return DataGenerator.generateRandomArray(size);
            case "sorted": return DataGenerator.generateSortedArray(size);
            case "reversed": return DataGenerator.generateReversedArray(size);
            default: throw new IllegalArgumentException("Неизвестный тип: " + type);
        }
    }
}