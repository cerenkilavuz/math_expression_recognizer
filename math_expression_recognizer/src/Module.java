import java.util.Scanner;
import java.util.Stack;

public class Module {

    public static boolean isValidExpression(String expression) {
        String[] tokens = expression.replaceAll("\\s+", "").split("");
        Stack<Character> stack = new Stack<>();
        boolean operandExpected = true;

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            char ch = token.charAt(0);

            if (Character.isDigit(ch) || ch == 'v' || ch == 'c') {
                operandExpected = false;
            } 
            else if (ch == '(') {
                stack.push(ch);
                operandExpected = true;
            } 
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    return false; 
                }
                stack.pop();
                operandExpected = false;
            } 
            else if ("+-*/".indexOf(ch) != -1) {
                if (operandExpected) {
                    return false; 
                }
                if (ch == '/' && i+1 < tokens.length && tokens[i+1].equals("0")) {
                    return false;
                }
                stack.push(ch);
                operandExpected = true;
            } 
            else {
                return false; 
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return false; 
            }
            stack.pop();
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Lütfen bir matematiksel ifade girin (Çıkmak için 'q' tuşuna basın): ");
            String ifade = scanner.nextLine();
            
            	if (ifade.equalsIgnoreCase("q")) {
                System.out.println("Programdan çıkılıyor...");
                break;
            }

            if (isValidExpression(ifade)) {
                System.out.println(ifade + " Çıktı: Girilen ifade matematiksel bir ifadedir.");
                System.out.println();
            } else {
                System.out.println(ifade + " Çıktı: Girilen ifade matematiksel bir ifade değildir.");
                System.out.println();
            }
        }

        scanner.close();
    }
}