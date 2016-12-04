package sort;

import static sort.SortUtils.*;
/**
 * ��������
 * @author Yang Weijie
 *
 */
public class InsertSort  implements OrdinarySort{
    @Override
    public <T extends Comparable<? super T>> void execute(T[] sequ) {
        int length = sequ.length;

        // �������һ��Ԫ��
        if(length < 2) {
            return;
        }

        // ���ν��¸�δ����Ԫ�ز��뵽������������
        // sortedEndIndex - ��һ��δ����Ԫ��λ��
        for(int sortedEndIndex = 1; sortedEndIndex < length; sortedEndIndex ++) {
            // �������Ե�ʹ�ý���
//            int sortedCurIndex = 0;
//            for(; sortedCurIndex < sortedEndIndex; sortedCurIndex ++) {
//                if(sequ[sortedCurIndex].compareTo(sequ[sortedEndIndex]) > 0) {
//                    break;
//                }
//            }// end 2nd for
//            insertEndToBegin(sequ, sortedCurIndex, sortedEndIndex);

            // TODO some errors
            T sortedEndValue = sequ[sortedEndIndex];
            int sortedCurIndex = sortedEndIndex;
            while(--sortedCurIndex > 0
                    && sequ[sortedCurIndex].compareTo(sortedEndValue) > 0) {
                    sequ[sortedCurIndex + 1] = sequ[sortedCurIndex];
            }
            sequ[sortedCurIndex] = sortedEndValue;
        }// end 1st for
    }

    /**
     * ��endIndexλ�õ�Ԫ�ز��뵽beginIndexλ��֮ǰ
     * @param sequ
     * @param beginIndex
     * @param endIndex
     */
    private <T extends Comparable<? super T>> void insertEndToBegin(T[] sequ, int beginIndex, int endIndex) {
        for(; endIndex > beginIndex; endIndex --) {
            swap(sequ, endIndex, endIndex - 1);
        }
    }
}
