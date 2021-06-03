import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner= new Scanner(System.in);
        int i = scanner.nextInt();

        for(int x=0;x<i;x++){
            for( int y=x;y>=0;y--){
                System.out.print(y+" ");
            }
            for (int y=1;y<i-x;y++){
                System.out.print(y+" ");
            }
            System.out.println();
        }

    }
}