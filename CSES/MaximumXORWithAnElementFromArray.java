package CSES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MaximumXORWithAnElementFromArray {
        static int[] basis = new int[32];
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = sc.nextInt();
                for(int bit =31;bit>=0;bit--){
                    if((arr[i] & (1<<bit)) != 0){
                        if(basis[bit] == 0){
                            basis[bit] = arr[i];
                            break;
                        }
                        arr[i] ^= basis[bit];
                    }
                }
            }
            int k = 0;
            for(int a:basis){
                if(a!=0)k++;
            }
            System.out.println(1L<<k);

        }
    }
