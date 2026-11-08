package mk.ukim.finki.moi.courses.PrvKolokvVezbi.zad1;

import java.util.Scanner;
import mk.ukim.finki.dataStructures.sll.SLL;
import mk.ukim.finki.dataStructures.sll.SLLNode;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> lista = new SLL<>();
        for (int i = 0; i < n; i++) {
            lista.insertLast(sc.nextInt());
        }



        int info = sc.nextInt();
        SLLNode<Integer> node = lista.getFirst();
        int counter = 0;
        while (node != null) {
            if (node.element.equals(info) ) {
                counter++;
            }
            node = node.succ;
        }

        node = lista.getFirst();

        if (node != null && node.element.equals(info) && counter%2==1) {
            lista.insertFirst(info);
            System.out.println(lista);
            return;
        }
        while (node != null && node.succ != null) {
            if (node.succ.element.equals(info)  ) {
                if(counter %2 ==1)lista.insertAfter(info, node);
                break;
            }
            node = node.succ;
        }

        System.out.println(lista);
    }
}