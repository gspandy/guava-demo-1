package com.porpoise

import demo.guava._
import org.junit.runner.RunWith
import org.junit.Assert
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{ FeatureSpec, FlatSpec, FunSuite, Spec }
import scala.collection.JavaConversions._
import demo.guava.metadata.GalaxyMetadata
import demo.guava.metadata.PlanetMetadata

/**
 * Specification for a Galaxy
 */
@RunWith(classOf[JUnitRunner])
class ScalaBeanSpec extends Spec with ShouldMatchers {

  def newSystem = {
    def newPlanet(name: String) = (new Planet(name, name.length))
    val system = new PlanetarySystem()
    system.addPlanet(newPlanet("Earth"))
    system.addPlanet(newPlanet("Venus"))
    system.addPlanet(newPlanet("Mars"))
    system.addPlanet(newPlanet("Saturn"))
    system
  }
  def newTestData = new {
    val galaxy = new Galaxy()
    galaxy.addSystem(newSystem)
    galaxy.addSystem(newSystem)
  }

  describe("A Galaxy") {
    it("should be able to get all planets") {
      val data = newTestData
      import data._
      val planets = galaxy.getPlanets

      Assert.assertFalse(galaxy.getSystems.isEmpty)
      val allPlanets = galaxy.getSystems.flatMap(_.getPlanets)

      Assert.assertTrue(allPlanets.containsAll(galaxy.getPlanets))
      Assert.assertTrue(galaxy.getPlanets.containsAll(allPlanets))
    }
    it("should be able to be diff'ed against another galaxy") {
      val galaxyOne = newTestData.galaxy
      val galaxyTwo = newTestData.galaxy

      val diff = GalaxyMetadata.diff(galaxyOne, galaxyTwo)
      println(diff)
    }
    it("should be able to be diff'ed against another galaxy on a subset of properties") {
      val galaxyOne = newTestData.galaxy
      val galaxyTwo = newTestData.galaxy

      val properties = AllMetadata.less(PlanetMetadata.NAME)
      val diff = GalaxyMetadata.diff(galaxyOne, galaxyTwo, properties)
      println(diff)
    }
  }
}