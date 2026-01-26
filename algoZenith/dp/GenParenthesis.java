package algoZenith.dp;

import java.util.*;
import  java.io.*;


public class GenParenthesis {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while(t-->0){
            int n = scanner.nextInt();
            solve(n, "", n/2, n/2);
        }
    }
    public static void solve(int n, String temp, int open, int close){
        //pruning
        if(open < 0 || close < 0 || open > close) return;
        if(n == 0){
            System.out.println(temp);
            return;
        }
        solve(n-1, temp+"(", open-1, close);
        solve(n-1, temp+")", open, close-1);
    }
}
