package com.porpoise

import demo.guava._
import org.junit.runner.RunWith
import org.junit.Assert
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{ FeatureSpec, FlatSpec, FunSuite, Spec }
import scala.collection.JavaConversions._
import demo.guava.metadata._

/**
 * Specification for a Galaxy
 */
@RunWith(classOf[JUnitRunner])
class ScalaBeanSpec extends Spec with ShouldMatchers {
    
    def forSome[T](f : Galaxy => T) = {
        val galaxyOne = newTestData.galaxy
        f(galaxyOne)
    }

  def newSystem(prefix: String) = {
    def newPlanet(name: String) = (new Planet(prefix + name, (prefix + name).length))
    val system = new PlanetarySystem
    system.addPlanet(newPlanet("Mercury"))
    system.addPlanet(newPlanet("Venus"))
    system.addPlanet(newPlanet("Earth"))
    system.addPlanet(newPlanet("Mars"))
    system
  }
  def newTestData = new {
    val galaxy = new Galaxy()
    galaxy.addSystem(newSystem("Alpha"))
    galaxy.addSystem(newSystem("Bravo"))
  }

  describe("A Galaxy") {
    
    it("should be able to get all planets") {
      val data = this.newTestData
      import data._
      val planets = galaxy.getPlanets

      Assert.assertFalse(galaxy.getSystems.isEmpty)
      val allPlanets = galaxy.getSystems.flatMap(_.getPlanets)

      Assert.assertTrue(allPlanets.containsAll(galaxy.getPlanets))
      Assert.assertTrue(galaxy.getPlanets.containsAll(allPlanets))
    }

    it("should be able to be diff'ed against another galaxy") {
      val galaxyOne = this.newTestData.galaxy
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
    
    it("should be converted to a string for all its properties") {
        val galaxyOne = newTestData.galaxy
        val str = GalaxyMetadata.toString(galaxyOne)
        println(str)
    }
    
    it("should be converted to a string for a subset of its properties") {
        val galaxyOne = newTestData.galaxy
        val properties = AllMetadata.less(PlanetMetadata.NAME)
        val str = GalaxyMetadata.toString(galaxyOne, properties)
        println(str)
    }
    
    it("should be converted to a string for only some of its properties") {
        val galaxyOne = newTestData.galaxy
        val str = GalaxyMetadata.toString(galaxyOne, GalaxyMetadata.SYSTEMS, PlanetarySystemMetadata.PLANETS, PlanetMetadata.NAME, PlanetMetadata.MASS)
        println(str)
    }
    
    it("should be converted to a tree for all of its properties") {
        val galaxyOne = newTestData.galaxy
        val str = GalaxyMetadata.toTreeString(galaxyOne)
        println(str)
    }

  }
}