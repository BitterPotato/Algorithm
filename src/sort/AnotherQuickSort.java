package sort;

import static sort.SortUtils.swap;

/**
 * ��������
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
    *            ���������Ԫ����ʼ�±�
    * @param right
    *            ���������Ԫ�ؽ����±�
    */
    protected <T extends Comparable<? super T>> void executeProcess(T[] sequ, int left, int right) {
        // ����±��Ƿ�Խ��
        super.rangeCheck(sequ.length, left, right);

        // �������Ϊ0��1����������ֹ������
        int size = right - left + 1;
        if (size < 2) {
            return;
        }

        // ����������٣����ò�������
        if(super.insertSortOptimized && size < super.insertSortOptimizedBound) {
            new InsertSort().execute(sequ);
            return;
        }

        int partionPoint = partition(sequ, left, right);
        if(partionPoint < 0) {
            return;
        }

        // �ݹ�
        executeProcess(sequ, left, partionPoint - 1);
        executeProcess(sequ, partionPoint + 1, right);
    }

    protected <T extends Comparable<? super T>> int partition(T[] sequ, int left, int right) {
        // �������������׼��left/right/��λ
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
        // �������2����3��Ԫ�أ���ʱ�Ѿ��ź���
        // ������С����ʹ�ò�������������û�б�Ҫ��
        if (!super.insertSortOptimized && middle == right - 1) {
            return super.InvalidPoint;
        }
        // ����׼��������ֵ������right-1λ��
        swap(sequ, middle, right - 1);

        int curPoint = left + 1;
        // ָ��С�ڻ�׼����ڵ��ڻ�׼�����еķָ�λ�ã����ڵ��ڻ�׼�����еĵ�һ��Ԫ��
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
