package newcode;

import java.util.Scanner;

/**
 * 华为机试 偶数分解成最接近的两个素数
 */
public class TwoSUSU {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int num = in.nextInt();
            for (int i = num / 2; i >= 1; i--) {
                boolean sign = true; // i 是不是一个素数
                boolean otherSign = true;
                for (int j = 2; j <= i / 2; j++) {
                    if (i % j == 0) {
                        sign = false;
                        break;
                    }
                }
                int otherNum = num - i;
                for (int j = 2; j <= otherNum / 2; j++) {
                    if (otherNum % j == 0) {
                        otherSign = false;
                        break;
                    }
                }
                if (sign && otherSign) {
                    System.out.println(i);
                    System.out.println(otherNum);
                    break;

                }
            }
        }
    }
}
