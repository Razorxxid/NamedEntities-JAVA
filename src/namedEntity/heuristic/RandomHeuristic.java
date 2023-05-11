package namedEntity.heuristic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import feed.Feed;
import namedEntity.NamedEntity;

public class RandomHeuristic extends Heuristic {
	
	private Random rnd = new Random();
	
	//usefull for random heuritic's consistency
	private List<String> positiveCases = new ArrayList<String>();
	private List<String> negativeCases = new ArrayList<String>();
	
	private boolean isPositiveCase(String entity){
		return this.positiveCases.contains(entity);
	}

	private boolean isNegativeCase(String entity){
		return this.negativeCases.contains(entity);
	}

	public boolean isEntity(String word){
		//already it was classified
		if (this.isPositiveCase(word)) return true;
		if (this.isNegativeCase(word)) return false;
		
		//if it was not classified yet, then lottery
		boolean b = ((int)(rnd.nextDouble() * 100)) % 2 == 0;
		if (b) this.positiveCases.add(word);
		else this.negativeCases.add(word);
		return b;
								
	}

	
	public void euristicTest(Feed feed){
		RandomHeuristic rh = new RandomHeuristic();
		// named entity list
		List<NamedEntity> namedEntityList = new ArrayList<NamedEntity>();
		System.out.println(feed.getNumberOfArticles());
		for (int i = 0; i < feed.getNumberOfArticles(); i++){
			String[] words = feed.getArticle(i).getText().split(" ");
			// if it ends with "," , "." , "'s", remove from the string

			for (int j = 0; j < words.length; j++) {
				if (rh.isEntity(words[j])) {
					NamedEntity ne = new NamedEntity(words[j], rh.getCategory(words[j]), 1);
					ne.prettyPrint();
					if (!namedEntityList.contains(ne)) {
						namedEntityList.add(ne);
					} else {
						namedEntityList.get(namedEntityList.indexOf(ne)).incFrequency();
					}
			}
		}
		}
		for (int i = 1; i < namedEntityList.size(); i++) {
			namedEntityList.get(i).prettyPrint();
		}
	}
}
	

