package sort;

import static sort.SortUtils.swap;

/**
 * 快速排序 优化点： 1.基准的选择：三数中值分割 2.小数组的处理
 * 插入排序 坑： 1.勿忘递归的终止条件 2.对于相同元素的处理
 *
 * @author Yang Weijie
 *
 */
public class QuickSort extends AbstractQuickSort{

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
        // Notice: 栽在小学算数，忘记+1
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

        // 对left+1和right-2之间的范围进行筛选
        // int forePoint = left + 1;
        // int backPoint = right - 2;
        int forePoint = left;
        int backPoint = right - 1;
        T pivot = sequ[right - 1];

        while (true) {
            // Wrong Version: 当sequ[forePoint]=sequ[backPoint]=pivot时将导致无限循环
            // for(; forePoint < backPoint; forePoint++) {
            // if(sequ[forePoint].compareTo(pivot) >= 0) {
            // break;
            // }
            // }
            // for(; forePoint < backPoint; backPoint--) {
            // if(sequ[backPoint].compareTo(pivot) <= 0) {
            // break;
            // }
            // }
            while (sequ[++forePoint].compareTo(pivot) < 0) {
            }
            while (sequ[--backPoint].compareTo(pivot) > 0) {
            }

            if (forePoint >= backPoint) {
                // 将基准放到合适位置
                swap(sequ, forePoint, right - 1);
                break;
            } else {
                swap(sequ, forePoint, backPoint);
            }
        }
        return forePoint;
    }

}
