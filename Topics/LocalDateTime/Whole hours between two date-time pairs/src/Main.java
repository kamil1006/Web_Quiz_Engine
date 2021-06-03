import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner= new Scanner(System.in);
        String line1= scanner.nextLine();
        String line2= scanner.nextLine();

        LocalDateTime dt1 = LocalDateTime.parse(line1);
        LocalDateTime dt2 = LocalDateTime.parse(line2);

        int hours = Math.toIntExact(Duration.between(dt1, dt2).toHours());
        System.out.println(hours);

    }
}