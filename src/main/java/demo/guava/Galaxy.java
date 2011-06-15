package demo.guava;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

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
        final Collection<Planet> planets = Lists.newArrayList();
        for (PlanetarySystem system : getSystems()) {
            planets.addAll(system.getPlanets());
        }
        return planets;
    }

    /**
     * @return all the planets in this galaxy
     */
    public Map<String, Collection<Planet>> getPlanetsByName() {
        final Multimap<String, Planet> planetsByName = ArrayListMultimap
                .create();
        for (Planet planet : getPlanets()) {
            planetsByName.put(planet.getName(), planet);
        }
        return planetsByName.asMap();
    }

    /**
     * @return all the planets in this galaxy
     */
    public Map<String, Planet> getPlanetsByNameUnique() {
        final Map<String, Planet> planetsByName = Maps.newHashMap();
        for (Entry<String, Collection<Planet>> entry : getPlanetsByName()
                .entrySet()) {
            planetsByName.put(entry.getKey(),
                    Iterables.getOnlyElement(entry.getValue()));
        }
        return planetsByName;
    }
}
