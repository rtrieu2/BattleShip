/*
 * File: BoardShot.java
 * 		 This file the grid for ships attacks.
 * 
 * 
 * Date: 11/16/2017
 * 
 * 
 * CS 342 Fall 2017
 * Project 4: Networked Battleship Game
 * 
 * 
 * Author(s):
 * 
 * 		Name   : Ronald Trieu
 * 		Net ID : rtrieu2
 *  
 *  	        Name   : Hend Khalil
 * 		Net ID : hkhali2
 * 
 * 		Name   : Priyank Patel
 * 		Net ID : ppate313
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BoardShot extends JPanel
{

	private Cell tiles[];
	private JPanel shipLayout;
	private JPanel letters;


	public BoardShot()
	{
	   //c = getContentPane();
		setLayout(new BorderLayout());
		setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2), new EmptyBorder(3, 3,0,0)));
		
	    shipLayout = new JPanel();
	    shipLayout.setLayout(new GridLayout(11,10));

	    tiles = new Cell[100];

	    letters = new JPanel();
	    letters.setLayout(new GridLayout(11,1));
	    

		initBoard();
		initLetters();
		
		add(letters,BorderLayout.WEST);
	    add(shipLayout,BorderLayout.CENTER);
	   
	    

	}
	private  void initBoard()
	{
		
		for(int i = 0;i<100;i++)
		{
			tiles[i] = new Cell(i);
			
			//add water image to all buttons
			ImageIcon icon = new ImageIcon("images/img/batt100.gif");
			
			Image img = icon.getImage() ;  
   			Image newimg = img.getScaledInstance(120,40,  java.awt.Image.SCALE_SMOOTH ) ;  
   			icon = new ImageIcon( newimg );	
   			tiles[i].setIcon(icon);
   			tiles[i].setPreferredSize(new Dimension(20, 20));
   			
   			shipLayout.add(tiles[i]);
   			
		}
		for(int x =1; x< 11; x++)
		{
			// add numbers of the grid
			JButton temp = new JButton();
			temp.setEnabled(false);
			temp.setText(Integer.toString(x));

			shipLayout.add(temp);
		}
	}
	private void initLetters()
	{
		//add letters to the grid
			for(int i =65 ; i< 75; i++)
			{
				
				

				JButton temp = new JButton();
				temp.setText(Character.toString((char) i) );
				temp.setEnabled(false);
				letters.add(temp);
			}
			JLabel temp = new JLabel();
			letters.add(temp);
			
	}
	public Cell[] getTiles() {
		return tiles;
	}
}
