package mk.ukim.finki.labs.lab02.task2;

import mk.ukim.finki.dataStructures.dll.DLL;
import mk.ukim.finki.dataStructures.dll.DLLNode;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < size; i++) {
            list.insertLast(sc.nextInt());
        }

        int n = sc.nextInt();
        for (int j = 0; j < n; j++) {
            int val = sc.nextInt();
            String direction = sc.next();
            int positions = sc.nextInt();

            int steps = (positions - 1) % size;

            DLLNode<Integer> node;
            if (direction.equals("left")) {
                node = list.getFirst();
                for (int i = 0; i < steps; i++) {
                    node = node.succ;
                }
            } else {
                node = list.getLast();
                for (int i = 0; i < steps; i++) {
                    node = node.pred;
                }
            }
            list.insertAfter(val, node);
            size++;
        }

        System.out.println(list);

        int k = sc.nextInt();
        DLLNode<Integer> node = list.getLast();
        while (true) {
            for (int i = 0; i < k && node != null; i++) {
                node = node.pred;
            }
            if (node == null) break;

            DLLNode<Integer> levo = node.pred;
            list.delete(node);
            node = levo;
        }

        System.out.println(list);

    }
}
