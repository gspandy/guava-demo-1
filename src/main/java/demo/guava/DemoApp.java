package demo.guava;

import com.porpoise.common.collect.tree.TreeNode;
import com.porpoise.common.metadata.Delta;

import demo.guava.metadata.GalaxyMetadata;
import demo.guava.metadata.PlanetAttributeMetadata;
import demo.guava.metadata.PlanetMetadata;
import demo.guava.metadata.PlanetarySystemMetadata;

public class DemoApp {

	public static void main(String[] args) {
		Galaxy galaxy = new Galaxy();
		galaxy.addSystem(newSystem("exmaple"));
		galaxy.addSystem(newSystem("another"));

		Galaxy galaxy2 = new Galaxy();
		galaxy2.addSystem(newSystem("exmaple"));
		galaxy2.addSystem(newSystem("meh"));

		Delta<Galaxy> diffAll = GalaxyMetadata.diff(galaxy, galaxy2);
		System.out.println(diffAll);

		Delta<Galaxy> justNames = GalaxyMetadata.diff(galaxy, galaxy2,
				GalaxyMetadata.PLANETS_THE_ACCESSORS_WAY, PlanetMetadata.NAME);
		System.err.println(justNames);

		// and to demonstrate, turn that sucker into a string:
		String string = GalaxyMetadata.toString(galaxy,
				GalaxyMetadata.PLANETS_BY_NAME_UNIQUE,
				GalaxyMetadata.PLANETS_THE_ACCESSORS_WAY,
				PlanetarySystemMetadata.PLANETS, PlanetMetadata.NAME,
				PlanetMetadata.ATTRIBUTES, PlanetMetadata.DATE_DISCOVERED,
				PlanetAttributeMetadata.KEY, PlanetAttributeMetadata.VALUE);
		System.out.println(string);

		// you can represent an object graph just as a tree of objects
		TreeNode<Object> propertyTree = GalaxyMetadata.asTree(galaxy);

		// and to demonstrate, turn that sucker into a string:
		String treeString = GalaxyMetadata.toTreeString(galaxy);
		System.out.println(treeString);

	}

	private static PlanetarySystem newSystem(String prefix) {
		PlanetarySystem system = new PlanetarySystem();
		system.addPlanet(newPlanet(prefix + " planet one"));
		system.addPlanet(newPlanet(prefix + " planet two"));
		system.addPlanet(newPlanet(prefix + " planet three"));
		return system;
	}

	private static Planet newPlanet(String name) {
		Planet planet = new Planet(name, (name).length());
		planet.addAttribute(name + " sweet", name + " value");
		planet.addAttribute(name + " sour", name + " meh");
		planet.setDateDiscovered(new java.util.Date());
		return planet;
	}
}
