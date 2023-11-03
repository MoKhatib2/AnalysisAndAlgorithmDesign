package Question1;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class a{

    public static double power1(double a, int n){
        double result = 1;
        for(int i = 1; i<=n; i++){
            result*=a;
        }
        return result;
    }

    public static double power2(double a, int n){
        if(n==0)
            return 1;

        if(n==1)
            return a;

        if(n%2==0){
                double temp = power2(a, n/2);
                return temp * temp;
        }

        double temp = power2(a, n/2);    
        return a * temp * temp;            
    }

    public static void saveToCSV(String filename, int[] values, double[] data) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i<data.length; i++) {
                writer.append(values[i] + ",");
                writer.append("" + data[i]);
                writer.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        // int[] n = new int[50];
        // int factor = 20000;
        // for (int i = 0; i <50; i++) {
        //     n[i] = factor;
        //     factor+=20000;
        // }

        // double[] iterativeTimes = new double[50];
        // double[] divideConquerTimes = new double[50];

        // for (int i = 0; i < 50; i++) {
        //     int a = 2;  

        //     long startTime = System.nanoTime();
        //     power1(a, n[i]);
        //     long endTime = System.nanoTime();
        //     iterativeTimes[i] = (endTime - startTime)/1e9;

        //     startTime = System.nanoTime();
        //     power2(a, n[i]);
        //     endTime = System.nanoTime();
        //     divideConquerTimes[i] = (endTime - startTime)/1e9;  
        // }

        // saveToCSV("iterative_times.csv", n, iterativeTimes);
        // saveToCSV("divide_conquer_times.csv", n, divideConquerTimes);
     

    System.out.println(power1(2,10));
    System.out.println(power2(2,10));
    } 

}
