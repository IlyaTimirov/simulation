package Game;

import Entity.*;

import java.util.HashMap;

public class Map {
    private final int width;
    private final int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public HashMap<Coordinates, Entity> entities = new HashMap<>();

    public void setEntity(Entity entity, Coordinates coordinates){
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }
    public void move(Coordinates from, Coordinates to){
        Entity entity = getEntity(from);
        removeEntity(from);
        entities.put(to, entity);
    }

    public void removeEntity(Coordinates coordinates){
        entities.remove(coordinates);
    }

    public boolean isBoxEmpty(Coordinates coordinates){
        return !entities.containsKey(coordinates);
    }
    public String spriteEntity(Entity entity){
        return entity.icon;
    }
    public Entity getEntity(Coordinates coordinates){
        return entities.get(coordinates);
    }
    public Creature getCreature(Coordinates coordinates) {
        return (Creature) entities.get(coordinates);
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
