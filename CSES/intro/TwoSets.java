
import java.util.ArrayList;
import java.util.Scanner;

public class TwoSets {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long sum = (n*(n+1))/2;
        if(sum%2 != 0){
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
        ArrayList<Integer> set1 = new ArrayList<>();//6 5 3
        ArrayList<Integer> set2 = new ArrayList<>();//7 4 2
        int i = n;
        boolean flag = true;
        while(i!=0){
            if(flag){
                set2.add(i--);
                if(i!=0)set1.add(i--);
                flag = false;
            }else{
                set1.add(i--);
                if(i != 0)set2.add(i--);
                flag = true;
            }
        }
        System.out.println(set1.size());
        for(int a:set1){
            System.out.print(a+" ");
        }
        System.out.println();
        System.out.println(set2.size());
        for(int a:set2){
            System.out.print(a+" ");
        }
    }
}
