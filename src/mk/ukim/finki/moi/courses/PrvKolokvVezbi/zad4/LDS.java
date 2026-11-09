package mk.ukim.finki.moi.courses.PrvKolokvVezbi.zad4;
import java.util.Scanner;


public class LDS {


    private static int najdolgaOpagackaSekvenca(int[] a) {

        // Vasiot kod tuka
        int dp[] = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            dp[i] = 1;
        }
        int res = 1;
        for (int i = 0; i <a.length ; i++) {
            int maks = 1;
            for (int j = 0; j < i; j++) {
                if(a[i] < a[j])
                {
                    if(maks<=dp[j]){
                        dp[i] = dp[j] + 1;
                        maks = dp[j];
                    }
                }
            }
            if(res<=dp[i]) res = dp[i];
        }
        return res;

    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
