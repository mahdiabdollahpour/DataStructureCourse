import java.util.Stack;

/**
 * Created by ASUS on 10/10/2017.
 */
public class InfixAndPrefix {
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

    public static boolean isOp(char c) {
        if (c == '+' | c == '-' | c == '*' | c == '/') {
            return true;
        }
        return false;
    }

    public static int precedense(char c) {
        if (c == '*' || c == '/') {
            return 3;
        } else if (c == '+' || c == '-') {
            return 5;
        }
        return 100;
    }
//
//
//    public static void infixToPrefix(String input) {
//        Stack<Character> s = new Stack<>();
//        // s.add(')');
//        //   input = input.concat("(");
//        for (int i = input.length() - 1; i >= 0; i--) {
//            char c = input.charAt(i);
//            if (isNum(c)) {
//                System.out.print(c);
//            }
////            else if (c == ')') {
////                s.add(c);
////            }
//            else if (isOp(c)) {
//                if (!s.empty()) {
//                    char temp = s.pop();
//                    while (precedense(temp) >= precedense(c) && !s.empty()) {
//                        System.out.println(temp);
//                        temp = s.pop();
//                    }
//                    s.add(temp);
//                }
//                s.add(c);
//            }
////            else if (c == '(') {
////                char temp = s.pop();
////                while (temp != '(' && !s.empty()) {
////                    System.out.println(temp);
////                    temp = s.pop();
////                }
////            }
//
//
//        }
//        while (!s.empty()) {
//            char a = s.pop();
//            System.out.print(a);
//        }
//
//
//    }

    public static String reverse(String input) {
        StringBuilder sb = new StringBuilder(input.length());
        for (int i = input.length() - 1; i >= 0; i--) {
            sb.append(input.charAt(i));
        }
        return sb.toString();
    }

    public static String infixToPreReversing(String input) {
        String reversed = reverse(input);

        String postfix = InfixAndPostfix.infixToPostfix(reversed);

        return reverse(postfix);

    }


    public static void main(String[] args) {

        System.out.println(infixToPreReversing("2+3*4"));

    }
}
