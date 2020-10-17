package greedy;

/**
 * 部分背包问题 可以用贪心算法求解
 */
public class Backpack {
    public static void main(String[] args) {

    }

    /**
     * 基于贪心算法的部分背包问题
     *
     * @param n 物品总个数
     * @param M 要求的最高重量
     * @param v 每一类物品的价值
     * @param w 每一类物品的重量
     * @param x 最后结果即 每一类物品怎么取多少
     */
    public static void Knapsack(int n, float M, float[] v, float[] w, float[] x) {
        //排序: 似的 v w 中的 结果 按照价值重量比从高到低
        sort(n, v, w);

        int i;
        for (i = 1; i <= n; i++) {
            //初始化结果集
            x[i] = 0;
        }


        float c = M;//当前还可以装的物品重量
        int lastIndex = 1; // 最后哪一个物品只装一部分
        for (int j = 1; i <= n; i++) {
            if (w[j] > c) {
                lastIndex = j;
                break;
            } else {
                c -= w[i];
                x[i] = 1;
            }
        }

        if (lastIndex <= n) {
            x[i] = c / w[i];
        }
    }

    /**
     * 排序后的 vw 中 按v[i] / w[i] 的降序排列
     * @param n
     * @param v
     * @param w
     */
    private static void sort(int n, float[] v, float[] w) {
        float[] z = new float[n + 1];
        for (int i = 0; i < n; i++) {
            z[i] = v[i] / w[i];//用z[]存商品的单位重量价值
        }
        for (int i = 0; i < n; i++) {//此排序的策略是每次把 单位重量商品的价值最大 的商品放在前面
            for (int j = i + 1; j < n; j++) {
                if (z[i] < z[j]) {
                    float temp = z[i];
                    z[i] = z[j];
                    z[j] = temp;
                    float tempw = w[i];//这里不要忘记把商品的重量和价值同时放到最前面
                    w[i] = w[j];
                    w[j] = tempw;
                    float tempv = v[i];
                    v[i] = v[j];
                    v[j] = tempv;
                }
            }
        }

    }
}
