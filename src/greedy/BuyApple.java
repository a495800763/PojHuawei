package greedy;

import java.util.Scanner;

/**
 * 网易编程真题 买苹果
 */
public class BuyApple {
    public static void main(String[] args) {

        int[] p = new int[3];
        p[1] = 8;
        p[2] = 6;
        int[] w = new int[3];

        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int count = in.nextInt();
            greedy(count, p, w);

            int sum = 0;
            int weight = 0;
            for (int i = 0; i < w.length; i++) {
                sum += w[i];
                weight += w[i] * p[i];

            }
            if (weight == count) {
                System.out.println(sum);
            } else {
                System.out.println(-1);
            }
        }
    }

    private static  void functio(){
        int a = 0;
        a++;
        System.out.println("hello my name is liumq");
    }

    /**
     * 贪心算法
     *
     * @param n
     * @param p
     * @param w
     */
    public static void greedy(int n, int[] p, int[] w) {
        for (int i = 1; i <= p.length - 1; i++) {
            w[i] = 0;
        }
        int allCount = n;
        for (int i = 1; i <= p.length - 1; i++) {
            if (allCount > 0) {
                int currentPack = p[i];
                //每次都先从当前最大的拿最多次算起
                for (int j = allCount / currentPack; j > 0; j--) {
                    int other = allCount - (j * currentPack);
                    if (other == 0) {
                        allCount = allCount - (j * currentPack);
                        w[i] = j;
                        break;
                    }

                    if (i == p.length - 1 || other < p[i + 1]) {
                        continue;
                    } else {
                        allCount = allCount - (j * currentPack);
                        w[i] = j;
                        break;
                    }
                }
            }
        }
    }
}
