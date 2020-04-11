package dht;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestKlasse {
    private static int tableSize = 269;
    public static void main(String args[]){
        int primZahl = 17;
        double logValue = (Math.log(primZahl) / Math.log(2));
        int roundedLog = (int) Math.floor(logValue);
////        System.out.println((Math.log(269) / Math.log(2)));
        System.out.println(roundedLog);
//        String key = "ola";
//        System.out.println(key.length());
//        System.out.println((int)Math.pow(2,8)-1);
//        System.out.println(4%3);


//        System.out.println(hash("01234567890123456789012345678912"));
//        Random r = new Random(349587);
//        System.out.println(r.nextInt(10) + 5);
//        System.out.println(r.nextInt(10) + 5);

    }

    public static int hash(String key) {
        /*
         * Todo
         */

        System.out.println(key.length()+"  ..");
        int keyLength = key.length();
        int w = log2(tableSize);
        int k = keyLength / w;
        ArrayList<Integer> randomKeyValues = new ArrayList<>();
        ArrayList<Integer> randomHashValues = new ArrayList<>();

        int keyLimit = (int)Math.pow(2,w)-1;
        int hashLimit = tableSize-1;
//        for (int i=0; i < k; i++){
//            int element = getRandomNumber(keyLimit);
//            randomKeyValues.add(i,element);
//        }
//
//        for (int i=0; i < k; i++){
//            int element = getRandomNumber(hashLimit);
//            randomHashValues.add(i,element);
//        }

        randomKeyValues.add(0,11);
        randomKeyValues.add(1,7);
        randomKeyValues.add(2,4);
        randomKeyValues.add(3,3);

        randomHashValues.add(0,2);
        randomHashValues.add(1,4);
        randomHashValues.add(2,261);
        randomHashValues.add(3,16);

        int sum =0;
        for (int i=0; i < k; i++){
            sum += randomKeyValues.get(i) * randomHashValues.get(i);
        }

        return sum % tableSize;
    }

    private static int log2(int m){
        double logValue = (Math.log(m) / Math.log(2));
        int roundedLog = (int) Math.floor(logValue);
        return roundedLog;
    }

    private static int getRandomNumber(int limit){
        int randomNum = ThreadLocalRandom.current().nextInt(0, limit + 1);
        return randomNum;
    }
}
