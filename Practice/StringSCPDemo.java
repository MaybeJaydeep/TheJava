public class StringSCPDemo {
    public static void main(String[] args) {
        String a = "Java";
        String b = "Java";
        String c = new String("Java");

        System.out.println(a == b); // true (SCP)
        System.out.println(a == c); // false (Heap vs SCP)
        System.out.println(a.equals(c)); // true (content)
    }
}