package divide;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//8
//10 2 5 3 7 13 1 6
/**
 * 归并排序迭代版
 * @author Yang Weijie
 *
 */
public class MergeSortIter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        int[] data = new int[size];
        for(int i = 0; i < size; i++) {
            data[i] = scanner.nextInt();
        }

        new MergeSortIter().solve(data);
    }

    private int[] data;
    // 存放merge()的中间结果（共用）
    private int[] tempData;

    private Queue<IndexArray> queue;

    class IndexArray {
        int from;
        int to;

        IndexArray(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public void solve(int[] data) {
        this.data = data;
        tempData = new int[data.length];

        mergeSort(0, data.length - 1);
        printResult();
    }

    private void mergeSort(int sequFrom, int sequTo) {
        if(sequFrom == sequTo)
            return;

        queue = new LinkedList<IndexArray>();

        for(int tempFrom = sequFrom; tempFrom <= sequTo; tempFrom++) {
            queue.offer(new IndexArray(tempFrom, tempFrom));
        }

        while(queue.size() > 1) {
            IndexArray x = queue.poll();
            IndexArray y = queue.poll();
            mergeOrdi(x.from, x.to, y.from, y.to);

            queue.offer(new IndexArray(x.from, y.to));
        }
    }

    private void mergeOrdi(int sequOneFrom, int sequOneTo, int sequTwoFrom, int sequTwoTo) {
        int xPos = sequOneFrom;
        int yPos = sequTwoFrom;
        int zPos = xPos;

        while(xPos <= sequOneTo && yPos <= sequTwoTo) {
            if(data[xPos] < data[yPos]) {
                tempData[zPos++] = data[xPos++];
            }
            else {
                tempData[zPos++] = data[yPos++];
            }
        }

        while(xPos <= sequOneTo)
            tempData[zPos++] = data[xPos++];
        while(yPos <= sequTwoTo)
            tempData[zPos++] = data[yPos++];

        // write back
        int from = sequOneFrom;
        int to = sequTwoTo;
        for(; from <= to; from++) {
            data[from] = tempData[from];
        }
    }

    private void printResult() {
        for(int val : data)
            System.out.print(val + " ");
    }

}
