package numbers.type;

import numbers.AmazingNumber;

public class Gapful implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        String numberStr = String.valueOf(number);
        if (numberStr.length() < 3) {
            return false;
        } else {
            int first = Integer.parseInt(String.valueOf(numberStr.toCharArray()[0]));
            int last = Integer.parseInt(String.valueOf(numberStr.toCharArray()[numberStr.length() - 1]));
            int divider = Integer.parseInt(first + String.valueOf(last));
            return number % divider == 0;
        }
    }
}
