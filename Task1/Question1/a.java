package Question1;

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

        if(n%2==0)
            return power2(a, n/2) * power2(a, n/2);
        
        return a * power2(a, n/2) * power2(a, n/2);            
    }

    public static void main(String[] args){
        System.out.println(power1(5, 2836));
        System.out.println(power2(5, 2836));
    }
}