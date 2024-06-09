package engine.utils;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
/**
 * La classe Cell serve a gestire le celle della griglia di Tetris.
 *
 */
public class Cell {
    public boolean filled;
    public TextColor color;

    /**
     * Costruttore
     * @param filled
     * @param color
     */
    public Cell(boolean filled,TextColor color){
        this.filled=filled;
        this.color=color;
    }

    /**
     * Metodo per disegnare a terminale la cella di tetris.
     * @param x coordinata x
     * @param y coordinata y
     * @param textGraphics permette di interagire con il testo nella finestra.
     */
    public void draw(int x, int y, TextGraphics textGraphics){
        textGraphics.setForegroundColor(color);
        if(this.filled){
            textGraphics.putString(x,y,"\u2588\u2588");
        }
        else{
            textGraphics.putString(x,y,"  ");
        }
    }

}
