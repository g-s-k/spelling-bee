import scala.util.Random

object Main {
  def main(args: Array[String]) {
    // all of the letters
    val allLetters = ('A'.to('Z')) toList

    // select seven at random
    val random = new Random
    // TODO: better heuristic than purely random (need vowels etc.)
    val pickOne = (l: List[Char]) => l(random.nextInt(l.length))
    val centerLetter = pickOne(allLetters)
    val ringLetters = for (_ <- 1 to 6) yield pickOne(allLetters)

    // TODO: load dictionary and find words spelled with only these letters

    // TODO: define template for prompt to maximize information conveyed to user

    // TODO: prompt user for words until all are found or they give up

    // TODO: display score

    println(centerLetter)
    println(ringLetters)
  }
}
