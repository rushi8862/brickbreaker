/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brickbreaker;

/**
 *
 * @author Hp
 */
import javax.swing.JFrame;
public class BrickBreaker {

    private static String BrickBreaker;

    public static void main(String[] args) {
        JFrame ob1=new JFrame();
        GamePlay gameplay=new GamePlay();//hole game will play in this class
        
        ob1.setBounds(10,10,700,600);
       
        ob1.setTitle("BrickBreaker");
        ob1.setResizable(false);
        ob1.setVisible(true);
        ob1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
    }
}
