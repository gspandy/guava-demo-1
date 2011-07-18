package demo;

import demo.guava.AllMetadata;
import demo.guava.Galaxy;
import demo.guava.Planet;
import demo.guava.PlanetarySystem;
import demo.guava.metadata.GalaxyMetadata;

/**
 * 
 */
public class DemoApp
{

	public static void main(final String[] args)
	{
		final Galaxy galaxy = new Galaxy();
		galaxy.addSystem(newSystem("Mercury", "Venus", "Earth"));
		galaxy.addSystem(newSystem("Mars", "Jupiter"));

		System.out.println(GalaxyMetadata.toString(galaxy,
				AllMetadata.less(GalaxyMetadata.PLANETS_BY_NAME, GalaxyMetadata.PLANETS)));
		// System.out.println(GalaxyMetadata.toTreeString(galaxy));
	}

	/**
	 * @return
	 */
	private static PlanetarySystem newSystem(final String... planets)
	{
		final PlanetarySystem system = new PlanetarySystem();
		for (final String planet : planets)
		{
			system.addPlanet(newPlanet(planet));
		}
		return system;
	}

	/**
	 * @return
	 */
	private static Planet newPlanet(final String name)
	{
		final Planet planet = new Planet(name, name.length());
		planet.setDateDiscovered(new java.util.Date());
		return planet;
	}
}
