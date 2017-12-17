/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.io.*;
import java.awt.*;
import java.util.*;

public class Hangman extends ConsoleProgram {
	
	public void init() {
		 canvas = new HangmanCanvas();
		 add(canvas);
		}

	public void run() {
		canvas.reset();
    	setUp();
    	playGame();
	}

    private void setUp(){
    	intro(); //prints out first line of game.
    	//getLexiconFile();
    	//readFileIntoArr(strList);
    }
		
    	/*private void getLexiconFile(){
			while (rd == null){
				try{
					rd = new BufferedReader(new FileReader("ShorterLexicon.txt"));
				}catch(IOException ex){
					System.out.println("Bad file.");
				}
			}
		}
		
		private void readFileIntoArr(ArrayList<String> strList){
			while(true){
				try{
					String line = rd.readLine();
					if (line == null) break;
					strList.add(line);
				}catch(IOException ex){
					throw new ErrorException(ex);
				}
			}
			try{
				rd.close();
			}catch(IOException ex){
				throw new ErrorException(ex);
			}
		}*/
	   
		/*A string method, generates the word we need. */
	    private String generateWord(){
			int index = rgen.nextInt(0 , hangmanWords.getWordCount() - 1);
	    	return hangmanWords.getWord(index);
	    }
	   
	    private void intro(){
	    	println("Welcome to Hangman!");
	    }
	    
	    
	    
    private void playGame(){
    	setWordStatus();
    	while (true){
	    	printWordStatus();
	    	if (guessesLeft == 0){
	    		println("You're completley hung.\n" +
	    				"The word was " + randWord + "\n" +
	    				"You lose.");
	    		break;
	    	}
	    	if (checkCompletion()){
	    		println("You win!");
	    		break;
	    	}
	    	printGuessStatus();
	    	takeGuess();
    	}
    }
	    private boolean checkCompletion(){
	    	return wordStatus.equals(randWord);
	    }
	    private void setWordStatus(){
	    	for (int i = 0; i < randWord.length(); i++){
	    		wordStatus += '-';
	    	}
	    }
	    private void printWordStatus(){
	    	/*The second part is to show the progress of the word, after at least the first guess*/
	    	println("The word now looks like this: " + wordStatus);
	    	canvas.displayWord(wordStatus);
	    }
	    private void printGuessStatus(){
	    	println("You have " + guessesLeft + " guesses left.");
	    }
	    private void takeGuess(){
	    	/*Take guess while error checking.*/
	    	while (true){
	    		guess = readLine("Your guess: ");
	    		if (guess.length() != 1) println("You may only guess one character at a time.") ;
	    		else if (Character.isDigit(guess.charAt(0))) println("You must enter a character.");
	    		else break;   
	    	}
	    	/*If the letter matches, replace '-' with the letter.*/
	    	guessedCh = Character.toUpperCase(guess.charAt(0));
	    	boolean isMatch = false;
	    	for (int i = 0; i< randWord.length(); i++){
	    		if (guessedCh == randWord.charAt(i)){
	    			wordStatus = wordStatus.substring(0, i) + guess.toUpperCase() + wordStatus.substring(i+1);
	    			isMatch = true;
	    		}
	    	}
	    	if (!isMatch){
	    		guessesLeft--;
	    		canvas.noteIncorrectGuess(guessedCh);
	    	}
	    }

    private RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanLexicon hangmanWords = new HangmanLexicon();
    private String randWord = generateWord();
    private String wordStatus = "";
    private int guessesLeft = 8;
    private char guessedCh;
    private HangmanCanvas canvas;
    public String guess = ""; //keeps track of the user's last guess.
    //public BufferedReader rd = null;
    //public ArrayList<String> strList = new ArrayList<String>();
    /*known glitches:
     * How to keep track of guesses.*/
}
