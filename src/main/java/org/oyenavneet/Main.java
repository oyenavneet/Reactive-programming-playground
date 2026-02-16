package org.oyenavneet;

import org.oyenavneet.sec01.publisher.PublisherImpl;
import org.oyenavneet.sec01.subscriber.SubscriberImpl;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        demo4();


        //Check palindrome (string / number)
        int num=121;

        int a=num;
        int b=0;

        while (num>0){
            int r=num%10;
            b=b*10+r;
            num=num/10;
        }
        System.out.println(a==b);


//        String s="madam";
//
//        int left=0, right=s.length()-1;
//        while (left<right) {
//            if(s.charAt(left)!=s.charAt(right)) {
//                System.out.println(false);
//                return;
//            }
//            left++;
//            right--;
//        }
//        System.out.println(true);


        //Find second largest element in an array
//        int[] arr = {10, 5, 20, 8, 20};
//
//        if(arr.length <2){
//            System.out.println("no second number");
//            return;
//        }
//        int largest = Integer.MAX_VALUE;
//        int secondLargest = Integer.MIN_VALUE;
//
//        for(int num : arr){
//            if(num>largest){
//                secondLargest= largest;
//                largest = num;
//            }else if(num>secondLargest && num!=largest){
//                secondLargest=num;
//            }
//        }
//        if(secondLargest==Integer.MAX_VALUE){
//            System.out.println("no second number");
//        }else {
//            System.out.println("second largest number: " + secondLargest);
//        }



        //Count frequency of characters in a string
//        String s = "programming";
//        Map<Character, Integer> map = new HashMap<>();
//        for(char c:s.toCharArray()){
//            map.put(c, map.getOrDefault(c,0)+1);
//        }
//        System.out.println(map);



        //Find missing number in an array
//        int[] numbers = {1,2,4,5,6,7,8,9,10};
//        int size=10;
//
//        int expectedSum = size*(size+1)/2;
//        int actualSum = 0;
//        for(int n:numbers){
//            actualSum+=n;
//        }
//        System.out.println(expectedSum-actualSum);


        //Find first non-repeating character in a string

//        String s="navneet";
//
//        int[] freq = new int[256];
//        for(char c:s.toCharArray()){
//            freq[c]++;
//        }
//
//        for(char c:s.toCharArray()){
//            if(freq[c]==1){
//                System.out.println(c);
//                return;
//            }
//        }

        //using HashMap
//        Map<Character , Integer> map=new HashMap<>();
//
//        for(char c:s.toCharArray()){
//            map.put(c,map.getOrDefault(c,0)+1);
//        }
//
//        for(Map.Entry<Character, Integer> entry : map.entrySet()){
//            if(entry.getValue()==1){
//                System.out.println(entry.getKey());
//                break;
//            }
//        }



        //Check if two strings are anagrams
//        String s1 = "testen";
//        String s2 = "silent";
//
//        if(s1.length()!=s2.length()){
//            System.out.println(false);
//        }
//
//        Map<Character, Integer> map = new HashMap<>();
//
//        for(char c : s1.toCharArray()){
//            map.put(c,map.getOrDefault(c,0)+1);
//        }
//
//        for(char c : s2.toCharArray()){
//            if(!map.containsKey(c)){
//                System.out.println(false);
//                return;
//            }
//
//            map.put(c,map.get(c) - 1);
//            if(map.get(c)==0){
//                map.remove(c);
//            }
//        }
//        System.out.println(map.isEmpty());



        //using sort and Arrays equals
//        String s1 = "listen";
//        String s2 = "silent";
//
//        char[] chars1 = s1.toCharArray();
//        char[] chars2 = s2.toCharArray();
//        Arrays.sort(chars1);
//        Arrays.sort(chars2);
//        System.out.println(Arrays.equals(chars1, chars2));




        //Reverse Words in a Sentence
//        String string = "navneet is my name";
//        String[] strings = string.split(" ");
//        StringBuilder builder =new StringBuilder();
//
//        for(int i=strings.length-1;i>=0;i--){
//            builder.append(strings[i]).append(" ");
//        }
//        System.out.println(builder.toString());



        //Reverse a string / reverse words in a sentence
//        String string = "navneet is my name";

        //using loop
//        String rev = "";
//
//        for(int i=string.length()-1;i>=0;i--){
//            rev = rev + string.charAt(i);
//        }
//        System.out.println(rev);


        //using string builder
//        String reversed = new StringBuilder(string).reverse().toString();
//        System.out.println(reversed);


        //Remove duplicates from array / list
//        int[] arr102 = {1,1,4,5,6,8,6,7,6,7,9,9};

//        Set<Integer> set = new HashSet<>();
//        for(int num : arr102){
//            set.add(num);
//        }
//        int[] result =new int[set.size()];
//        int index=0;
//        for(Integer num : set){
//            result[index++]=num;
//        }
//        System.out.println(Arrays.toString(result));


        //Find duplicate elements in an array
//        int[] arr101 = {1,1,4,5,6,8,6,7,6,7,9,9};
        //hashset
//        Set<Integer> seen = new HashSet<>();
//        Set<Integer> duplicate = new HashSet<>();
//        for(int num : arr101){
//            if(!seen.add(num)){
//                duplicate.add(num);
//            }
//        }
//        System.out.println("dplicate element:"+ duplicate);


        //using Map
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int num : arr101){
//            map.put(num, map.getOrDefault(num,0)+1);
//        }
//
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
//            if(entry.getValue()>1){
//                System.out.println("Duplicate :"+entry.getKey());
//            }
//        }


//brut force
//        for(int i=0;i<arr101.length;i++){
//            for(int j=i+1;j<arr101.length;j++){
//                if(arr101[i]==arr101[j]){
//                    System.out.println("duplicate"+arr101[i]);
//                    break;
//                }
//            }
//        }


        //merge two sorted array
//        int arr1[] = {1, 2,2, 4, 5, 6};
//        int arr2[] = {3,4,5,6,9,9,10,11,13};
//        List<Integer> list = new ArrayList<>();
//
//        int f=0;
//        int s=0;
//        while(f<arr1.length && f<arr2.length){
//            if(arr1[f]<=arr2[s]){
//                list.add(arr1[f]);
//                f++;
//            }else {
//                list.add(arr2[s]);
//                s++;
//            }
//        }
//        while (f<arr1.length){
//            list.add(arr1[f]);
//            f++;
//        }
//
//        while (s<arr2.length){
//            list.add(arr2[s]);
//            s++;
//        }
//        list.forEach(System.out::print);





    }

    //Publisher does not produce data unless subscriber request for it.
    private static void demo1(){
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }
    //publisher will only produce <= subscriber requested items. publisher can also produce 0 items
    private static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);

    }

    //subscriber can cancel the subscription . producer should stop at that moment as subscriber is no longer interested in consuming data
    private static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }
    //producer can send the error signal to indicate something is wrong
    private static void demo4() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }
}