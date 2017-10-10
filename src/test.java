import java.util.Stack;

/**
 * Created by ASUS on 10/10/2017.
 */



public class test {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.add(1);
        s.add(2);
        while (!s.empty()){
            int a = s.pop();
            System.out.println(a);
        }


    }

}
