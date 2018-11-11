import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * @author AleksandrovichK
 */
class Parser {
    Parser(){
    }

    private String parseFile(String path){
        InputStream io =  getClass().getResourceAsStream(path);
        Scanner s = new Scanner(io).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    RestClass parseJavaClass(String path){
        var str = parseFile(path);
        var lines = str.split("\r\n");

        var restClass = new RestClass();
        for (String line : lines) {
           if (line.contains("@GET") || line.contains("@PUT") || line.contains("@POST") || line.contains("@DELETE")){
               Service srv = new Service();

               srv.url = line.substring(line.indexOf('@'));
               restClass.pushService(srv);
           }
        }

        return restClass;
    }

    public List<String> parseRestPathes(String path) {
        var str = parseFile(path);
        var lines = str.split("\r\n");


        return null;
    }
}
