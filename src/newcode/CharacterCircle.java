package newcode;

import java.util.*;

/**
 * 华为机试 字符逆序
 */
public class CharacterCircle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String s = in.nextLine();
            getCharacterCircle(s);
        }
    }

    private static void getCharacterCircle(String s) {
        //除去空格的每一个单词
        String[] words = s.split(" ");
        List<Integer> countList = new ArrayList<>();
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            countList.add(chars.length);
            for (char aChar : chars) {
                stack.push(aChar);
            }

        }

        for (int i = countList.size(); i >= 1; i--) {
            Integer pushCount = countList.get(i - 1);
            int j = 1;
            while (j <= pushCount) {
                System.out.print(stack.pop());
                j++;
            }
            System.out.print(" ");
        }
        System.out.print('\n');

    }
}
