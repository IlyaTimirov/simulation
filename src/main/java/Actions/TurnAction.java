package Actions;

import Entity.*;
import Game.Coordinates;
import Game.Map;
import java.util.*;

public class TurnAction {
    private final Random random = new Random();

    public void makeMoves(Map map){
        HashMap<Coordinates, Entity> maps = new HashMap<>(map.entities);
        Set<Coordinates> coordinatesEntity = maps.keySet();
        for(Coordinates coordinates: coordinatesEntity){
            if(map.getEntity(coordinates).getClass().equals(Predator.class)){
                if(checkHp(map.getCreature(coordinates))){
                    map.removeEntity(coordinates);
                    continue;
                }

                map.getCreature(coordinates).makeMove(map, coordinates);
                continue;
            }
            if(map.getEntity(coordinates).getClass().equals(Herbivore.class)){
                if(checkHp(map.getCreature(coordinates))){
                    map.removeEntity(coordinates);
                    continue;
                }
                map.getCreature(coordinates).makeMove(map, coordinates);
            }
        }
    }
    public void addEntity(Map map){
        for (int i = 0; i < map.getHeight(); i++) {
            addGrass(map);
            addHerbivore(map);
        }
        double d = 15/100d;
        for (int i = 0; i < map.getHeight() * d; i++) {
            addPredator(map);
        }
    }
    private boolean checkHp(Creature creature){
        return creature.hp <= 0;
    }
    private void addGrass(Map map){
        Grass grass = new Grass(InitAction.ICON_GRASS, new Coordinates(0,0));
        add(map, grass);
    }
    private void addHerbivore(Map map){
        Herbivore herbivore = new Herbivore(InitAction.ICON_HERBIVORE, new Coordinates(1,1), random.nextInt(1, 4), 10);
        add(map, herbivore);
    }

    private void addPredator(Map map){
        Predator predator = new Predator(InitAction.ICON_PREDATOR, new Coordinates(0,0), random.nextInt(1, 4), 10, 10);
        add(map, predator);
    }

    private void add(Map map, Entity entity){
        boolean check = true;
        while (check){
            int corX = random.nextInt(map.getHeight());
            int corY = random.nextInt(map.getWidth());
            Coordinates coordinates = new Coordinates(corX, corY);
            if(map.isBoxEmpty(coordinates)) {
                map.setEntity(entity, coordinates);
                check = false;
            }
        }
    }
}
