package engine;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import engine.utils.BlockType;
import engine.utils.Constants;

/**
 * La classe Block usa la classe BlockType e le coordinate del blocco per poter fare dei controlli durante il gioco.
 */
public class Block {
    public BlockType type;
    public int posX;
    public int posY;

    /**
     * Costruttore che assegna il tipo di blocco con la posizione in X e in Y.
     * @param type Tipo di blocco
     * @param posX posizione sull'asse X.
     * @param posY posizione sull'asse Y.
     */
    public Block(BlockType type,int posX, int posY){
        this.type=type;
        this.posX=posX;
        this.posY=posY;
    }

    /**
     * Questa funzione serve a muovere il blocco.
     * @param amountX quanto viene spostato nell'asse X.
     * @param amountY quanto viene spostato nell'asse Y.
     */
    public void move(int amountX, int amountY){
        posX += amountX;
        posY += amountY;
    }

    /**
     * Questa funzione serve a disegnare il blocco nel terminale.
     * @param textGraphics variabile di Lanterna per controllare come stampare le cose.
     */
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
}
