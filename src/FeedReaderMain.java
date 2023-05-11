import httpRequest.httpRequester;
import parser.SubscriptionParser;
import subscription.SingleSubscription;
import subscription.Subscription;
import parser.RedditParser;
import parser.RssParser;

import namedEntity.heuristic.QuickHeuristic;
import namedEntity.heuristic.RandomHeuristic;




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

		    SubscriptionParser subParser = new SubscriptionParser("grupo04_lab02_2023/config/subscriptions.json");
			Subscription subs = subParser.parser(); 

			System.out.println("/n Feeds disponibles: /n");
			System.out.println("Ingrese el numero del feed que desea leer: ");
			for (int i = 0; i < subs.getSubscriptionListSize(); i++) {
				String site = subs.getSingleSubscription(i).getUrl();
				// slices the url to get the site name, from "https://" to "/"
				site = site.substring(8, site.indexOf("/", 8));
				System.out.println(i + " - " + site);
			}

			// asks for input
			int feedNumber = Integer.parseInt(System.console().readLine());

			System.out.println(subs.getSubscriptionListSize());
			if (feedNumber >= 0 && feedNumber < subs.getSubscriptionListSize()) {
				SingleSubscription chosenSub = subs.getSingleSubscription(feedNumber);
				
				System.out.println("Seleccione el tipo de articulos que desea leer: ");
				for (int i = 0; i < chosenSub.getUlrParamsSize(); i++) {
					System.out.println(i + " - " + chosenSub.getUlrParams(i));
				}
				int artNumber = Integer.parseInt(System.console().readLine());
				
				if (artNumber >= 0 && artNumber < chosenSub.getUlrParamsSize()) {
					String urlType = chosenSub.getUrlType();
					httpRequester httpReq = new httpRequester();
					Feed feed = new Feed(urlType);

					if (urlType.equals("reddit")) {
						
						String redditReq = httpReq.getFeedReddit(chosenSub, artNumber);
						RedditParser feedParser = new RedditParser(redditReq);
						feed = feedParser.feedParser();

						//feed.prettyPrint();

					} else if (urlType.equals("rss")) {

						String rssReq = httpReq.getFeedRss(chosenSub, artNumber);
						RssParser feedParser = new RssParser(rssReq);
						feed = feedParser.feedParser();
						
						//feed.prettyPrint();
					} 
					
					QuickHeuristic qh = new QuickHeuristic();
					qh.euristicTest(feed);
				} else {
					System.out.println("Invalid article number");
				}

			} else {
				System.out.println("Invalid feed number");
				}

			
		} else if (args.length == 1 && args[0].equals("-ne")){
			
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
