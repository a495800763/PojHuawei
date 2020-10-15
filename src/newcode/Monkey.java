package newcode;

import java.util.Scanner;

public class Monkey {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int count = in.nextInt();
            System.out.println(getDpResult(count));
        }
    }

    /**
     * dp
     *
     * @param count
     * @return
     */
    private static int getDpResult(int count) {
        int[] dp = new int[count + 1];

        for (int i = 1; i <= count; i++) {
            if (i == 1 || i == 2) {
                dp[i] = 1;
            } else if (i == 3) {
                dp[i] = 2;
            } else {
                dp[i] = dp[i - 1] + dp[i - 3];
            }
        }

        return dp[count];

    }
}
