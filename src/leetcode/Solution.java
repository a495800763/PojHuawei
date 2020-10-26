package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
//        int[] a = new int[5];
//        a[0] = 8;
//        a[1] = 1;
//        a[2] = 2;
//        a[3] = 2;
//        a[4] = 3;
//
//        int[] ints = smallerNumberThanCurrent(a);
//
//        for (int anInt : ints) {
//            System.out.print(anInt);
//            System.out.print(",");
//        }
//
//        System.out.println('\n');

        System.out.println(findContinuousSequence(15));

    }

    /**
     * leetcode:1365 有多少小于当前数字的数字
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumberThanCurrent(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int currentResult = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getKey() < current) {
                    currentResult += entry.getValue();
                }
            }
            result[i] = currentResult;

        }
        return result;
    }

    /**
     * leetcode 和位s 的连续正数序列
     *
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence(int target) {
        Map<Integer, int[]> map = new TreeMap<>();
        for (int i = 2; i < target; i++) {
            // 项数 i
            if (target * 2 % i == 0) {
                for (int j = target / 2; j >= 1; j--) {
                    //首项 j
                    // 末项 j+i-1
                    if ((j + j + i - 1) * i / 2 == target) {
                        int[] current = new int[i];
                        for (int k = 0; k < i; k++) {
                            current[k] = j + k;
                        }
                        map.put(j, current);
                        break;
                    }
                }
            }
        }

        int i = 0;
        int[][] result = new int[map.size()][];
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            result[i] = entry.getValue();
            i++;
        }
        return result;
    }
}
