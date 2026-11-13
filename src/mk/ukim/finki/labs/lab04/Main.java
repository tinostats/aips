package mk.ukim.finki.labs.lab04;

import java.util.Scanner;

class Proces {
    int time;
    int weight;

    Proces(int time, int weight){
        this.time = time;
        this.weight = weight;
    }
}

public class Main {

    static void sorting(Proces procesi[]){
        for (int i = 0; i < procesi.length; i++) {
            for (int j = 0; j < procesi.length - 1 - i; j++) {
                if ((procesi[j].time * procesi[j+1].weight) > (procesi[j+1].time * procesi[j].weight)) {
                    Proces tmp = procesi[j+1];
                    procesi[j+1] = procesi[j];
                    procesi[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Proces[] procesi = new Proces[n];

        for (int i = 0; i < n; i++) {
            procesi[i] = new Proces(sc.nextInt(), sc.nextInt());
        }

        sorting(procesi);

        long totalCost = 0;
        long currentTime = 0;

        for (int i = 0; i < n; i++) {
            totalCost += currentTime * procesi[i].weight;
            currentTime += procesi[i].time;
        }

        System.out.println(totalCost);
        sc.close();
    }
}