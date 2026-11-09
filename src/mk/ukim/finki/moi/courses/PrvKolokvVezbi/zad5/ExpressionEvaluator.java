package mk.ukim.finki.moi.courses.PrvKolokvVezbi.zad5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression) {
        Stack<Integer> sobirach = new Stack<>();
        String currentNum = "";
        int res = 1;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                currentNum += c;
            }
            else {
                if (c == ' ') continue;

                if (!currentNum.isEmpty()) {
                    int num = Integer.parseInt(currentNum);
                    res *= num;
                    currentNum = "";
                }

                if (c == '+') {
                    sobirach.push(res);
                    res = 1;
                }
            }
        }

        if (!currentNum.isEmpty()) {
            int num = Integer.parseInt(currentNum);
            res *= num;
        }
        sobirach.push(res);

        int result = 0;
        while (!sobirach.isEmpty()) {
            result += sobirach.pop();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line = input.readLine();
        if (line != null) {
            System.out.println(evaluateExpression(line.trim()));
        }
    }
}