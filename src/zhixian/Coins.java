package zhixian;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 智线笔试1 游戏币组合
 */
public class Coins {
    public static void main(String[] args) {
        /**
         * 典型的动态规划问题，分析知，当游戏币个数n=1时，只有总面值m=游戏币面值的dp[1][m]=1,
         * 即dp[1][1]=dp[1][2]=dp[1][5]=dp[1][10]=1;
         * 而所求结果dp[n][m]经分析容易得知：其由一下集中情况相加
         * dp[n-1][m-1],dp[n-1][m-2],dp[n-1][m-5],dp[n-1][m-10];
         * 即所求值在最顶层，需要从底部依次计算，全部数值分布在n*m的矩阵的一半三角矩阵中
         *
         * 但是这样所求结果并不是全部情况的组合数，而是排列数
         * 例如 当n=2，m=7时
         * 所求结果 result=2
         * 代表两种情况 (1,2)+<5> 或者 (1,5)+<2>
         * 但其实这两种情况一样，即一个2元币和一个五元币的组合
         */

        System.out.println("请依次输入总游戏币数n 和总面值数 m：");
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();//总游戏币数
            int m = in.nextInt();//总面值数
            int result = getDpResult(n, m);
            System.out.println("结果：" + result);
        }
    }

    private static int getDpResult(int n, int m) {
        List<Integer> denominations = new ArrayList<>();
        denominations.add(0, 1);
        denominations.add(1, 2);
        denominations.add(2, 5);
        denominations.add(3, 10);

        //数组长度+1,使下标与n m实际值一致
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1) {
                    if (denominations.contains(j)) {
                        dp[i][j] = 1;
                    } else {
                        continue;
                    }
                } else {
                    for (Integer k : denominations) {
                        if (j - k <= 0) {
                            continue;
                        } else {
                            dp[i][j] += dp[i - 1][j - k];
                        }
                    }

                }
            }
        }

        return dp[n][m];
    }
}
