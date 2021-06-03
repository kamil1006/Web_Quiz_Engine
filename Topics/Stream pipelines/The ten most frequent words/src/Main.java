import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String line= scanner.nextLine();

        String[] table = line.toLowerCase(Locale.ROOT).split(" ");
        Stream<String> stream = Arrays.stream(table);
        List<String> lista = stream.map(s -> s.replaceAll("[^0-9a-zA-Z]+", "")).sorted().collect(Collectors.toList());


        Map<String,Long> mapa=lista.stream().collect(Collectors.groupingBy(String::toString,Collectors.counting()));

        var sorted = mapa.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).collect(Collectors.toList());


        var collect = mapa.entrySet()
                .stream()
                .sorted(Comparator.comparing((Map.Entry<String, Long> m) -> m.getValue()).reversed()
                        .thenComparing(Map.Entry::getKey))
                .limit(10)
                .collect(Collectors.toList());


        var collect1 = collect.stream().map(x -> x.getKey()).collect(Collectors.toList());
        collect1.stream().forEach(System.out::println);

    }

    Comparator<Map.Entry<String, Long>> comparator = (o1, o2) -> {
        int i = o1.getValue().compareTo(o2.getValue());
        if (i == 0) {
            return o1.getKey().compareTo(o2.getKey());
        } else {
            return i;
        }
    };

}