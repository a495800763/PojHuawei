package newcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 华为机试 第一个只出现一次的字符
 */
public class FirstOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.next();
            System.out.println(getResult(str));
        }
    }

    private static String getResult(String str) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (map.containsKey(aChar)) {
                map.put(aChar, map.get(aChar) +1);
            } else {
                map.put(aChar, 1);
            }
        }
        for (char aChar : chars) {
            if(map.get(aChar).equals(1))
            {
                return  String.valueOf(aChar);
            }
        }
        return "-1";
    }
}
