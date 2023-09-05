package Entity;

import Game.Coordinates;

import java.util.Objects;

public abstract class Entity {
    public String icon;
    public Coordinates coordinates;

    public Entity(String icon, Coordinates coordinates) {
        this.icon = icon;
        this.coordinates = coordinates;
    }

}
