package demo.guava;

import java.util.Collection;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.porpoise.common.collect.Sequences;

/**
 */
public class Galaxy {
    private final Collection<PlanetarySystem> systems = Lists.newArrayList();

    /**
     * @return the systems in this galaxy
     */
    public Collection<PlanetarySystem> getSystems() {
        return this.systems;
    }

    /**
     * add a planetary system
     * 
     * @param system
     * @return true if the system was added
     */
    public boolean addSystem(final PlanetarySystem system) {
        if (system == null) {
            return false;
        }
        return this.systems.add(system);
    }

    /**
     * @return all the planets in this galaxy
     */
    public Collection<Planet> getPlanets() {
        return Sequences.flatMap(getSystems(),
                new Function<PlanetarySystem, Collection<Planet>>() {
                    @Override
                    public Collection<Planet> apply(final PlanetarySystem input) {
                        return input.getPlanets();
                    }
                });
    }

    /**
     * @return all the planets in this galaxy
     */
    public Map<String, Collection<Planet>> getPlanetsByName() {
        return Sequences.groupBy(getPlanets(), new Function<Planet, String>() {
            @Override
            public String apply(final Planet input) {
                return input.getName();
            }
        });
    }

    /**
     * @return all the planets in this galaxy
     */
    public Map<String, Planet> getPlanetsByNameUnique() {
        return Sequences.groupByUnique(getPlanets(),
                new Function<Planet, String>() {
                    @Override
                    public String apply(final Planet input) {
                        return input.getName();
                    }
                });
    }
}
