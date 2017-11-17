/*
 * File: AircraftCarrier.java
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


import javax.swing.border.*;

public class AircraftCarrier extends JPanel
{
	private Cell button;
	private boolean placed; 

	public AircraftCarrier()
	{

		placed = false;
		setBorder(new EmptyBorder(1, 2, 1, 2));
		setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/img/uss_carl_vinson_cvn-70_underwa.jpg");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );	
		button = new Cell();
		button.setIcon(icon);
		button.setText("AircraftCarrier");
		button.setShip(1,0,0);
		button.setPreferredSize(new Dimension(50, 50));
		add(button);
	}

	public Cell getaccb(){
		return button;
	}
	public boolean getStatusOfShip()
	{
		return placed; 
	}
	public void updateStatusOfShip()
	{
		placed = false;
	}


	public boolean checkIfValid(int vertOrHorz,int starting,Cell [] arr)
	{
		if(vertOrHorz ==0)
		{
			for(int i =0; i<5; i++)
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
			if(starting %10 >5)
			{
				return false;
			}
			
			for(int i =0; i<5; i++)
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

		if(vertOrHorz == 0){
			ImageIcon icon = new ImageIcon("images/img/batt6.gif");
			Image img = icon.getImage() ; 
			Image newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);	

			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,1);
			arr[num3].setPreferredSize(new Dimension(20, 20));

			icon = new ImageIcon("images/img/batt7.gif");
			img = icon.getImage() ; 
			newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);	
			num3=num3+10;
			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,2);

			icon = new ImageIcon("images/img/batt8.gif");
			img = icon.getImage() ; 
			newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);	
			num3=num3+10;
			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,2);

			icon = new ImageIcon("images/img/batt9.gif");
			img = icon.getImage() ; 
			newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);	
			num3=num3+10;
			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,2);

			 icon = new ImageIcon("images/img/batt10.gif");
			img = icon.getImage() ; 
			newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);	
			num3=num3+10;
			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,3);
		}
		else if(vertOrHorz == 1){

			ImageIcon icon = new ImageIcon("images/img/batt1.gif");
			Image img = icon.getImage() ; 
			img = icon.getImage() ; 
			Image newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);	
			
			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,1);

			icon= new ImageIcon("images/img/batt2.gif");
			img = icon.getImage() ; 
			newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);	
			
			num3=num3+1;
			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,2);

			icon = new ImageIcon("images/img/batt3.gif");
			img = icon.getImage() ; 
			newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);	
			
			num3=num3+1;
			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,2);

			icon = new ImageIcon("images/img/batt4.gif");
			img = icon.getImage() ; 
			newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);	
			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,2);

			icon = new ImageIcon("images/img/batt5.gif");
			img = icon.getImage() ; 
			newimg = img.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);		
			num3=num3+1;
			arr[num3].setIcon(icon);
			arr[num3].setShip(1,vertOrHorz,3);
		}


		return true;
	}

}