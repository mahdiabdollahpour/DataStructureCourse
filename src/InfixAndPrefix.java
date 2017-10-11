import java.util.Stack;

/**
 * Created by ASUS on 10/10/2017.
 */

/**
 * This algorithm maintains two stacks. 1st stack for all operators and 2nd stack to store the operands.
 * 1)  Validate the Infix Expression for correctness
 * a) ‘(‘ and ‘)’ are in pairs
 * b) Operator is in between 2 operands (Binary operators are considered only)
 */
public class InfixAndPrefix {

    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!isNum(s.charAt(i))) {
                return false;
            }
        }
        return true;
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

    public static boolean isOp(String c) {
        if (c.equals("+") | c.equals("-") | c.equals("*") | c.equals("/")) {
            return true;
        }
        return false;
    }

    public static int precedense(String c) {
        if (c.equals("*") || c.equals("/")) {
            return 7;
        } else if (c.equals("+") || c.equals("-")) {
            return 3;
        }
        return 0;
    }


    public static String inToPre(String input) {
        input = input.concat(" )");
        String[] arr = input.split(" ");
        Stack<String> operatorStack = new Stack<>();
        Stack<String> numbersStack = new Stack<>();
        operatorStack.add("(");
        for (int i = 0; i < arr.length; i++) {
            String c = arr[i];

            if (c.equals("(")) {
                operatorStack.add(c);
            } else if (c.equals(")")) {

                String OPERATOR = operatorStack.pop();

                while (!OPERATOR.equals("(")) {
                    String OPERAND1 = numbersStack.pop();
                    String OPERAND2 = numbersStack.pop();
                    String newElement = OPERATOR + " " + OPERAND1 + " " + OPERAND2;
                    numbersStack.add(newElement);
                    OPERATOR = operatorStack.pop();

                }
            } else if (isOp(c)) {
                String op = operatorStack.pop();
                boolean higherOrEqualPrecedense = precedense(op) >= precedense(c);
                while (higherOrEqualPrecedense) {

                    String OPERAND1 = numbersStack.pop();
                    String OPERAND2 = numbersStack.pop();

                    String newElement = op + " " + OPERAND1 + " " + OPERAND2;
                    numbersStack.add(newElement);
                    op = operatorStack.pop();
                    higherOrEqualPrecedense = precedense(op) >= precedense(c);


                }
                operatorStack.add(op);
                operatorStack.add(c);


            } else {
                numbersStack.add(c);
            }


        }
        return numbersStack.pop();


    }

    public static String preToIn(String input) {
        String[] arr = input.split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            String c = arr[i];
            if (isNumber(c)) {
                stack.add(c);
            } else if (isOp(c)) {
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                String newElemet = " ( " + operand1 + " " + c + " " + operand2 + " ) ";
                stack.add(newElemet);
            }

        }
        return stack.pop();

    }

    public static void main(String[] args) {
        String infix = "( 2 + 3 ) * ( 8 - 4 )";
        String pre = inToPre(infix);
        System.out.println(pre);
        String in = preToIn(pre);
        System.out.println(in);
    }
}
