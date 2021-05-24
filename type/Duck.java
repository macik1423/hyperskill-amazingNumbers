package numbers.type;

import numbers.AmazingNumber;

public class Duck implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        String withoutTrailingZeros = String.valueOf(number);
        return withoutTrailingZeros.contains("0");
    }
}
