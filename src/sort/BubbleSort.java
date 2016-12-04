package sort;

import static sort.SortUtils.*;
/**
 * 冒泡排序
 * @author Yang Weijie
 *
 */
public class BubbleSort implements OrdinarySort{
    @Override
    public <T extends Comparable<? super T>> void execute(T[] sequ) {
        int length = sequ.length;

        // 数组仅有一个元素
        if(length < 2) {
            return;
        }

        // 依次比较0~toIndex中的相邻两个，将它们交换成正确顺序
        // 每一趟都将当前范围中最大的冒泡到toIndex位置
        for(int toIndex = length -1; toIndex > 1; toIndex --) {
            for(int fromIndex = 0; fromIndex < toIndex; fromIndex ++) {
                if(sequ[fromIndex].compareTo(sequ[fromIndex + 1]) > 0) {
                    swap(sequ, fromIndex, fromIndex + 1);
                }
            }// end 2nd for
        }// end 1st for

    }
}
