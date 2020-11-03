package leetcode;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] a = new int[2];
        a[0] = 2;
        a[1] = 1;
        System.out.println(validMountainArray(a));

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

    /**
     * leetcode ： 回文链表
     *
     * @param head
     * @return
     */
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

    /**
     * leetcode ：1290 二进制链表转整数
     *
     * @param head
     * @return
     */
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            //每次就把之前的结果往前推一位（乘以2），然后加上当前的值（1或者0）
            result = result * 2 + head.val;
            head = head.next;
        }
        return result;
    }


    /**
     * leetcode:1614 字符串的最大深度
     *
     * @param s
     * @return
     */
    public static int maxDepth(String s) {
        Stack<Character> stack = new Stack<>();
        int depth = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(') {
                //当前是左括号 入栈
                stack.push(aChar);
                int size = stack.size();
                //入栈后当前剩余左括号(深度)是不是最大深度？
                depth = depth <= size ? size : depth;
            }
            if (aChar == ')') {
                //遇到右括号，出栈一组，此时栈中元素数量减1 即当前深度减1
                stack.pop();
            }
        }
        return depth;
    }


    /**
     * leetcode:941 有效的山脉数组
     *
     * @param A
     * @return
     */
    public static boolean validMountainArray(int[] A) {
        if (A.length == 2) {
            //如果数组只有两个元素 肯定不行
            return false;
        }
        //记录是否有上升的过程
        boolean upStart = false;
        // 记录是否开始进入下降过程了
        boolean isfall = false;
        for (int i = 1; i < A.length; i++) {
            if (!isfall) {
                if (A[i - 1] < A[i]) {
                    upStart = true;
                    continue;
                } else if (A[i - 1] == A[i]) {
                    return false;
                } else {
                    isfall = true;
                    continue;
                }
            } else {
                if (A[i - 1] > A[i]) {
                    continue;
                } else {
                    // 在下降过程中只要出现非下降对 直接返回false
                    return false;
                }
            }
        }
        //有上升过程 而且经历了下降过程到结束
        if (upStart && isfall) {
            return true;
        } else {
            return false;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
