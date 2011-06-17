package demo.guava

import TestData.scala.collection.JavaConversions.demo.guava.metadata.java.util.com.porpoise.common.functions._
import TestData.scala.collection.JavaConversions.demo.guava.metadata.java.util._
import TestData.scala.collection.JavaConversions.demo.guava.metadata._
import TestData.scala.collection.JavaConversions._
import TestData._

class GuavaTest {

  var testGalaxy: Galaxy = _
  @Before
  def setup() {
    val data = TestData.newTestData()
    testGalaxy = data.galaxy
  }

  @Test
  def testDiffAgainstAnotherGalaxy() = {
    val diff = withAll { galaxy => GalaxyMetadata.diff(galaxy, newTestData().galaxy) }
    println(diff)
  }

  @Test
  def testFunctionalSet() = {
    val planet = new Planet("Mars", 1234)
    planet.setInhabitable(true)

    val planet2 = new Planet("Dave", 1234)
    planet2.setInhabitable(false)

    val planet3 = new Planet("Blah", 1234)
    planet3.setInhabitable(true)

    val key = Keys.keyFunction( PlanetAccessors.GET_MASS, PlanetAccessors.IS_INHABITABLE)
    val set = new FunctionSet(key)

    Assert.assertTrue( set.add(planet))
    Assert.assertTrue( set.add(planet2))
    Assert.assertFalse( set.add(planet3))
    
    println(set)
  }

  @Test
  def testDiffForSubset() = {
    val diff = withSome {
      case (galaxy, properties) =>
        GalaxyMetadata.diff(galaxy, testGalaxy, properties)
    }
    println(diff)

    val noDiff = GalaxyMetadata.diff(testGalaxy, testGalaxy)
    System.err.println(noDiff)
  }

  @Test
  def testToString() = {
    val str = withAll { galaxy => GalaxyMetadata.toString(galaxy) }
    println(str)
  }

  @Test
  def testToStringForSubset() = {
    val str = withSome { case (galaxy, properties) => GalaxyMetadata.toString(galaxy, properties) }
    println(str)
  }

  @Test
  def testToStringForSpecifiedProperties() = {
    val galaxyOne = newTestData().galaxy
    val str = GalaxyMetadata.toString(galaxyOne, GalaxyMetadata.SYSTEMS, PlanetarySystemMetadata.PLANETS, PlanetMetadata.NAME, PlanetMetadata.MASS)
    println(str)
  }

  @Test
  def testTreeForAllProperties() = {
    val galaxyOne = newTestData().galaxy
    val str = GalaxyMetadata.toTreeString(galaxyOne)
    println(str)
  }

  @Test
  def testToStringSomeProps() {
    println(GalaxyMetadata.toString(testGalaxy))
  }
}