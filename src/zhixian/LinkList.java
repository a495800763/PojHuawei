package zhixian;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class LinkList {

    private static Node head;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            //先确定需要输入的前驱后继关系的数目
            Integer count = Integer.valueOf(in.next());
            for (int i = 1; i <= count; i++) {
                String[] s = new String[2];
                s[0] = in.next();
                s[1] = in.next();
                if (head == null) {
                    //先创建头结点
                    head = new Node(Integer.valueOf(s[0]));
                }
                if (Integer.valueOf(s[1]) == head.value) {
                    //在当前头结点之前插入
                    Node currentNext = new Node(Integer.valueOf(s[0]));
                    head = Node.insert(head, null, currentNext);
                } else {
                    //正常插入
                    Node next = new Node(Integer.valueOf(s[1]));
                    head = Node.insert(head, Integer.valueOf(s[0]), next);
                }
            }
            printLinkedList(head);
            Node resetLinkedList = resetLinkedList(head);
            printLinkedList(resetLinkedList);
            head = null;

        }
    }

    /**
     * 智线笔试 单链表处理
     *
     * @param head
     * @return
     */
    private static Node resetLinkedList(Node head) {
        /**
         * 分析： 使用队列和栈分别得到最头和最尾节点依次拼接可以得到结果
         * 需要节点个数为奇数是多一次操作，操作队列或是堆都可以
         */
        Stack<Node> stack = new Stack<>();
        Queue<Node> queue = new LinkedList<>();
        while (head != null) {
            Node current = head;
            head = head.next;
            current.next = null;
            stack.push(current);
            queue.add(current);
        }
        //范围值：指向重排后的头结点
        Node resultHead = null;
        int listLength = stack.size();
        //标记需不需要最后多一次操作
        boolean sign = listLength % 2 == 1;
        //一共进行多少次基本操作
        int count = listLength / 2;
        for (int i = 1; i <= count; i++) {
            //先将当前这一组组合好
            Node first = queue.poll();
            Node last = stack.pop();
            first.next = last;
            //在将这一组节点贴在当前链表的最后
            if (resultHead != null) {
                Node currentHead = resultHead;
                while (currentHead.next != null) {
                    currentHead = currentHead.next;
                }
                currentHead.next = first;
            } else {
                resultHead = first;
            }
        }
        //如果需要进行多一次操作
        if (sign) {
            Node last = queue.peek();
            Node currentHead = resultHead;
            while (currentHead.next != null) {
                currentHead = currentHead.next;
            }
            currentHead.next = last;
        }

        return resultHead;
    }


    private static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.print('\n');
    }

    static class Node {
        public Integer value;
        public Node next;

        public Node(Integer v) {
            this.value = v;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        /**
         * @param head         当前头节点
         * @param preNodeValue 在哪里插入
         * @param next         待插入的节点
         * @return
         */
        public static Node insert(Node head, Integer preNodeValue, Node next) {
            Node result = head;
            if (preNodeValue == null) {
                //next没有前驱,将next插入到头，并返回
                next.next = head;
                return next;
            }
            if (preNodeValue.equals(head.value) && head.next == null) {
                //head没有后继,插入到head后面
                head.next = next;
                return head;
            }

            //head有后继而且并不是插入在head之前的一般情况,返回值是result所指向的head节点的地址
            while (head.hasNext()) {
                if (head.value.equals(preNodeValue)) {
                    //就是插入在head后面，原来的next后移
                    next.next = head.next;
                    head.next = next;
                    break;
                } else {
                    if (head.next.value.equals(next.value)) {
                        //next存在 反而是preNodeValue需要插入的情况
                        //这种情况主要是产生于调用本函数时，并不仔细考虑node 和next 谁是待插值，使调用得到正确的结果
                        Node current = new Node(preNodeValue);
                        current.next = head.next;
                        head.next = current;
                        break;
                    }
                }
                //不满足进行操作的条件 迭代当前节点
                head = head.next;
            }
            if (head.next == null && head.value.equals(preNodeValue)) {
                //在链表尾部插入的情况
                head.next = next;
            }
            return result;
        }
    }
}
