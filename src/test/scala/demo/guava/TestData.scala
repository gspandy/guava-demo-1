package demo.guava

import com.porpoise.common.metadata._
import demo.guava._
import org.junit.runner.RunWith
import org.junit.Assert
import org.scalatest.junit._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{ FeatureSpec, FlatSpec, FunSuite, Spec }
import scala.collection.JavaConversions._
import demo.guava.metadata._

object TestData {

  def withSome[T](f: (Galaxy, Collection[Metadata[_, _]]) => T) = {
    val properties = AllMetadata.less(PlanetMetadata.NAME)
    f(newTestData().galaxy, properties)
  }
  def withAll[T](f: Galaxy => T) = f(newTestData().galaxy)

  def newSystem(prefix: String) = {
    def newPlanet(name: String) = {
      val planet = new Planet(prefix + name, (prefix + name).length)
      planet.addAttribute(prefix + " sweet", prefix + " value")
      planet.addAttribute(prefix + " sour", prefix + " carp")
      planet.setDateDiscovered(new java.util.Date());
      planet
    }
    val system = new PlanetarySystem
    system.addPlanet(newPlanet("Mercury"))
    system.addPlanet(newPlanet("Venus"))
    system.addPlanet(newPlanet("Earth"))
    system.addPlanet(newPlanet("Mars"))
    system
  }
  def newTestData(prefix1: String = "Alpha", prefix2: String = "Bravo") = new {
    val galaxy = new Galaxy()
    galaxy.addSystem(newSystem(prefix1))
    galaxy.addSystem(newSystem(prefix2))
  }
}