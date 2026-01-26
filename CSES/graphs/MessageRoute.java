
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MessageRoute {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[] parent = new int[n];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++)adj.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            adj.get(u-1).add(v-1);
            adj.get(v-1).add(u-1);
        }
        Queue<Integer>q = new LinkedList<>();
        q.add(0);
        Arrays.fill(parent, -1);
        parent[0] = -2;
        boolean flag = false;
        while(!q.isEmpty()){
            int front = q.poll();
            if(front == n-1) {
                flag = true;
                break;
            }
            for(int v:adj.get(front)){
                if(parent[v]==-1){
                    parent[v] = front;
                    q.add(v);
                }
            }
        }
        if(flag){
            ArrayList<Integer> arr = new ArrayList<>();
            int c = n-1;
            while(parent[c]!=-2){
                arr.add(c);
                c = parent[c];
            }
            arr.add(0);
            Collections.reverse(arr);
            System.out.println(arr.size());
            for(int v:arr){
                System.out.print(v+1+" ");
            }
        }else{
            System.out.println("IMPOSSIBLE");
        }
    }
}
