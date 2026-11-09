package mk.ukim.finki.moi.courses.PrvKolokvVezbi.zad3;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        br.close();

        // Vasiot kod tuka
        int min = Math.max(m,n)*100;
        System.out.println(min);
        int max = (Math.max(m-1,0)+n)*100;
        System.out.println(max);
    }

}