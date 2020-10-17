package newcode;

import sun.text.normalizer.UBiDiProps;

import java.util.*;

/**
 * 华为机试 四则运算
 */
public class OperationCount {
    private static Map<Character, Integer> numberMap = new HashMap<>();
    private static List<Character> opreationList = new ArrayList<>();
    private static Map<Character, Character> bracketsMap = new HashMap<>();

    public static void main(String[] args) {
        init();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            try {
                System.out.println(getResult(line));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void init() {
        numberMap.put('1', 1);
        numberMap.put('2', 2);
        numberMap.put('3', 3);
        numberMap.put('4', 4);
        numberMap.put('5', 5);
        numberMap.put('6', 6);
        numberMap.put('7', 7);
        numberMap.put('8', 8);
        numberMap.put('9', 9);
        numberMap.put('0', 0);
        opreationList.add('+');
        opreationList.add('-');
        opreationList.add('*');
        opreationList.add('/');
        bracketsMap.put('(', ')');
        bracketsMap.put('{', '}');
        bracketsMap.put('[', ']');
    }

    private static int getResult(String line) throws Exception {
        int result = 0;
        char[] chars = line.toCharArray();

        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opreationStack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (bracketsMap.containsKey(chars[i])) {
                String current = "";
                for (int j = i + 1; i <= chars.length; j++) {
                    if (chars[j] == bracketsMap.get(chars[i])) {
                        i = j;
                        break;
                    }
                    current += chars[j];
                }
                int innerResult = getResult(current);
                numStack.push(innerResult);
            } else {
                if (opreationList.contains(chars[i])) {
                    opreationStack.push(chars[i]);
                } else {
                    numStack.push(numberMap.get(chars[i]));
                }
            }
        }
        if (numStack.size() - opreationStack.size() <= 1) {
            while (!numStack.isEmpty() && !opreationStack.isEmpty()) {

                Queue<Integer> numqueue = new LinkedList<>();
                Queue<Character> opQueue = new LinkedList<>();
                while (numStack.size() > 1) {
                    int a = numStack.pop();
                    int b = numStack.pop();
                    Character op = opreationStack.pop();
                    if (op.equals('*') || op.equals('/')) {
                        int currentresult;
                        if (op.equals('*')) {
                            currentresult = a * b;
                        } else {
                            currentresult = b / a;
                        }
                        // result += currentresult;
                        numStack.push(currentresult);
                    } else {
                        //numqueue.add(b);
                        numStack.push(b);
                        numqueue.add(a);
                        opQueue.add(op);
                    }
                }

                Stack<Integer> currentStack = new Stack<>();
                Stack<Integer> midStack = new Stack<>();
                while (numqueue.size() > 0) {
                    midStack.push(numqueue.poll());
                }
                while (midStack.size() > 0) {
                    currentStack.push(midStack.pop());
                }
                while (currentStack.size() > 0) {
                    boolean sign = false;

                    if (opreationStack.size() > 0 && opreationStack.peek().equals('-')) {
                        sign = true;
                    }
                    if (currentStack.size() > 1) {
                        Integer a = currentStack.pop();
                        if (sign) {
                            a = 0 - a;
                        }
                        Integer b = currentStack.pop();
                        Character c = opQueue.poll();
                        if (c.equals('+')) {
                           // result += (a + b);
                            currentStack.push(a + b);

                        } else {
                            //result += (b - a);
                            currentStack.push(b - a);
                        }
                    } else {
                        Integer a = numStack.pop();
                        Integer b = currentStack.pop();
                        if (sign) {
                            a = 0 - a;
                        }
                        Character c = opQueue.poll();
                        if (c.equals('+')) {
                            result += (a + b);
                            numStack.push(a + b);
                        } else {
                            result += (a - b);
                            numStack.push(a - b);
                        }
                    }
                }
            }
        } else {
            throw new Exception("输入的表达式有误！！");
        }
        if (numStack.size() > 0) {
            result += numStack.pop();
        }
        System.out.println(result);
        return result;
    }
}
