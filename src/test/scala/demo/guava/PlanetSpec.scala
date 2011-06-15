package com.porpoise

import scala.collection.JavaConversions._
import demo.guava._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{ FeatureSpec, FlatSpec, FunSuite, Spec }
import org.junit.Assert
import demo.guava.Galaxy

/**
 * Specification for a Galaxy
 */
@RunWith(classOf[JUnitRunner])
class PlanetSpec extends Spec with ShouldMatchers {

  describe("A Planet") {
    it("should be able to be shown as a string") {
    }
  }
}