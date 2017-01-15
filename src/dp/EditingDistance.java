package dp;

import java.util.Scanner;

//EXPONENTIAL
//POLYNOMIAL
/**
 *
 * @author Yang Weijie
 *
 */
public class EditingDistance {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();

        EditingDistance ed = new EditingDistance();
        System.out.println(ed.caculate(str1.toCharArray(), str2.toCharArray()));
    }

    /**
    *
    * @param str1
    * @param str2
    * @return 编辑距离
    */
    public int caculate(char[] str1, char[] str2) {
        int m = str1.length;
        int n = str2.length;

        // notice:序列0表示不包含字符串的任意字符
        int[][] E = new int[m+1][n+1];
        for(int i=0; i<=m; i++) {
            E[i][0] = i;
        }
        for(int j=1; j<=n; j++) {
            E[0][j] = j;
        }

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                int val1 = E[i-1][j] + 1;
                int val2 = E[i][j-1] + 1;
                int val3 = E[i-1][j-1] + diff(str1[i-1], str2[j-1]);

                E[i][j] = val1 > val2 ? val2 : val1;
                E[i][j] = E[i][j] > val3 ? val3 : E[i][j];
            }
        }

        return E[m][n];
    }

    private int diff(char c1, char c2) {
        if(c1 == c2)
            return 0;
        return 1;
    }
}
