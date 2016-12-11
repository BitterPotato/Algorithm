package sort;
import java.util.Random;

/**
 * 排序主入口
 * @author Yang Weijie
 *
 */
public class MainEntry {
    public static void main(String[] argc) {
        int sequLength = 10;
        int range = 10;
        Integer[] sequ = buildIntegerSequ(sequLength, range);

        sortByType(sequ, SortType.QuickSort);
//        sortByType(sequ, SortType.InsertSort);
        
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

    private static void sortByType(Integer[] sequ, SortType sortType) {
        Integer[] sequCopy = sequ.clone();

        System.out.println("Before:");
        for (int i = 0; i < sequCopy.length; i++) {
            System.out.print(sequCopy[i] + " ");
        }

        long startTime = System.nanoTime();

        switch(sortType){
        case InsertSort:
            InsertSort in = new InsertSort();
            in.execute(sequCopy);
        case QuickSort:
            QuickSort q = new QuickSort();
            q.execute(sequCopy, 0, sequCopy.length - 1);
            break;
        case ChooseSort:
            ChooseSort c = new ChooseSort();
            c.execute(sequCopy);
            break;
        case BubbleSort:
          BubbleSort b = new BubbleSort();
          b.execute(sequCopy);
          break;
        }

        long endTime = System.nanoTime();

        System.out.println("");
        System.out.println("After:");
        for (int i = 0; i < sequCopy.length; i++) {
            System.out.print(sequCopy[i] + " ");
        }

        System.out.println("");
        System.out.println("Total Time: \n" + (endTime-startTime) + " nanosecond");
    }

}
