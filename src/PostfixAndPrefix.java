import java.util.Stack;

/**
 * Created by ASUS on 11/10/2017.
 */
public class PostfixAndPrefix {
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


    public static String postToPre(String input) {
        String[] arr = input.split(" ");

        Stack<String> s = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            String c = arr[i];
            //          System.out.print(c);
            if (isNumber(c)) {
                s.add(c);
            } else if (isOp(c)) {
                String operand1 = s.pop();
                String operand2 = s.pop();
                String newElement = c + " " + operand2 + " " + operand1;
                s.add(newElement);
            }
        }
//        System.out.println();
        return s.pop();


    }

    public static String preToPost(String input) {
        String[] arr = input.split(" ");

        Stack<String> s = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            String c = arr[i];
            //     System.out.print(c);
            if (isNumber(c)) {
                s.add(c);
            } else if (isOp(c)) {
                String operand1 = s.pop();
                String operand2 = s.pop();
                String newElement = operand1 + " " + operand2 + " " + c;
                s.add(newElement);
            }
        }
        //     System.out.println();
        return s.pop();


    }


    public static void main(String[] args) {
        String pre = "* + 3 2 - 8 4 ";
        String post = preToPost(pre);
        System.out.println(post);
        String preback = postToPre(post);
        System.out.println(preback);

    }


}
