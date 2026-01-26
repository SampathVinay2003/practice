package LeetCode.dp.gameDP;

import java.util.Arrays;

public class StoneGame8 {
    public  static void main(String[] args) {
        int[] arr = {5,3,1,4,2};
        System.out.println(stoneGameVII(arr));
    }
    public static int stoneGameVII(int[] nums) {
        int n = nums.length;
        int [] prefix = new int[n+1];
        int[][] dp = new int[n][n];
        for(int[] d: dp) Arrays.fill(d, -1);
        for(int i=0;i<nums.length;i++){
            prefix[i+1] = prefix[i]+nums[i];
        }
        return solve(0, n-1,nums, dp, prefix);
    }
    public static int solve(int l, int r, int[] arr,int[][] dp, int[] prefix){
        if(l==r)return 0;
        if(dp[l][r]!=-1)return dp[l][r];
        int left = prefix[r+1]-prefix[l+1]-solve(l+1, r, arr, dp, prefix);
        int right = prefix[r]-prefix[l]-solve(l, r-1, arr, dp, prefix);
        return dp[l][r] = Math.max(left, right);
    }

}
