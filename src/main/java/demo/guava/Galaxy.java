package demo.guava;

import java.util.Collection;

import com.google.common.collect.Lists;

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
}
