import scala.util.Random

object Main {
  def main(args: Array[String]) {
    // all of the letters
    val allLetters = ('A'.to('Z')) toList

    // select seven at random
    val random = new Random
    val pickOne = (l: List[Char]) => l(random.nextInt(l.length))
    val centerLetter = pickOne(allLetters)
    val ringLetters = for (_ <- 1 to 6) yield pickOne(allLetters)

    println(centerLetter)
    println(ringLetters)
  }
}
