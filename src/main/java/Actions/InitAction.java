package Actions;
import Entity.*;
import Game.Coordinates;
import Game.Map;
import java.util.Random;

public class InitAction {
    public static final String ICON_PREDATOR = "@";
    public static final String ICON_HERBIVORE = "*";
    public static final String ICON_GRASS = "#";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND_TREE = "\u001B[42m";
    public static final String ANSI_WHITE_BACKGROUND_ROCK = "\u001B[47m";

    private final static double QUANTITY_STATIC_ENTITY = 15/100d;
    private final static double QUANTITY_CREATURE = 20/100d;
    private final static double QUANTITY_GRASS = 20/100d;
    private final Random random = new Random();
    public void initEntity(Map map){
        createStaticTree(map);
        createStaticRock(map);
        createPredator(map);
        createGrass(map);
        createHerbivore(map);
    }
    private void createStaticTree(Map map){
        Tree tree = new Tree(ANSI_GREEN_BACKGROUND_TREE + " " + ANSI_RESET, new Coordinates(0,0));
        createEntity(map, quantity(map, QUANTITY_STATIC_ENTITY), tree);
    }

    private void createStaticRock(Map map){
        Rock rock = new Rock(ANSI_WHITE_BACKGROUND_ROCK + " " + ANSI_RESET, new Coordinates(0,0));
        createEntity(map, quantity(map, QUANTITY_STATIC_ENTITY), rock);
    }


    private void createGrass(Map map){
        Grass grass = new Grass(ICON_GRASS, new Coordinates(0,0));
        createEntity(map, quantity(map, QUANTITY_GRASS), grass);
    }
    private void createPredator(Map map){
        Predator predator = new Predator(ICON_PREDATOR, new Coordinates(0,0), random.nextInt(1,4), 10,10);
        createEntity(map, quantity(map, QUANTITY_CREATURE), predator);
    }

    private void createHerbivore(Map map){
        Herbivore herbivore = new Herbivore(ICON_HERBIVORE, new Coordinates(0,0), random.nextInt(1,4),10);
        createEntity(map, quantity(map, QUANTITY_CREATURE), herbivore);
    }

    private double quantity(Map map, double quantity){
        return (map.getWidth() * map.getHeight()) * quantity;
    }

    private void createEntity(Map map, double quantity, Entity entity){
        for (int i = 0; i < quantity; i++) {
            int corX = random.nextInt(map.getHeight());
            int corY = random.nextInt(map.getWidth());
            Coordinates coordinates = new Coordinates(corX, corY);
            if(map.isBoxEmpty(coordinates)){
                map.setEntity(entity, coordinates);
            }
        }
    }
}
