package newcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class YuanYin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int degree = in.nextInt();
            String str = in.next();
            System.out.println(getResult(degree, str));
        }
    }

    private static int getResult(int degree, String str) {
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        list.add('A');
        list.add('E');
        list.add('I');
        list.add('O');
        list.add('U');
        char[] chars = str.toCharArray();
        List<String> resList = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (list.contains(chars[i])) {
                int count = 0;//当前瑕疵度

                for (int j = i + 1; j < chars.length; j++) {
                    if (count > degree) {
                        //瑕疵度超了
                        break;
                    } else {
                        //瑕疵度没超
                        if (list.contains(chars[j])) {
                            if (count == degree) {
                                //瑕疵度达到了 可以计算长度
                                resList.add(str.substring(i, j+1));
                            } else {
                                // 瑕疵度没达到 这组不算
                                continue;
                            }
                        } else {
                            count += 1;
                        }
                    }
                }
            }
        }

        int max = 0;
        if (resList.size() > 1) {
            for (String s : resList) {
                max = s.length() > max ? s.length() : max;
            }
        }

        return max ;
    }
}
