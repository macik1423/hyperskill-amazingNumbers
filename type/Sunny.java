package numbers.type;

import numbers.AmazingNumber;

public class Sunny implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        return number + 1 == Math.pow((int) Math.sqrt(number + 1), 2);
    }
}
