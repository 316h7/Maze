package com.javamaze.maze;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ShowMaze extends javax.swing.JPanel implements ComponentListener {
//componentListener listens to changes in size of window, then resize image

    private Image image;
    private Image imageOriginal;
    private Dimension newSize;
    private File imageFile = new File("MazeResolved.png");
    private int inset;//size of top title panel
    private double x1 = -1, x2 = -1, y1 = -1, y2 = -1;
    private int[][] maze;
    private double scale;//scale for fitting image

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        newSize = e.getComponent().getBounds().getSize();
        //-200 is a offset for buttons etc.
        double size2 = (newSize.getWidth() - 200) / imageOriginal.getWidth(this);
        double size3 = (newSize.getHeight() - inset) / imageOriginal.getHeight(this);
        if (size2 < size3) {
            scale = size2;
        } else {
            scale = size3;
        }
        //scaled dimensions of image
        double sc1 = scale * imageOriginal.getWidth(this);
        double sc2 = scale * imageOriginal.getHeight(this);
        image = imageOriginal.getScaledInstance((int) sc1, (int) sc2, Image.SCALE_DEFAULT);
    }

    private int ScaleCoords(int num) {

        return 0;
    }

    public ShowMaze(int ins, int[][] myMaze) {
        maze = myMaze;
        inset = ins;
        initComponents();
        try {
            image = ImageIO.read(imageFile);
            imageOriginal = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Error while loading maze!");
            e.printStackTrace();
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (x1 < 0) {
                    x1 = e.getX() / 16 / scale;
                    y1 = e.getY() / 16 / scale;
                } else if (x2 < 0) {
                    x2 = e.getX() / 16 / scale;
                    y2 = e.getY() / 16 / scale;
                    System.out.println((x1) + " " + (y1) + " " + (x2) + " " + (y2));
System.out.println(maze.length);
                    System.out.println(Math.round(x1) + " " + Math.round(y1) + " " + Math.round(x2) + " " + Math.round(y2));
                    MazeSolver solve = new MazeSolver();
                    solve.SolveMaze(maze,(int) Math.round(x1), (int) Math.round(y1), (int) Math.round(x2), (int) Math.round(y2));
                    //init again values with -1
                    x1 = x2 = y1 = y2 = -1;
                }
            }
        });
    }

    //show image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
