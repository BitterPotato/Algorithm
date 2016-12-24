package struc;

public class BinaryHeap {
    private Comparable[] objs;
    // 堆容量
    private int num;
    // 堆内元素个数
    private int pointer = 0;

    private swapOrderRepsonder callback;

    /**
     * false: 最小堆
     * true: 最大堆
     */
    private boolean order;

    protected BinaryHeap(boolean order, int num) {
        this.order = order;
        this.num = num;
        objs = new Comparable[num];
    }

    protected BinaryHeap(boolean order, int num, swapOrderRepsonder callback) {
        this.order = order;
        this.num = num;
        objs = new Comparable[num];

        this.callback = callback;
    }


    /**
    * 插入新元素
    *
    * @param obj
    */
    public void insertObject(Comparable obj) {
        // 堆容量未满，新元素插入在末尾，从下往上调整堆
        if (pointer < num) {
            objs[pointer] = obj;
            fromBottomToTop(obj);
            pointer++;
        }
        // 堆容量已满:
        else {
            // 若新元素比根节点大时，新元素替换根节点，从上往下调整堆
            if (!myCompareWith(obj, objs[0])) {
                objs[0] = obj;
                fromTopToBottom(obj);
            }
            // 否则丢弃
        }
    }

    public Comparable deleteTopObject() {
        if(pointer == 0) {
            return null;
        }
        else {
            Comparable topObject = objs[0];
            pointer--;

            if(pointer > 0) {
                objs[0] = objs[pointer];
                fromTopToBottom(objs[0]);
            }

            return topObject;
        }
    }
    /**
    * 从上往下调整堆,过滤
    *
    * @param obj
    */
    private void fromTopToBottom(Comparable obj) {
        // 暂存新元素位置
        int pos = 0;

        // 新元素比两个子节点都合适的时候才停止
        while ((2 * pos + 1 < pointer && !myCompareWith(objs[pos], objs[2 * pos + 1]))
                || (2 * pos + 2 < pointer && !myCompareWith(objs[pos], objs[2 * pos + 2]))) {
            // 与较合适的子节点交换
            if (!myCompareWith(objs[2 * pos + 1], objs[2 * pos + 2])) {
                // 交换位置
                swap(pos, 2 * pos + 2);

                pos = 2 * pos + 2;
            } else {
                // 交换位置
                swap(pos, 2 * pos + 1);

                pos = 2 * pos + 1;
            }
        }
    }

    /**
    * 从下往上调整堆，冒泡
    *
    * @param obj
    */
    private void fromBottomToTop(Comparable obj) {
        // 暂存新元素位置
        int pos = pointer;

        // 新元素至根节点或者合适的父节点才停止
        while (pos != 0 && !myCompareWith(objs[(pos - 1) / 2], objs[pos])) {
            // 交换位置
            swap(pos, (pos - 1) / 2);

            pos = (pos - 1) / 2;
        }
    }

    public boolean isEmpty() {
        return pointer==0 ;
    }

    /**
    * 交换堆内元素位置
    *
    * @param orderA
    * @param orderB
    */
    private void swap(int orderA, int orderB) {
        Comparable temp = objs[orderA];
        objs[orderA] = objs[orderB];
        objs[orderB] = temp;

        callback.swapOrder(objs[orderA], orderB, objs[orderB], orderA);
    }


    /**
     * 若是最大堆，objOne>objTwo为true
     * 若是最小堆，objOne<objTwo为true
     * @param objOne
     * @param objTwo
     * @return
     */
    private boolean myCompareWith(Comparable objOne, Comparable objTwo) {
        return order ? objOne.compareTo(objTwo) > 0 : objOne.compareTo(objTwo) < 0;
    }
}