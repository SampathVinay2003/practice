
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Towers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> topEle = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            if (topEle.isEmpty()) {
                topEle.add(num);
                ans.add(new ArrayList<>());
                ans.get(0).add(num);
            } else {
                int ind = Collections.binarySearch(topEle, num);
                if (ind < 0) ind = -(ind + 1);
                while (ind < topEle.size() && topEle.get(ind) <= num) ind++;

                if (ind == topEle.size()) {
                    // start a new tower
                    topEle.add(num);
                    ArrayList<Integer> newTower = new ArrayList<>();
                    newTower.add(num);
                    ans.add(newTower);
                } else {
                    // place on that tower: update top
                    topEle.set(ind, num);
                    // append to the tower (no need to insert at front)
                    ans.get(ind).add(num);
                }

            }
        }
        System.out.println(ans.size());
    }
}
