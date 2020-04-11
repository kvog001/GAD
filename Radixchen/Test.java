package Radixchen;

public class Test {
    public static void main(String arg[]) {
        Integer[] elements = new Integer[]{1, 33, 34, 660, 809, 99, 101, 8989, 20};

        try {
            System.out.println("bad: "+ key2(958,2));
            System.out.println( "correct: "+key(958,2) );

            System.out.println("bad: "+ key2(105038915,5));
            System.out.println("correct: "+ key(105038915,5));

            System.out.println( "correct: "+key(1050,5) );
            System.out.println("bad: "+ key2(1050,5));
        }catch (StringIndexOutOfBoundsException siob){

        }


    }

    public static int key(Integer element, int digit) throws IllegalArgumentException {
        if(element < 0 || digit < 0) throw new IllegalArgumentException();
        String elemString = element.toString();
        if(digit >= elemString.length()) return 0;  //digit is an index from the right
        int index = elemString.length() - digit;
        return Integer.parseInt(elemString.substring(index-1, index));
    }

    private static int key2(Integer element, int decimalPlace) throws IllegalArgumentException {
        // TODO
        if(element < 0){
            throw new IllegalArgumentException("Negative numbers not allowed!");
        }

        String el = Integer.toString(element);
        char result = el.charAt(el.length()-decimalPlace-1);
        return Character.getNumericValue(result);
    }

}
