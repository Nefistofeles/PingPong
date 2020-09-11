package textCreator;

import java.util.ArrayList;
import java.util.List;


public class Line {
	
	private double maxLength;

	private List<Word> words; 
	private double currentLineLength ;
	
	public Line(double maxLength) {
		this.maxLength = maxLength ;
		words = new ArrayList<Word>();
		currentLineLength = 0;
	}
	
	public boolean tryAddToWord(Word word) {
		double wordLength = word.getWidth() ;
		if (currentLineLength + wordLength <= maxLength ) {
			words.add(word);
			currentLineLength += wordLength;
			
			return true;
		} else {
			return false;
		}
	}

	public double getMaxLength() {
		return maxLength;
	}

	public double getCurrentLineLength() {
		return currentLineLength;
	}

	public List<Word> getWords() {
		return words;
	}
	
}
