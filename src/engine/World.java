package engine;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import engine.utils.*;

import java.util.Arrays;
import java.util.Random;

/**
 * The Word class implements the logic for the world in your game
 */
public class World implements IRenderable {
    private boolean running; // Variable to determine if the world is still running or not
    private Cell[][] grid = new Cell[10][20];
    private boolean isHintTextDisplayed = true;
    public Block CurrentBlock;
    int centerX;

    // Constructor
    public World(int screenWidth, int screenHeight) {
        // Find the center of the screen
        centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        // Create a player object positioned at the center of the screen
        CurrentBlock = new Block(BlockType.BLOCKS[1], centerX, 0);
        //System.out.println(Arrays.toString(Constants.blockTypes[1]));
        running = true;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){

                grid[i][j]= new Cell(true, TextColor.ANSI.RED);
            }
        }
    }

    /**
     * Draws the world on a given TextGraphics aa aaa
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Hide the cursor
        Utils.hideCursor(0, 0, textGraphics);
        // Draw the player
        CurrentBlock.draw(textGraphics);
        //Draw tetris grid
        for(int x=-1;x<grid.length*2+1;x+=2){
            for(int y=-1;y<grid[0].length+1;y++){
                if(x<0 || y<0 || x>grid.length*2-3 || y==grid[0].length){
                    textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
                    textGraphics.putString(centerX-grid.length+x,y+1,"\u2588\u2588");
                }
                else{
                    grid[x/2][y].draw(centerX-grid.length+x,y+1,textGraphics);
                }
            }
        }
        // Draw the hint text if necessary
        if (isHintTextDisplayed) {
            Utils.drawText(textGraphics, "[Hint] Try to move around using the arrow keys");
        }
    }
    /*
    public void draw(TextGraphics textGraphics,int posX,int posY){
        textGraphics.setForegroundColor(CurrentBlock.color); // This is optional
        // Draw the actual player on the screen
        int count=0;
        for(String row : CurrentBlock.type){
            textGraphics.putString(posX, posY+count, row);
            count++;
        }
     */

    /**
     * This method should contain all the processing of your game, not graphical updates!
     * Examples:
     * - Moving players, enemies, goals
     * - Manipulating arrays, lists, variables
     * - Manipulating the world in general
     *
     * @param keyPress the last keypress if any
     */
    public void update(KeyStroke keyPress) {
        if(running) {
            /*
            You could do a check and throw an exception if the world is no longer running, and this method is called
            if (!running){
                throw new IllegalStateException("The world is no longer running, and this method cannot be called.");
            }
            */
            if (false) {
                Random r = new Random();
                CurrentBlock = new Block(BlockType.BLOCKS[r.nextInt(0, BlockType.BLOCKS.length)],0,0);
            }
            //Use a switch statement to handle different key types
            if (keyPress != null) {
                switch (keyPress.getKeyType()) {


                    case ArrowLeft:
                        // Move the player left
                        CurrentBlock.move(-1, 0);
                        break;
                    case ArrowRight:
                        // Move the player right
                        CurrentBlock.move(1, 0);
                        break;
                    case EOF, Escape:
                        // Stop the game
                        running = false;
                        break;
                }

                isHintTextDisplayed = false;


            }
        }
    }
    public boolean isRunning () {
        return running;
    }

}



