package LeetCode.dp;

import java.util.HashMap;

public class DiffPalindromicSubSeq {
    public  static void main(String[] args){
        String s = "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba";
        System.out.println(countPalindromicSubsequence(s));
    }
    public static int countPalindromicSubsequence(String s) {
        HashMap<String, Boolean> map = new HashMap<>();
        return solve(0, s, s.length(), "", map);
    }
    public static int solve(int ind, String s, int n, String temp, HashMap<String, Boolean> map){
        if(ind == n){
            if(!temp.isEmpty() && isPalindrome(temp)){
                if(map.containsKey(temp)){
                    return 0;
                }else{
                    map.put(temp, true);
                    return 1;
                }
            }else{
                return 0;
            }
        }
        return solve(ind+1, s, n, temp+s.charAt(ind), map)+ solve(ind+1, s, n, temp, map);
    }
    public static boolean isPalindrome(String s){
        int i = 0;
        int j = s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j))return false;
            i++;
            j--;
        }
        return true;
    }
}
