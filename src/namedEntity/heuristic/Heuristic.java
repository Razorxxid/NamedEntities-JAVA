package namedEntity.heuristic;

import java.util.HashMap;
import java.util.Map;

public abstract class Heuristic {

	private static Map<String, String> categoryMap;

	static {
		/* extendida por chat gpt */
		categoryMap = new HashMap<>();
		categoryMap.put("Microsoft", "Company");
		categoryMap.put("Apple", "Company");
		categoryMap.put("Google", "Company");
		categoryMap.put("Facebook", "Company");
		categoryMap.put("Amazon", "Company");
		categoryMap.put("Tesla", "Company");
		categoryMap.put("Alphabet", "Company");
		categoryMap.put("IBM", "Company");
		categoryMap.put("Samsung", "Company");
		categoryMap.put("General Electric", "Company");
		categoryMap.put("Intel", "Company");
		categoryMap.put("Sony", "Company");
		categoryMap.put("Huawei", "Company");
		categoryMap.put("Oracle", "Company");
		categoryMap.put("HP", "Company");
		categoryMap.put("Dell", "Company");
		categoryMap.put("Cisco", "Company");
		categoryMap.put("Lenovo", "Company");
		categoryMap.put("Nvidia", "Company");
		categoryMap.put("Qualcomm", "Company");
		categoryMap.put("AMD", "Company");
		categoryMap.put("Netflix", "Company");
		categoryMap.put("PayPal", "Company");
		categoryMap.put("Barcelona", "SportsTeam");
		categoryMap.put("Musk", "Person");
		categoryMap.put("Biden", "Person");
		categoryMap.put("Trump", "Person");
		categoryMap.put("Obama", "Person");
		categoryMap.put("Messi", "Person");
		categoryMap.put("Federer", "Person");
		categoryMap.put("Williams", "Person");
		categoryMap.put("Nadal", "Person");
		categoryMap.put("Hamilton", "Person");
		categoryMap.put("Vettel", "Person");
		categoryMap.put("Lebron", "Person");
		categoryMap.put("Neymar", "Person");
		categoryMap.put("Jordan", "Person");
		categoryMap.put("Kobe", "Person");
		categoryMap.put("Ronaldo", "Person");
		categoryMap.put("Djokovic", "Person");
		categoryMap.put("Bolt", "Person");
		categoryMap.put("India", "Country");
		categoryMap.put("China", "Country");
		categoryMap.put("Russia", "Country");
		categoryMap.put("UK", "Country");
		categoryMap.put("USA", "Country");
		categoryMap.put("Brazil", "Country");
		categoryMap.put("Japan", "Country");
		categoryMap.put("France", "Country");
		categoryMap.put("Germany", "Country");
		categoryMap.put("Italy", "Country");
		categoryMap.put("Spain", "Country");
		categoryMap.put("Canada", "Country");
		categoryMap.put("Mexico", "Country");
		categoryMap.put("Argentina", "Country");
		categoryMap.put("Australia", "Country");
		categoryMap.put("Chile", "Country");
		categoryMap.put("Colombia", "Country");
		categoryMap.put("Peru", "Country");

	}
	
	public String getCategory(String entity){
		return categoryMap.get(entity);
	}
	
	
	public abstract boolean isEntity(String word);
		
}
