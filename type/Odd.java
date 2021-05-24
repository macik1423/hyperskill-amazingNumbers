package numbers.type;

import numbers.AmazingNumber;

public class Odd implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        return number % 2 != 0;
    }
}
