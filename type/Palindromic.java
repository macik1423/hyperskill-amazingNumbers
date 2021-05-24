package numbers.type;

import numbers.AmazingNumber;

public class Palindromic implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        String numberStr = String.valueOf(number);
        StringBuilder numberBuilder = new StringBuilder(numberStr);
        StringBuilder reverse = numberBuilder.reverse();
        return numberStr.contentEquals(reverse);
    }
}
