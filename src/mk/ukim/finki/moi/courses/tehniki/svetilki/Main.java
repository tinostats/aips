package mk.ukim.finki.moi.courses.tehniki.svetilki;
import mk.ukim.finki.dataStructures.priorityqueue.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        PriorityQueue<Integer> positions = new PriorityQueue<>();
        int  num =0;
        for (int i = 0; i < n; i++) {
            positions.enqueue(sc.nextInt());
            num++;
        }
        int last = -10;
        while(!positions.isEmpty()){
            if(Math.abs(last - positions.peek())<=1)num--;
            last = positions.peek();
            positions.dequeue();

        }

        System.out.println(num);
    }
}
