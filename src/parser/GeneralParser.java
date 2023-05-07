package parser;
import feed.Feed;



/*Esta clase modela los atributos y metodos comunes a todos los distintos tipos de parser existentes en la aplicacion*/
public abstract class GeneralParser {
    
	protected String filename;
    
    public GeneralParser(String fname) {
    	
    	this.filename = fname;
    	
    }

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	

}
