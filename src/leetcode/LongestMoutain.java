package leetcode;

/**
 * leetcode:845 数组中的最长山脉
 */
public class LongestMoutain {
    public static void main(String[] args) {

        int[] a = new int[7];
        a[0] = 2;
        a[1] = 1;
        a[2] = 3;
        a[3] = 7;
        a[4] = 3;
        a[5] = 2;
        a[6] = 5;

        System.out.println(longetMountain(a));

    }


    public static int longetMountain(int[] A) {
        int res = 0, down = 0, up = 0;
        boolean isUp = false;
        for (int i = 1; i < A.length; i++) {
            //上升
            if (A[i] > A[i - 1]) {
                //如果之前是下降，则此时可以结算之前的峰值
                if (!isUp) {
                    if (up > 0 && down > 0) {
                        res = Math.max(res, down + up + 1);
                    }
                    up = 0;
                    down = 0;
                }
                isUp = true;
                up++;
            } else if (A[i] < A[i - 1]) {
                //下降无需理会，因为它不涉及结算
                down++;
                isUp = false;
            } else {
                //如果刚好从下坡到平地，则可以结算
                if (!isUp && up > 0 && down > 0) {
                    int size = Math.min(down, up);
                    res = Math.max(res, down + up + 1);
                }
                up = 0;
                down = 0;
            }
        }
        //这里需要考虑，如果全程都处于完美的上升下降。
        if (up > 0 && down > 0) {
            res = Math.max(res, down + up + 1);
        }
        return res;
    }

}
