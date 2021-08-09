package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private int id;
    private int maxSize;
    private String name;
    private String reason;
    private List<Hero> heroes;
    private static List<Squad> squads = new ArrayList<>();

    public Squad(int maxSize, String name, String reason) {
        this.maxSize = maxSize;
        this.name = name;
        this.reason = reason;
        squads.add(this);
        heroes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public static List<Squad> getSquads() {
        return squads;
    }

    public static void setSquads(List<Squad> squads) {
        Squad.squads = squads;
    }
}
