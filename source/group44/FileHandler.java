package group44;

import group44.entities.Cell;
import group44.entities.LevelObject;
import group44.game.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {

    public static Level readLevelFile(String filename) {
        File inputFile = new File ("source/group44/levels/" + filename);
        Scanner in = null;
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + filename);
            System.exit(0);
        }
        return FileHandler.readLevelFile(in);

    }

    private static Level readLevelFile(Scanner in) {
        int levelWidth = in.nextInt();
        int levelHeight = in.nextInt();
        Level ourLevel = new Level(levelWidth, levelHeight, 5);

        //FIXME: This is all trash. Rethink adding walls.
        String levelWalls = null;
        while(in.next() == "#" || in.next() == " "){
            levelWalls += in.next();
        }

        //Ignore for the time being - read everything above properly first.
        int startRow = in.nextInt();
        int startCol = in.nextInt();

        return ourLevel;

    }

    private static Level addWalls(Level someLevel, String walls){
        //FIXME: This is bad - rethink addingWalls
        //Add walls to a level and return it.
        for(int i = 0; i < walls.length(); i++){
            for(int x = 0; x < walls.length(); x++){
                if(walls.charAt(x) == '#'){
                    //TODO: Create wall variable, insert this to gameGrid[i,x]
                    //Cell wall = new Cell(...);
                    //gameGrid[i][x] = wall;
                }
                else{
                    //TODO: Create tile variable, insert this to gameGrid[i,x]
                    //StepableCell floor = new Stepable(...);
                    //gameGrid[i][x] = floor;
                }
            }
        }

        return someLevel;
    }

}
