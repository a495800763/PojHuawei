package kaoshi;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class yuanyin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            System.out.println(myFun(str));
        }
    }

    private static int myFun(String str) {
        String[] strArray = str.split(" ");
        int result = 0;
        for (String s : strArray) {
            int aTrue = isTrue(s);
            if (aTrue > 0) {
                result += aTrue;
            }
        }
        return result;
    }

    private static int isTrue(String s) {

        s = s.toLowerCase();
        Character[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'
                , 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


        boolean circle = true;


        List<Character> charList = Arrays.asList(chars);
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            if (charList.contains(sChar[i])) {
                continue;
            } else {
                circle = false;
            }
        }
        String current = "";
        if (circle) {
            for (int i = sChar.length - 1; i >= 0; i--) {
                current += sChar[i];
            }
        } else {
            current = s;
        }

        return judge(current);


    }

    private static int judge(String current) {
        List<Character> aeiou = Arrays.asList('a', 'e', 'i', 'o', 'u');

        int result = 0;

        for (int i = 0; i <= current.length() - 4; i++) {
            String subStr = current.substring(i, i + 4);

            if (!subStr.substring(3, 4).equals("e")) {
                continue;
            }
            boolean firstNotAeiou = !aeiou.contains(subStr.charAt(0));
            boolean secondAeiou = aeiou.contains(subStr.charAt(1));
            boolean third = (!aeiou.contains(subStr.charAt(2))) && (subStr.charAt(2) != 'r');

            if (firstNotAeiou && secondAeiou && third) {
                result += 1;
            } else {
                continue;
            }
        }

        return result;

    }
}
