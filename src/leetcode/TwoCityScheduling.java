package leetcode;

import java.util.PriorityQueue;

/**
 * leetcode 1029 两地调度
 *
 */
public class TwoCityScheduling {
    public static void main(String[] args) {

    }

    public int twoCitySchedCost (int[][] costs)
    {
        //首先假设全部去A市，再从其中找出去b市比较便宜的n个
        int ret = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int[] i : costs){
            ret += i[0];
            pq.add(i[1] - i[0]);
        }

        for (int i = 0; i < costs.length / 2 ; i++) {
            ret += pq.poll();
        }
        return ret;
    }
}
