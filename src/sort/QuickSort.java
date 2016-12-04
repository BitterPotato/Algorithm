package sort;

import static sort.SortUtils.swap;

/**
 * �������� �Ż��㣺 1.��׼��ѡ��������ֵ�ָ� 2.С����Ĵ����������� �ӣ� 1.�����ݹ����ֹ���� 2.������ͬԪ�صĴ��� �룺
 * 1.������ѡȡ@ȫ���ԣ����ܷ��� 2.�㷨��ͨ����@רҵ��
 *
 * @author Yang Weijie
 *
 */
public class QuickSort {

    // main code
    /**
    *
    * @param sequ
    * @param left
    *            ���������Ԫ����ʼ�±�
    * @param right
    *            ���������Ԫ�ؽ����±�
    */
    public <T extends Comparable<? super T>> void execute(T[] sequ, int left, int right) {
        // ����±��Ƿ�Խ��
        rangeCheck(sequ.length, left, right);

        // �������Ϊ0��1����������ֹ������
        // Notice: ����Сѧ����������+1
//        int size = right - left + 1;
        int size = right - left;
        if (size < 2) {
            return;
        }

        // ����������٣����ò�������

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
        // �������3��Ԫ�أ���ʱ�Ѿ��ź���
        // ������С����ʹ�ò�������������û�б�Ҫ��
        if (middle == right - 1) {
            return;
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

        // �ݹ�
        execute(sequ, left, forePoint - 1);
        execute(sequ, forePoint + 1, right);
    }

    /**
    * ����±��Ƿ�Ϸ�
    *
    * @param arrayLength
    * @param fromIndex
    * @param toIndex
    * @reference java.util.Arrays
    */
    private void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
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

}
