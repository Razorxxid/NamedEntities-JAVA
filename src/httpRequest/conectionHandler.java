package httpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class conectionHandler {

    public StringBuffer conHandler (String urlFeed) {
        BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
        URL url;
        try {
            /* Establece la coneccion */
            url = new URL(urlFeed);
            HttpURLConnection con;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestProperty("User-Agent", "FeedReader 1.0 by /Grupo04Lab02");
            int status = con.getResponseCode();

            /* Lee el output y lo escribe en responseContent */
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

            /* Se desconecta del sitio y devuelve los contenidos */
            con.disconnect();
            return responseContent;        
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
    }
    }
}