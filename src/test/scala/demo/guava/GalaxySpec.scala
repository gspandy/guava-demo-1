package com.porpoise

import com.porpoise.common.metadata._
import demo.guava._
import org.junit.runner.RunWith
import org.junit.Assert
import org.scalatest.junit._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{ FeatureSpec, FlatSpec, FunSuite, Spec }
import scala.collection.JavaConversions._
import demo.guava.metadata._

/**
 * Specification for a Galaxy
 */
@RunWith(classOf[JUnitRunner])
class GalaxySpec extends Spec with ShouldMatchers {

  import TestData._

  describe("A Galaxy") {

    it("should be able to get all planets") {
      val data = newTestData()
      import data._
      val planets = galaxy.getPlanetsTheAccessorsWay

      Assert.assertFalse(galaxy.getSystems.isEmpty)
      val allPlanets = galaxy.getSystems.flatMap(_.getPlanets)

      Assert.assertTrue(allPlanets.containsAll(galaxy.getPlanetsTheAccessorsWay))
      Assert.assertTrue(galaxy.getPlanetsTheAccessorsWay.containsAll(allPlanets))
    }

    it("should be able to be diff'ed against another galaxy") {
      val diff = withAll { galaxy => GalaxyMetadata.diff(galaxy, newTestData().galaxy) }
      println(diff)
    }

    it("should be able to be diff'ed against another galaxy on a subset of properties") {
      val diff = withSome { case (galaxy, properties) => GalaxyMetadata.diff(galaxy, newTestData().galaxy, properties) }
      println(diff)
    }

    it("should be converted to a string for all its properties") {
      val str = withAll { galaxy => GalaxyMetadata.toString(galaxy) }
      println(str)
    }

    it("should be converted to a string for a subset of its properties") {
      val str = withSome { case (galaxy, properties) => GalaxyMetadata.toString(galaxy, properties) }
      println(str)
    }

    it("should be converted to a string for only specified properties") {
      val galaxyOne = newTestData().galaxy
      val str = GalaxyMetadata.toString(galaxyOne, GalaxyMetadata.SYSTEMS, PlanetarySystemMetadata.PLANETS, PlanetMetadata.NAME, PlanetMetadata.MASS)
      println(str)
    }

    it("should be converted to a tree for all of its properties") {
      val galaxyOne = newTestData().galaxy
      val str = GalaxyMetadata.toTreeString(galaxyOne)
      println(str)
    }

  }
}