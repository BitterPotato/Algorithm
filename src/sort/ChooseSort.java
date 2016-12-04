package sort;

import static sort.SortUtils.*;
/**
 * 选择排序
 * @author Yang Weijie
 *
 */
public class ChooseSort implements OrdinarySort{
    @Override
    public <T extends Comparable<? super T>> void execute(T[] sequ) {
        int length = sequ.length;

        // 数组仅有一个元素
        if(length < 2) {
            return;
        }

        // 依次将index~length-1中的最小元素放至index处
        int index = 0;
        // index=length-2的情况已处理，那么length-1位置自然是最大值
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
