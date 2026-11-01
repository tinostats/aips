package mk.ukim.finki.moi.courses.tehniki.najgolemBroj;

import mk.ukim.finki.dataStructures.priorityqueue.PriorityQueue;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> red = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            red.enqueue(-1 * sc.nextInt());
        }
        String odg = "";
        for (int i = 0; i < n; i++) {
            odg+=(-1*red.dequeue());
        }
        System.out.printf(odg);

    }
}
