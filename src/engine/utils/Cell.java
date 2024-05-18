package engine.utils;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Cell {
    public boolean filled;
    public TextColor color;
    public Cell(boolean filled,TextColor color){
        this.filled=filled;
        this.color=color;
    }
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
