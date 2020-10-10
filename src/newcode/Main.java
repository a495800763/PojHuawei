package newcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = bReader.readLine()) != null) {

            char s = bReader.readLine().toCharArray()[0];
            int total = 0;
            for(int i = 0;i<line.length();i++){
                if(line.charAt(i) == s || line.charAt(i) == (char)s-32){
                    total++;
                }
            }
            System.out.println(total);
        }
    }
}
