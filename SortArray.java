import java.util.*;

public class SortArray {
    public static void main(String[] args) {
        Integer [] input = new Integer[] {1,4,6,7,8,2};
        sortMyArray(input);
        for(int i = 0;i < input.length;i++ ){
            System.out.print(input[i] + " ");
        }


    }
    public  static void  sortMyArray(Integer [] input) {
        //TODO Add your implementation here
        int len = input.length;
        ArrayList<Integer> ll = new ArrayList<Integer>();
        ArrayList<Integer> lr = new ArrayList<Integer>();
        ArrayList<Integer> lmain = new ArrayList<Integer>();
        Arrays.sort(input);
        ll.add(input[len-1]);
        lr.add(input[len-2]);
        int sl = input[len-1];
        int sr = input[len-2];
        for(int i = len-3; i >= 0;i--){
            if(sl>sr){
                lr.add(input[i]);
                sr = sr + input[i];

            }
            else{
                ll.add(input[i]);
                sl = sl + input[i];

            }
        }
        if(lr.size() > ll.size()){
            lmain.addAll(lr);
            lmain.addAll(ll);
        }
        else{
            lmain.addAll(ll);
            lmain.addAll(lr);
        }
        sl = 0;
        sr = 0;
        for(int i = 0; i < input.length/2;i++){
            sl = sl + lmain.get(i);
            input[i] = lmain.get(i);
            sr = sr + lmain.get(len - i - 1);
            input[len - i - 1] = lmain.get(len - i - 1);
        }
        System.out.println("Sum of left half is " + sl);
        System.out.println("Sum of Right half is " + sr);
        System.out.println();
    }
}
