package Entity;

import Game.Coordinates;
import Game.FindPath;
import Game.Map;

public class Predator extends Creature {

    private int attack;

    private final FindPath findPath = new FindPath();
    public Predator(String icon, Coordinates coordinates, int speed, int hp, int attack) {
        super(icon, coordinates, speed, hp);
        this.attack = attack;
    }

    public void attackHerbivore(Map map, Coordinates predator, Coordinates herbivore){
        if(attack >= map.getCreature(herbivore).hp){
            hp++;
            map.move(predator, herbivore);
        }else {
            map.getCreature(herbivore).hp -= attack;
        }
    }

    @Override
    public void makeMove(Map map, Coordinates from) {
        Coordinates coordinates1 = findPath.findPath(map, from);
        if(map.getEntity(coordinates1) != null && map.getEntity(coordinates1) != map.getEntity(from)){
            attackHerbivore(map, from, coordinates1);
        }else {
            map.move(from, coordinates1);
            map.getCreature(coordinates1).hp--;
        }
    }
}
