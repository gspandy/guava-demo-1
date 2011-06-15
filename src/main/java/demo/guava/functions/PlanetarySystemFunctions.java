package demo.guava.functions;

import java.util.Collection;

import com.google.common.base.Function;

import demo.guava.Planet;
import demo.guava.PlanetarySystem;

public enum PlanetarySystemFunctions {
    ; // uninstantiable

    public static final Function<PlanetarySystem, Collection<Planet>> GET_PLANETS;

    static {
        GET_PLANETS = new Function<PlanetarySystem, Collection<Planet>>() {
            @Override
            public Collection<Planet> apply(final PlanetarySystem input) {
                return input.getPlanets();
            }
        };
    }

}
