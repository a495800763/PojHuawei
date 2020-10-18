package leetcode;

import java.util.*;

/**
 * leetcode 763 划分字母区间
 */
public class PartitionLabels {
    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }

    public static List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        char[] chars = S.toCharArray();
        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.get(chars[i]).add(i);
            } else {
                TreeSet<Integer> value = new TreeSet<>();
                value.add(i);
                map.put(chars[i], value);
            }
        }

        int currentMinIndex = 0;
        int currentMaxIndex = 0;
        //操作过的就放到list 是的操作的char不重复
        List<Character> list = new ArrayList<>();
        //使用chars顺序执行，确保一开始的index 是0
        for (char aChar : chars) {
            if (list.contains(aChar)) {
                continue;
            }
            list.add(aChar);
            Character currentChar = aChar;
            TreeSet<Integer> value = map.get(currentChar);

            Integer first = value.first();
            Integer last = value.last();


            if (first == 0) {
                //初始化
                currentMaxIndex = last;
            } else {
                if (first < currentMaxIndex) {
                    if (last < currentMaxIndex) {
                        continue;
                    } else {
                        currentMaxIndex = last;
                    }
                } else {
                    result.add(currentMaxIndex - currentMinIndex + 1);
                    currentMinIndex = first;
                    currentMaxIndex = last;
                }
            }
        }

        result.add(currentMaxIndex - currentMinIndex + 1);
        return result;

    }
}
