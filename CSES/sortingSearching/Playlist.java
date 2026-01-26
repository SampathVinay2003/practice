
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Playlist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int l = 0;
        long ans = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
            while(map.get(arr[i])>1){
                map.put(arr[l], map.get(arr[l])-1);
                l++;
            }
            ans = Math.max(ans, i-l+1);
        }
        System.out.println(ans);
    }
}
