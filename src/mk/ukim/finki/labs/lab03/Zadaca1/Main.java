package mk.ukim.finki.labs.lab03.Zadaca1;

import mk.ukim.finki.dataStructures.queue.LinkedQueue;
import mk.ukim.finki.dataStructures.queue.Queue;

import java.util.Scanner;

class Proces {
    int time;
    String ime;
    Proces(int time, String ime){
        this.ime = ime;
        this.time = time;
    }
}


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        Queue<Proces> red = new LinkedQueue<>();
        for (int j = 0; j < n; j++) {
            String imence = sc.next();
            int vreme = sc.nextInt();
            Proces procesche = new Proces(vreme , imence) ;
            red.enqueue(procesche);
        }
        int j=0;
        while(!red.isEmpty()){
            if( red.peek().time<=t){
                j++;
                System.out.println(j+". "+red.dequeue().ime);

            }
            else {
                red.peek().time -= t;
                red.enqueue(red.dequeue());
            }
        }


        }
    }


