import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();


        boolean isReached = true;
        int i = 0;

        do{
            Random rand = new Random(k);
            isReached=true;

//            System.out.println("i = " + i + ", isReached = " + isReached);

            for(int counter = 0; counter<n; counter++){

//                System.out.println("counter = " + counter);

                double dnum = rand.nextGaussian();
//                System.out.println("dnum = " + dnum);

                if(dnum>m){
//                    System.out.println("Enter to if");
                    isReached=false;
                    break;
                }
            }
            if(isReached){
//                System.out.println("hello");
//                System.out.println("k+i = " + a + ", isReached = " + isReached);
                System.out.println(k);
            }else{
                k++;
            }
        }while(!isReached);


        scanner.close();
    }
}