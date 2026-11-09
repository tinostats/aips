package mk.ukim.finki.moi.courses.PrvKolokvVezbi.zad6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class card_trick {
    public static int count(int N){
        // Vasiot kod tuka
        Queue<Integer> shpil  = new LinkedList<>();
        Stack<Integer> mikser = new Stack<>();
        for (int i = 0; i < 51; i++) {
            shpil.add(i+1);
        }
        int counter = 0;
        while(shpil.peek()!=N){
            for (int i = 0; i < 7; i++) {
                mikser.push(shpil.remove());
            }
            while(!mikser.isEmpty()){
                shpil.add(mikser.pop());
                shpil.add(shpil.remove());
            }
            counter++;
        }
        return counter;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
