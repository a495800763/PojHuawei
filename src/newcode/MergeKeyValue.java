package newcode;

import java.util.*;

/**
 * 华为机试
 */
public class MergeKeyValue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        int i = 0;
        List<String> strings = new ArrayList<>();
        while (in.hasNext()) {
            if (count == 0) {
                count = in.nextInt();
                continue;
            } else {
                String current = in.next();
                i += 1;
                strings.add(current);
                if (i == count * 2) {
                    print(strings);
                    i = 0;
                    count = 0;
                    strings.clear();
                }
            }
        }
    }

    private static void print(List<String> strings) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < strings.size(); i = i + 2) {
            Integer key = Integer.valueOf(strings.get(i));
            Integer value = Integer.valueOf(strings.get(i + 1));
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
        }
        TreeSet<Integer> set = new TreeSet<>(map.keySet());
        for (Integer integer : set) {
            Integer value = map.get(integer);
            System.out.println(integer + " " + value);
        }
    }
}
