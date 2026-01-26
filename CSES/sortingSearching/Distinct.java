package CSES.sortingSearching;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Distinct {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<Long> set = new HashSet<>();
        while(n-->0){
            set.add(sc.nextLong());
        }
        System.out.println(set.size());
    }
}
