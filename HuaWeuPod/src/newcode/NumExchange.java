package newcode;

import java.util.Scanner;

/**
 * 华为机考 进制转换
 */
public class NumExchange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int result = 0;
            String next = in.next();
            String[] split = next.split("0x");
            String s = split[1];
            char[] chars = s.toCharArray();
            int length = chars.length;
            for (int i = 0; i <= length - 1; i++) {
                result += getInteger(chars[i]) * (Math.pow(16, length - i - 1));
            }
            System.out.println(result);
        }
    }

    private static Integer getInteger(char c) {
        if (c == '0') {
            return 0;
        }
        if (c == '1') {
            return 1;
        }
        if (c == '2') {
            return 2;
        }
        if (c == '3') {
            return 3;
        }
        if (c == '4') {
            return 4;
        }
        if (c == '5') {
            return 5;
        }
        if (c == '6') {
            return 6;
        }
        if (c == '7') {
            return 7;
        }
        if (c == '8') {
            return 8;
        }
        if (c == '9') {
            return 9;
        }
        if (c == 'A') {
            return 10;
        }
        if (c == 'B') {
            return 11;
        }
        if (c == 'C') {
            return 12;
        }
        if (c == 'D') {
            return 13;
        }
        if (c == 'E') {
            return 14;
        }
        if (c == 'F') {
            return 15;
        }
        return 0;
    }

}
