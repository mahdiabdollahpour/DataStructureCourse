import java.util.Stack;

/**
 * Created by ASUS on 10/10/2017.
 */
public class InfixAndPostfix {
    public static int icp(char c) {
        if (c == '(') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 3;
        } else if (c == '+' || c == '-') {
            return 5;
        }
        return 100;
    }

    public static int isp(char c) {
        if (c == '(') {
            return 7;
        } else if (c == '*' || c == '/') {
            return 3;
        } else if (c == '+' || c == '-') {
            return 5;
        }
        return 100;
    }

    public static boolean isOp(char c) {
        if (c == '(' | c == '+' | c == '-' | c == '*' | c == '/') {
            return true;
        }
        return false;
    }

    public static boolean isNum(char c) {
        int ascii = (int) c;
        if (ascii < 48) {
            return false;
        }
        if (ascii > 57) {
            return false;
        }
        return true;
    }

    public static void printOp(char c) {
        if (c != '(') {
            System.out.print(c);
        }
    }

    public static void infixToPostfix(String input) {


        Stack<Character> s = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isNum(c)) {
                System.out.print(c);
            } else if (isOp(c)) {
                if (s.empty()) {
                    s.add(c);
                    continue;
                }
                char in = s.pop();
                if (isp(in) >= icp(c)) {// c ola tar ast
                    s.add(in);
                    s.add(c);
                } else {
                    printOp(in);
                    while (isp(in) < icp(c) && !s.empty()) {
                        in = s.pop();
                        printOp(in);
                    }
                    s.add(c);
                }
            } else if (c == ')') {
                while (!s.empty()) {
                    char a = s.pop();
                    printOp(a);
                }
            }


        }
        while (!s.empty()) {
            char a = s.pop();
            printOp(a);
        }


    }

    public static String postfixToInfix(String input) {
        Stack<String> s = new Stack();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isNum(c)) {
                s.add(Character.toString(c));
            } else if (isOp(c)) {
                String a = s.pop();
                String b = s.pop();
                String result = "(" + b + c + a + ")";
                s.add(result);
            }


        }
        String res = s.pop();
        return res;
    }


    public static void main(String[] args) {
       // infixToPostfix("(2-5)*7+(3/8)");
        System.out.println(postfixToInfix("23+45-*"));
    }

}
