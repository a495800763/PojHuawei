package newcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 华为机考 兔子总数
 */
public class Rabbit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Person> list = new ArrayList<>();
        list.add(new Person(1));
        while (in.hasNext()) {
            Integer mounth = Integer.valueOf(in.next());
            int count = 0;
            for (int i = 1; i <= mounth; i++) {
                for (Person person : list) {
                    if (i - person.birth >= 2) {
                        count++;
                    }
                }

                for (int j = 1; j <= count; j++) {
                    list.add(new Person(i));
                }
                count = 0;
            }
            System.out.println(list.size());
        }


    }


    static class Person {
        int birth;

        Person(int v) {
            birth = v;
        }
    }
}
