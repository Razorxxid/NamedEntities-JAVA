package httpRequest;

import subscription.SingleSubscription;


public class UrlBuilder {


    // Devuelve la url construida
    public String buildUrl(SingleSubscription subs, int n) {
        
        // Crea url
        String url = subs.getUrl();
        
        // Obtener parametro n
        String param = subs.getUlrParams(n);

        // Reemplazar %s por el parametro
        url = url.replace("%s", param);

        // Devolver url
        return url;
        
    }
    
}