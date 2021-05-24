package numbers;

import java.util.*;
import java.util.stream.Collectors;

public class Validator {
    public static void validNumber(long number) throws NotNaturalException {
        if (number <= 0) {
            throw new NotNaturalException("The first parameter should be a natural number or zero.");
        }
    }

    public static void validRange(int range) throws NotNaturalException {
        if (range < 0) {
            throw new NotNaturalException("The second parameter should be a natural number.");
        }
    }

    public static void validProps(Map<PropsType, List<String>> props) throws WrongProp, MutuallyException {
        List<String> numberTypesValues = Arrays.stream(NumberType.values()).map(Enum::name).collect(Collectors.toList());
        List<String> wrongValues = new ArrayList<>();
        if (props.containsKey(PropsType.NORMAL)) {
            List<String> wrongValuesNormal = props.get(PropsType.NORMAL)
                    .stream().filter(s -> !numberTypesValues.contains(s)).collect(Collectors.toList());
            wrongValues.addAll(wrongValuesNormal);
        }
        if (props.containsKey(PropsType.MINUS)) {
            List<String> wrongValuesMinus = props.get(PropsType.MINUS)
                    .stream().filter(s -> !numberTypesValues.contains(s)).collect(Collectors.toList());
            wrongValues.addAll(wrongValuesMinus);
        }

        String wrongValueStr = String.join(", ", wrongValues);
        if (wrongValues.size() == 1) {
            throw new WrongProp(
                String.format(
                    "The property %s is wrong.%nAvailable properties: %s",
                    wrongValues, numberTypesValues
                )
            );
        }
        if (wrongValues.size() > 1) {
            throw new WrongProp(
                String.format(
                    "The properties [%s] are wrong.%nAvailable properties: %s",
                    wrongValueStr, numberTypesValues
                )
            );
        }
        checkMutuallyCases(props);
    }

    private static void checkMutuallyCases(Map<PropsType, List<String>> propsNumberType) throws MutuallyException {
        if (propsNumberType.containsKey(PropsType.NORMAL)) {
            List<String> numberType = propsNumberType.get(PropsType.NORMAL);
            mutually(numberType, NumberType.EVEN.name(), NumberType.ODD.name());
            mutually(numberType, NumberType.DUCK.name(), NumberType.SPY.name());
            mutually(numberType, NumberType.SUNNY.name(), NumberType.SQUARE.name());
            mutually(numberType, NumberType.HAPPY.name(), NumberType.SAD.name());
        }

        if (propsNumberType.containsKey(PropsType.MINUS)) {
            List<String> numberType = propsNumberType.get(PropsType.MINUS).stream().map(s -> "-" + s).collect(Collectors.toList());
            mutually(numberType, "-" + NumberType.EVEN.name(), "-" + NumberType.ODD.name());
        }

        if (propsNumberType.containsKey(PropsType.NORMAL) && propsNumberType.containsKey(PropsType.MINUS)) {
            List<String> numberTypeNormal = propsNumberType.get(PropsType.NORMAL);
            List<String> numberTypeMinus = propsNumberType.get(PropsType.MINUS).stream().map(s -> "-" + s).collect(Collectors.toList());
            List<String> numberType = new ArrayList<>();
            numberType.addAll(numberTypeNormal);
            numberType.addAll(numberTypeMinus);
            mutually(numberType, "-" + NumberType.EVEN.name(), NumberType.EVEN.name());
            mutually(numberType, "-" + NumberType.ODD.name(), NumberType.ODD.name());
            mutually(numberType, "-" + NumberType.BUZZ.name(), NumberType.BUZZ.name());
            mutually(numberType, "-" + NumberType.DUCK.name(), NumberType.DUCK.name());
            mutually(numberType, "-" + NumberType.PALINDROMIC.name(), NumberType.PALINDROMIC.name());
            mutually(numberType, "-" + NumberType.GAPFUL.name(), NumberType.GAPFUL.name());
            mutually(numberType, "-" + NumberType.SPY.name(), NumberType.SPY.name());
            mutually(numberType, "-" + NumberType.SQUARE.name(), NumberType.SQUARE.name());
            mutually(numberType, "-" + NumberType.SUNNY.name(), NumberType.SUNNY.name());
            mutually(numberType, "-" + NumberType.JUMPING.name(), NumberType.JUMPING.name());
            mutually(numberType, "-" + NumberType.HAPPY.name(), NumberType.HAPPY.name());
            mutually(numberType, "-" + NumberType.SAD.name(), NumberType.SAD.name());
        }
    }

    private static void mutually(List<String> chosenProps, String type1, String type2) throws MutuallyException {
        if (chosenProps.containsAll(Arrays.asList(type1, type2))) {
            throw new MutuallyException(
                    String.format("The request contains mutually exclusive properties: [%s, %s]%nThere are no numbers with these properties.", type1.toString(), type2.toString())
            );
        }
    }
}
