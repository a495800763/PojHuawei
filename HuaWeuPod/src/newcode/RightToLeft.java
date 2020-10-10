package newcode;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 华为机试 提取不重复的整数
 */
public class RightToLeft {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String s = in.nextLine();
            Set<Character> set = new HashSet<>();
            char[] chars = s.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                if (set.contains(chars[i])) {
                    continue;
                } else {
                    char aChar = chars[i];
                    System.out.print(aChar);
                    set.add(aChar);
                }
            }

        }
    }
}
