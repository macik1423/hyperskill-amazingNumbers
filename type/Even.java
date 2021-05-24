package numbers.type;

import numbers.AmazingNumber;

public class Even implements AmazingNumber {
    @Override
    public boolean checkProp(final long number) {
        return number % 2 == 0;
    }
}
