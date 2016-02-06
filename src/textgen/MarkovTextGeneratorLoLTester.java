package textgen;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

/**
 * @author Mohit Goel
 */
public class MarkovTextGeneratorLoLTester {

	private String textString1 = "hi there hi leo";
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
	private Random rnGenerator;
	private MarkovTextGeneratorLoL mtGen1;
	private WordNode shortWordNode;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception{
		rnGenerator = new Random(42);
		
		mtGen1 = new MarkovTextGeneratorLoL(rnGenerator);
		
		shortWordNode = new WordNode("hi");
		shortWordNode.addNextWord("hello");
		shortWordNode.addNextWord("brother");
		shortWordNode.addNextWord("hello.");
		shortWordNode.addNextWord("sister");
		shortWordNode.addNextWord("friend");
	}
	
	@Test
	public void testGenerateText(){
		//Test Text Generation before even training.
			String generatedText = mtGen1.generateText(5);
			assertEquals("MTGLOL: check text generation before training", null, generatedText);
	}
	
	@Test
	public void testGetRandomNextWord(){
		// Test generation of specified count
		mtGen1.train(textString2);
		System.out.println(mtGen1.generateText(100));
		for (int i = 0; i < 5; i++) {
		System.out.print(shortWordNode.getRandomNextWord(rnGenerator) + ", ");
		}
	}
}
