package greedy;

/**
 * leetcode122:买卖股票的最佳时机Ⅱ
 */
public class MaxProfit {
    public static void main(String[] args) {

    }

    public static int maxProfit(int [] prices)
    {
        //由于可以尽可能多的完成股票交易
        // 而且题目中并无规定某一点给不能即买又卖 则
        // 遇到涨就卖
        int ans=0;
        for(int i=1;i<=prices.length-1;i++)
        {
            if(prices[i]>prices[i-1])
            {
                ans+=prices[i]-prices[i-1];
            }
        }
        return ans;
    }
}
