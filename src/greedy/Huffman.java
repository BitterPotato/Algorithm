package greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//4
//A 70000000
//B 3000000
//C 20000000
//D 37000000
/**
 * @author Yang Weijie
 *
 */
public class Huffman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int strSize = scanner.nextInt();

        Huffman huffman = new Huffman();

        while(strSize-- > 0) {
            String str = scanner.next();
            int freq = scanner.nextInt();
            huffman.addStrFreq(str, freq);
        }
        StrFreq strFreq = huffman.encode();
        huffman.print(strFreq);

    }

    class StrFreq {
        StrFreq left;
        StrFreq right;

        String str;
        int freq;

        public StrFreq(String str, int freq) {
            this.str = str;
            this.freq = freq;
        }

        public StrFreq(StrFreq left, StrFreq right, String str, int freq) {
            this(str, freq);
            this.left = left;
            this.right = right;
        }
    }

    private List<StrFreq> strFreqArray;
    private MyComparator myComparator = new MyComparator();

    Huffman() {
        strFreqArray = new ArrayList<StrFreq>();
    }

    private void formStrFreqNode(StrFreq left, StrFreq right, String str, int freq) {
        StrFreq strFreq = new StrFreq(left, right, str, freq);
        strFreqArray.add(strFreq);
        strFreqArray.sort(myComparator);
    }
    public void addStrFreq(String str, int freq) {
        strFreqArray.add(new StrFreq(str, freq));
        strFreqArray.sort(myComparator);
    }

    /**
     * return root
     * @return
     */
    public StrFreq encode() {
        while(isOneLeft()) {
            StrFreq strFreqOne = getMinFreq();
            StrFreq strFreqTwo = getMinFreq();


            formStrFreqNode(strFreqOne, strFreqTwo, null, strFreqOne.freq + strFreqTwo.freq);
        }

        return getMinFreq();
    }

    public void print(StrFreq strFreq) {
        printProcess(strFreq, 1);
    }

    private void printProcess(StrFreq strFreq, int num) {
        if(strFreq == null)
            return;

        printProcessNode(strFreq, num);
        printProcess(strFreq.left, 2*num);
        printProcess(strFreq.right, 2*num + 1);
    }

    private void printProcessNode(StrFreq strFreq, int num) {
        if(strFreq.str != null) {
            String res = Integer.toBinaryString(num).substring(1);
            System.out.println(strFreq.str + " -> " + res);
        }
    }
    private boolean isOneLeft() {
        return strFreqArray.size() > 1;
    }

    private StrFreq getMinFreq() {
        if(strFreqArray.isEmpty()) {
            return null;
        }
        StrFreq strFreq = strFreqArray.get(0);
        strFreqArray.remove(0);
        return strFreq;
    }

    class MyComparator implements Comparator<StrFreq> {

        @Override
        public int compare(StrFreq o1, StrFreq o2) {
            return o1.freq - o2.freq;
        }

    }
}