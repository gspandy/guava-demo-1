package demo.guava;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Lists;
import com.porpoise.common.collect.Sequences;

import demo.guava.metadata.PlanetAccessors;
import demo.guava.metadata.PlanetarySystemAccessors;

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
                PlanetarySystemAccessors.GET_PLANETS);
    }

    /**
     * @return all the planets in this galaxy
     */
    public Map<String, Collection<Planet>> getPlanetsByName() {
        return Sequences.groupBy(getPlanets(), PlanetAccessors.GET_NAME);
    }

    /**
     * @return all the planets in this galaxy
     */
    public Map<String, Planet> getPlanetsByNameUnique() {
        return Sequences.groupByUnique(getPlanets(), PlanetAccessors.GET_NAME);
    }
}
