public class PrimeFactors {
    public static void main(String[] args) {
        computePrimeFactors(29);
    }
    public static void computePrimeFactors(int x) {
        //TODO Add your implementation here
        if(x%2 == 0){
            int t = x - 1;
            while(true){
                if(isPrime(t) && isPrime(x-t)){
                    System.out.println(t + " + " + (x-t) );
                    break;
                }
                else{
                    t = t-1;
                }
            }
        }
        else{
            if(isPrime(x-2)) {
                System.out.println((x - 2) + " + 2");
            }
            else{
                int t = x - 2;
                while(true){
                    if(isPrime(t) && isPrime(x-t-1)){
                        System.out.println(t + " + " + (x-t-1) + " + 1"  );
                        break;
                    }
                    else{
                        t = t-1;
                    }
                }
            }
        }
        return;
    }

    static  boolean isPrime(int num)
    {
        if(num == 1)
        {
            return true;
        }
        for(int i=2;i<=num/2;i++)
           {
            if((num%i)==0)
                return  false;
        }
        return true;
    }


}


