package hangman;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {

	public static void main(String[] args) throws FileNotFoundException {

		//Accessing the local JSON file:
		File wordList = new File("C:\\Users\\timmc\\eclipse-workspace\\hangman\\src\\hangman\\wordlist.txt");
		Scanner wordScanner = new Scanner(wordList);
		// Making an array for the new array to be read into:
		List<String> words = new ArrayList<>();
		while (wordScanner.hasNext())  {
			words.add(wordScanner.nextLine());
			} 
		System.out.println("The list of words: " + words); 
				
		// Accessing a random word:
		Random rand = new Random();
		String word = words.get(rand.nextInt(words.size()));
		System.out.println("A random word: " + word); 

		// List of letters that the player has guessed. 
		List<Character> playerGuessesList = new ArrayList<>();
		
		//Iterate through the entire word with the plays letter list - if word is in there show the letter, if not, show a dash. 
		extractWord(word, playerGuessesList);
		
		// User Input
		Scanner input = new Scanner(System.in);
		
		// Initialising wrongCount variable to count how many times user is incorrect:
	    Integer wrongCount = 0;
		
		// Loop that gets input from user and adds to the list
		while (true) {
		     printHangedMan(wrongCount);


		      if (wrongCount >= 6) {
		        System.out.println("You lose!");
		        System.out.println("The word was: " + word);
		        break;
		      }
		      
		      extractWord(word, playerGuessesList);
		      if (!getPlayerGuess(word, playerGuessesList, input)) {
		        wrongCount++;
		      }
		      
		      if(extractWord(word, playerGuessesList)) {
		        System.out.println("You win!");
		        break;
		      }
		      
		      System.out.println("Please enter your guess for the word: ");
		      if(input.nextLine().equals(word)) {
		        System.out.println("You won!");
		        break;
		      }
		      else {
		        System.out.println("Nope! Try again.");
		      }
		}		
		
	}

	private static void printHangedMan(Integer wrongCount) {
//	    System.out.println(" -------");
//	    System.out.println(" |     |");
	    if (wrongCount >= 1) {
	      System.out.println(" ____________");
	    }
	    
	    if (wrongCount >= 2) {
	      System.out.println("   |	 |  ");
	      if (wrongCount >= 3) {
	        System.out.println("   |     O  ");
	      }
	      else {
	        System.out.println("");
	      }
	    }
	    
	    if (wrongCount >= 4) {
	      System.out.println("   |	\\ / ");
	    }
	    
	    if (wrongCount >= 5) {
	      System.out.println("   |	 |  ");
	      if (wrongCount >= 6) {
	        System.out.println("   |	/ \\");
	      }
	      else {
	        System.out.println("   |\r\n"
	        		+ "   |\r\n"
	        		+ "   |\r\n"
	        		+ "   |\r\n"
	        		+ "___|___");
	      }
	    }
	    
	    if (wrongCount >= 7) {
		      System.out.println("___|___");
		    }
	    
	    System.out.println("");
	    System.out.println("");
	  }
	
	private static boolean getPlayerGuess(String word, List<Character> playerGuessesList, Scanner input) {
		System.out.println("Please enter a letter... ");
		String letterGuess = input.nextLine();
		playerGuessesList.add(letterGuess.charAt(0));
		
		return word.contains(letterGuess);
	}

	private static boolean extractWord(String word, List<Character> playerGuessesList) {
		int correctCount = 0;
		for (int i = 0; i < word.length(); i++) {
			if (playerGuessesList.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
				correctCount++;
			} else {
				System.out.print("-");
			}
		}
		System.out.println();
			
		// This will return true until the player has correctly guessed all correct letters in the word:
		return (word.length() == correctCount);
	}

}
