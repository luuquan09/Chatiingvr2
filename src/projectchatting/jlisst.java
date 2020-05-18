/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectchatting;

/**
 *
 * @author LUUQUAN-PC
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class jlisst extends JFrame{

    private JPanel panelNew;

    public jlisst(){
        super("Work 1");


        // create Panel
        panelNew = new JPanel();
        panelNew.setLayout(null);
        panelNew.setBackground(Color.cyan );
        add(panelNew);

        // create Button
        JButton btn = new JButton("click me");
        // position and size of a button
        btn.setBounds(100, 50, 150, 30);

        panelNew.add(btn);


        // add event to button
        btn.addMouseListener(new MouseAdapter() { 
                public void mouseClicked(MouseEvent me) { 

                     // change text of button after click

                     if (btn.getText() == "abraCadabra"){
                          btn.setText("click me again") ;
                     }
                     else  btn.setText("abraCadabra");
                } 
         });        
    }




    public static void main(String[] args){
        jlisst go1 = new jlisst();
        go1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        go1.setSize(320,200);
        go1.setVisible(true);   
    }




}