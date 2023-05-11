package httpRequest;
import subscription.SingleSubscription;


/* Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias
 * Leer sobre como hacer una http request en java
 * https://www.baeldung.com/java-http-request
 * */

public class httpRequester {

	public String getFeedRss(SingleSubscription sub, int n){
		/* Crea la url */
		UrlBuilder urlBuilder = new UrlBuilder();
		String urlFeed = urlBuilder.buildUrl(sub, n);

		/* Establece coneccion al sitio */
		StringBuffer responseContent = new StringBuffer();
		conectionHandler conectionHandler = new conectionHandler();
		responseContent = conectionHandler.conHandler(urlFeed);

		// guarda los datos en un archivo xml
		fileManager fileManager = new fileManager();
		String filePath = fileManager.fileBuilder("xml-files/", sub.getUlrParams(n), ".xml", responseContent.toString());

		//se devuelve el contenido
		return filePath;
	}

	public String getFeedReddit(SingleSubscription sub, int n) {
		/* Crea la url */
		UrlBuilder urlBuilder = new UrlBuilder();
		String urlFeed = urlBuilder.buildUrl(sub, n);
		
		/* Establece coneccion al sitio */
		StringBuffer responseContent = new StringBuffer();
		conectionHandler conectionHandler = new conectionHandler();
		responseContent = conectionHandler.conHandler(urlFeed);
			
		/* guarda los datos en un archivo json */
		fileManager fileManager = new fileManager();
		String filePath = fileManager.fileBuilder("json-files/", sub.getUlrParams(n), ".json", responseContent.toString());

		//se devuelve el contenido
		return filePath;
	}
}
