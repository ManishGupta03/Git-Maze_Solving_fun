/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Algorithm.DFS;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class View extends JFrame implements ActionListener,MouseListener{
//maze layout
    //private final int[][] maze;// values : 0=not visited, 1 = blocked wall, 2 = visited, 9 = target
    private int[][] maze = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1 }, { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1 }, { 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
    private int[] target = {8,11};
    private List<Integer> path = new ArrayList<Integer>();
    
    JButton submitButton;
    JButton cancelButton;
    JButton clearButton;
    public View() {
        //this.maze = new int[][] { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1 }, { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1 }, { 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
    
        this.setTitle("Maze Solver"); // set title in the middle
        this.setSize(520,500);
        this.setLayout(null);//layouts in java
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//Window appearing in the middle of the screen
        
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setBounds(60,400,80,30);
        
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(240,400,80,30);
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setBounds(380,400,80,30);
        
        this.addMouseListener(this);
        
        this.add(submitButton);
        this.add(clearButton);
        this.add(cancelButton);
    }
    @Override
    public void paint(Graphics g){// to paint a maze on jframe
        super.paint(g);
        for(int row=0; row<maze.length; row++){
            for(int col=0; col<maze[0].length; col++){
                Color color;
                switch(maze[row][col]){
                    case 1 : color = Color.BLACK; break;
                    case 9 : color = Color.RED; break;
                    default : color = Color.WHITE; break;
                }
                g.setColor(color);
                g.fillRect(40*col, 40*row, 40, 40);
                g.setColor(color.BLACK);
                g.drawRect(40*col, 40*row, 40, 40);
            }
        }
        for(int p=0; p<path.size(); p+=2){
            int pathX = path.get(p);
            int pathY = path.get(p+1);
            g.setColor(Color.GREEN);
            g.fillRect(40*pathY, 40*pathX, 40, 40);
        }
    }
    public static void main(String[] args){
        View gui = new View();
        gui.setVisible(true);//by default, the JFrame is set to be in invisible.so we have to set the visibility true
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton){
          try{
              DFS.searchPath(maze,1,1,path);
              this.repaint();
          }
          catch(Exception ex){
              JOptionPane.showMessageDialog(null,ex.toString());
          }
        }
        if(e.getSource()==cancelButton){
           int flag = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit","Submit",JOptionPane.YES_NO_OPTION);
           if(flag==0){
               System.exit(0);
           }
        }
        if(e.getSource()==clearButton){
            path.clear();
            for(int row=0; row<maze.length; row++){
            for(int col=0; col<maze[0].length; col++){
                if(maze[row][col]==2){
                 maze[row][col]=0;}
            }
        }
            this.repaint();
    }
  }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getX()>=0 && e.getX()<=maze[0].length*40 && e.getY()>=0 && e.getY()<=maze.length*40){
            int row=e.getY()/40;
            int col=e.getX()/40;
            
            if(maze[row][col]==1) return;
            Graphics g=getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(40*target[1], 40*target[0], 40, 40);
            g.setColor(Color.RED);
            g.fillRect(col*40, row*40, 40, 40);
            maze[target[0]][target[1]]=0;
            maze[row][col]=9;
            target[0]=row;
            target[1]=col;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
