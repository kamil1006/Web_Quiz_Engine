import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner= new Scanner(System.in);
        int k=-1;
        List<Integer> lista= new ArrayList<>();
        while (k!=0){

            k= scanner.nextInt();
            if(k!=0){
                lista.add(k);
            }

        }

        for(int i:lista){
            System.out.println(i%2==0 ? "even":"odd");
        }


    }
}