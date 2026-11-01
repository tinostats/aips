package mk.ukim.finki.moi.courses.tehniki.svetilki;
import mk.ukim.finki.dataStructures.priorityqueue.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        PriorityQueue<Integer> positions = new PriorityQueue<>();
        PriorityQueue<Integer> neosvetleni = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            positions.enqueue(sc.nextInt());
        }
        for (int i = 1; i <= m; i++) {
            neosvetleni.enqueue(i);
        }

        int svetilki = 0;
        int last= 0;
        while (!neosvetleni.isEmpty()) {

            while (!positions.isEmpty() && positions.peek() <= neosvetleni.peek() + 2) {
                if(positions.peek()<=neosvetleni.peek()+2){
                    last = positions.peek();
                    positions.dequeue();
                }
            }
                while (!neosvetleni.isEmpty() && neosvetleni.peek() <= last + 2) {
                neosvetleni.dequeue();
            }
            svetilki++;
            last = 0;
        }

        System.out.println(svetilki);
    }
}
