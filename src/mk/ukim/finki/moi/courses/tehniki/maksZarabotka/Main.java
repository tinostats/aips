package mk.ukim.finki.moi.courses.tehniki.maksZarabotka;

import mk.ukim.finki.dataStructures.priorityqueue.PriorityQueue;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Double> wages = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            double a  = sc.nextInt();
            double b = sc.nextInt();
            double perHour = b*1.0/a;
            for (int j = 0; j < a; j++) {
                wages.enqueue(-1*perHour);
            }
        }
        double sum=0;
        for (int i = 0; i < 40; i++) {
            if(wages.isEmpty())break;
            sum+=(-1*wages.peek());
            ///System.out.println((i+1) + " " +  (-1*wages.peek()) + " " + sum);
            wages.dequeue();
        }
        System.out.println(sum);
    }
}
