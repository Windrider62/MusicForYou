package omniserver.demo.fileEditor;

import java.io.File;
import java.io.FileWriter;

public class WriteFile {
    public  boolean WriteFileOperator(String path, String json){
        try{
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);

            fileWriter.flush();
            fileWriter.close();

            return true;
        }
        catch (Exception e){
            return false;
        }

    }
}
