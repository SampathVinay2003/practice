
import java.util.Scanner;

public class IncArray {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        long ans = 0;
        for(int i=0;i<n;i++){
            arr[i] = sc.nextLong();
            if(i>0){
                if(arr[i]<arr[i-1]){
                    long s = arr[i-1]-arr[i];
                    ans+= s;
                    arr[i] += s;
                }
            }
        }
        System.out.println(ans);
    }
}
