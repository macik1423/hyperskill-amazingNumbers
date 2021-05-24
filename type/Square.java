package numbers.type;

import numbers.AmazingNumber;

public class Square implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        return Math.pow((int) Math.sqrt(number), 2) == number;
    }
}
