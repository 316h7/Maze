
package com.javamaze.maze;

public class Cell {
    public int x,y,d;
    //x and y are coordinates
    //d is distance from the start
    public Cell(int xx, int yy, int dd)
    {
        this.x=xx;
        this.y=yy;
        this.d=dd;
    }
}
