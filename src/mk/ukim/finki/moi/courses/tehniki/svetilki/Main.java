package mk.ukim.finki.moi.courses.tehniki.svetilki;
import mk.ukim.finki.dataStructures.priorityqueue.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        PriorityQueue<Integer> positions = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            positions.enqueue(sc.nextInt());
        }
        int  last = positions.peek();
        positions.dequeue();
        int dist = 0;
        int total = 0;
        int svetilki = 1;
        while(!positions.isEmpty()){

            dist = Math.abs(last - positions.peek());
            if(dist <=2 && total <= 4){
                total+=dist;
            }
            else {
               total = 0;
               svetilki++;
            }
           /// System.out.println(last + " " + positions.peek() + "   " + svetilki + "   " + dist +" " + total );
            last = positions.peek();
            positions.dequeue();

        }
        if(total>4)svetilki++;

        System.out.println(svetilki);
    }
}
