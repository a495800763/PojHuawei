package newcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 华为机试
 * 汽水瓶
 */
public class Drink{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        List<Integer> result = new ArrayList<>();
        while (sc.hasNext()) {
            Integer num = Integer.valueOf(sc.nextLine());
            if (num == 0) {
                break;
            }
            int res = drink(num);
            result.add(res);
        }
        for (Integer integer : result) {
            System.out.println(integer);
        }

    }

    public static int drink(Integer num) {
        int result = 0;
        while (num >= 3) {
            result += 1;
            num = num - 3 + 1;
        }
        if (num == 2) {
            result += 1;
        }
        return result;
    }
}
