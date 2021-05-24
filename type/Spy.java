package numbers.type;

import numbers.AmazingNumber;

public class Spy implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        String numberStr = String.valueOf(number);
        int sum = numberStr.chars().map(i -> i - 48).sum();
        int product = numberStr.chars().map(i -> i - 48).reduce(1, (prev, next) -> prev * next);
        return sum == product;
    }
}
