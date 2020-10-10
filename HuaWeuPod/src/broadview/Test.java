package broadview;

public class Test {
    static int x, y;

    static {
        int x = 5;
    }

    public static void myMethod() {
        y = x++ + ++x;
    }

    public static void main(String[] args) {
        x--;
        myMethod();
        System.out.println(x+y++ +x);

        int a = 0;
        System.out.println(a);
        System.out.println(1+a++);
        System.out.println(++a);
        System.out.println(a);

        int b = 017;
        System.out.println(b);




        StringBuffer sb = new StringBuffer("origin");
        addStr(sb);
        System.out.println(sb);

    }

    public static void addStr (StringBuffer a)
    {
       a.append("added");

    }
}
