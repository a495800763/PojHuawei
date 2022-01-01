package kaoshi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class matrixMove {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer n = null;
        Integer e = null;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (in.hasNextLine()) {
            String str = in.nextLine();
            String[] s1 = str.split(" ");
            if (n == null && e == null) {
                n = Integer.valueOf(s1[0]);
                e = Integer.valueOf(s1[1]);
            } else {
                if (map.size() < n) {
                    map.put(Integer.valueOf(s1[0]), Integer.valueOf(s1[1]));
                } else {
                    break;
                }
            }

        }

        System.out.println(function(map,e));
    }

    private static Integer function(HashMap<Integer, Integer> map,int e) {
        int lastY = 0;
        int lastX = 0;
        int endX = 0;
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int currentX = entry.getKey();
            int currentY = entry.getValue();
            int cTruelyY = lastY;
            cTruelyY = cTruelyY + currentY;

            int cTruelyX = lastX;

            cTruelyX = currentX + cTruelyX;

            int currentResult = Math.abs(cTruelyY) * Math.abs(cTruelyX);
            result += currentResult;
            endX = currentX;
        }
        return result;
    }
}
