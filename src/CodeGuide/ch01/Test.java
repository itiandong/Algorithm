package CodeGuide.ch01;

public class Test {
    private static boolean check(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            if(arr[i] != i+1) return false;
        }
        return true;
    }

    static void func(int[] arr, int i) {
        int tmp = arr[0];
        arr[0] = arr[i];
        arr[i] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 4, 1, 5, 3, 2};
        int len = arr.length;
        int cnt = 0;

        for (int i = 0; i < len; ++i) {
            int currentMax = 0;
            for (int j = 1; j < len - i; ++j) {
                if (arr[j] > arr[currentMax]) currentMax = j;
            }
            if (currentMax == 0) {
                func(arr, currentMax);
                cnt++;
            }
            func(arr, len - i - 1);
            cnt++;
        }

        assert check(arr);

        System.out.println(cnt);

    }
}
