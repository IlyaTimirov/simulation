package Entity;


import Game.Coordinates;
import Game.FindPath;
import Game.Map;

public class Herbivore extends Creature {


    public Herbivore(String icon, Coordinates coordinates, int speed, int hp) {
        super(icon, coordinates, speed, hp);
    }
    @Override
    public int getHp(){
        return hp;
    }
    FindPath findPath = new FindPath();
    public void absorption(Map map, Coordinates from){
        hp++;
        map.move(from, from);
    }
    @Override
    public void makeMove(Map map, Coordinates from) {
        Coordinates coordinates1 = findPath.findPath(map, from);
        map.move(from, coordinates1);
        //map.getCreature(coordinates1).hp--;
    }




}
