package group44.entities;

import java.util.ArrayList;

import group44.Main;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;


public class Player extends MoveableObject {
    private Node player;
    private static ArrayList<CollectibleObject> inventory;



    public static void useCollectibleItem(CollectibleObject item, LevelObject obj){
        for(CollectibleObject anObj: inventory){
            if (item == anObj){
                inventory.remove(anObj);
                //obj.interact(item); //this method has to be written or some further development as to how they will interact.
            }
        }
    }

    public static void addCollectibleItem(CollectibleObject item){
        inventory.add(item);
    }

    public static ArrayList<CollectibleObject>  getInventory(){
        return inventory;
    }

    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:    move(0,1); break;
            case DOWN:  move(0,-1); break;
            case LEFT:  move(-1,0); break;
            case RIGHT: move(1,0); break;
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = player.getBoundsInLocal().getWidth()  / 2;
        final double cy = player.getBoundsInLocal().getHeight() / 2;

        double x = cx + player.getLayoutX() + dx;
        double y = cy + player.getLayoutY() + dy;

        movePlayerTo(x, y);
    }

    private void movePlayerTo(double x, double y) {
        final double cx = player.getBoundsInLocal().getWidth()  / 2;
        final double cy = player.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= Main.getWidth() &&
                y - cy >= 0 &&
                y + cy <= Main.getHeight()) {
            player.relocate(x - cx, y - cy);
        }
    }
}
