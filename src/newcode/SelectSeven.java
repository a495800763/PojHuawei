package newcode;

import java.util.Scanner;

/**
 * 华为机试 挑7
 */
public class SelectSeven {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int nextInt = in.nextInt();
            System.out.println(getResult(nextInt));
        }
    }

    private static int getResult(int nextInt) {
        int count = 0;
        for (int i = 1; i <= nextInt; i++) {
            int num = i;
            if (i % 7 == 0) {
                count++;
                continue;
            }
            boolean sign = false;
            while (num > 10) {

                int digits = num % 10;
                if (digits == 7) {
                    //count++;
                    sign = true;
                    break;
                }
                num = num / 10;
            }
            if ((num == 7&&! sign)||sign) {
                count++;
            }
        }
        return count;
    }
}
