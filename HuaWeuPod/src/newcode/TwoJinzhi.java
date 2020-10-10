package newcode;

import java.util.Scanner;

public class TwoJinzhi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int i = in.nextInt();
            int count = 0;
            if (i == 1 || i == 2) {
                System.out.print(1);
            } else {
                while (i >= 2) {
                    int i1 = i % 2;
                    if (i1 == 1) {
                        count += 1;
                    }
                    i = i / 2;
                }
                System.out.print(count + 1);
            }
            System.out.print('\n');
        }
    }
}
