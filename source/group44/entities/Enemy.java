package group44.entities;

import group44.entities.MoveableObject;

public abstract class Enemy extends MoveableObject {

    public abstract void attackPlayer(Player p);

}
