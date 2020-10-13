package zhixian;

import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 智线笔试 邀请码检测
 */
public class InvitationCode {
    public static void main(String[] args) {

        /**
         * 理解题目意思按照题意顺序执行,
         * 如果将字符直接转成对应的ASCII码会比较简便
         * 这样就不需要另外建立字符与数字的对应关系
         */
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            System.out.println("请输入验证码：");
            String code = in.nextLine();
            boolean reuslt = false;
            try {
                reuslt = isCorrect(code);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(reuslt ? "ok" : "error");
        }

    }

    private static boolean isCorrect(String code) throws Exception {
        //得到每一位字符
        char[] chars = code.toCharArray();

        //奇数位的和
        int oddSum = 0;
        //偶数位的和
        int evenSum = 0;

        //从字符的尾部开始遍历
        for (int i = chars.length - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                //偶数位
                evenSum += char2Num(chars[i]) > 4 ? ((char2Num(chars[i]) * 2) - 9) : (char2Num(chars[i]) * 2);

            } else {
                //奇数位
                oddSum += char2Num(chars[i]);
            }
        }
        return (oddSum + evenSum) % 10 == 0;
    }

    private static int char2Num(char aChar) throws Exception {
        int res = 0;
        //根据小写字母的ascii码
        if (aChar >= 97 && aChar <= 122) {
            res = (aChar - 96) % 9;
        } else if (aChar >= 48 && aChar <= 57) {
            res = aChar - 48;
        } else {
            throw new Exception("输入的验证码不符合规范");
        }
        return res;
    }
}
