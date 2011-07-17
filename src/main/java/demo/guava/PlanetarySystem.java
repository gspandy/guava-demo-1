package demo.guava;

import java.util.Collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 */
public class PlanetarySystem {
	private final Collection<Planet> planets = Lists.newArrayList();

	/**
	 * @return the planets in this system
	 */
	public Collection<Planet> getPlanets() {
		return ImmutableList.copyOf(this.planets);
	}

	/**
	 * @param newPlanet
	 * @return true if the new planet was added
	 */
	public boolean addPlanet(final Planet newPlanet) {
		if (newPlanet == null) {
			return false;
		}
		return this.planets.add(newPlanet);
	}
}
