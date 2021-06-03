import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        int[] tablica=new int[n];

        for (int i=0;i<n;i++){
            tablica[i]=i;

        }

        for (int i=0;i<n;i++){
           int comp=0;
            System.out.print( jumpSearch(tablica,tablica[i],comp)+" ");

        }


    }

    public static int jumpSearch(int[] array, int target,int comp) {
        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (array.length == 0) {
            return -1;
        }

        /* Check the first element */
        if (array[currentRight] == target) {
           comp++;
           // return 0;
            return comp;
        }
        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.length);
        /* Finding a block where the element may be present */
        while (currentRight < array.length - 1) {
            comp++;
            /* Calculating the right border of the following block */
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);
            if (array[currentRight] >= target) {

                break; // Found a block that may contain the target element
            }
            prevRight = currentRight; // update the previous right block border
        }
        /* If the last block is reached and it cannot contain the target value => not found */
        if ((currentRight == array.length - 1) && target > array[currentRight]) {
            return -1;
        }
        /* Doing linear search in the found block */
        return backwardSearch(array, target, prevRight, currentRight, comp);
    }

    public static int backwardSearch(int[] array, int target, int leftExcl, int rightIncl, int comp) {
        for (int i = rightIncl; i > leftExcl; i--) {
            comp++;
            if (array[i] == target) {
                //return i;
                return comp;
            }
        }
        return -1;
    }

}