package mk.ukim.finki.moi.courses.voved.zad1;

import java.util.Scanner;

public class PushZero
{
    static void pushZerosToBeginning(int[] arr, int n) {
        int w = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i]!=0) {
                arr[w] = arr[i];
                w--;
            }
        }
        for (int i=0;i<=w;i++) {
            arr[i] = 0;
        }
        for(int el : arr){
            System.out.print(el + " ");
        }
    }

    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        pushZerosToBeginning(arr, n);
    }
}