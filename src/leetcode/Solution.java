package leetcode;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[][] points = new int[3][];
        int[] a = new int[2];
        a[0] = 1;
        a[1] = 1;
        points[0] = a;
        int[] b = new int[2];
        b[0] = 3;
        b[1] = 4;
        points[1] = b;
        int[] c = new int[2];
        c[0] = -1;
        c[1] = 0;
        points[2] = c;


        System.out.println(minTimeToVisitAllPoints(points));

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
     * leetcode:1243 : 数组变换
     *
     * @param arr
     * @return
     */
    public static List<Integer> transformArray(int[] arr) {
        int[] narr = new int[arr.length]; //建立新数组和老数组区分
        narr[0] = arr[0];
        narr[arr.length - 1] = arr[arr.length - 1];//首尾
        for (int i = 1; i < arr.length - 1; i++) {
            if ((arr[i] < arr[i + 1]) && (arr[i] < arr[i - 1])) {
                narr[i] = arr[i] + 1;
            } else if ((arr[i] > arr[i + 1]) && (arr[i] > arr[i - 1])) {
                narr[i] = arr[i] - 1;
            } else {
                narr[i] = arr[i];
            }
        }//判断
        if (Arrays.equals(narr, arr)) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < narr.length; i++) {
                ans.add(narr[i]);
            }
            return ans;//新旧相等返回
        } else {
            return transformArray(narr);//不相等进行下一天
        }
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


    /**
     * 访问所有点的所有点的最小时间
     *
     * @param points
     * @return
     */
    public static int minTimeToVisitAllPoints(int[][] points) {
        int result = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int ax = points[i][0];
            int ay = points[i][1];
            int bx = points[i + 1][0];
            int by = points[i + 1][1];

            if (ax == bx) {
                result += Math.abs(ay - by);
            } else if (ay == by) {
                result += Math.abs(ax - bx);
            } else {
                //计算初始斜率
                float k = Math.abs((float) ((float) (ay - by) / (float) (ax - bx)));
                if (k == 1) {
                    //初始斜率等于1 直接从对角线过去
                    result += Math.abs(ax - bx);
                } else {
                    int current = 0;
                    int tempResult;
                    while (k != 1) {
                        if (k < 1) {
                            //斜率<1,从x方向平移至斜率为1
                            if (ax < bx) {
                                ax = ax + 1;
                            } else {
                                bx = bx + 1;
                            }
                            //计算当前斜率
                            k = Math.abs((float) ((float) (ay - by) / (float) (ax - bx)));
                            current += 1;
                        } else {
                            //斜率>1,从y方向平移至斜率为1
                            if (ay < by) {
                                ay = ay + 1;
                            } else {
                                by = by + 1;
                            }
                            //计算当前斜率
                            k = Math.abs((float) ((float) (ay - by) / (float) (ax - bx)));
                            //累计平移的步数
                            current += 1;
                        }
                    }
                    //这一步的步数=平移步数+对角线步数
                    tempResult = Math.abs(ax - bx) + current;
                    result += tempResult;
                }
            }
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
