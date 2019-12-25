package mindustry.world;

import arc.util.ArcAnnotate.*;

import java.util.*;

/** A tile container. */
public class Tiles implements Iterable<Tile>{
    private final Tile[] array;
    private final int width, height;
    private final TileIterator iterator = new TileIterator();

    public Tiles(int width, int height){
        this.array = new Tile[width * height];
        this.width = width;
        this.height = height;
    }

    /** fills this tile set with empty air tiles. */
    public void fill(){
        for(int i = 0; i < array.length; i++){
            array[i] = new Tile(i % width, i / width);
        }
    }

    /** set a tile at a position; does not range-check. use with caution. */
    public void set(int x, int y, Tile tile){
        array[y*width + x] = tile;
    }

    /** @return whether these coordinates are in bounds */
    public boolean in(int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /** @return a tile at coordinates, or null if out of bounds */
    public @Nullable Tile get(int x, int y){
        return (x < 0 || x >= width || y < 0 || y >= height) ? null : array[y*width + x];
    }

    /** @return a tile at coordinates; throws an exception if out of bounds */
    public @NonNull Tile getn(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >= height) throw new IllegalArgumentException(x + ", " + y + " out of bounds: width=" + width + ", height=" + height);
        return array[y*width + x];
    }

    /** @return a tile at an iteration index [0, width * height] */
    public @NonNull Tile geti(int idx){
        return array[idx];
    }

    /** @return a tile at an int position (not equivalent to geti) */
    public @Nullable Tile getp(int pos){
        return get(Pos.x(pos), Pos.y(pos));
    }

    public int width(){
        return width;
    }

    public int height(){
        return height;
    }

    @Override
    public Iterator<Tile> iterator(){
        iterator.index = 0;
        return iterator;
    }

    private class TileIterator implements Iterator<Tile>{
        int index = 0;

        @Override
        public boolean hasNext(){
            return index < array.length;
        }

        @Override
        public Tile next(){
            return array[index++];
        }
    }
}