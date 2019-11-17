package group44.entities;

import group44.entities.MovableObject;

/**
 * Abstract class for enemies.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public abstract class Enemy extends MovableObject {
    /**
     * Attack player if player collided with enemy.
     * 
     * @param p - Player
     */
    public abstract void attackPlayer(Player p);
}
