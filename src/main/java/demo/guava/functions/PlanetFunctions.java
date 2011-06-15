package demo.guava.functions;

import com.google.common.base.Function;

import demo.guava.Planet;

public enum PlanetFunctions {
    ; // uninstantiable

    public static final Function<Planet, String> GET_NAME;

    static {
        GET_NAME = new Function<Planet, String>() {
            @Override
            public String apply(final Planet input) {
                return input.getName();
            }
        };
    }

}
