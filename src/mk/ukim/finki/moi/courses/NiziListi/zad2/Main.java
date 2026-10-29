package mk.ukim.finki.moi.courses.NiziListi.zad2;
import mk.ukim.finki.dataStructures.sll.SLL;
import mk.ukim.finki.dataStructures.sll.SLLNode;

import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<String> list = new SLL<>();
        for(int i=0;i<n;i++){
            list.insertLast(sc.next());
        }
        int L = sc.nextInt();

        SLLNode<String> node = list.getFirst();
        while(node!=null){
            if(node.succ != null)System.out.print(node.element + "->");
            else System.out.println(node.element);
            node = node.succ;
        }

        node = list.getFirst();
        while(node!=null){
            SLLNode<String> nextNode = node;
            if(node.element.length()<L){
                list.delete(node);
            }
            node = nextNode.succ;
        }

        node = list.getFirst();
        while(node!=null){
            if(node.succ!= null)System.out.print(node.element + "->");
            else System.out.println(node.element);
            node = node.succ;
        }

    }
}
