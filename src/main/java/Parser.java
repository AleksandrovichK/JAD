import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author AleksandrovichK
 */
class Parser {
    Parser() {
    }

    private String parseFile(String path) {
        InputStream io = getClass().getResourceAsStream(path);
        Scanner s = new Scanner(io).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    List<RestClass> parseJavaClasses() {
        List<RestClass> restClasses = new LinkedList<>();
        File[] folderEntries = new File("D:/Workspace3/JAD/src/main/resources").listFiles();

        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (!entry.getName().equals("RestApiEndpoints.java")) {
                    var code = parseFile("/" + entry.getName());
                    var rest = handleFile(code);
                    rest.setClassName(entry.getName().substring(0, entry.getName().lastIndexOf(".")));
                    restClasses.add(rest);
                }
            }
        }
        return restClasses;
    }

    private RestClass handleFile(String code) {
        var lines = code.split("\r\n");
        var globalPath = "";
        var recorded = false;

        var restClass = new RestClass();
        for (String line : lines) {
            if (line.contains("@Path")) {
                globalPath = line.substring(line.indexOf("@Path") + 6);
                globalPath = globalPath.substring(0, globalPath.indexOf(")"));
            }
            if (line.contains("public class"))
                break;
        }

        Service srv = null;

        for (String line : lines) {
            if (!recorded && line.contains("@GET") || line.contains("@PUT") || line.contains("@POST") || line.contains("@DELETE")) {
                recorded = true;
                srv = new Service();

                srv.type = line.substring(line.indexOf('@'));
            }

            if (recorded) {
                if (line.contains("@Path")) {
                    var url = line.substring(line.indexOf("@Path") + 6);
                    url = url.substring(0, url.indexOf(")"));
                    srv.url = globalPath + " + " + url;
                }

                if (line.contains("@ApiOperation")) {
                    var desc = line.substring(line.indexOf("\"") + 1);
                    desc = desc.substring(0, desc.lastIndexOf("\""));
                    srv.description = desc;
                }

                if (line.contains("Response")) {
                    recorded = false;
                    if (srv.url == null) {
                        srv.url = globalPath;
                    }
                    restClass.pushService(srv);
                }
            }
        }
        return restClass;
    }

    Map<String, String> parseRestPaths(String path) {
        var paths = new HashMap<String, String>();
        var str = parseFile(path);
        var lines = str.split("\r\n");
        var subdivision = new StringBuilder();
        var currentInterface = "";

        for (String line : lines) {
            if (line.contains("interface")) {
                currentInterface = line.substring(line.indexOf("interface") + 10);
                currentInterface = currentInterface.substring(0, currentInterface.indexOf('{') - 1);

                if (!subdivision.toString().equals("")) {
                    subdivision.append('.');
                }
                subdivision.append(currentInterface);
            }

            if (line.contains("String")) {
                paths.put(subdivision + "." + line.substring(line.indexOf("String") + 7, line.indexOf('=') - 1),
                        line.substring(line.indexOf('\"') + 1, line.lastIndexOf('\"')));
            }

            if (line.contains("}") && !line.contains("{") && subdivision.toString().contains(".")) {
                var lastDotPlace = subdivision.lastIndexOf(".");
                subdivision.replace(lastDotPlace, subdivision.length(), "");
            }
        }

        return paths;
    }
}
