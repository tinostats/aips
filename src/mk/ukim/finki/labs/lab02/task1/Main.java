package mk.ukim.finki.labs.lab02.task1;

import mk.ukim.finki.dataStructures.sll.SLL;
import mk.ukim.finki.dataStructures.sll.SLLNode;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    static long min(long a, long b){
        if(a<=b)return a;
        return b;
    }
    static long abs(long a){
        if(a>=0)return a;
        return -a;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sizeA = sc.nextInt();

        SLL<String> listaA = new SLL<>();
        for (int i = 0; i < sizeA; i++) {
            listaA.insertLast(sc.next());
        }

        int sizeB = sc.nextInt();
        SLL<String> listaB = new SLL<>();
        for (int i = 0; i <sizeB ; i++) {
            listaB.insertLast(sc.next());
        }

        double x = sc.nextDouble();

        SLLNode<String> nodeA = listaA.getFirst();
        SLLNode<String> nodeB = listaB.getFirst();
        for (int i = 0; i < min(sizeA,sizeB); i++) {
            if(abs(Long.parseLong(nodeA.element)-Long.parseLong(nodeB.element))>x){
                SLLNode<String> tmp = nodeA;
                nodeA.element = "Outlier " + nodeA.element;
                listaA.insertAfter(nodeA.element, nodeA);
                tmp = nodeA.succ;
                listaA.delete(nodeA);
                nodeA = tmp.succ;
                tmp = nodeB.succ;
                listaB.delete(nodeB);
                nodeB = tmp;
            }
            else{
                nodeA = nodeA.succ;
                nodeB = nodeB.succ;
            }

        }
        if(sizeA>sizeB){
            SLLNode<String> node = listaA.getFirst();
            for (int i = 0; i < sizeB; i++) {
                node = node.succ;
            }
            for (int i = sizeB; i < sizeA; i++) {
                node.element = "Unchecked "+ node.element;
                node = node.succ;
            }
        }

        System.out.println(listaA);
        System.out.println(listaB);
    }

}
