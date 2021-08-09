package models;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private int id;
    private String name;
    private int age;
    private String power;
    private String weakness;
    private static List<Hero> heroes = new ArrayList<>();

    public Hero(String name, int age, String power, String weakness) {
        this.name = name;
        this.age = age;
        this.power = power;
        this.weakness = weakness;
        heroes.add(this);
        id = heroes.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public static List<Hero> getHeroes() {
        return heroes;
    }

    public static void setHeroes(List<Hero> heroes) {
        Hero.heroes = heroes;
    }

    public static Hero find(int id){
        return heroes.get(id-1);
    }

    public static void remove(int id){
        heroes.remove(id-1);
        for (Hero hero:heroes) {
            hero.id = heroes.indexOf(hero)+1;
        }
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", power='" + power + '\'' +
                ", weakness='" + weakness + '\'' +
                '}';
    }
}
