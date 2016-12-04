package sort;
/**
 * 交换元素帮助
 * @author Yang Weijie
 *
 */
public class SortUtils {
    /**
        * 交换两个位置的元素
        *
        * @param sequ
        * @param left
        * @param right
        */
        public static <T extends Comparable<? super T>> void swap(T[] sequ, int left, int right) {
            T temp = sequ[left];
            sequ[left] = sequ[right];
            sequ[right] = temp;
        }

}
