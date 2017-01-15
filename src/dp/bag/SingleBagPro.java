package dp.bag;

import java.util.Scanner;

//4 10
//6 30
//3 14
//4 16
//2 9
/**
* �����������������
* @author Yang Weijie
*
*/
public class SingleBagPro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int thingsNum = scanner.nextInt();
        int bagCapa = scanner.nextInt();

        SingleBagPro bagPro = new SingleBagPro(thingsNum);
        int tempNum = thingsNum;
        while(tempNum-->0) {
            int capa = scanner.nextInt();
            int value = scanner.nextInt();
            bagPro.putThing(capa, value);
        }

        bagPro.caculate(thingsNum, bagCapa);
    }

    class Thing{
        // ��Ʒ��ռ����
        int capa;
        // ��Ʒ�ļ�ֵ
        int value;

        Thing(int capa, int value) {
            this.capa = capa;
            this.value = value;
        }
    }

    private Thing[] things;
    private int index;

    // ÿ����������ÿ��������Ʒ��Ӧ�ı�����ֵ
    private int[][] eachCapaValue;
    // ÿ����������ÿ������Ʒ�Ƿ�װ����Ʒ
    // trueΪװ
    private boolean[][] eachCapaThing;

    SingleBagPro(int thingsNum) {
        things = new Thing[thingsNum];
        index = 0;
    }

    public void putThing(int capa, int value) {
        things[index] = new Thing(capa, value);
        index++;
    }

    public void caculate(int thingsNum, int bagCapa) {
        // notice:��������Ϊ0����
        eachCapaValue = new int[bagCapa+1][thingsNum+1];
        eachCapaThing = new boolean[bagCapa+1][thingsNum+1];

//        for(int index = 0; index <= bagCapa; index++) {
//            eachCapaValue[index][0] = 0;
//        }
//        for(int index = 1; index <= thingsNum; index++) {
//            eachCapaValue[0][index] = 0;
//        }

        for(int capa = 1; capa <= bagCapa; capa++) {
            for(int index = 0; index < things.length; index++) {
                int tCapa = things[index].capa;
                int tValue = things[index].value;
                if(tCapa <= capa
                        && (eachCapaValue[capa - tCapa][index] + tValue) > eachCapaValue[capa][index]) {
                    eachCapaValue[capa][index + 1] = eachCapaValue[capa - tCapa][index] + tValue;
                    eachCapaThing[capa][index + 1] = true;
                }
                else {
                    eachCapaValue[capa][index + 1] = eachCapaValue[capa][index];
//                    eachCapaThing[capa][index + 1] = false;
                }
            }
        }

        printResult(thingsNum, bagCapa);
    }

    private void printResult(int thingsNum, int bagCapa) {
        System.out.println("totalCapacity: " + bagCapa + " Value: " + eachCapaValue[bagCapa][thingsNum]);
        int remainCapa = bagCapa;
        int tempThingIndex = thingsNum;

        while(eachCapaValue[remainCapa][tempThingIndex] != 0) {
            if(eachCapaThing[remainCapa][tempThingIndex]) {
                System.out.println(things[tempThingIndex - 1].capa + " " + things[tempThingIndex - 1].value);
                remainCapa -= things[tempThingIndex - 1].capa;
            }
            else {
                tempThingIndex--;
            }
        }
    }
}
