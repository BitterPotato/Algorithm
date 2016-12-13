package sort;

public abstract class AbstractQuickSort implements OrdinarySort{

    protected static final int InvalidPoint = -1;
    protected static final int insertSortOptimizedBound = 25;
    protected static final boolean insertSortOptimized = true;

    // main code
    /**
    *
    * @param sequ
    * @param left
    *            数组排序段元素起始下标
    * @param right
    *            数组排序段元素结束下标
    */
    protected abstract <T extends Comparable<? super T>> void executeProcess(T[] sequ, int left, int right);

    protected abstract <T extends Comparable<? super T>> int partition(T[] sequ, int left, int right);

    /**
    * 检查下标是否合法
    *
    * @param arrayLength
    * @param fromIndex
    * @param toIndex
    * @reference java.util.Arrays
    */
    protected void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > arrayLength - 1) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }

    }

    @Override
    public <T extends Comparable<? super T>> void execute(T[] sequ) {
        // TODO Auto-generated method stub
        executeProcess(sequ, 0, sequ.length - 1);
    }

}
