package numbers;

import java.util.Arrays;
import java.util.Optional;

public enum NumberType {
    EVEN("EVEN"),
    ODD("ODD"),
    BUZZ("BUZZ"),
    DUCK("DUCK"),
    PALINDROMIC("PALINDROMIC"),
    GAPFUL("GAPFUL"),
    SPY("SPY"),
    SQUARE("SQUARE"),
    SUNNY("SUNNY"),
    JUMPING("JUMPING"),
    HAPPY("HAPPY"),
    SAD("SAD");

    private final String name;

    NumberType(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Optional<NumberType> fromText(String text) {
        return Arrays.stream(values())
                .filter(bl -> bl.name.equalsIgnoreCase(text))
                .findFirst();
    }
}
