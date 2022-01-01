package newcode;

import java.util.Scanner;

public class FindFirstCharacter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(function(line));
        }
    }

    private static Integer function(String line) {
        String[] s = line.split(" ");
        return s[s.length - 1] == null ? 0 : s[s.length - 1].length();

    }
}
