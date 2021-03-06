package com.javamaze.maze;

import java.util.ArrayList;

public class GenSaveSolve {

    private final int[][] maze;
    private ArrayList<Cell> path = new ArrayList<>();
    private final int pathLength;

    public ArrayList<Cell> GetPath() {
        return path;
    }

    public int[][] GetMaze() {
        return maze;
    }

    public int GetPathLength() {
        return pathLength;
    }

    public GenSaveSolve(int[][] mazex) {
        this.maze = mazex;
        //print unresolved maze as image
        PrintMaze print = new PrintMaze();
        print.SaveAsImage(maze);

        //find entrance and exit
        int rows = maze.length - 1;
        int cols = maze[0].length - 1;

        int sx = 0;
        int tx = 0;
        for (int a = 1; a <= rows; a++) {
            if (maze[a][0] == 0) {
                sx = a;
            }
            if (maze[a][cols] < 2) {
                tx = a;
            }
        }
        //solve maze
        MazeSolver solve = new MazeSolver();
        solve.SolveMaze(maze, sx, 1, tx, cols);
        path = solve.GetPath();
        pathLength = solve.GetMinLength();
        print.SaveAsImageResolved(solve.GetPath(), true);
    }

    public GenSaveSolve(int cols, int rows) {
        //generate maze

        MakeMaze myMaze = new MakeMaze(cols, rows);
        maze = myMaze.getMaze();
        //print unresolved maze as image
        PrintMaze print = new PrintMaze();
        print.SaveAsImage(maze);

        //find entrance and exit
        int sx = 0;
        int tx = 0;
        for (int a = 1; a <= cols; a++) {
            if (maze[a][0] == 0) {
                sx = a;
            }
            if (maze[a][rows] < 2) {
                tx = a;
            }
        }

        //solve maze
        MazeSolver solve = new MazeSolver();
        solve.SolveMaze(maze, sx, 1, tx, rows);
        path = solve.GetPath();
        pathLength = solve.GetMinLength();
        print.SaveAsImageResolved(solve.GetPath(), true);
    }
}
