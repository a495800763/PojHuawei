package zhixian;

import java.util.ArrayList;
import java.util.List;

/**
 * 有趣的两位数
 */
public class SmartNumber {
    public static void main(String[] args) {
        /**
         * 分析：首先由于乘法的交换律容易知道：对于任意小于10的a,b，下式成立
         * (10*a+b)*(10*b+a)=(10*b+a)*(10*a+b),例如：12*21=21*12，但此时等式中四个数存在两两相等
         * 要满足题意，需要等式中四个数各不相等，以及根据等式的性质 需要在等式两边同时乘以一个倍数，并且根据结合律结合到不同的数上，即
         * (10*a+b)*(10*b+a)*i=(10*b+a)*(10*a+b)*i,其中i是倍数，且有(10*a+b)*[(10*b+a)*i]=(10*b+a)*[(10*a+b)*i]
         * 且(10*a+b)*(10*b+a)*i=(10*b+a)*(10*a+b)*i 等价于 (10*a+b)*[(10*b+a)*i]=(10*b+a)*[(10*a+b)*i]
         * 并且分析可知，当且仅当a!=b时 (10*a+b)，[(10*b+a)*i]，(10*b+a)，[(10*a+b)*i] 是四个不相同的数，满足题意
         * 并且乘法的进位制可注意到 仅当 a*i<10 && b*i<10(乘倍后不进位) 时有 ： (10*b+a)*i 与 (10*a+b)*i 十位各位仍相反
         * 另外注意到 如果对于一组a，b 存在多个满足条件的倍数i ，则这样的i可以被任意两两组合 u,v,有下式
         * [(10*a+b)*u]*[(10*b+a)*v]=[(10*b+a)*u]*[(10*a+b)*v]
         * 且此时等式中四个数仍是互不相同的数 满足题意
         * 综上所述倍数i需要满足的条件：
         * 1 等式中四个数中的每个数乘以i后仍是一个两位数
         * 2 个位十位上的数乘以i后不进位
         *
         * 最后注意：由于乘法的交换律和等式左右交换等价，所得结果中需要去重处理
         */
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                // i,j即为每一位上的数字，十进制中每一位都小于10 ，不考虑0的情况，因为0不能在十位
                if (i == j) {
                    //个位十位相同的数字，不在题目所考察的范围内（即四个不相同的数）
                    continue;
                }
                // 先得到一组基础数据 形如 12*21=21*12，但这组数据本身不满足题意（即四个不相同的数）
                int a = 10 * i + j;
                int b = 10 * j + i;

                //倍数从2开始
                int count = 2;
                // 存放多个满足条件的倍数的list
                List<Integer> countList = new ArrayList<>();
                while (i * count < 10 && j * count < 10) {
                    countList.add(count);
                    //此时 a,b,a*count,b*count是四个不相同的数，满足题意
                    result.add(a + "*" + b * count + "=" + b + "*" + a * count);
                    count++;
                }

                //所有单独满足题意的倍数count还可以进行两两组合后乘到等式上
                if (countList.size() > 1) {
                    for (int u = 0; u <= countList.size() - 1; u++) {
                        for (int v = u + 1; v <= countList.size() - 1; v++) {
                            //分别得到这两个倍数
                            int countU = countList.get(u);
                            int countV = countList.get(v);

                            //此时 a*countU,b * countV,b*countU,a * countV 是四个不相同的数
                            result.add(a * countU + "*" + b * countV + "=" + b * countU + "*" + a * countV);
                        }
                    }
                }
            }
        }

        // 去重并打印
        List<String> distincList = new ArrayList<>();
        for (String s : result) {
            String[] split = s.split("=");
            if (distincList.contains(split[0]) || distincList.contains(split[1])) {
                continue;
            } else {
                System.out.println(s);
                distincList.add(split[0]);
            }
        }
        System.out.println(distincList.size());

    }
}
