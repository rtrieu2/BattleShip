/*
 * File: Destroyer.java
 * 		 This file contains the JMenubar for the grid.
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
 *  	Name   : Hend Khalil
 * 		Net ID : hkhali2
 * 
 * 		Name   : Priyank Patel
 * 		Net ID : ppate313
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import javax.swing.border.*;

public class Destroyer extends JPanel
{	
	private boolean placed;
	private Cell button2;
  
	
	public Destroyer()
	{
		//create a ship jpanel 
		placed = false;
		setBorder(new EmptyBorder(1, 2, 1, 2));
		setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/img/1200px-uss_winston_s-_churchil.jpg");
		Image img = icon.getImage() ;  //add image
		Image newimg = img.getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );	
		button2 = new Cell();
		button2.setText("Destroyer");
		button2.setIcon(icon);
		button2.setShip(3,0,0);
		button2.setPreferredSize(new Dimension(50, 50));
		add(button2);

	}
	
	public Cell getds(){
	  return button2;
	}
	public boolean getStatusOfShip()
	{
		return placed; 
	}
	public void updateStatusOfShip()
	{
		placed = false;
	}
  	//6 7 10
	//1 2 5
  
	
	public boolean checkIfValid(int vertOrHorz,int starting,Cell [] arr)
	{
		if(vertOrHorz ==0)
		{
			for(int i =0; i<3; i++)
			{
				int temp = 10*i;
				if(arr[starting + temp].getShip() > 0)
				{
					return false;
				}
			}
		}
		else
		{
			if(starting %10 >7)
			{
				return false;
			}
			for(int i =0; i<3; i++)
			{
				int temp = i;
				if(arr[starting + temp].getShip() > 0)
				{
					return false;
				}
			}
		}
		return true;
	}
	public boolean addacclis (String s1, int vertOrHorz, Cell [] arr){
		
	 	placed = true;
		
		char c1 = s1.charAt(0);
		char c2 = s1.charAt(1);
		c1 = Character.toUpperCase(c1);
		int num1 = c1-65;
		int num2 = c2-49;
		
		String str3 = s1.substring(1);
		if(str3.equals("10")){
			num2 = 9;
		}
		int num3 = num1 * 10 + num2;
		
		if(!checkIfValid(vertOrHorz,num3,arr))
		{
			placed = false;
			return false;
		}
	
	
		if(vertOrHorz == 0){ //check if vertical 
			//scale image 
			ImageIcon icon = new ImageIcon("images/img/batt6.gif");
			Image img = icon.getImage() ;  
   			Image newimg = img.getScaledInstance(120,40,  java.awt.Image.SCALE_SMOOTH ) ;  
   			icon = new ImageIcon( newimg );	
			 num3 = num1 * 10 + num2;
			arr[num3].setIcon(icon);
			arr[num3].setShip(3,vertOrHorz,1);
			
			 icon = new ImageIcon("images/img/batt7.gif");
			img = icon.getImage() ;  
   			newimg = img.getScaledInstance(120,40,  java.awt.Image.SCALE_SMOOTH ) ;  
   			icon = new ImageIcon( newimg );	
			num3=num3+10;
			arr[num3].setIcon(icon);
			arr[num3].setShip(3,vertOrHorz,2);
			
			
			icon = new ImageIcon("images/img/batt10.gif");
			img = icon.getImage() ;  
   			newimg = img.getScaledInstance(120,40,  java.awt.Image.SCALE_SMOOTH ) ;  
   			icon = new ImageIcon( newimg );	
			num3=num3+10;
			arr[num3].setIcon(icon);
			arr[num3].setShip(3,vertOrHorz,3);
		}
		else if(vertOrHorz == 1){
			
			ImageIcon icon = new ImageIcon("images/img/batt1.gif");
			Image img = icon.getImage() ;  
   			Image newimg = img.getScaledInstance(120,40,  java.awt.Image.SCALE_SMOOTH ) ;  
   			icon = new ImageIcon( newimg );	
			 num3 = num1 * 10 + num2;
			arr[num3].setIcon(icon);
			arr[num3].setShip(3,vertOrHorz,1);
			
			 icon = new ImageIcon("images/img/batt2.gif");
   			img = icon.getImage() ;  
   			newimg = img.getScaledInstance(120,40,  java.awt.Image.SCALE_SMOOTH ) ;  
   			icon = new ImageIcon( newimg );	
			num3=num3+1;
			arr[num3].setIcon(icon);
			arr[num3].setShip(3,vertOrHorz,2);
			
			
			
			 icon = new ImageIcon("images/img/batt5.gif");
   			img = icon.getImage() ;  
   			newimg = img.getScaledInstance(120,40,  java.awt.Image.SCALE_SMOOTH ) ;  
   			icon = new ImageIcon( newimg );	
			num3=num3+1;
			arr[num3].setIcon(icon);
			arr[num3].setShip(3,vertOrHorz,3);
		}
		return true;

     
 
}
}