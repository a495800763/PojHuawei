package zhixian;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 游戏币组合
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
            int dpResult = getDPResultByMap(n, m);
            System.out.println("结果：" + dpResult);
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


    /**
     * @param n
     * @param m
     * @return
     */
    private static int getDPResultByMap(int n, int m) {
        /**
         * 使用list 嵌套map 将每一种可能的结果储存下来
         * 最后进行去重得到正确的组合数
         */

        List<Integer> denominations = new ArrayList<>();
        denominations.add(0, 1);
        denominations.add(1, 2);
        denominations.add(2, 5);
        denominations.add(3, 10);

        //数组长度+1,使下标与n m实际值一致
        List<Map<Integer, Integer>>[][] dp = new ArrayList[n + 1][m + 1];
        Integer[] countArray = new Integer[n + 1];
        for (int i = 1; i <= n; i++) {
            //如果m>i*10;则构造超过i*10 的结果是无意义的
            // 例如 i=1,m=50,使用一个币最多只能得到面值10
            int count = i * 10 <= m ? i * 10 : m;
            countArray[i] = count;
            for (int j = 1; j <= count; j++) {
                if (dp[i][j] == null) {
                    dp[i][j] = new ArrayList<>();
                }
                if (i == 1) {
                    if (dp[i][j].size() == 0) {
                        HashMap<Integer, Integer> e = new HashMap<>();
                        e.put(1, 0);
                        e.put(2, 0);
                        e.put(5, 0);
                        e.put(10, 0);
                        dp[i][j].add(e);
                    }
                    if (denominations.contains(j)) {
                        dp[i][j].get(0).put(j, 1);
                    } else {
                        continue;
                    }
                } else {
                    for (Integer k : denominations) {
                        //System.out.println(LocalDateTime.now().toString() +i + ":" + j + ":" + k);
                        if (j - k <= 0) {
                            continue;
                        } else {
                            boolean sign = true;
                            // dp[i-1]只初始化了前countArray[i-1]个数据，其他的情况直接continue
                            if (j - k > countArray[i - 1]) {
                                continue;
                            }
                            for (Map<Integer, Integer> map : dp[i - 1][j - k]) {
                                // 如果当前map中所有的数据都是0,则直接continue
                                for (Integer value : map.values()) {
                                    if (value != 0) {
                                        sign = false;
                                        break;
                                    }
                                }
                                if (sign) {
                                    continue;
                                }
                                //map中数据有值时 说明是一种可能情况，将这种情况拿出来作为当前的解，每个解中增加当前的游戏币面值，即map中面值的值=1;
                                //每次都新建全新的map 从以前值中拷贝数据
                                Map<Integer, Integer> current = new HashMap<>();
                                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                                    Integer value = entry.getValue();
                                    if (entry.getKey().equals(k)) {
                                        value += 1;
                                    }
                                    current.put(entry.getKey(), value);
                                }
                                dp[i][j].add(current);
                            }
                        }
                    }
                }
            }

            // 清理数据  避免 内存移除


            //i循环完以后 dp[i]里面存在很多重复的map 必须清理重复数据
            List<String> list = new ArrayList<>();
            for (List<Map<Integer, Integer>> maps : dp[i]) {
                if (maps == null) {
                    continue;
                }
                Iterator<Map<Integer, Integer>> iterator = maps.iterator();
                while (iterator.hasNext()) {
                    String str = "";
                    Map<Integer, Integer> next = iterator.next();
                    for (Map.Entry<Integer, Integer> entry : next.entrySet()) {
                        str += entry.getKey() + ":" + entry.getValue() + ";";
                    }
                    if (!list.contains(str)) {
                        list.add(str);
                    } else {
                        iterator.remove();
                    }
                }
            }
            //i循环完以后 i-1的数据便没意义了，清理
            for (int u = 0; u < dp[i - 1].length; u++) {
                if (dp[i - 1][u] != null) {
                    dp[i - 1][u].clear();
                }
            }
        }


        // 数据已经被清理过了，剩下的map中每种情况是唯一的 直接返回size
        System.out.println(dp[n][m]);
        return dp[n][m].size();
    }
}
