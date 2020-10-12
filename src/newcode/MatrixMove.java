package newcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 华为机试 坐标移动
 */
public class MatrixMove {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] points = s.split(";");
            Integer[] result = getMovedPointer(points);
            System.out.print(result[0] + "," + result[1]);
            System.out.print('\n');


        }
    }

    private static Integer[] getMovedPointer(String[] points) {

        List<Character> effctiveFistChar = new ArrayList<>();
        effctiveFistChar.add('A');
        effctiveFistChar.add('D');
        effctiveFistChar.add('W');
        effctiveFistChar.add('S');
        Integer[] result = new Integer[2];
        result[0] = 0;
        result[1] = 0;
        for (String point : points) {
            if (point.equals("")) {
                continue;
            }
            char first = point.charAt(0);
            if (effctiveFistChar.contains(first)) {
                //只有以ASDW开头的字符才操作
                String substring = point.substring(1);
                try {
                    Integer integer = Integer.valueOf(substring);
                    switch (first) {
                        case 'A':
                            result[0] -= integer;
                            break;
                        case 'D':
                            result[0] += integer;
                            break;
                        case 'W':
                            result[1] += integer;
                            break;
                        case 'S':
                            result[1] -= integer;
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }

        return result;

    }
}
