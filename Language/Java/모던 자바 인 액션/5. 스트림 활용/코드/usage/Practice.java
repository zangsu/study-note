package modern_java.stream.usage;

import modern_java.stream.model.Trader;
import modern_java.stream.model.Transaction;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Practice {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        Practice practice = new Practice();

        List<Transaction> result1 = practice.problem1(transactions);
        System.out.println(result1);

        List<String> result2 = practice.problem2(transactions);
        System.out.println(result2);

        List<Trader> result3 = practice.problem3(transactions);
        System.out.println(result3);

        List<String> result4 = practice.problem4(transactions);
        System.out.println(result4);

        boolean result5 = practice.problem5(transactions);
        System.out.println(result5);

        int result6 = practice.problem6(transactions);
        System.out.println(result6);

        int result7 = practice.problem7(transactions);
        System.out.println(result7);

        int result8 = practice.problem8(transactions);
        System.out.println(result8);
    }

    private List<Transaction> problem1(List<Transaction> list){
        List<Transaction> result = list.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                //.sorted((a, b) -> a.getValue() - b.getValue())
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        return result;
    }

    private List<String> problem2(List<Transaction> list){
        return list.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    private List<Trader> problem3(List<Transaction> list){
        return list.stream()
                .map(transaction -> transaction.getTrader())
                //.filter(trader -> trader.getCity() == "Cambridge")
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    private List<String> problem4(List<Transaction> list){
        return list.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private boolean problem5(List<Transaction> list){
        return list.stream()
                //.map(transaction -> transaction.getTrader())
                //.distinct()
                //.anyMatch(trader -> trader.getCity().equals("Milan"));
                .anyMatch(transaction -> transaction.getTrader()
                        .getCity()
                        .equals("Milan"));
    }

    private int problem6(List<Transaction> list){
        return list.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, Integer::sum);
    }

    private int problem7(List<Transaction> list) {
        return list.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .get();
    }
    private int problem8(List<Transaction> list) {
        return list.stream()
                //.map(Transaction::getValue)
                //.reduce(Integer::min)
                .min(Comparator.comparing(Transaction::getValue))
                .get()
                .getValue();
    }

}