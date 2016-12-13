package sort;

import static sort.SortUtils.swap;

/**
 * 快速排序
 *
 * @author Yang Weijie
 *
 */
public class AnotherQuickSort extends AbstractQuickSort{

    // main code
    /**
    *
    * @param sequ
    * @param left
    *            数组排序段元素起始下标
    * @param right
    *            数组排序段元素结束下标
    */
    protected <T extends Comparable<? super T>> void executeProcess(T[] sequ, int left, int right) {
        // 检查下标是否越界
        super.rangeCheck(sequ.length, left, right);

        // 数组个数为0或1，已排序（终止条件）
        int size = right - left + 1;
        if (size < 2) {
            return;
        }

        // 数组个数过少，采用插入排序
        if(super.insertSortOptimized && size < super.insertSortOptimizedBound) {
            new InsertSort().execute(sequ);
            return;
        }

        int partionPoint = partition(sequ, left, right);
        if(partionPoint < 0) {
            return;
        }

        // 递归
        executeProcess(sequ, left, partionPoint - 1);
        executeProcess(sequ, partionPoint + 1, right);
    }

    protected <T extends Comparable<? super T>> int partition(T[] sequ, int left, int right) {
        // 三数排序决定基准，left/right/中位
        int middle = (left + right) / 2;
        if (sequ[left].compareTo(sequ[middle]) > 0) {
            swap(sequ, left, middle);
        }
        if (sequ[left].compareTo(sequ[right]) > 0) {
            swap(sequ, left, right);
        }
        if (sequ[middle].compareTo(sequ[right]) > 0) {
            swap(sequ, middle, right);
        }
        // 数组仅有2个或3个元素，此时已经排好序
        // （若对小数组使用插入排序，则该语句没有必要）
        if (!super.insertSortOptimized && middle == right - 1) {
            return super.InvalidPoint;
        }
        // 将基准（三数中值）放至right-1位置
        swap(sequ, middle, right - 1);

        int curPoint = left + 1;
        // 指向小于基准与大于等于基准的序列的分割位置，大于等于基准的序列的第一个元素
        int parPoint = left + 1;
        T pivot = sequ[right - 1];

        while(curPoint < right - 1) {
            if(sequ[curPoint].compareTo(pivot) < 0) {
                swap(sequ, curPoint, parPoint);
                parPoint++;
            }
            curPoint++;
        }
        swap(sequ, parPoint, right - 1);
        return parPoint;
    }

}
