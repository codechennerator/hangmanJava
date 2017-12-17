/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.util.ArrayList;
import java.io.*;
import acm.util.*;

public class HangmanLexicon {
	
	private ArrayList<String> strList = new ArrayList<String>();
	
	public HangmanLexicon() {
		//your initialization code goes here
		BufferedReader rd = null;
		while (rd == null){
			try{
				rd = new BufferedReader(new FileReader("ShorterLexicon.txt"));
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
		
	}


/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return strList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return strList.get(index);
		
		/* switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		} */
	};
}
