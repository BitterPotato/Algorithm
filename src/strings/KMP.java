package strings;

public class KMP {
    private int match(Object[] origin, Object[] mode) {
        int[] next = generate(mode);
//        for(int i=0; i<next.length; i++)
//            System.out.print(next[i] + " ");

        int i=0, j=0;
        while(i < origin.length
                && j < mode.length) {
            if(j==-1 || origin[i].equals(mode[j])) {
                i++;
                j++;
            }
            else {
                j=next[j];
            }
        }

        if(j == mode.length)
            return i-j;
        return -1;
    }

    private int[] generate(Object[] mode) {
        int len = mode.length;
        int[] next = new int[len];
        next[0] = -1;

        int k = -1;
        int j = 0;
        while(j < len - 1) {
            if(k == -1 || mode[j].equals(mode[k])) {
                k++;
                j++;
//                next[j] = k;
                if(mode[j] != mode[k])
                    next[j] = k;
                else {
                    next[j] = next[k];
                }
            }
            else {
                k = next[k];
            }
        }

        return next;
    }

    public static void main(String[] args) {
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "DABCDABDE";
        Character[] c1 = new Character[s1.length()];
        Character[] c2 = new Character[s2.length()];

        for(int i=0; i<s1.length(); i++)
            c1[i] = s1.charAt(i);
        for(int i=0; i<s2.length(); i++)
            c2[i] = s2.charAt(i);

        System.out.print(new KMP().match(c1, c2));
    }
}
