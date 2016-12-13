package sort;
import java.util.Random;

/**
 * ���������
 * @author Yang Weijie
 *
 */
public class MainEntry {

    private static final SortFactory sortFactory = new SortFactory();

    public static void main(String[] argc) {
        int sequLength = 50;
        int range = 40;
        Integer[] sequ = buildIntegerSequ(sequLength, range);

        sortByType(sequ, SortType.AnotherQuickSort);
        sortByType(sequ, SortType.NonRecurQuickSort);
        sortByType(sequ, SortType.QuickSort);
        sortByType(sequ, SortType.InsertSort);
    }


    /**
    * �������������
    *
    * @param sequLength
    *            ���г���
    * @param range Ԫ������ֵ
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

        OrdinarySort ordinarySort = sortFactory.createSort(sortType);
        ordinarySort.execute(sequCopy);

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
