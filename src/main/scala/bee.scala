import scala.util.Random
import scala.io.Source

object Main {
  def main(args: Array[String]) {
    // all of the letters
    val allLetters = ('a' to 'z') toList

    // select seven at random
    val random = new Random
    // TODO: better heuristic than purely random (need vowels etc.)
    val availableLetters = Random.shuffle((0 until allLetters.length).toList) take 7 map allLetters
    val centerLetter = availableLetters.head
    val ringLetters = availableLetters.tail toSet


    // load dictionary and find words spelled with only these letters
    val words = Source.fromResource("english-words/words_alpha.txt")
      .getLines()
      .filter((w: String) => w.length() > 3)
      .filter((w: String) => w contains centerLetter)
      .filter((w: String) => w.toSet.diff(ringLetters + centerLetter).size == 0)

    // define template for prompt to maximize information conveyed to user
    val prompt = (n: Int) => {
      val cLet = centerLetter.toUpper
      val letSet = availableLetters.map((v: Char) => v.toUpper).sorted.mkString(", ")

      println("\nWords must contain the letter " + cLet + ".")
      println("Words cannot contain any letters other than " + letSet)
      println("Progress: " + n + " words guessed.")
      println("Guess a word or press <enter> to exit.")
      print("> ")
    }

    prompt(centerLetter, ringLetters, 0)

    // TODO: prompt user for words until all are found or they give up

    // TODO: display score
  }
}
