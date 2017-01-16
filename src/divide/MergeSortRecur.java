package divide;

import java.util.Scanner;

//8
//10 2 5 3 7 13 1 6
public class MergeSortRecur {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        int[] data = new int[size];
        for(int i = 0; i < size; i++) {
            data[i] = scanner.nextInt();
        }

        new MergeSortRecur().solve(data);
    }

    private int[] data;
    // 存放merge()的中间结果（共用）
    private int[] tempData;

    public void solve(int[] data) {
        this.data = data;
        tempData = new int[data.length];

        mergeSort(0, data.length - 1);
        printResult();
    }

    private void mergeSort(int sequFrom, int sequTo) {
        if(sequFrom == sequTo)
            return;

        int sequCut = ( sequFrom + sequTo ) / 2;
        mergeSort(sequFrom, sequCut);
        mergeSort(sequCut+1, sequTo);
        mergeOrdi(sequFrom, sequCut, sequCut+1, sequTo);
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
