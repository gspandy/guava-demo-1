package demo.guava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import scala.actors.threadpool.Arrays;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
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
     * readable, but explicit and verbose -- especially when we want to do other things, like filter, transform, search, check for occurrences, etc.
     * also, for good or bad, we *always* evaluate the entire collections. 
     * That means if we were to map it into an other collection, we'd have to visit every item again. 
     * Also, we'd be doing a lot of work if we were, for instance, only going to call an "isEmpty" on the collection 
     * Or lets say we wanted to see if the collection contained a certain planet. We would process the whole list, even if the planet we were looking 
     * for was the first item in the collection.
     * 
     * @return all the planets in this galaxy
     */
    public Collection<Planet> getPlanetsTheJavaWay() {
    	final Collection<Planet> allPlanets = new ArrayList<Planet>();
    	for (PlanetarySystem system : getSystems()) {
    		for (Planet planet : system.getPlanets()) {
    			allPlanets.add(planet);
    		}
    	}
    	return allPlanets;
    }
    
    /**
     * A typical copy/paste scenario for having to naviate the same collection twice in Java
     * @param mass
     * @return all planets in the galaxy with a mass greater than the given mass
     */
    public Collection<Planet> getPlanetsTheJavaWayWithMassGreaterThanInput(int mass) {
    	final Collection<Planet> allPlanets = new ArrayList<Planet>();
    	for (PlanetarySystem system : getSystems()) {
    		for (Planet planet : system.getPlanets()) {
    			if (planet.getMass() > mass)
    			{
    				allPlanets.add(planet);
    			}
    		}
    	}
    	return allPlanets;
    }
    
    /**
     * Google Guava exposes interfaces to allow a more functional style. A popular operation is to transform a collection of one type into a collection
     * of another.
     * 
     * Using a function which can retrieve a property (here the planets) from a planetary system, we can transform the planetary systems into a collection of all the planets.
     * 
     * Normally transform is straight forward, like this:
     * <code>
     * Collection<String> words = Arrays.asList(new String[] {"one", "two", "three"});
     * Collection<Integer> lengths  = Collections2.transform(words, new Function<String, Integer>() {
     * 		@Override
     * 			public Integer apply(String input) {
     * 				return input.length(); 
     * 	}});
     * </code>
     * 
     * But here our property is actually a Collection in itself -- the "planets" property of a {@link PlanetarySystem}, so when we transform it, 
     * we get a collection of collections.
     * 
     * That's not much good to us, so we need to flatten it out before returning it.
     * 
     * @return all the planets in this galaxy
     */
    public Iterable<Planet> getPlanetsTheGuavaWayOne() {
    	// given a planetary system, return the planets for that system
    	final Function<PlanetarySystem, Collection<Planet>> getPlanetsFromSystem = new Function<PlanetarySystem, Collection<Planet>>() {
			@Override
			public Collection<Planet> apply(PlanetarySystem input) {
				return input.getPlanets();
			}
		};
		
		final Collection<Collection<Planet>> planetsInSystems = Collections2.transform(getSystems(), getPlanetsFromSystem);
		final Iterable<Planet> allPlanets = Iterables.concat(planetsInSystems);
    	return allPlanets;
    }
    
    /**
     * Here we use our own (non-guava) utility, {@link Sequences}, to do the transform and flatten all in one operation
     * @return all the planets in this galaxy
     */
    public Iterable<Planet> getPlanetsTheGuavaWayTwo() {
    	// given a planetary system, return the planets for that system
    	final Function<PlanetarySystem, Collection<Planet>> getPlanetsFromSystem = new Function<PlanetarySystem, Collection<Planet>>() {
    		@Override
    		public Collection<Planet> apply(PlanetarySystem input) {
    			return input.getPlanets();
    		}
    	};
    	
    	final Collection<Planet> planetsInSystems = Sequences.flatMap(getSystems(), getPlanetsFromSystem);
    	return planetsInSystems;
    }

    /**
     * @return all the planets in this galaxy
     */
    public Collection<Planet> getPlanetsTheAccessorsWay() {
        return Sequences.flatMap(getSystems(), PlanetarySystemAccessors.GET_PLANETS);
    }

    /**
     * @return all the planets in this galaxy
     */
    public Map<String, Collection<Planet>> getPlanetsByName() {
        return Sequences.groupBy(getPlanetsTheAccessorsWay(), PlanetAccessors.GET_NAME);
    }

    /**
     * @return all the planets in this galaxy
     */
    public Map<String, Planet> getPlanetsByNameUnique() {
        return Sequences.groupByUnique(getPlanetsTheAccessorsWay(), PlanetAccessors.GET_NAME);
    }
}
