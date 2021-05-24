package numbers.type;

import numbers.AmazingNumber;

public class Buzz implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        String numberStr = String.valueOf(number);
        long leftPart = numberStr.length() == 1 ? number : Long.parseLong(numberStr.substring(0, numberStr.length() - 1));
        int rightPart = Integer.parseInt(numberStr.substring(numberStr.length() - 1));
        return (leftPart - rightPart * 2L) % 7 == 0 || rightPart == 7;
    }
}
