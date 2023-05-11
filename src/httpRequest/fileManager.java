package httpRequest;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileManager {
    
    public String fileBuilder(String folder, String filename, String fileType, String content) {
        
        // crea la carpeta si no existe
        File fold = new File(folder);
        if (!fold.exists()) {
            fold.mkdir();
        }

        // crea el nombre del archivo usando el url
        String filePath = folder + filename + fileType;
        FileWriter file;
        try {
            file = new FileWriter(filePath);
            file.write(content);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filePath;
        
    }

}
