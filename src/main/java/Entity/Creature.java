package Entity;

import Game.Coordinates;
import Game.Map;


public abstract class Creature extends Entity {
    public int speed;

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int hp;

    public Creature(String icon, Coordinates coordinates, int speed, int hp) {
        super(icon, coordinates);
        this.speed = speed;
        this.hp = hp;
    }

    public abstract void makeMove(Map map, Coordinates from);
}
