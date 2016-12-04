package sort;
import java.util.Random;

/**
 * 排序主入口
 * @author Yang Weijie
 *
 */
public class MainEntry {
    public static void main(String[] argc) {
        int sequLength = 25;
        int range = 15;
        Integer[] sequ = buildIntegerSequ(sequLength, range);

        System.out.println("Before:");
        for (int i = 0; i < sequ.length; i++) {
            System.out.print(sequ[i] + " ");
        }

//        QuickSort q = new QuickSort();
//        q.execute(sequ, 0, sequ.length - 1);
//        ChooseSort c = new ChooseSort();
//        c.execute(sequ);
//        BubbleSort b = new BubbleSort();
//        b.execute(sequ);
        InsertSort in = new InsertSort();
        in.execute(sequ);

        System.out.println("");
        System.out.println("After:");
        for (int i = 0; i < sequ.length; i++) {
            System.out.print(sequ[i] + " ");
        }
    }


    /**
    * 随机数生成样本
    *
    * @param sequLength
    *            序列长度
    * @param range 元素上限值
    * @return
    */
    private static Integer[] buildIntegerSequ(int sequLength, int range) {
//    	new ArrayList<Integer>().sort();
        Random random = new Random(System.currentTimeMillis());
        Integer[] sequ = new Integer[sequLength];
        for (int index = 0; index < sequLength; index++) {
            sequ[index] = random.nextInt(range + 1);
        }

        return sequ;
    }
}
