import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        Merge merge = new Merge();
        int[] testArr = {9,1,5,86,5,2,4,2,5,8,9,6,3,21,5,88,2,554,23,65,2};
        merge.mergeSort(testArr);
        System.out.println(Arrays.toString(testArr));

    }

    void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length);
    }

    void mergeSort(int[] arr, int lo, int hi) {
        // hi lo 间隔小于等于一个元素，递归跳出
        if(hi - lo < 2) return;

        int mi = lo + (hi - lo) / 2;
        mergeSort(arr, lo, mi);
        mergeSort(arr, mi, hi);
        merge(arr, lo, mi, hi);
    }

    void merge(int[] arr, int lo, int mi, int hi) {
        int la = mi - lo;

        // copy the left part
        int[] aux = new int[la];
        for(int i=0; i<la; aux[i] = arr[lo + i], i++);

        int i = lo, j = 0, k = mi;
        while (j < la && k < hi) {
            if (aux[j] <= arr[k]) arr[i++] = aux[j++];
            else arr[i++] = arr[k++];
        }
        while (j < la) arr[i++] = aux[j++];
        while (k < lo) arr[i++] = arr[k++];
    }

}
