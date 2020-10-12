package newcode;

/**
 * 旷视笔试 部门优化
 */
public class Optimization {

    private static final int TARGET_MOUTH = 183;

    public static void main(String[] args) {
        getOptimitionResultOne(TARGET_MOUTH);
        getOptimitionResultTwo(TARGET_MOUTH);
        getOptimitionResultThree(TARGET_MOUTH);
    }

    /**
     * 优化方案一
     * 按照题目意思按步骤进行
     */
    private static void getOptimitionResultOne(int targetMouth) {
        Integer[] array = new Integer[4];
        array[0] = 10;
        array[1] = 7;
        array[2] = 5;
        array[3] = 4;
        int mounth = 1;
        while (mounth <= targetMouth) {
            int index = getMostIndex(array);
            for (int i = 0; i <= array.length - 1; i++) {
                if (i == index) {
                    array[i] -= 3;
                } else {
                    array[i] += 1;
                }
            }
            mounth++;
        }
        for (Integer integer : array) {
            System.out.print(integer + " ");
        }
        System.out.print('\n');
    }

    /**
     * 找出当前数组中最大数字的下标index
     *
     * @param array
     * @return
     */
    private static int getMostIndex(Integer[] array) {
        int result = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[result]) {
                result = i;
            }
        }
        return result;
    }

    /**
     * 优化方案2
     * 遇到8就减3 其他加1
     */
    private static void getOptimitionResultTwo(int targetMounth) {
        /**
         * 经分析后 经过第一个月以后
         * 数据总是在5 6 7 8 四个数之间循环
         */
        int[] array = new int[4];
        array[0] = 10;
        array[1] = 7;
        array[2] = 5;
        array[3] = 4;

        int mounth = 1;
        while (mounth <= targetMounth) {
            if (mounth == 1) {
                array[0] -= 3;
                array[1] += 1;
                array[2] += 1;
                array[3] += 1;
            } else {
                for (int i = 0; i < array.length; i++) {
                    if (array[i] == 8) {
                        array[i] -= 3;
                    } else {
                        array[i] += 1;
                    }
                }

            }
            mounth++;
        }
        for (Integer integer : array) {
            System.out.print(integer + " ");
        }
        System.out.print('\n');
    }

    /**
     * 优化方案三
     */
    private static void getOptimitionResultThree(int targetMouth) {
        /**
         * 经分析得知 所求优化结果与月份成周期函数
         */
        String[] result = new String[5];
        //result[0] = "10;7;5;4";
        result[1] = "7;8;6;5";
        result[2] = "8;5;7;6";
        result[3] = "5;6;8;7";
        result[0] = "6;7;5;8";
        String[] obj = result[targetMouth % 4].split(";");
        for (String a : obj) {
            System.out.print(a + " ");
        }
        System.out.print('\n');



    }
}
