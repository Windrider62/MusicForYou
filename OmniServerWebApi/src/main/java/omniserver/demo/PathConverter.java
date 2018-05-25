package omniserver.demo;



public class PathConverter {

    public String path;
    public PathConverter( ){

    }
    public static String StringConverter(String oldPath){
        if(System.getProperty("os.name").contains("Windows")){
             return oldPath;
        }
        else {
            return oldPath.replace("\\","/");
        }
    }
}
