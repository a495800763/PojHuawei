package leetcode;

import javax.xml.soap.Node;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        //System.out.println(findContinuousSequence(15));
        // System.out.println(calculateTime("abcdefghijklmnopqrstuvwxyz", "cba"));
        ListNode a = new ListNode(-129);
        ListNode b = new ListNode(-129);
        a.next = b;

        System.out.println(ispalindrome(a));

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

    /**
     * leetcode 1165：单行键盘
     *
     * @param keyBoard
     * @param word
     * @return
     */
    public static int calculateTime(String keyBoard, String word) {
        Map<Character, Integer> map = new HashMap<>();
        char[] keyBoardCharArray = keyBoard.toCharArray();

        for (int i = 0; i < keyBoardCharArray.length; i++) {
            map.put(keyBoardCharArray[i], i);
        }

        char[] wordArray = word.toCharArray();
        int result = 0;
        for (int i = 0; i < wordArray.length; i++) {
            if (i == 0) {
                result = map.get(wordArray[i]);
            } else {
                result += Math.abs(map.get(wordArray[i]) - map.get(wordArray[i - 1]));
            }
        }
        return result;
    }

    /**
     * leetcode 760： 找出变位映射
     *
     * @param A
     * @param B
     * @return
     */
    public static int[] anagramMapping(int[] A, int[] B) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            if (map.containsKey(B[i])) {
                map.get(B[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(B[i], list);
            }
        }
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int currentValue = A[i];
            int currentIndexInB = map.get(currentValue).get(0);
            result[i] = currentIndexInB;
            map.get(currentValue).remove(0);
        }

        return result;
    }

    public static boolean ispalindrome(ListNode head) {
        String a = "";
        while (head != null) {
            a += head.val;
            head = head.next;
        }
        boolean result = true;
        int length = a.length();
        int i = 0;
        int j = length - i - 1;
        while (i < j) {
            if (a.charAt(i) != a.charAt(j)) {
                result = false;
                break;
            }
            i++;
            j--;
        }
        return result;
    }
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
