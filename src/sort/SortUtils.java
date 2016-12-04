package sort;
/**
 * ����Ԫ�ذ���
 * @author Yang Weijie
 *
 */
public class SortUtils {
    /**
        * ��������λ�õ�Ԫ��
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
