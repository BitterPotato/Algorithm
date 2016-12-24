package struc;

public class BinaryHeap {
    private Comparable[] objs;
    // ������
    private int num;
    // ����Ԫ�ظ���
    private int pointer = 0;

    private swapOrderRepsonder callback;

    /**
     * false: ��С��
     * true: ����
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
    * ������Ԫ��
    *
    * @param obj
    */
    public void insertObject(Comparable obj) {
        // ������δ������Ԫ�ز�����ĩβ���������ϵ�����
        if (pointer < num) {
            objs[pointer] = obj;
            fromBottomToTop(obj);
            pointer++;
        }
        // ����������:
        else {
            // ����Ԫ�رȸ��ڵ��ʱ����Ԫ���滻���ڵ㣬�������µ�����
            if (!myCompareWith(obj, objs[0])) {
                objs[0] = obj;
                fromTopToBottom(obj);
            }
            // ������
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
    * �������µ�����,����
    *
    * @param obj
    */
    private void fromTopToBottom(Comparable obj) {
        // �ݴ���Ԫ��λ��
        int pos = 0;

        // ��Ԫ�ر������ӽڵ㶼���ʵ�ʱ���ֹͣ
        while ((2 * pos + 1 < pointer && !myCompareWith(objs[pos], objs[2 * pos + 1]))
                || (2 * pos + 2 < pointer && !myCompareWith(objs[pos], objs[2 * pos + 2]))) {
            // ��Ϻ��ʵ��ӽڵ㽻��
            if (!myCompareWith(objs[2 * pos + 1], objs[2 * pos + 2])) {
                // ����λ��
                swap(pos, 2 * pos + 2);

                pos = 2 * pos + 2;
            } else {
                // ����λ��
                swap(pos, 2 * pos + 1);

                pos = 2 * pos + 1;
            }
        }
    }

    /**
    * �������ϵ����ѣ�ð��
    *
    * @param obj
    */
    private void fromBottomToTop(Comparable obj) {
        // �ݴ���Ԫ��λ��
        int pos = pointer;

        // ��Ԫ�������ڵ���ߺ��ʵĸ��ڵ��ֹͣ
        while (pos != 0 && !myCompareWith(objs[(pos - 1) / 2], objs[pos])) {
            // ����λ��
            swap(pos, (pos - 1) / 2);

            pos = (pos - 1) / 2;
        }
    }

    public boolean isEmpty() {
        return pointer==0 ;
    }

    /**
    * ��������Ԫ��λ��
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
     * �������ѣ�objOne>objTwoΪtrue
     * ������С�ѣ�objOne<objTwoΪtrue
     * @param objOne
     * @param objTwo
     * @return
     */
    private boolean myCompareWith(Comparable objOne, Comparable objTwo) {
        return order ? objOne.compareTo(objTwo) > 0 : objOne.compareTo(objTwo) < 0;
    }
}