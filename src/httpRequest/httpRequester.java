package httpRequest;

import subscription.SingleSubscription;
import subscription.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import org.json.*;
import java.io.File;
import java.io.FileWriter;


/* Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias
 * Leer sobre como hacer una http request en java
 * https://www.baeldung.com/java-http-request
 * */

public class httpRequester {

	public String getFeedRss(String urlFeed){
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();

		try {	
			URL url = new URL(urlFeed);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);

			int status = con.getResponseCode();
			System.out.println(status);

			if (status >299) {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			con.disconnect();
			return responseContent.toString();
			
		} catch (MalformedURLException e) {
			System.out.println("Error en la conexion");
			return null;
		} catch (IOException e){
			System.out.println("Error en la conexion");
			return null;
		}
	}

	public String getFeedReddit(String urlFeed) {
		BufferedReader reader;
		String line;
		StringBuilder responseContent = new StringBuilder();
	
		try {   
			URL url = new URL(urlFeed);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			
			con.setRequestProperty("User-Agent", "FeedReader 1.0 by /Grupo04Lab02");
			int status = con.getResponseCode();
			System.out.println(status);
	
			if (status >299) {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
	
			// return responseContent.toString();
			// try {
			// 	JSONObject jsonObject = new JSONObject(responseContent.toString());
			// 	// Write the JSON array to a file
			// 	File outputFile = new File("output.json");
			// 	FileWriter writer = new FileWriter(outputFile);
			// 	writer.write(jsonObject.toString());
			// 	writer.close();
			// } catch (JSONException e) {
			// 	e.printStackTrace();
			// }

			con.disconnect();
			return responseContent.toString();
	
		} catch (MalformedURLException e) {
			System.out.println("Error en la conexion");
			return null;
	
		} catch (IOException e){
			System.out.println("Error en la conexion");
			return null;
		}
	}
}


