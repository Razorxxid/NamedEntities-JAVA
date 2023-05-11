package namedEntity.heuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import feed.Feed;
import namedEntity.EntityList;
import namedEntity.NamedEntity;

public class QuickHeuristic extends Heuristic{
	
	private static List<String> keyWords = Arrays.asList(
		    "i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you",
		    "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she",
		    "her", "hers", "herself", "it", "its", "itself", "they", "them", "your",
		    "their", "theirs", "themselves", "what", "which", "who", "whom",
		    "this", "that", "these", "those", "am", "is", "are", "was", "were",
		    "be", "been", "being", "have", "has", "had", "having", "do", "does",
		    "did", "doing", "a", "an", "the", "and", "but", "if", "or",
		    "because", "as", "until", "while", "of", "at", "by", "for", "with",
		    "about", "against", "between", "into", "through", "during", "before",
		    "after", "above", "below", "to", "from", "up", "down", "in", "out",
		    "off", "over", "under", "again", "further", "then", "once", "here",
		    "there", "when", "where", "why", "how", "all", "any", "both", "each",
		    "few", "more", "most", "other", "some", "such", "no", "nor", "not",
		    "only", "own", "same", "so", "than", "too", "very", "s", "t", "can",
		    "will", "just", "don", "should", "now", "on", "nsfw",
		    "im", "ive", "id", "Youre", "youd", "youve", "i'm", "i've", "i'd", "i’m" , "i’ve", "i’d",
		    "hes", "hed", "shes", "shed", "itd", "were", "wed", "weve",
		    "theyre", "theyd", "theyve",
		    "shouldnt", "couldnt", "musnt", "cant", "wont",
		    // Common uppercase words
		    "hi", "hello", "yet", "hey", "however", "hello!", "move" , "$"
			);
	
/*	
	public boolean isEntity(String word) {
		return word.length() > 1 && Character.isUpperCase(word.charAt(0)) && !keyWords.contains(word.toLowerCase());
	}
*/	
	
	public boolean isEntity(String word) {
		if (word.startsWith("“", 0) || word.startsWith("\"", 0) ) {
			word = word.substring(1, word.length()-1);
		}	
		boolean isEntity = word.length() > 1;
		isEntity = isEntity && word.substring(0, 1).compareTo(word.substring(0, 1).toUpperCase()) == 0;
		isEntity = isEntity && !keyWords.contains(word.toLowerCase());
		return isEntity;
	}
	/**
	 * @param feed
	 */
	public void euristicTest(Feed feed) {
		QuickHeuristic qh = new QuickHeuristic();
		// named entity list
		EntityList namedEntityList = new EntityList();
		for (int i = 0; i < feed.getNumberOfArticles(); i++){
			String[] words = feed.getArticle(i).getText().split(" ");

			for (int j = 0; j < words.length; j++) {
				if (qh.isEntity(words[j])) {
					String wordIs = words[j];
					// if the next word is entity too, add it to the string
					if (j < words.length - 1 && isEntity(words[j + 1])) {
						wordIs = words[j] + " " + words[j + 1];
						j++;
					}
					// if it ends with "," , "." , "'s", remove from the string
					if (wordIs.endsWith(",") || wordIs.endsWith("?") || wordIs.endsWith(".") || wordIs.endsWith("!") || wordIs.endsWith(":") || wordIs.endsWith(";")) {
						wordIs = words[j].substring(0, words[j].length() - 1);
					} else if (wordIs.endsWith("’s") || wordIs.endsWith("'s")) {
						wordIs = wordIs.substring(0, words[j].length() - 2);
					}
					
					
					NamedEntity ne = new NamedEntity(wordIs, qh.getCategory(wordIs), 1);
					namedEntityList.add(ne);
			}
		}
		}
		for (int i = 1; i < namedEntityList.size(); i++) {
			namedEntityList.get(i).prettyPrint();
		}
	}

	
	

}

