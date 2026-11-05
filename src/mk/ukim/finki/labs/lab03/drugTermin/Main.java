package mk.ukim.finki.labs.lab03.drugTermin;

import mk.ukim.finki.dataStructures.queue.LinkedQueue;
import mk.ukim.finki.dataStructures.queue.Queue;
import mk.ukim.finki.dataStructures.stack.LinkedStack;
import mk.ukim.finki.dataStructures.stack.Stack;
import java.util.Scanner;

class Podproces{
    int time;
    String ime;
    Podproces(int time, String ime){
        this.ime = ime;
        this.time = time;
    }
}

class Proces{
    String ime;
    Stack<Podproces> podprocesi = new LinkedStack<>();
    Proces(String ime){
        this.ime = ime;
    }

}


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        Queue<Proces> red = new LinkedQueue<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int k = sc.nextInt();
            Proces naRed = new Proces(name);
            for (int j = 0; j < k; j++) {
                String imence = sc.next();
                int vreme = sc.nextInt();
                Podproces procesche = new Podproces(vreme , imence) ;
                naRed.podprocesi.push(procesche);
            }
            red.enqueue(naRed);
        }
        while(!red.isEmpty()){
            Proces naRed = red.dequeue();
            int flag = 0;
            while(flag < t && !naRed.podprocesi.isEmpty()){
                flag  += naRed.podprocesi.peek().time;
                if(flag<=t)naRed.podprocesi.pop();
                else naRed.podprocesi.peek().time -= (t-flag);
            }
            if(!naRed.podprocesi.isEmpty())red.enqueue(naRed);
            else System.out.println(naRed.ime);
        }
    }

}
