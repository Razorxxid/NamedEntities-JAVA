import httpRequest.httpRequester;
import parser.SubscriptionParser;
import subscription.Subscription;
import parser.RedditParser;
import parser.RssParser;

import feed.Feed;


public class FeedReaderMain {

	private static void printHelp(){
		System.out.println("Please, call this program in correct way: FeedReader [-ne]");
	}
	
	public static void main(String[] args) {
		System.out.println("************* FeedReader version 1.0 *************");
		if (args.length == 0) {

			/*
			Leer el archivo de suscription por defecto;
			Llamar al httpRequester para obtenr el feed del servidor
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion 
			Llamar al constructor de Feed
			LLamar al prettyPrint del Feed para ver los articulos del feed en forma legible y amigable para el usuario
			*/

			/*Test de Suscription parser*/
		    SubscriptionParser subParser = new SubscriptionParser("grupo04_lab02_2023/config/subscriptions.json");
			Subscription subs = subParser.parser(); 
			subs.prettyPrint();
			/*Test de httpRequester*/
			httpRequester httpReq = new httpRequester();
			String RedditReq = httpReq.getFeedReddit("https://www.reddit.com/r/Sales/hot/.json?count=100");
			String rssReq = httpReq.getFeedRss("https://rss.nytimes.com/services/xml/rss/nyt/Business.xml");
			/* tendra un feo output  */
			//System.out.println(RedditReq);
			//System.out.println(rssReq); 
			/*Test de parser*/
			RedditParser feedParser = new RedditParser("json-files/Sales.json");
			Feed feed = feedParser.feedParser();
			feed.prettyPrint();
			
		} else if (args.length == 1){
			
			/*
			Leer el archivo de suscription por defecto;
			Llamar al httpRequester para obtenr el feed del servidor
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion 
			Llamar al constructor de Feed
			Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
			LLamar al prettyPrint de la tabla de entidades nombradas del feed.
			 */
			
		}else {
			printHelp();
		}
	}

}
