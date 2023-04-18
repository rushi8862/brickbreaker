/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreaker;

/**
 *
 * @author Hp
 */
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
class GamePlay extends JPanel implements KeyListener,ActionListener{
    private boolean play=false;
    private int score=0;
    private int totalBricks=21;
    private final Timer Timer;
    private final int delay=8;
    private int playerX=310;
    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private MapGenerator map;
   public GamePlay(){
    map=new MapGenerator(3,7);
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    Timer=new Timer(delay,this);
    Timer.start();
}
    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString("" + score, 590, 30);
        
        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 0);
        
        g.setColor(Color.green);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        if(ballposY>570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString(" Game Over score:" + score, 190, 300);
            
             g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString(" Please Enter To Restart", 190, 340);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
      if(e.getKeyCode()==KeyEvent.VK_RIGHT){
          if(playerX>600){
              playerX=600;
      }
          else{
              moveRight();
          }
    }
       if(e.getKeyCode()==KeyEvent.VK_LEFT){
          if(playerX<10){
              playerX=10;
      }
          else{
              moVeLeft();
              
          }
    }
       if(e.getKeyCode()==KeyEvent.VK_ENTER){
           if(!play){
               ballposX=120;
               ballposY=350;
               ballXdir=-1;
               ballYdir=-2;
               score=0;
               playerX=310;
               totalBricks=21;
               map=new MapGenerator(3,7);
               repaint();
           }
       }
               
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if(play){
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir=-ballYdir;
            }
            A:
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                       int brickX=j*map.brickwidth+80;
                       int brickY =i*map.brickheight+50;
                       int brickwidth=map.brickwidth;
                       int brickheight=map.brickheight;
                       
                       Rectangle rect=new Rectangle(brickX,brickY,brickwidth,brickheight);
                       Rectangle ballrect=new Rectangle(ballposX,ballposY,20,20);
                       Rectangle brickrect=rect; 
                       
                       if(ballrect.intersects(brickrect)){
                           map.setBrickValue(0, i, j);
                           totalBricks--;
                           score+=5;
                           
                           if(ballposX+19<=brickrect.x || ballposX+1>=brickrect.x + brickwidth){
                               ballXdir=-ballXdir;
                           }
                           else{
                               ballYdir=-ballYdir;
                           }
                           break A;
                           
                       }
                    }
                }
                ballposX+=ballXdir;
                ballposY+=ballYdir;
                if(ballposX<0){
                    ballXdir=-ballXdir;
                    
                }
                if(ballposY<0){
                    ballYdir=-ballYdir;
                    
                }
                if(ballposX>670){
                    ballXdir=-ballXdir;
                }
            }
            repaint();
        }
    }

    private void moVeLeft() {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    play=true;
    playerX-=20;
    }
    

    private void moveRight() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    play=true;
    playerX+=20;
    }
    
}
