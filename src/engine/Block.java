package engine;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import engine.utils.Constants;


public class Block {

    public String[] type;
    public TextColor color;
    public int posX;
    public int posY;
    public void move(int amountX, int amountY){
        // If you don't want the player to go out the screen, you should limit the position of the player here
        posX += amountX;
        posY += amountY;
    }
    public void draw(TextGraphics textGraphics){
        // Set the foreground color to myColor
        textGraphics.setForegroundColor(Constants.blocksColor[0]); // This is optional
        // Draw the actual player on the screen
        int count=0;
        for(String row : Constants.blockTypes[0]){
            textGraphics.putString(posX, posY+count, row);
            count++;
        }
    }
    public Block(String[] type,TextColor color,int posX, int posY){
        this.type=type;
        this.color=color;
        this.posX=posX;
        this.posY=posY;
    }
    public static Block I_Block= new Block(Constants.blockTypes[0],Constants.blocksColor[0],0,0);
    public static Block J_Block= new Block(Constants.blockTypes[1],Constants.blocksColor[1],0,0);
    public static Block L_Block= new Block(Constants.blockTypes[2],Constants.blocksColor[2],0,0);
    public static Block O_Block= new Block(Constants.blockTypes[3],Constants.blocksColor[3],0,0);
    public static Block S_Block= new Block(Constants.blockTypes[4],Constants.blocksColor[4],0,0);
    public static Block T_Block= new Block(Constants.blockTypes[5],Constants.blocksColor[5],0,0);
    public static Block Z_Block= new Block(Constants.blockTypes[6],Constants.blocksColor[6],0,0);
    public static Block[] BLOCKS= {I_Block,J_Block,L_Block,O_Block,S_Block,T_Block,Z_Block};



}
