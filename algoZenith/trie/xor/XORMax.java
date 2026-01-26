package algoZenith.trie.xor;

import algoZenith.trie.IntTrie;
import algoZenith.trie.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class XORMax {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = scanner.nextInt();
        IntTrie trie = new IntTrie(0);
        trie.add(0); // Add initial 0 as specified in problem
//        Parent parent = new Parent();
//        parent.parentMethod();
//        parent.childMethod();
//        Child child = new Child();
//        child.parentMethod();
//        child.childMethod();
//        Parent child2 = new Child();
//        child2.parentMethod();
//        child2.childMethod();
//        Child child3 = (Child) child2;
//        child3.childMethod();
//        child3.parentMethod();
//        Parent parent2 = new Child();
//        Child childx = (Child) parent2;
//        childx.childMethod();
//        childx.parentMethod();
//        Child child4 = new Parent();
//        child4.childMethod();
//        Child2 child5 = new Child2();
//        child5.child2Method();
//        child5.parentMethod();
//        Parent child6 = (Parent) child5;
//        Child x = (Child) child6;
//        x.childMethod();
//        x.parentMethod();

        while(t-- > 0){
            String string = bufferedReader.readLine();
            char prefix = string.charAt(0);
            Integer number = Integer.valueOf(string.charAt(2));
            if(prefix == '?'){
                System.out.println(trie.getMax(number));
            }else if(prefix == '-'){
                trie.remove(number);
            }else{
                trie.add(number);
            }
        }
    }

    private static class Parent{
        void parentMethod(){

        }

    }
    private  static class Child extends  Parent{
        void childMethod(){

        }
    }
    private static class Child2 extends Parent{
        void child2Method(){

        }
    }

}
