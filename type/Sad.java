package numbers.type;

import numbers.AmazingNumber;
import numbers.type.Happy;

public class Sad implements AmazingNumber {
    @Override
    public boolean checkProp(long number) {
        Happy happy = new Happy();
        return !happy.checkProp(number);
    }
}
