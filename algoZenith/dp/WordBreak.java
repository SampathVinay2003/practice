package algoZenith.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordBreak {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            String s = scanner.next();
            List<String> dict = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                dict.add(scanner.next());
            }
            Map<String, Boolean> map = new HashMap<>();
            System.out.println(solve(s, dict, map) ? "Yes" : "No");
        }
    }

    public static Boolean solve(String s, List<String> dict, Map<String, Boolean> visited) {
        if (s.equals("")) return true;
        if (visited.containsKey(s)) {
            return visited.get(s);
        }
        for (int i = 1; i <= s.length(); i++) {
            String left = s.substring(0, i);
            if (dict.contains(left)) {
                boolean k = solve(s.substring(i), dict, visited);
                if(k) {
                    visited.put(s, true);
                    return true;
                }
            }
        }
        visited.put(s, false);
        return false;
    }
}
