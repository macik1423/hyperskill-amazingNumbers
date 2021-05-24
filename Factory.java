package numbers;


import numbers.type.*;

public class Factory {

    public AmazingNumber getAmazingNumber(NumberType numberType) {
        if (numberType.equals(NumberType.EVEN)) {
            return new Even();
        }
        if (numberType.equals(NumberType.ODD)) {
            return new Odd();
        }
        if (numberType.equals(NumberType.DUCK)) {
            return new Duck();
        }
        if (numberType.equals(NumberType.BUZZ)) {
            return new Buzz();
        }
        if (numberType.equals(NumberType.GAPFUL)) {
            return new Gapful();
        }
        if (numberType.equals(NumberType.PALINDROMIC)) {
            return new Palindromic();
        }
        if (numberType.equals(NumberType.SPY)) {
            return new Spy();
        }
        if (numberType.equals(NumberType.SQUARE)) {
            return new Square();
        }
        if (numberType.equals(NumberType.SUNNY)) {
            return new Sunny();
        }
        if (numberType.equals(NumberType.JUMPING)) {
            return new Jumping();
        }
        if (numberType.equals(NumberType.HAPPY)) {
            return new Happy();
        }
        if (numberType.equals(NumberType.SAD)) {
            return new Sad();
        }

        throw new IllegalArgumentException();
    }
}
