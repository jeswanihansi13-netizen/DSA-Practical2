import java.util.Stack;
import java.util.Scanner;

public class InfixToPostfix {

    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            // If operand, add it to output
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            }

            // If opening bracket, push it
            else if (ch == '(') {
                stack.push(ch);
            }

            // If closing bracket, pop until '('
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }

                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            }

            // If operator
            else {
                while (!stack.isEmpty() &&
                       precedence(ch) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter infix expression: ");
        String expression = sc.nextLine();

        String postfix = infixToPostfix(expression);

        System.out.println("Postfix expression: " + postfix);

        sc.close();
    }
}