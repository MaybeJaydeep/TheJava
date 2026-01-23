public class NestedTryCatch{
    public static void main(String[] args) {
        try{
        int[] arr = new int[5];
        int arrayIndex = arr[5];
        System.out.println(arrayIndex);
        try{
            int a=10,b=0;
            System.out.println((a/b));

            try{
                NestedTryCatch e = new NestedTryCatch();
                System.out.println(e);
            }catch(NullPointerException ex){
                ex.printStackTrace();
            }
        }catch(ArithmeticException ex){
            ex.printStackTrace();
        }
    }catch(Exception ex){
        ex.printStackTrace();
    }
    finally{
        System.out.println("DB connection: closed ");
    }
    }
    
}

