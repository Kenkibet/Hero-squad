import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) throws Exception {
        staticFileLocation("public");
        String appFile = "app.vtl";
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 3000;
        }
        port(port);

        get("/", (Request request, Response response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = "Home";
            model.put("title",title);
            if(Squad.getSquads().size()>0){
                model.put("squads",Squad.getSquads());
            }
            model.put("template","templates/main.vtl");
            return new ModelAndView(model, appFile);
        }, new ViewEngine());

        get("/squad/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = "New Squad";
            model.put("title",title);
            model.put("template","templates/add-squad.vtl");
            return new ModelAndView(model, appFile);
        }, new ViewEngine());
        post("/squad/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("squad-name");
            int size = Integer.parseInt(request.queryParams("squad-size"));
            String reason = request.queryParams("squad-cause");
            Squad squad = new Squad(size,name,reason);
            String title = "New Squad";
            request.session().attribute("squads", Squad.getSquads());
            model.put("title",title);
            model.put("squad", squad);
            model.put("template","templates/success.vtl");
            return new ModelAndView(model, appFile);
        }, new ViewEngine());

        get("/squad/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Squad chosenSquad = Squad.find(Integer.parseInt(request.params("id")));
            model.put("title", chosenSquad.getName());
            model.put("squad",chosenSquad);
            model.put("template", "templates/details.vtl");
            return new ModelAndView(model, appFile);
        }, new ViewEngine());

        post("/squad/:id/addhero", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            Squad chosenSquad = Squad.find(Integer.parseInt(request.params("id")));
            if(chosenSquad.getHeros().size()==chosenSquad.getMaxSize()){
                String message = "Could not add the hero to squad. It is already full. ";
                model.put("message", message);
            }else{
                String heroName = request.queryParams("hero-name");
                int heroAge = Integer.parseInt(request.queryParams("hero-age"));
                String heroPower =request.queryParams("special-power");
                String heroWeakness = request.queryParams("weakness");
                Hero myHero = new Hero(heroName,heroAge,heroPower,heroWeakness);
                request.session().attribute("heroes", Hero.getHeroes());
                chosenSquad.getHeros().add(myHero);
                String message = "The Hero was added successfully";
                model.put("message", message);
            }

            model.put("title", chosenSquad.getName());
            model.put("squad",chosenSquad);
            model.put("template", "templates/details.vtl");
            return new ModelAndView(model, appFile);
        },new ViewEngine());


        get("/squad/delete/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.remove(Integer.parseInt(request.params("id")));
            model.put("title","Deleted Successfully");
            model.put("template", "templates/main.vtl");
            return new ModelAndView(model, appFile);
        }, new ViewEngine());

        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("squads", request.session().attribute("squads"));
            model.put("template","templates/squads.vtl");
            model.put("title","Squads");
            return new ModelAndView(model, appFile);
        }, new ViewEngine());

        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("heroes", request.session().attribute("heroes"));
            model.put("title", "Heroes");
            model.put("template", "templates/heroes.vtl");
            return new ModelAndView(model, appFile);
        }, new ViewEngine());

        get("/hero/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Hero chosenHero = Hero.find(Integer.parseInt(request.params("id")));
            model.put("title", chosenHero.getName());
            model.put("hero", chosenHero);
            model.put("template", "templates/detail.vtl");
            return new ModelAndView(model, appFile);
        },new ViewEngine());


        get("/hero/delete/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.remove(Integer.parseInt(request.params("id")));
            model.put("title","Deleted Successfully");
            model.put("template", "templates/main.vtl");
            return new ModelAndView(model, appFile);
        }, new ViewEngine());
    }
}
