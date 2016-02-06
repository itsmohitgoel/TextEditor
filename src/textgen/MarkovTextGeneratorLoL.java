package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of distinct words with their possible next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The previous "word"
	private ListNode prevWord;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if(sourceText.equals("")){ // Training not possible on empty string
			return;
		}
		
		if(!starter.equals("") && !wordList.isEmpty()){
			starter = "";
			wordList = new LinkedList<>();
		}
		
		List<String> availaibleWords = getAllWords(sourceText);
		if(availaibleWords == null || availaibleWords.isEmpty()){
			return;
		}
		
		starter = availaibleWords.get(0);
		prevWord = new ListNode(starter);
		String currentWordString = null;
		
		// For each word 'currentWord' in source text starting at second word
		for (int i = 1; i < availaibleWords.size(); i++) {
			currentWordString = availaibleWords.get(i);
			if (isWordPresent(prevWord)) {
				prevWord.addNextWord(currentWordString);
			} else {
				wordList.add(prevWord);
				prevWord.addNextWord(currentWordString);
			}
			
			// Set the prevWord to be the current word
			prevWord = new ListNode(currentWordString);
		}
		
		if(isWordPresent(prevWord)){
			prevWord.addNextWord(starter);
		}else{
			prevWord.addNextWord(starter);
			wordList.add(prevWord);
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if(numWords == 0){
			return "";
		}
		if(starter.equals("") && wordList.isEmpty()){
			return null;
		}
		
		String currentWord = starter;
		StringBuilder output = new StringBuilder();
		output.append(currentWord);
		--numWords;
		while(numWords > 0){
			ListNode wordNode = findWordNode(currentWord);
			String nextword = wordNode.getRandomNextWord(rnGenerator);
			
			output.append(" ").append(nextword);
			currentWord = nextword;
			
			--numWords;
		}
		return output.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		if(sourceText.equals("")){
			starter = "";
			wordList = new LinkedList<>();
			return;
		}
		wordList = new LinkedList<>();
		starter = "";
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	/** Return list of strings, representing each word in the source text
	 * , duplicate words should be allowed
	 * @param text
	 * @return
	 */
	private List<String> getAllWords(String text){
		List<String> tokens = getTokens("[^\\s]+", text);
		if(tokens.isEmpty() || tokens == null){
			return null;
		}
		List<String> allWordsList = new ArrayList<>();
		
		for (String string : tokens) {
//			if(isWord(string)){
				allWordsList.add(string);
//			}
		}
		
		return allWordsList;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	private List<String> getTokens(String pattern, String text)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	private boolean isWordPresent(ListNode word){
		if(wordList.isEmpty() || wordList == null){
			return false;
		}
		
		for (int i = 0; i < wordList.size(); i++) {
			ListNode wordNode = wordList.get(i);
			if(wordNode.getWord().equals(word.getWord())){
				prevWord = wordNode;
				return true;
			}
		}
		return false;
	}
	
	/** Find the String as node in wordList of ListNode objects
	 * @param String
	 * @return ListNode reference if present, null otherwise.
	 */
	private ListNode findWordNode(String wordString){
		for (int i = 0; i < wordList.size(); i++) {
			ListNode tempWord = wordList.get(i);
			if(tempWord.getWord().equals(wordString)){
				return tempWord;
			}
		}
		return null;
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
//		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		String textString = "hi there hi there";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println("Yo Yo text: " +gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println("Yo Yo Text2: " +gen.generateText(20));
	}

} 

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	// The duplicate list of next words
	private List<String> cloneNextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
		cloneNextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
		cloneNextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		
		//Create duplicate list of available nextWords
		if(cloneNextWords.isEmpty()){
			for (String string : nextWords) {
				cloneNextWords.add(string);
			}
		}
		
		int nextWordIndex = generator.nextInt(cloneNextWords.size());
		
	    return cloneNextWords.remove(nextWordIndex);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


