package dp;

import java.util.Scanner;

//4
//50 20 1 10 100
/**
 * 矩阵相乘
 * @author Yang Weijie
 *
 */
public class MatrixChain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // n为矩阵个数
        int n = scanner.nextInt();
        // dimenNum为矩阵维数总数
        int dimenNum = n + 1;

        int[] matrixDimen = new int[dimenNum];
        for(int i = 0; i < dimenNum; i++) {
            matrixDimen[i] = scanner.nextInt();
        }

        new MatrixChain().calculate(matrixDimen);
    }

    private int matrixNum;
    private int[] matrixDimen;
    private int[][] chainResults;
    private int[][] chainCuts;

    public void calculate(int[] matrixDimen) {
        this.matrixDimen = matrixDimen;
        this.matrixNum = matrixDimen.length - 1;
        this.chainResults = new int[matrixNum][matrixNum];
        this.chainCuts = new int[matrixNum][matrixNum];

        for(int i=0; i<matrixNum; i++)
            for(int j=0;j<matrixNum; j++)
                chainCuts[i][j] = -1;
        calculateProcess();
        printResults();
    }

    private void calculateProcess() {
        // init
        // 对角线已被自动初始化为0

        // main
        for(int size=1; size<matrixNum; size++) {
            for(int i=0; i< matrixNum - size; i++) {
                int j = i+size;

                int k=i;
                chainResults[i][j] = chainResults[i][k] + chainResults[k+1][j]
                        + matrixDimen[i]*matrixDimen[k+1]*matrixDimen[j+1];
                chainCuts[i][j] = k;
                for(; k<j; k++) {
                    int cost = chainResults[i][k] + chainResults[k+1][j]
                            + matrixDimen[i]*matrixDimen[k+1]*matrixDimen[j+1];
                    if(cost < chainResults[i][j]) {
                        chainResults[i][j] = cost;
                        chainCuts[i][j] = k;
                    }

                }

            }
        }
    }

    private void printResults() {
        System.out.println("Cost:" + chainResults[0][matrixNum-1]);

        subPrintResults(0, matrixNum-1);
    }

    private void subPrintResults(int i, int j) {
        if( j-i+1 == 2) {
            System.out.print("A" + (i+1));
            System.out.print("A" + (j+1));
            return;
        }
        else if(j-i+1 == 1) {
            System.out.print("A" + (i+1));
        }

        int k = chainCuts[i][j];

        System.out.print("(");
        subPrintResults(0, k);
        System.out.print(")");

        System.out.print("(");
        subPrintResults(k+1, matrixNum-1);
        System.out.print(")");
    }


}
