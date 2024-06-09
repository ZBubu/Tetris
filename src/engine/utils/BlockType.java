package engine.utils;

import com.googlecode.lanterna.TextColor;
import engine.Block;

/**
 * La classe BlockType viene usata per definire tutti i tipi di blocchi che vengono usati nel gioco.
 * I vari blocchi sono tutti contenuti nell'array di stringhe shape.
 */
public class BlockType {
    public String[] shape;
    public TextColor color;
    private BlockType(String[] shape, TextColor color){
        this.shape=shape;
        this.color=color;
    }
    public static BlockType I_Block= new BlockType(Constants.blockTypes[0],Constants.blocksColor[0]);
    public static BlockType J_Block= new BlockType(Constants.blockTypes[1],Constants.blocksColor[1]);
    public static BlockType L_Block= new BlockType(Constants.blockTypes[2],Constants.blocksColor[2]);
    public static BlockType O_Block= new BlockType(Constants.blockTypes[3],Constants.blocksColor[3]);
    public static BlockType S_Block= new BlockType(Constants.blockTypes[4],Constants.blocksColor[4]);
    public static BlockType T_Block= new BlockType(Constants.blockTypes[5],Constants.blocksColor[5]);
    public static BlockType Z_Block= new BlockType(Constants.blockTypes[6],Constants.blocksColor[6]);
    public static BlockType[] BLOCKS= {I_Block,J_Block,L_Block,O_Block,S_Block,T_Block,Z_Block};

}
