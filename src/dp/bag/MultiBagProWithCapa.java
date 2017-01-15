package dp.bag;

import java.util.Scanner;

//4 10
//6 30
//3 14
//4 16
//2 9
/**
 * ������Ϊ���������ั����������
 * @author Yang Weijie
 *
 */
public class MultiBagProWithCapa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int thingsNum = scanner.nextInt();
        int bagCapa = scanner.nextInt();

        MultiBagProWithCapa bagPro = new MultiBagProWithCapa(thingsNum);
        while(thingsNum-->0) {
            int capa = scanner.nextInt();
            int value = scanner.nextInt();
            bagPro.putThing(capa, value);
        }

        bagPro.caculate(bagCapa);
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

    // ÿ��������������Ӧ�ı�����ֵ
    private int[] eachCapaValue;
    // ÿ��������������Ӧ�Ķ�װ��Ʒ�����
    private int[] eachCapaThing;

    MultiBagProWithCapa(int thingsNum) {
        things = new Thing[thingsNum];
        index = 0;
    }

    public void putThing(int capa, int value) {
        things[index] = new Thing(capa, value);
        index++;
    }

    public void caculate(int bagCapa) {
        // notice:��������Ϊ0����
        eachCapaValue = new int[bagCapa+1];
        eachCapaThing = new int[bagCapa+1];

        eachCapaValue[0] = 0;
        for(int i=0; i < eachCapaThing.length; i++)
            eachCapaThing[i] = -1;
        for(int capa = 1; capa <= bagCapa; capa++) {
            for(int index = 0; index < things.length; index++) {
                int tCapa = things[index].capa;
                int tValue = things[index].value;
                if(tCapa <= capa
                        && (eachCapaValue[capa - tCapa] + tValue) > eachCapaValue[capa]) {
                    eachCapaValue[capa] = eachCapaValue[capa - tCapa] + tValue;
                    eachCapaThing[capa] = index;
                }
            }
        }

        printResult(bagCapa);
    }

    private void printResult(int bagCapa) {
        System.out.println("totalCapacity: " + bagCapa + " Value: " + eachCapaValue[bagCapa]);
        int remainCapa = bagCapa;
        while(true) {
            int thingIndex = eachCapaThing[remainCapa];
            if(thingIndex == -1)
                break;

            System.out.println(things[thingIndex].capa + " " + things[thingIndex].value);
            remainCapa -= things[thingIndex].capa;
        }
    }
}
