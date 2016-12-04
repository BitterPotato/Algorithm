package sort;

import static sort.SortUtils.*;
/**
 * 插入排序
 * @author Yang Weijie
 *
 */
public class InsertSort  implements OrdinarySort{
    @Override
    public <T extends Comparable<? super T>> void execute(T[] sequ) {
        int length = sequ.length;

        // 数组仅有一个元素
        if(length < 2) {
            return;
        }

        // 依次将下个未排序元素插入到已排序序列中
        // sortedEndIndex - 下一个未排序元素位置
        for(int sortedEndIndex = 1; sortedEndIndex < length; sortedEndIndex ++) {
            // 过于明显地使用交换
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
     * 将endIndex位置的元素插入到beginIndex位置之前
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
