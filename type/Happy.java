package numbers.type;

import numbers.AmazingNumber;

public class Happy implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        if (number == 1 || number == 7)
            return true;
        long sum = number, x = number;

        while(sum > 9) {
            sum = 0;

            while (x > 0) {
                long d = x % 10;
                sum += d*d;
                x /= 10;
            }
            if (sum == 1)
                return true;
            x = sum;
        }
        return sum == 7;
    }
}
