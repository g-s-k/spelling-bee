import scala.util.Random
import scala.io.Source

object Main {
  def main(args: Array[String]) {
    // all of the letters
    val allLetters = ('a' to 'z') toList

    // select seven at random
    val random = new Random
    // TODO: better heuristic than purely random (need vowels etc.)
    val centerLetter = allLetters(random.nextInt(allLetters.length))
    val ringLetters = Random.shuffle((0 until allLetters.length).toList) take 6 map allLetters toSet

    // load dictionary and find words spelled with only these letters
    val words = Source.fromResource("english-words/words_alpha.txt")
      .getLines()
      .filter((w: String) => w.length() > 3)
      .filter((w: String) => w contains centerLetter)
      .filter((w: String) => w.toSet.diff(ringLetters + centerLetter).size == 0)

    // TODO: define template for prompt to maximize information conveyed to user

    // TODO: prompt user for words until all are found or they give up

    // TODO: display score

    println(centerLetter)
    println(ringLetters)
  }
}
