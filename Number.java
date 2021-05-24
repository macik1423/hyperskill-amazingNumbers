package numbers;

import java.util.List;
import java.util.stream.Collectors;

public class Number {
    final private long value;
    final private List<NumberType> types;

    public Number(long value, List<NumberType> types) {
        this.value = value;
        this.types = types;
    }

    public String getTypes() {
        return types
            .stream()
            .map(type -> type.name().toLowerCase())
            .collect(Collectors.joining(", "));
    }

    public long getValue() {
        return value;
    }
}
