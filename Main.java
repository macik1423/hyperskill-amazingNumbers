package numbers;

import java.nio.file.DirectoryStream;
import java.util.*;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcome();

        while (true) {
            System.out.println("Enter a request:");
            String[] input = sc.nextLine().split(" ");
            String number = input[0];
            if (number.equals("0")) {
                break;
            }
            chooseOption(input);
        }
    }

    private static void chooseOption(final String[] input) {
        if (input.length == 1) {
            firstOption(input);
        } else if (input.length == 2) {
            secondOption(input);
        } else if (input.length > 2){
            propsOption(input);
        }
    }

    private static List<LongPredicate> filterAmazingNumber(Map<PropsType, List<String>> propsNumberType) {
        Factory factory = new Factory();
        List<LongPredicate> predicates = new ArrayList<>();
        for (Map.Entry<PropsType, List<String>> propsTypeNumberTypeEntry : propsNumberType.entrySet()) {
            for (String numberType : propsTypeNumberTypeEntry.getValue()) {
                AmazingNumber amazingNumber = factory.getAmazingNumber(NumberType.valueOf(numberType));
                LongPredicate predicate = amazingNumber::checkProp;
                if (propsTypeNumberTypeEntry.getKey().equals(PropsType.MINUS)) {
                    predicates.add(predicate.negate());
                } else {
                    predicates.add(predicate);
                }
            }
        }
        return predicates;
    }

    private static void propsOption(String[] input) {
        long number = Integer.parseInt(input[0]);
        int range = Integer.parseInt(input[1]);

        Map<PropsType, List<String>> propsNumberType = new HashMap<>();
        String[] props = Arrays.copyOfRange(input, 2, input.length);
        for (String prop : props) {
            if (prop.charAt(0) == '-') {
                if (!propsNumberType.containsKey(PropsType.MINUS)) {
                    List<String> values = new ArrayList<>();
                    values.add(prop.substring(1).toUpperCase(Locale.ROOT));
                    propsNumberType.put(PropsType.MINUS, values);
                } else {
                    propsNumberType.get(PropsType.MINUS).add(prop.substring(1).toUpperCase(Locale.ROOT));
                }
            } else {
                if (!propsNumberType.containsKey(PropsType.NORMAL)) {
                    List<String> values = new ArrayList<>();
                    values.add(prop.toUpperCase(Locale.ROOT));
                    propsNumberType.put(PropsType.NORMAL, values);
                } else {
                    propsNumberType.get(PropsType.NORMAL).add(prop.toUpperCase(Locale.ROOT));
                }
            }
        }

        try {
            Validator.validNumber(number);
            Validator.validRange(range);
            Validator.validProps(propsNumberType);
            List<Long> filtered =
                LongStream
                    .iterate(number, i -> i + 1)
                    .filter(filterAmazingNumber(propsNumberType).stream().reduce(x -> true, LongPredicate::and))
                    .limit(range)
                    .boxed()
                    .collect(Collectors.toList());
            List<Number> numbers = new ArrayList<>();
            Factory factory = new Factory();
            for (Long filteredNum: filtered) {
                List<NumberType> numberTypes = new ArrayList<>();
                for (NumberType numberType: NumberType.values()) {
                    AmazingNumber an = factory.getAmazingNumber(numberType);
                    if (an.checkProp(filteredNum)) {
                        numberTypes.add(numberType);
                    }
                }
                numbers.add(new Number(filteredNum, numberTypes));
            }

            for (Number num : numbers) {
                System.out.printf("%d is %s%n", num.getValue(), num.getTypes());
            }
        } catch (NotNaturalException | MutuallyException | WrongProp e) {
            System.out.println(e.getMessage());
        }
    }

    private static void secondOption(final String[] input) {
        long number = Long.parseLong(input[0]);
        int range = Integer.parseInt(input[1]);
        try {
            Validator.validNumber(number);
            Validator.validRange(range);
            do {
                List<String> trueProp = new ArrayList<>();
                Factory factory = new Factory();
                for (NumberType numberType : NumberType.values()) {
                    AmazingNumber an = factory.getAmazingNumber(numberType);
                    if (an.checkProp(number)) {
                        trueProp.add(numberType.name().toLowerCase());
                    }
                }
                System.out.printf("%d is " + String.join(", ", trueProp) + "%n", number);
                number++;
            } while (--range != 0);
        } catch (NotNaturalException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void firstOption(final String[] input) {
        try {
            long number = Long.parseLong(input[0]);
            Validator.validNumber(number);

            System.out.printf("Properties of %s%n", String.format("%,d", number));
            Factory factory = new Factory();
            for (NumberType numberType : NumberType.values()) {
                AmazingNumber an = factory.getAmazingNumber(numberType);
                System.out.printf("%s: %s%n", numberType.name().toLowerCase(), an.checkProp(number));
            }
        } catch (NotNaturalException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void welcome() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit");
    }
}

class WrongProp extends Exception {
    public WrongProp(String message) {
        super(message);
    }
}

class NotNaturalException extends Exception {
    public NotNaturalException(String message) {
        super(message);
    }
}

class MutuallyException extends Exception {
    public MutuallyException(String message) {
        super(message);
    }
}