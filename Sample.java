import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Sample {
    public  static void main(String[] args) {
        System.out.println(checkValidString("(*))"));
    }
    public static boolean checkValidString(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<>();
        return solve(s, 0, n, st);
    }
    public static boolean solve(String s, int i, int j, Stack<Character> st){
        if(i >= j){
            if(st.isEmpty())return true;
            return false;
        }
        char ch = s.charAt(i);
        if(ch == '*'){
            return solve(s, i, s.length(), st) || solve('('+s, i, j, st) || solve(')'+s, i, j, st);
        }
        if(!st.isEmpty() && ch == ')' && st.peek() == '(')st.pop();
        else st.push(ch);
        return solve(s, i+1, s.length(), st);
    }
}
