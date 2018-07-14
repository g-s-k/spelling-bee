import scala.util.Random
import scala.io.Source

object Main {
  def main(args: Array[String]) {
    // all of the letters
    val vowels = Set('a', 'e', 'i', 'o', 'u')
    val consonants = ('a' to 'z').toSet.diff(vowels)

    // select one to three vowels and backfill with consonants
    val r = new Random
    val numVowels = 2 + r.nextInt(2)
    val pickedVowels = Random.shuffle(vowels).take(numVowels)
    val pickedConsonants = Random.shuffle(consonants).take(7 - numVowels)
    val availableLetters = Random.shuffle(pickedVowels union pickedConsonants).toList

    // pick a critical letter
    val centerLetter = availableLetters.head
    val ringLetters = availableLetters.tail toSet


    // load dictionary and find words spelled with only these letters
    val words = Source.fromResource("english-words/words_alpha.txt")
      .getLines()
      .filter((w: String) => w.length() > 3)
      .filter((w: String) => w contains centerLetter)
      .filter((w: String) => w.toSet.diff(ringLetters + centerLetter).size == 0)
      .toSet

    // define template for prompt to maximize information conveyed to user
    val cLet = centerLetter.toUpper
    val letSet = availableLetters
      .map((v: Char) => v.toUpper)
      .sorted
      .mkString(", ")

    val prompt = (n: Int) => {
      println("\nWords must contain the letter " + cLet + ".")
      println("Words cannot contain any letters other than " + letSet)
      println("Progress: " + n + " words guessed out of " + words.size + " possibilities.")
      println("Guess a word or press <enter> to exit.")
      print("> ")
    }

    // prompt user for words until all are found or they give up
    def repl(guessed: Set[String]) {
      var g_mut = guessed

      prompt(g_mut.size)
      val attempt = scala.io.StdIn.readLine().trim().toLowerCase()

      if (g_mut contains attempt) {
        println("You already got " + attempt + "! Try again.")
      } else if (words contains attempt) {
        g_mut += attempt
        println("Good job! " + attempt + " is a valid word.")
      } else if (attempt.length() != 0) {
        if (!(attempt contains centerLetter)) {
          println("Sorry, " + attempt + " does not contain the letter " + cLet + ".")
        } else {
          println("Sorry, " + attempt + " is not a valid word.")
        }
      }

      if (attempt.length() == 0 || words.size == g_mut.size) {
        println("\nYour final score was " + g_mut.size + "/" + words.size + ".\n")
      } else {
        repl(g_mut)
      }
    }

    println("\nWelcome to Spelling Bee!")
    repl(words.empty)
  }
}
