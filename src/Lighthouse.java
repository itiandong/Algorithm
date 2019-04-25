
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Lighthouse {
    /**
     * 每个灯塔都配有一盏探照灯，照亮其东北、西南两个对顶的直角区域。
     * 若灯塔A、B均在对方的照亮范围内，则称它们能够照亮彼此。
     * 上北下南左西右东。
     * <p>
     * 输入：
     * 共n+1行。
     * 第1行为1个整数n，表示灯塔的总数。
     * 第2到n+1行每行包含2个整数x, y，分别表示各灯塔的横、纵坐标。
     * <p>
     * 输出
     * 1个整数，表示可照亮彼此的灯塔对的数量。
     * <p>
     * 对于90%的测例：1 ≤ n ≤ 3×105
     * <p>
     * 对于95%的测例：1 ≤ n ≤ 106
     * <p>
     * 全部测例：1 ≤ n ≤ 4×106
     * <p>
     * 灯塔的坐标x, y是整数，且不同灯塔的x, y坐标均互异
     * <p>
     * 1 ≤ x, y ≤ 10^8
     * C/C++中的int类型通常被编译成32位整数，其范围为[-231, 231 - 1]，不一定足够容纳本题的输出。
     *
     * 3
     * 2 2
     * 4 3
     * 5 1
     *
     * 1
     */

    static class Point {
        int x, y;
        Point(int aX, int aY) {
            x = aX;
            y = aY;}
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Point[] arr = new Point[n];
        for (int i = 0; i < n; ++i) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            arr[i] = new Point(x, y);
        }
        long cnt1 = brute(arr);
        long cnt2 = dac(arr);
        assert cnt1 == cnt2;
        System.out.println(cnt1);
    }

    static long brute(Point[] arr) {
        long cnt = 0;
        int n = arr.length;
        for (int i=0; i<n; ++i) {
            for (int j=i+1; j<n; ++j) {
                if (((arr[i].x < arr[j].x) && (arr[i].y < arr[j].y)) ||
                        ((arr[i].x > arr[j].x) && (arr[i].y > arr[j].y))) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static long dac(Point[] arr) {
        // 问题转化为，按照x排序后，求 y所组成数组的顺序数目。
        Arrays.sort(arr, Comparator.comparingInt(a -> a.x));
        return in(arr, 0, arr.length);
    }

    static long in(Point[] arr, int lo, int hi) {
        if (hi - lo < 2) return 0;

        int mi = (lo + hi) >> 1;
        long left = in(arr, lo, mi);
        long right = in(arr, mi, hi);
        long between = inBetween(arr, lo, mi, hi);
        return left+right+between;
    }

    static long inBetween(Point[] arr, int lo, int mi, int hi) {
        int la = mi - lo;
        // 拷贝前半段数组到辅助数组
        Point[] aux = new Point[la];
        for (int i = 0; i < la; i++) {
            int x = arr[lo+i].x;
            int y = arr[lo+i].y;
            aux[i]= new Point(x, y);
        }

        long cnt = 0;
        int i=lo, j=0, k=mi;
        while (j < la && k < hi) {
            if (aux[j].y <= arr[k].y) arr[i++] = aux[j++];
            else {
                arr[i++] = arr[k++];
                // 如果 后半数组的元素更小，则当前元素与所有前半已归位元素构成 顺序。
                cnt += j;
            }
        }
        while (j < la) arr[i++] = arr[j++];
        while (k < hi) {
            // 如果 后半数组有剩余，则当前元素与所有前半元素分别构成 顺序。
            arr[i++] = arr[k++];
            cnt += la;
        }

        return cnt;
    }
}
