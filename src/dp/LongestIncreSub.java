package dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//8
//5 2 8 6 3 6 9 7
/**
 * �����������
 * @author Yang Weijie
 *
 */
public class LongestIncreSub {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sequSize = scanner.nextInt();

        int[] sequ = new int[sequSize];
        for(int i=0; i<sequSize; i++) {
            sequ[i] = scanner.nextInt();
        }

        LongestIncreSub lis = new LongestIncreSub();
        lis.calLis(sequ);
    }

    class Node {
        // ��ǰ�ڵ��ӦԪ��
        int num;

        // ����ǰ�ڵ��·��ֵ
        int length;

        List<Integer> nextNodes;

        // ǰ���ڵ�����
        Integer preNode;

        Node(int num) {
            this.num = num;
            this.length = 1;
            nextNodes = new ArrayList<Integer>();
            this.preNode = num;
        }

        void addNextNodeIndex(int num) {
            nextNodes.add(num);
        }
    }

    private Node[] nodeArray;
    // ����������յ�
    private Node destNode;

    public void calLis(int[] sequ) {
        formGraph(sequ);

        calDest();

        printCal();
    }

    private void formGraph(int[] sequ) {
        nodeArray = new Node[sequ.length];

        for(int index=0; index < sequ.length; index++) {
            Node node = new Node(sequ[index]);
            nodeArray[index] = node;
            for(int jIndex=index; jIndex < sequ.length; jIndex++) {
                // ��
                if(sequ[jIndex] > sequ[index]) {
                    node.addNextNodeIndex(jIndex);
                }
            }// end 2nd for
        }// end 1st for
    }

    // ����formGraph()�ϲ�
    private void calDest() {
        // ����ÿ���ڵ���·��
        for(int index = 0; index < nodeArray.length; index++) {
            for(int toIndex : nodeArray[index].nextNodes) {
                if(nodeArray[toIndex].length < nodeArray[index].length + 1) {
                    nodeArray[toIndex].length = nodeArray[index].length + 1;
                    nodeArray[toIndex].preNode = index;
                }
            }
        }

        destNode = nodeArray[0];
        for(int index = 0; index < nodeArray.length; index++) {
            if(nodeArray[index].length > destNode.length) {
                destNode = nodeArray[index];
            }
        }
    }

    private void printCal() {
        System.out.print(destNode.length + " :");
        printCalProcess(destNode);
    }

    private void printCalProcess(Node node) {
        if(node.preNode != node.num) {
            printCalProcess(nodeArray[node.preNode]);
        }
        System.out.print(" " + node.num);
    }
}
