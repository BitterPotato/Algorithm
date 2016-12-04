package sort;

import static sort.SortUtils.*;
/**
 * ѡ������
 * @author Yang Weijie
 *
 */
public class ChooseSort implements OrdinarySort{
    @Override
    public <T extends Comparable<? super T>> void execute(T[] sequ) {
        int length = sequ.length;

        // �������һ��Ԫ��
        if(length < 2) {
            return;
        }

        // ���ν�index~length-1�е���СԪ�ط���index��
        int index = 0;
        // index=length-2������Ѵ�����ôlength-1λ����Ȼ�����ֵ
        for(; index < length - 1; index++) {
            int minIndex = index;
            for(int tempIndex = index + 1; tempIndex <= length - 1; tempIndex++) {
                if(sequ[tempIndex].compareTo(sequ[minIndex]) < 0) {
                    minIndex = tempIndex;
                }
            }// end 2nd for
            swap(sequ, index, minIndex);
        }// end 1st for
    }
}
