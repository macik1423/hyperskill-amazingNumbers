package numbers.type;

import numbers.AmazingNumber;

import java.util.Collections;
import java.util.OptionalInt;

public class Jumping implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        boolean flag = true;
        while (number != 0) {
            long digit1 = number % 10;
            number = number / 10;
            if (number != 0) {
                long digit2 = number % 10;
                if (Math.abs(digit1 - digit2) != 1) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
