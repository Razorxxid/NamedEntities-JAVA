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

			/* Establece coneccion al sitio */
			URL url = new URL(urlFeed);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			int status = con.getResponseCode();
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

			// guarda los datos en un archivo xml

			// crea el nombre del archivo usando el url
			String fileName = "";
			int slashIndex = urlFeed.lastIndexOf('/');
			int dotIndex = urlFeed.lastIndexOf('.');
			if (slashIndex >= 0 && dotIndex > slashIndex) {
				fileName = urlFeed.substring(slashIndex + 1, dotIndex);
			}
			File outputFile = new File("xml-files/" + fileName + ".xml");
			
			// crea la carpeta xml-files si no existe
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
			
			// escribe el archivo y lo cierra al finalizar
			FileWriter writer = new FileWriter(outputFile);
			writer.write(responseContent.toString());
			writer.close();

			// se cierra la conexion y se devuelve el contenido
			con.disconnect();
			return responseContent.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}

	public String getFeedReddit(String urlFeed) {
		BufferedReader reader;
		String line;
		StringBuilder responseContent = new StringBuilder();
	
		try {   

			// Establece coneccion al sitio
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
		
			// guarda los datos en un archivo json
			// crea la carpeta json-files si no existe
			try {
			  File dir = new File("json-files");
			  if (!dir.exists()) {
				  dir.mkdirs();
				}
				
				// crea el nombre del archivo usando el url
				JSONObject jsonResponse = new JSONObject(responseContent.toString());
				String[] urlParts = urlFeed.split("/");
				String subredditName = "";
				
			  for (String part : urlParts) {
				  if (part.matches("[A-Z][a-z]+")) { // busca la primera palabra que empiece con mayuscula y siga con minusculas
					  subredditName = part;
					  break;
					}
				}
				
				// escribe el archivo y lo cierra al finalizar
				FileWriter file = new FileWriter("json-files/" + subredditName + ".json");
				file.write(jsonResponse.toString());
				file.flush();
				file.close();

				// se cierra la conexion y se devuelve el contenido
				con.disconnect();
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return responseContent.toString();
	
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
	
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}
}


