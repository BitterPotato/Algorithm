package sort;

import static sort.SortUtils.swap;

import java.util.Stack;

/**
 * �ǵݹ��������
 *
 * @author Yang Weijie
 *
 */
public class NonRecurQuickSort extends AbstractQuickSort{
    // main code
    /**
    *
    * �������
    *
    * @param sequ
    * @param left
    *            ���������Ԫ����ʼ�±�
    * @param right
    *            ���������Ԫ�ؽ����±�
    */
    protected <T extends Comparable<? super T>> void executeProcess(T[] sequ, int left, int right) {
        Stack<Integer> stack = new Stack<Integer>();

        int partionPoint = partition(sequ, left, right);
        if(partionPoint < 0) {
            return;
        }

        stack.push(partionPoint + 1);
        stack.push(right);

        stack.push(left);
        stack.push(partionPoint - 1);

        while(!stack.isEmpty()) {
            int sRight = stack.pop();
            int sLeft = stack.pop();

            partionPoint = partition(sequ, sLeft, sRight);
            if(partionPoint < 0) {
                continue;
            }

            stack.push(partionPoint + 1);
            stack.push(sRight);

            stack.push(sLeft);
            stack.push(partionPoint - 1);
        }

    }

    protected <T extends Comparable<? super T>> int partition(T[] sequ, int left, int right) {
        // ����±��Ƿ�Խ��
        super.rangeCheck(sequ.length, left, right);

        // �������Ϊ0��1����������ֹ������
        int size = right - left + 1;
        if (size < 2) {
            return super.InvalidPoint;
        }

        // ����������٣����ò�������
        if(super.insertSortOptimized && size < super.insertSortOptimizedBound) {
            new InsertSort().execute(sequ);
            return super.InvalidPoint;
        }

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

        // ��left+1��right-2֮��ķ�Χ����ɸѡ
        // int forePoint = left + 1;
        // int backPoint = right - 2;
        int forePoint = left;
        int backPoint = right - 1;
        T pivot = sequ[right - 1];

        while (true) {
            // Wrong Version: ��sequ[forePoint]=sequ[backPoint]=pivotʱ����������ѭ��
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
                // ����׼�ŵ�����λ��
                swap(sequ, forePoint, right - 1);
                break;
            } else {
                swap(sequ, forePoint, backPoint);
            }
        }
        return forePoint;
    }

}
