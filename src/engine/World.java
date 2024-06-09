package engine;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.sun.source.util.SourcePositions;
import engine.utils.*;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.lang.Thread;

/**
 * La classe World serve a implementare tutto ciò che vedi nello schermo.
 */
public class World implements IRenderable {
    private boolean running; // Variable to determine if the world is still running or not
    private Cell[][] grid = new Cell[30][20];
    public Block CurrentBlock;
    Random r= new Random();
    int gravity=0;
    int CurrentIndex;
    int YBorder=20;
    int LeftBorder;
    int RightBorder;
    private final int screenWidth;
    private final int screenHeight;
    boolean Rowfilled=true;

    // Constructor
    public World(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        running = true;

        /*
        Istanzia le classi Cell contenute nell'array grid.
        Le coordinate x e y di grid sono e saranno per tutto il programma sempre invertite.
         */
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                grid[i][j]= new Cell(false, TextColor.ANSI.BLACK);
            }
        }
        setup();
    }

    /**
     * La funzione setup era una parte del costruttore che ho deciso di mettere in una funzione per poterla richiamare
     * in modo da resettare il gioco.
     */
    private void setup(){
        //Default borders
        LeftBorder=2;
        RightBorder=18;
        YBorder=20;

        //Current Index and Block
        CurrentIndex=r.nextInt(BlockType.BLOCKS.length);
        CurrentBlock=new Block(BlockType.BLOCKS[CurrentIndex], 2, 2);

        if(CurrentIndex==0){
            RightBorder-=2;
        }
    }

    /**
     * Draws the world on a given TextGraphics
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Hide the cursor
        Utils.hideCursor(0, 0, textGraphics);
        //Draw tetris grid
        for(int x=0;x<grid.length;x++){
            for(int y=0;y<grid[0].length;y++){
//                if(x<1 || y<1 || x>grid.length*2-1 || y==grid[0].length ){
//                    //Draws the tetris border
//                    textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
//                    textGraphics.putString(1-grid.length+x,y+1,"\u2588\u2588");
//                }
                if(x>0){
                    //Draws the tetris grid
                    grid[x][y].draw(x,y,textGraphics);
                }

            }
        }
        // Draw the player
        CurrentBlock.draw(textGraphics);
    }

    /**
     * Questa funzione l'ho usata per disegnare i blocchi di tetris una volta che collidono con qualcosa.
     *
     * @param nCells il numero di blocchi da disegnare.
     * @param X l'offset nella posizione X.
     * @param Y l'offset nella posizione Y.
     */
    public void drawCells(int nCells,int X,int Y){
        for(int i=0;i<nCells;i++){
            grid[CurrentBlock.posX+i+X][CurrentBlock.posY-2+Y].filled = true;
            grid[CurrentBlock.posX+i+X][CurrentBlock.posY-2+Y].color=CurrentBlock.type.color;
        }
    }
    /**
     * In questa funzione viene controllato tutto quello che deve fare il gioco una volta avviato.
     *
     * @param keyPress l'ultimo tasto premuto
     */
    public void update(KeyStroke keyPress) {
        if(running) {
            //Questo switch serve a controllare gli spostamenti del blocco.
            if (keyPress != null) {
                switch (keyPress.getKeyType()) {
                    case ArrowLeft:
                        if (CurrentBlock.posX > LeftBorder && CurrentBlock.posY != YBorder) {
                            CurrentBlock.move(-2, 0);
                        }
                        break;
                    case ArrowRight:
                        if (CurrentBlock.posX < RightBorder && CurrentBlock.posY != YBorder) {
                            CurrentBlock.move(2, 0);
                        }
                        break;
                    case EOF, Escape:
                        // Stop the game
                        running = false;
                        break;

                }
            }
            //Questo codice controlla la gravità che spinge giù il blocco.
            gravity++;
            if(gravity==10 && CurrentBlock.posY!=YBorder){
                CurrentBlock.posY+=1;
                gravity=0;
            }
            /*
            Questo gigantesco If serve a controllare le collisioni tra i blocchi, c'erano sicuramente metodi migliori
            per controllarle, tutti i problemi con le collisioni vengono da questo if.
            */
            if(CurrentBlock.posY==YBorder || grid[CurrentBlock.posX][CurrentBlock.posY-1].filled || grid[CurrentBlock.posX+1][CurrentBlock.posY-1].filled
            || grid[CurrentBlock.posX+2][CurrentBlock.posY-1].filled || grid[CurrentBlock.posX+3][CurrentBlock.posY-1].filled
            || grid[CurrentBlock.posX+4][CurrentBlock.posY-1].filled  || grid[CurrentBlock.posX+5][CurrentBlock.posY-1].filled
            || grid[CurrentBlock.posX+2][CurrentBlock.posY].filled && (CurrentIndex==4 || CurrentIndex==6) || grid[CurrentBlock.posX+3][CurrentBlock.posY].filled
            &&(CurrentIndex==4 || CurrentIndex==6)){
                        //Questo switch serve a disegnare il blocco giusto a seconda del suo indice.
                        switch(CurrentIndex){
                            case 0:
                                drawCells(8,0,0);
                                break;
                            case 1:
                                drawCells(6,0,0);
                                drawCells(2,0,-1);
                                break;
                            case 2:
                                drawCells(6,0,0);
                                drawCells(2,4,-1);
                                break;
                            case 3:
                                drawCells(4,0,0);
                                drawCells(4,0,-1);
                                break;
                            case 4:
                                drawCells(4,2,0);
                                drawCells(4,0,1);
                                break;
                            case 5:
                                drawCells(6,0,0);
                                drawCells(2,2,-1);
                                break;
                            case 6:
                                drawCells(4,2,1);
                                drawCells(4,0,0);
                                break;
                        }
                        //Questo pezzo di codice cerca le righe complete per poi cancellarle.
                        for(int i=2;i<20;i++){
                                Rowfilled=true;
                            for (int j=2; j<22; j++){
                                if (!grid[j][i].filled){
                                    Rowfilled = false;
                                    break;
                                }
                            }
                            for(int j=2;j<22;j++){
                                if (Rowfilled) {
                                    grid[j][i].filled = false;
                                    grid[j][i].color = TextColor.ANSI.BLACK;
                                }
                            }
                        }
                        //Questo controlla il gameover, ovvero se ci sono dei blocchi all'altezza dove viene generato
                        //il blocco nuovo.
                        for(int k=0;k<20;k++){
                            if(grid[k][2].filled){
                                running=false;
                            }
                        }
                    //alla fine di tutto viene richiamato il metodo setup per generare un nuovo blocco casuale.
                    setup();
            }
        }
    }
    //get di running.
    public boolean isRunning () {
        return running;
    }

}



