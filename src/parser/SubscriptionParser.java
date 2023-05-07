package parser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import subscription.SingleSubscription;
import subscription.Subscription;
/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser{
   
	
    
	public SubscriptionParser(String fname) {
		
		super(fname);
		
	}
	
	// Devuelve un objeto de tipo Subscription


	public Subscription parser() {
		
		// Crear nueva suscripcion
		subscription.Subscription subs = new Subscription(this.filename);
		
		try {
			// Parsear el archivo JSON
			FileReader reader = new FileReader(this.filename);
			
			// Crear el JSONarray
			JSONArray jsonArray = new JSONArray(new JSONTokener(reader));  
			
			// Iterar los objetos del array (JSONArray)
			for (int i = 0; i < jsonArray.length(); i++) {  
              
				// Guardar cada objeto en explrObject para poder manipularlos
				JSONObject explrObject = jsonArray.getJSONObject(i);  

				// Crear lista de Strings para los parametros de la url
				List<String> paramstmp =  new ArrayList<String>();

				// Agregar cada campo del array a la lista de parametros para nuestro objeto SingleSubscription
				for (int j = 0; j < explrObject.getJSONArray("urlParams").length(); j++) {  
					paramstmp.add(explrObject.getJSONArray("urlParams").getString(j));
				}

				// Agregar la Singlesuscription al objeto tipo Suscription
				subs.addSingleSubscription(new SingleSubscription(explrObject.getString("url"), paramstmp, explrObject.getString("urlType")));

			}      
			
			
			return subs;
		}
		catch(Exception e) {
			System.out.println("Error al parsear el archivo de suscripcion: " + e.getMessage());
			return subs;
	    }
		
	}
}