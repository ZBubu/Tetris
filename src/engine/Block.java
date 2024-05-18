package engine;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import engine.utils.BlockType;
import engine.utils.Constants;


public class Block {

    public BlockType type;

    public int posX;
    public int posY;
    public void move(int amountX, int amountY){
        // If you don't want the player to go out the screen, you should limit the position of the player here
        posX += amountX;
        posY += amountY;
    }
    public void draw(TextGraphics textGraphics){
        // Set the foreground color to myColor
        textGraphics.setForegroundColor(type.color); // This is optional
        // Draw the actual player on the screen
        int count=0;
        for(String row : type.shape){

            textGraphics.putString(posX, posY+count, row);
            count++;
        }
    }
    public Block(BlockType type,int posX, int posY){
        this.type=type;
        this.posX=posX;
        this.posY=posY;
    }



}
