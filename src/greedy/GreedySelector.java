package greedy;

import java.security.cert.TrustAnchor;

/**
 * 贪心算法的活动安排问题
 */
public class GreedySelector {

    public static void main(String[] args) {
        int[] s = new int[12];
        s[1] = 1;
        s[2] = 3;
        s[3] = 0;
        s[4] = 5;
        s[5] = 3;
        s[6] = 5;
        s[7] = 6;
        s[8] = 8;
        s[9] = 8;
        s[10] = 2;
        s[11] = 12;
        int[] f = new int[12];
        //结束时间按照递增顺序排列

        //只有结束时间按照顺序递增后才能使用贪心算法进行求解
        f[1] = 4;
        f[2] = 5;
        f[3] = 6;
        f[4] = 7;
        f[5] = 8;
        f[6] = 9;
        f[7] = 10;
        f[8] = 11;
        f[9] = 12;
        f[10] = 13;
        f[11] = 14;
        boolean[] a = new boolean[12];
        for (int i = 0; i < a.length; i++) {
            a[i] = false;
        }

        greedySelector(11, s, f, a);

    }

    /**
     * @param n 活动总数
     * @param s 起始时间集合
     * @param f 结束时间集合
     * @param a 每个活动是否被选择的结果集
     */
    public static void greedySelector(int n, int[] s, int[] f, boolean[] a) {
        a[1] = true;
        //当前排到第几个活动了
        int j = 1;
        System.out.print(1 + " ");
        for (int i = 2; i <= n; i++) {
            //此处选择的是开始时间在当前结束时间之后 的最近一个即其最早结束（贪心）
            if (s[i] > f[j]) {
                a[i] = true;
                System.out.print(i + " ");
                j = i;
            } else {
                a[i] = false;
            }

        }

        System.out.println('\n');
    }
}
