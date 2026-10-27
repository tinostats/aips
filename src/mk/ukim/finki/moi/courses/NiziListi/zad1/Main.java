package mk.ukim.finki.moi.courses.NiziListi.zad1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        int sum = 0;
        for(int i=0;i<n;i++){
            arr[i]  = sc.nextInt();
            sum+= arr[i];
        }
        double avg  = sum * 1.00  / n;
        int j = 0;

        System.out.print("{");
        for(int i = 0; i<n-1;i++){
            System.out.print(arr[i]+ ",");
        }
        System.out.println(arr[n-1]+ "}");

        for(int i = 0;i<n;i++){
            if(arr[i]>=avg){
                arr[j] = arr[i];
                j++;
            }

        }

        System.out.print("{");
        for(int i = 0; i<j-1;i++){
            System.out.print(arr[i]+ ",");
        }
        System.out.print(arr[j-1]+ "}");

    }


}
