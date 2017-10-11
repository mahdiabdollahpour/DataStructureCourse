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
            return 7;
        } else if (c == '+' || c == '-') {
            return 3;
        }
        return 0;
    }


    public static String inToPre(String input) {
        Stack<Character> operatorStack = new Stack<>();
        Stack<String> numbersStack = new Stack<>();
        operatorStack.add('(');
        input = input.concat(")");
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);


            if (c == '(') {
                operatorStack.add(c);
            } else if (c == ')') {

                char OPERATOR = operatorStack.pop();

                while (OPERATOR != '(') {
                    String OPERAND1 = numbersStack.pop();
                    String OPERAND2 = numbersStack.pop();
                    String newElement = Character.toString(OPERATOR).concat(OPERAND1).concat(OPERAND2);
                    numbersStack.add(newElement);
                    OPERATOR = operatorStack.pop();

                }
            } else if (isOp(c)) {
                char op = operatorStack.pop();
                boolean higherOrEqualPrecedense = precedense(op) >= precedense(c);
                while (higherOrEqualPrecedense) {

                    String OPERAND1 = numbersStack.pop();
                    String OPERAND2 = numbersStack.pop();
                    String newElement = Character.toString(op).concat(OPERAND1).concat(OPERAND2);
                    numbersStack.add(newElement);
                    op = operatorStack.pop();
                    higherOrEqualPrecedense = precedense(op) >= precedense(c);


                }
                operatorStack.add(op);
                operatorStack.add(c);


            } else {
                numbersStack.add(Character.toString(c));
            }


        }
        return numbersStack.pop();


    }


    public static void main(String[] args) {

        //    System.out.println(infixToPreReversing("((3+4)*(6+7))/9"));
        System.out.println(inToPre("(2+3)*(5-1)"));
    }
}
