import java.util.Scanner;

public class KnapsackProblem {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int N = reader.nextInt();
        int V = reader.nextInt();
        int[] v = new int[N+1];
        int[] w = new int[N+1];
        for (int i = 1; i <= N; ++i) {
            v[i] = reader.nextInt();
            w[i] = reader.nextInt();
        }
        reader.close();

        int[][] dp1 = dp1(N, V, v, w);
        int[] dp2 = dp2(N, V, v, w);

        assert dp1[N][V] == dp2[V];
        System.out.println(dp2[V]);
    }

    /**
     * 01背包问题-1
     * 使用二维数组
     */
    private static int[][] dp1(int N, int V, int[] v, int[] w) {
        // dp[i][j]表示在 只能选择前i+1个物品，背包容量为j+1的情况下，背包中物品的最大价值
        int[][] dp = new int[N+1][V+1];
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= V; ++j) {
                if (j >= v[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp;
    }

    /**
     * 01背包问题-1
     * 改进，事实上一维数组就可以，但在循环中要从后往前，避免覆盖之后要用的之前的值
     */
    private static int[] dp2(int N, int V, int[] v, int[] w) {
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; ++i) {
            for (int j = V; j >= 1; --j) {
                if (j >= v[i]) dp[j] = Math.max(dp[j], dp[j-v[i]]+w[i]);
            }
        }
        return dp;
    }

    /**
     * 完全背包问题
     * 直接修改 dp2 函数，在循环内枚举 第i个可取的所有可能值。
     */
    private static int[] dp3(int N, int V, int[] v, int[] w) {
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; ++i) {
            for (int j = V; j >= 1; --j) {
                int max = j / v[i];

                for (int l = 1; l <= max; ++l) {
                    dp[j] = Math.max(dp[j], dp[j-l*v[i]]+l*w[i]);
                }
            }
        }
        return dp;
    }


}


