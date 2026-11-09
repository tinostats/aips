package mk.ukim.finki.moi.courses.PrvKolokvVezbi.zad2;
import mk.ukim.finki.dataStructures.dll.DLL;
import mk.ukim.finki.dataStructures.dll.DLLNode;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        DLL<DLL<Integer>> lista = new DLL<>();

        int prod = 1;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            DLL<Integer> podlista = new DLL<>();
            for (int j = 0; j < m; j++) {
                podlista.insertLast(sc.nextInt());
                sum+=podlista.getLast().element;
            }
            lista.insertLast(podlista);
            prod*=sum;
        }
        System.out.println(prod);
    }
}
