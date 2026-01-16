public class StringVsStringBuilderVsStringBuffer {
    public static void main(String[] args) {
        // Using String
        String str = "Hello";
        String str1 = "Hello"; // Creates a new String object
        System.out.println(str == str1 ? "True" : "False"); // false, different objects
        str1 = str; // Now both refer to the same object
        System.out.println(str == str1 ? "True" : "False"); // true, same object

        String str2 = new String("Hello"); // New object in heap
        System.out.println(str == str2 ? "True" : "False"); // false, different objects
        System.out.println(str.equals(str2) ? "True" : "False"); // true, same content
    
        // Using StringBuilder
        StringBuilder sb = new StringBuilder("Hello");
        StringBuilder sb1 = new StringBuilder("Hello"); // New object   
        System.out.println(sb == sb1 ? "True" : "False"); // false, different objects
        sb1 = sb; // Now both refer to the same object
        System.out.println(sb == sb1 ? "True" : "False"); // true, same object
        sb1.append(" World");
        System.out.println("StringBuilder sb: " + sb.toString()); // Hello World
        System.out.println("StringBuilder sb1: " + sb1.toString()); // Hello World

        // Using StringBuffer
        StringBuffer sbf = new StringBuffer("Hello");
        StringBuffer sbf1 = new StringBuffer("Hello"); // New object
        System.out.println(sbf == sbf1 ? "True" : "False"); // false, different objects
        sbf1 = sbf; // Now both refer to the same object
        System.out.println(sbf == sbf1 ? "True" : "False"); // true, same object
        sbf1.append(" World");
        System.out.println("StringBuffer sbf: " + sbf.toString()); // Hello World
        System.out.println("StringBuffer sbf1: " + sbf1.toString()); // Hello World

    }

}