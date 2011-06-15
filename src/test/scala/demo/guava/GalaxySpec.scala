package com.porpoise

import scala.collection.JavaConversions._

import demo.guava.GalaxyMother
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{ FeatureSpec, FlatSpec, FunSuite, Spec }
import org.junit.Assert

/**
 * Specification for a Galaxy
 */
@RunWith(classOf[JUnitRunner])
class ScalaBeanSpec extends Spec with ShouldMatchers {

  describe("A Galaxy") {
    it("should be able to get all planets") {
      val galaxy = GalaxyMother.create()
      val planets = galaxy.getPlanets

      Assert.assertFalse(galaxy.getSystems.isEmpty)
      val allPlanets = galaxy.getSystems.flatMap(_.getPlanets)

      Assert.assertTrue(allPlanets.containsAll(galaxy.getPlanets))
      Assert.assertTrue(galaxy.getPlanets.containsAll(allPlanets))
    }
  }
}