package parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import feed.Feed;
import feed.Article;

/*
 * Esta clase implementa el parser de feed de tipo reddit (json)
 * pero no es necesario su implemntacion 
 * */

public class RedditParser extends GeneralParser {
	

	public RedditParser(String fname) {
		super(fname);
		
	}

	public Feed feedParser() {
		Feed feed = new Feed(this.filename);
		try {
			// Lee el archivo JSON
			FileReader fileReader = new FileReader(this.getFilename());
			StringBuilder stringBuilder = new StringBuilder();
			int c;
			while ((c = fileReader.read()) != -1) {
				stringBuilder.append((char) c);
			}
			fileReader.close();
			String jsonStr = stringBuilder.toString();
			JSONObject jsonData = new JSONObject(jsonStr);
	
			// Obtiene el arreglo children del objeto data
			JSONArray children = jsonData.getJSONObject("data").getJSONArray("children");
	
			// Itera sobre los elementos del arreglo children
			for (int i = 0; i < children.length(); i++) {
				JSONObject childData = children.getJSONObject(i).getJSONObject("data");
	
				// Obtiene el timestamp de la creacion del articulo
				long createdUtc = childData.getLong("created_utc");
	
				// Crea un objeto Date con el timestamp
				Date pubDate = new Date(createdUtc * 1000);
	
				// Obtiene los demas datos del articulo
				String title = childData.getString("title");
				String description = childData.getString("selftext");
				String link = childData.getString("url");
	
				// Crea un objeto Article y lo agrega al feed
				Article art = new Article(title, description, pubDate, link);
				feed.addArticle(art);
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
			return null;
		}
		return feed;
	}
}

	
	
	

