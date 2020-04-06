package com.tencent.avengong.algorithm.sort;

public class SortUtil {

    public static void main(String[] args) {
        int[] ints = {4, 3, 6, 7, 5};

        bubbleSort(ints);
        for (int i : ints) {
            System.out.println(" bubbleSort-- end!!!-i:" + i);
        }
    }


    public static void bubbleSort(int[] a) {
        int length = a.length;
        boolean isExchange;
        for (int i = 0; i < length; i++) {
            System.out.println(" bubbleSort---i:" + i);
            isExchange = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    isExchange = true;
                }
            }
            if (!isExchange) { // 优化
                break;
            }

        }

    }

}
