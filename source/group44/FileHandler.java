package group44;

import group44.entities.Cell;
import group44.entities.LevelObject;
import group44.game.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {

    public static Level readLevelFile(String filename) {
        File inputFile = new File ("group44/levels/" + filename);
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
        String levelWalls = null;
        while(in.next() == "#" || in.next() == " "){
            levelWalls += in.next();
        }
        LevelObject[][] gameGrid = FileHandler.createGrid(levelWidth,levelHeight,levelWalls);
        int startRow = in.nextInt();
        int startCol = in.nextInt();

    }

    private static LevelObject[][] createGrid(int width, int height, String level){

        LevelObject[][] gameGrid = new LevelObject[width-1][height-1]; //Counting from 0 and our width/height does not.

        for(int i = 0; i < level.length(); i++){
            for(int x = 0; x < level.length(); x++){
                if(level.charAt(x) == '#'){
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

        return gameGrid;
    }

}
