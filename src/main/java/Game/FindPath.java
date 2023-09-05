package Game;

import Entity.Entity;
import Entity.Grass;
import Entity.Herbivore;
import Entity.Predator;

import java.util.*;

public class FindPath {

    public Coordinates findPath(Map map, Coordinates entityCoordinates){
        Deque<Coordinates> visit = new ArrayDeque<>();
        List<Coordinates> visited = new ArrayList<>();
        HashMap<Coordinates, Coordinates> pathCoordinates = new HashMap<>();
        visit.add(entityCoordinates);
        visited.add(entityCoordinates);

        while (!visit.isEmpty()){
            Coordinates coordinatesSearch = visit.poll();

            for(Coordinates coordinates : getNeighboringCoordinates(map, coordinatesSearch)){
                if(!visited.contains(coordinates) && map.isBoxEmpty(coordinates)){
                    visited.add(coordinates);
                    visit.add(coordinates);
                    pathCoordinates.put(coordinates, coordinatesSearch);
                }
                Entity entity = map.getEntity(coordinates);
                if(entity != null){
                    if(map.getEntity(entityCoordinates).getClass().equals(Predator.class)){
                        if(entity.getClass().equals(Herbivore.class)){
                            pathCoordinates.put(coordinates, coordinatesSearch);
                            return getMoveCoordinates(map, coordinates, entityCoordinates, pathCoordinates);
                        }
                    }else {
                        if(entity.getClass().equals(Grass.class)){
                            pathCoordinates.put(coordinates, coordinatesSearch);
                            return getMoveCoordinates(map, coordinates, entityCoordinates, pathCoordinates);
                        }
                    }
                }
            }
        }
        return entityCoordinates;
    }
    private Coordinates getMoveCoordinates(Map map, Coordinates start, Coordinates finish, HashMap<Coordinates , Coordinates> pathCoordinates){
        int speed = map.getCreature(finish).speed;
        List<Coordinates> list = new ArrayList<>();
        while (start != finish){
            list.add(start);
            start = pathCoordinates.get(start);
        }
        if(list.size() <= speed){
            return list.get(0);
        }else {
            return list.get(list.size() - speed);
        }
    }

    private List<Coordinates> getNeighboringCoordinates(Map map, Coordinates coordinates){
        List<Coordinates> result = new ArrayList<>();
        if(coordinates.x - 1 >= 0){
            result.add(new Coordinates(coordinates.x - 1, coordinates.y));
        }
        if(coordinates.y - 1 >= 0){
            result.add(new Coordinates(coordinates.x, coordinates.y - 1));
        }
        if(coordinates.x + 1 <= map.getHeight()){
            result.add(new Coordinates(coordinates.x + 1, coordinates.y));
        }
        if(coordinates.y + 1 <= map.getWidth()){
            result.add(new Coordinates(coordinates.x, coordinates.y + 1));
        }
        return result;
    }
}
