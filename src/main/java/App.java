import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("public");
        String appFile = "app.vtl";
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
    }
}
