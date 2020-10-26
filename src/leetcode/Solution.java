package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        int[] a = new int[5];
        a[0] = 8;
        a[1] = 1;
        a[2] = 2;
        a[3] = 2;
        a[4] = 3;

        int[] ints = smallerNumberThanCurrent(a);

        for (int anInt : ints) {
            System.out.print(anInt);
            System.out.print(",");
        }

        System.out.println('\n');

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
}
