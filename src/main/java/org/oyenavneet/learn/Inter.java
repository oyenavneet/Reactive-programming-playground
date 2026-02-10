package org.oyenavneet.learn;

import java.util.Scanner;

public class Inter {

    public static long gcd(long a, long b){
        while(b!=0){
            long temp =b;
            b=a%b;
            a=temp;
        }
        return a;
    }

    public static long lcm(long a, long b){
        return (a/gcd(b,a))*b;
    }

    public static long lcmOfArray(int[] arr){
        long result = arr[0];
        for (int i=1;i<arr.length;i++){
            result=lcm(result,arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {


        System.out.println("Enter a string");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strings = str.split(" ");
        StringBuilder sb2 = new StringBuilder();
        for(int i = strings.length-1; i>=0; i--){
            sb2.append(strings[i]).append(" ");
        }

        System.out.println(sb2.toString());


    }
}
