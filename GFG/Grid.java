package GFG;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Grid {
    public static void main(String[] args){
        int[][] grid = {{0,1,1,0},{1,1,0,0},{0,0,1,1}};
        nearest(grid);
    }
    public static ArrayList<ArrayList<Integer>> nearest(int[][] grid) {
        int m = grid.length;
        int n = 0;
        if (m > 0) {
            n = grid[0].length;
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        //int[][] copyOfGrid = new int[m][n];
        if(n>0){
            Queue<int[]> q = new LinkedList<>();
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    //copyOfGrid[i][j] = grid[i][j];
                    if(grid[i][j] == 1){
                        q.add(new int[]{i,j});
                    }
                }
            }
            int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
            while(!q.isEmpty()){
                int[] front = q.poll();
                int i = front[0];
                int j = front[1];
                for(int[] d:dir){
                    int r = i+d[0];
                    int c = j+d[1];
                    if(r<m && r>=0 && c<n && c>=0 && (grid[r][c] == 0 || grid[r][c]>grid[i][j]+1) ){
                        grid[r][c] = grid[i][j]+1;
                        q.add(new int[]{r, c});
                    }
                }
            }

            for(int i=0;i<m;i++){
                ArrayList<Integer> ass = new ArrayList<>();
                for(int j=0;j<n;j++){
                    ass.add(grid[i][j]-1);
                }
                ans.add(ass);
            }
        }
        return ans;
    }
}