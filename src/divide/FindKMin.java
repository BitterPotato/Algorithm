package divide;

import java.util.Random;
import java.util.Scanner;

//11 8
//2 36 5 21 8 13 11 20 5 4 1
/**
 * �ҵ�����S�е�kС����
 * @author Yang Weijie
 *
 */
public class FindKMin {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int k = scanner.nextInt();

        int[] data = new int[size];
        for(int i = 0; i < size; i++) {
            data[i] = scanner.nextInt();
        }

        int res = new FindKMin().solve(data, k);
        System.out.print(res);
    }

    private int[] data;

    public int solve(int[] data, int k) {
        this.data = data;

        return pSolve(0, data.length - 1, k);
    }

    private int pSolve(int left, int right, int k) {
        if(left == right && k == 1) {
            return data[left];
        }

        // find the pivot this time
        int pivotIndex = findPivot(left, right);
        int pivot = data[pivotIndex];
        swap(left, pivotIndex);

        pivotIndex = partition(left, right, pivot);

        int lSize = pivotIndex - left;
        int lmSize = lSize + 1;
        if(k == lmSize) {
            return pivot;
        }
        else if(k <= lSize) {
            return pSolve(left, pivotIndex-1, k);
        }
        else {
            return pSolve(pivotIndex+1, right, k-lmSize);
        }


    }

    private int partition(int left, int right, int pivot) {
        int start = left + 1;
        // mstart��ߣ�����������С��pivot
        int mstart = start;
        // bstart��ߣ�����������С�ڵ���pivot
//		int bstart = start;

        for(; start <= right; start++) {
            if(data[start] < pivot) {
                swap(mstart, start);
                mstart++;
            }
            else {
                // do nothing
            }
        }

        if(mstart > left) {
            swap(mstart-1, left);
            return mstart-1;
        }
        else {
            return left;
        }
    }

    /**
    * ���ػ�׼���ڵ�λ�ã�random��
    * @param left
    * @param right
    * @return
    */
    private int findPivot(int left, int right) {
        return new Random().nextInt(right-left+1)+left;
    }

    private void swap(int one, int two) {
        int temp = data[one];
        data[one] = data[two];
        data[two] = temp;
    }
}
