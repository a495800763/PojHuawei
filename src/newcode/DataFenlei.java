package newcode;

import java.util.*;
import java.util.spi.CurrencyNameProvider;

public class DataFenlei {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int c = in.nextInt();
            int b = in.nextInt();
            int count = 0;
            int[] array = new int[10];
            while (count < 10) {
                array[count] = in.nextInt();
                count++;
            }
            System.out.println(getResult(c, b, array));

        }
    }

    private static int getResult(int c, int b, int[] array) {
        int[] sumArray = new int[array.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < b; i++) {
            map.put(i, 0);
        }
        for (int current : array) {
            String a = get16Result(current);
            String substr = a.substring(2);
            int sum = 0;
            for (int i = 0; i < 4; i++) {
                sum += Integer.valueOf(substr.substring(i * 2, i * 2 + 2));
            }
            int i = sum % b;
            map.put(i, map.get(i) + 1);
        }
        int max = 0;
        for (int i = 0; i < c && i < b; i++) {
            if (map.get(i) > max) {
                max = map.get(i);
            }
        }
        return max;
    }

    //得到一个数的16进制
    private static String get16Result(int current) {
        Map<Integer, Character> map = new HashMap<>();
        map.put(0, '0');
        map.put(1, '1');
        map.put(2, '2');
        map.put(3, '3');
        map.put(4, '4');
        map.put(5, '5');
        map.put(6, '6');
        map.put(7, '7');
        map.put(8, '8');
        map.put(9, '9');
        map.put(10, 'a');
        map.put(11, 'b');
        map.put(12, 'c');
        map.put(13, 'd');
        map.put(14, 'e');
        map.put(15, 'f');


        if (current < 16) {
            return "0x0000000" + map.get(current);
        }
        String res = "";
        //使用辗转相除法存16进制每一位的数
        Stack<Integer> stack = new Stack<>();
        while (current >= 16) {
            int i = current % 16;
            stack.push(i);
            current = current / 16;
        }
        stack.push(current);
        while (!stack.isEmpty()) {
            res = res + stack.pop().toString();
        }
        int length = res.length();
        if (length < 8) {
            for (int i = 1; i <= 8 - length; i++) {
                res = "0" + res;
            }
        }
        return "0x" + res;

    }

}
