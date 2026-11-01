package mk.ukim.finki.moi.courses.tehniki.vozovi;

import mk.ukim.finki.dataStructures.queue.ArrayQueue;
import mk.ukim.finki.dataStructures.queue.Queue;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> arr = new ArrayQueue<>(n);
        Queue<Integer> dep = new ArrayQueue<>(n);
        for (int i = 0; i < n; i++) {
            arr.enqueue(sc.nextInt());
            dep.enqueue(sc.nextInt());
        }
        int count = 0;
        int max = 0;
        while (!dep.isEmpty())
        {

            if(!arr.isEmpty() &&  arr.peek()<=dep.peek()){
                count ++;
                arr.dequeue();
            }
            else {
                count--;
                dep.dequeue();
            }
            if(count>max)max = count;
        }
        System.out.println(max);
    }
}