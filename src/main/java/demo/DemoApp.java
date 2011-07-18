package demo;

import java.util.Collection;
import java.util.Map;

import com.google.common.base.Function;
import com.porpoise.common.collect.Sequences;
import com.porpoise.common.collect.tree.TreeNode;
import com.porpoise.common.functions.Key;
import com.porpoise.common.functions.Keys;
import com.porpoise.common.metadata.Delta;

import demo.guava.Galaxy;
import demo.guava.Planet;
import demo.guava.PlanetarySystem;
import demo.guava.metadata.GalaxyMetadata;
import demo.guava.metadata.PlanetAccessors;
import demo.guava.metadata.PlanetAttributeMetadata;
import demo.guava.metadata.PlanetMetadata;
import demo.guava.metadata.PlanetarySystemMetadata;

/**
 */
@SuppressWarnings("unchecked")
public class DemoApp
{

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		final Galaxy galaxy = new Galaxy();
		galaxy.addSystem(newSystem("exmaple"));
		galaxy.addSystem(newSystem("another"));

		final Galaxy galaxy2 = new Galaxy();
		galaxy2.addSystem(newSystem("exmaple"));
		galaxy2.addSystem(newSystem("meh"));

		final Delta<Galaxy> diffAll = GalaxyMetadata.diff(galaxy, galaxy2);
		System.out.println(diffAll);

		final Delta<Galaxy> justNames = GalaxyMetadata.diff(galaxy, galaxy2,
				GalaxyMetadata.PLANETS_THE_ACCESSORS_WAY, PlanetMetadata.NAME);
		System.err.println(justNames);

		// and to demonstrate, turn that sucker into a string:
		final String string = GalaxyMetadata.toString(galaxy,
				GalaxyMetadata.PLANETS_BY_NAME_UNIQUE,
				GalaxyMetadata.PLANETS_THE_ACCESSORS_WAY,
				PlanetarySystemMetadata.PLANETS, PlanetMetadata.NAME,
				PlanetMetadata.ATTRIBUTES, PlanetMetadata.DATE_DISCOVERED,
				PlanetAttributeMetadata.KEY, PlanetAttributeMetadata.VALUE);
		System.out.println(string);

		// you can represent an object graph just as a tree of objects
		final TreeNode<Object> propertyTree = GalaxyMetadata.asTree(galaxy);

		// and to demonstrate, turn that sucker into a string:
		final String treeString = GalaxyMetadata.toTreeString(galaxy);
		System.out.println(treeString);

		// another very common operation is to group a collection based on a property.
		// We can use 'Sequences' helper to group by a given property, and the Keys utility to create compound property
		// keys
		final Function<Planet, Key<Planet>> mapper = Keys.keyFunction(PlanetAccessors.GET_NAME,
				PlanetAccessors.GET_MASS);

		final Map<Key<Planet>, Collection<Planet>> planetsByNameAndMass = Sequences.groupBy(
				galaxy.getPlanetsTheJavaWay(), mapper);
		final Map<Key<Planet>, Planet> planetsUnique = Sequences.groupByUnique(galaxy.getPlanetsTheJavaWay(), mapper);
	}

	private static PlanetarySystem newSystem(final String prefix)
	{
		final PlanetarySystem system = new PlanetarySystem();
		system.addPlanet(newPlanet(prefix + " planet one"));
		system.addPlanet(newPlanet(prefix + " planet two"));
		system.addPlanet(newPlanet(prefix + " planet three"));
		return system;
	}

	private static Planet newPlanet(final String name)
	{
		final Planet planet = new Planet(name, (name).length());
		planet.addAttribute(name + " sweet", name + " value");
		planet.addAttribute(name + " sour", name + " meh");
		planet.setDateDiscovered(new java.util.Date());
		return planet;
	}
}
