package omniserver.fileEditor;


import java.io.File;

public class PathConverter {

    public String path;
    public PathConverter( ){

    }
    public static String StringConverter(String oldPath){


        if(System.getProperty("os.name").contains("Windows")){
            return oldPath.replace("/", "\\");
        }
        else {
            return oldPath;
        }
    }
}
