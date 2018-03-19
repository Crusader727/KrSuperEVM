package main.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonGenerator {
    private ArrayList<String> names;
    private ArrayList<String> lastnames;
    private ArrayList<String> positions;
    private Random rand;

    public PersonGenerator() {
        rand = new Random();
        positions = new ArrayList<>(Arrays.asList("headmaster", "vice", "guide", "guard", "manager"));
        names = new ArrayList<>(Arrays.asList("Allison", "Arthur", "Ana", "Alex", "Arlene", "Alberto",
                "Barry", "Bertha", "Bill", "Bonnie", "Bret", "Beryl", "Chantal", "Cristobal", "Claudette",
                "Charley", "Cindy", "Chris", "Dean", "Dolly", "Danny", "Danielle", "Dennis", "Debby", "Erin",
                "Edouard", "Erika", "Earl", "Emily", "Ernesto", "Felix", "Fay", "Fabian", "Frances", "Franklin",
                "Florence", "Gabielle", "Gustav", "Grace", "Gaston", "Gert", "Gordon", "Humberto", "Hanna", "Henri",
                "Hermine", "Harvey", "Helene", "Iris", "Isidore", "Isabel", "Ivan", "Irene", "Isaac", "Jerry", "Josephine",
                "Juan", "Jeanne", "Jose", "Joyce", "Karen", "Kyle", "Kate", "Karl", "Katrina", "Kirk", "Lorenzo", "Lili",
                "Larry", "Lisa", "Lee", "Leslie", "Michelle", "Marco", "Mindy", "Maria", "Michael", "Noel", "Nana",
                "Nicholas", "Nicole", "Nate", "Nadine", "Olga", "Omar", "Odette", "Otto", "Ophelia", "Oscar", "Pablo",
                "Paloma", "Peter", "Paula", "Philippe", "Patty", "Rebekah", "Rene", "Rose", "Richard", "Rita"));

        lastnames = new ArrayList<>(Arrays.asList("Adams", "Adkins", "Aguilar", "Aguirre", "Albert", "Alexander", "Alford",
                "Allen", "Allison", "Alston", "Alvarado", "Alvarez", "Anderson", "Andrews", "Anthony", "Armstrong", "Arnold",
                "Ashley", "Atkins", "Atkinson", "Austin", "Blankenship", "Blevins", "Bolton", "Bond", "Bonner", "Booker",
                "Boone", "Booth", "Bowen", "Bowers", "Carey", "Carlson", "Carney", "Carpenter", "Carr", "Carrillo", "Carroll",
                "Carson", "Carter", "Carver", "Case", "Casey", "Cash", "Castaneda", "Castillo", "Figueroa", "Finch", "Finley",
                "Fischer", "Fisher", "Fitzgerald", "Fitzpatrick", "Fleming", "Fletcher", "Flores", "Flowers", "Floyd", "Flynn",
                "Foley", "Forbes", "Ford", "Foreman", "Foster", "Fowler", "Fox", "Francis", "Franco", "Hendricks", "Hendrix",
                "Henry", "Hensley", "Henson", "Herman", "Hernandez", "Herrera", "Herring", "Hess", "Hester", "Hewitt", "Hickman",
                "Hicks", "Higgins", "Hill", "Hines", "Hinton", "Hobbs", "Hodge", "Hodges", "Hoffman", "Leach", "Leblanc", "Lee",
                "Leon", "Leonard", "Lester", "Levine", "Levy", "Lewis", "Lindsay"));
    }

    public String getRandomPerson() {
        return names.get(rand.nextInt(names.size())) + " " + lastnames.get(rand.nextInt(lastnames.size()));
    }

    public String getRandomPosition() {
        Integer i = rand.nextInt(30);
        if (i == 5) {
            return positions.get(0);
        } else if (i == 1 || i == 2) {
            return positions.get(1);
        } else {
            return positions.get(rand.nextInt(3) + 2);
        }
    }

}
