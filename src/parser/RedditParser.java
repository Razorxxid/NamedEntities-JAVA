package parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
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
			FileReader fileReader = new FileReader(this.filename);
			StringBuilder stringBuilder = new StringBuilder();
			int c;
			while ((c = fileReader.read()) != -1) {
				stringBuilder.append((char) c);
			}
			fileReader.close();
			String jsonStr = stringBuilder.toString();
			JSONObject jsonData = new JSONObject(jsonStr);
	
			// Get the children array from the data object
			JSONArray children = jsonData.getJSONObject("data").getJSONArray("children");
	
			// Loop through the children array and get the data for each child
			for (int i = 0; i < children.length(); i++) {
				JSONObject childData = children.getJSONObject(i).getJSONObject("data");
	
				// Get the created_utc property from the data object
				long createdUtc = childData.getLong("created_utc");
	
				// Convert the timestamp to a Date object
				Date pubDate = new Date(createdUtc * 1000);
	
				// Get the other properties you need from the data object
				String title = childData.getString("title");
				String description = childData.getString("selftext");
				String link = childData.getString("url");
	
				// Create a new Article object with the properties above
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

	
	
	

