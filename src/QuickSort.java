import java.util.Random;

public class QuickSort {
    static final Random rand = new Random();

    public static void main(String[] args) {
        int[] testArr = new int[20];

        int n=5;
        while (n-- > 0) {
            for (int i = 0; i < 20; ++i) {
                testArr[i] = rand.nextInt(100);
            }
            System.out.println("BeforeSort:");
            for(int e: testArr) System.out.print(e + " ");
            System.out.println();
            quickSort(testArr);
            System.out.print("AfterSort: ");
            System.out.println(check(testArr));
            for(int e: testArr) System.out.print(e + " ");
            System.out.println();
        }

    }

    static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length);
    }

    /**
     * 对 [lo, hi) 排序。
     */
    static void quickSort(int[] arr, int lo, int hi) {
        if(hi -lo < 2) return; //单区间自然有序

        int mi = partition(arr, lo, hi-1); //轴点已经排序
        quickSort(arr, lo, mi);
        quickSort(arr, mi + 1, hi);
    }

    /**
     * 在 [lo, hi] 中寻找轴点（pivot）
     */
    private static int partition(int[] arr, int lo, int hi) {
        swap(arr, lo, lo + rand.nextInt(hi-lo+1));
        int pivot = arr[lo];
        while (lo < hi) {
            while ((lo < hi) && pivot <= arr[hi]) {
                hi--;
            }
            arr[lo] = arr[hi];
            while ((lo < hi) && arr[lo] <= pivot) {
                lo++;
            }
            arr[hi] = arr[lo];
        }
        assert lo==hi;
        arr[lo] = pivot;
        return lo;
    }

    private static void swap(int[] arr, int a, int b) {
        if(a == b) return;
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static boolean check(int[] arr) {
        assert arr.length > 1;
        for (int i = 1; i < arr.length; ++i) {
            if(arr[i] < arr[i-1]) return false;
        }
        return true;
    }
}
