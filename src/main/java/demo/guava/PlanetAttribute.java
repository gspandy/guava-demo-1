package demo.guava;

public class PlanetAttribute {
    private final String key;
    private final Object value;

    public PlanetAttribute(final String keyValue, final Object att) {
        key = keyValue;
        value = att;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

}
