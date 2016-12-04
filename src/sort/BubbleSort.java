package sort;

import static sort.SortUtils.*;
/**
 * ð������
 * @author Yang Weijie
 *
 */
public class BubbleSort implements OrdinarySort{
    @Override
    public <T extends Comparable<? super T>> void execute(T[] sequ) {
        int length = sequ.length;

        // �������һ��Ԫ��
        if(length < 2) {
            return;
        }

        // ���αȽ�0~toIndex�е����������������ǽ�������ȷ˳��
        // ÿһ�˶�����ǰ��Χ������ð�ݵ�toIndexλ��
        for(int toIndex = length -1; toIndex > 1; toIndex --) {
            for(int fromIndex = 0; fromIndex < toIndex; fromIndex ++) {
                if(sequ[fromIndex].compareTo(sequ[fromIndex + 1]) > 0) {
                    swap(sequ, fromIndex, fromIndex + 1);
                }
            }// end 2nd for
        }// end 1st for

    }
}
