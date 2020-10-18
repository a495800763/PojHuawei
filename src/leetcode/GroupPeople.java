package leetcode;

import java.util.*;

/**
 * leetcode 1282 用户分组
 */
public class GroupPeople {

    public static void main(String[] atgs) {
        int[] a = new int[7];
        for (int i = 0; i < 7; i++) {
            if (i == 5) {
                a[i] = 1;
            } else {
                a[i] = 3;
            }
        }
        List<List<Integer>> lists = groupThePeopeo(a);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }


    }

    public static List<List<Integer>> groupThePeopeo(int[] groupsizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groupsizes.length; i++) {
            int currentGroupSize = groupsizes[i];
            if (map.containsKey(currentGroupSize)) {
                List<Integer> list = map.get(currentGroupSize);
                add(map, result, i, currentGroupSize, list);
            } else {
                List<Integer> list = new ArrayList<>();
                add(map, result, i, currentGroupSize, list);
            }
        }
        return result;
    }

    private static void add(Map<Integer, List<Integer>> map, List<List<Integer>> result, int i, int currentGroupSize, List<Integer> list) {
        list.add(i);
        map.put(currentGroupSize, list);
        if (list.size() == currentGroupSize) {
            List<Integer> addList = new ArrayList<>();
            for (Integer integer : list) {
                addList.add(integer);
            }

            result.add(addList);
            map.get(currentGroupSize).clear();
        }
    }
}
