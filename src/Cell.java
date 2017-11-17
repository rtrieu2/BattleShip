/*
 * File: Cell.java
 * 		 This file used to give the current ship position.
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

import javax.swing.*;

public class Cell extends JButton
{
	
	//ship type from 1-5
	private int ship;
	//1 = front piece 2 middle pieces 3 back piece
	private int shipPartNumber; 
	//horizontal or vert 0 for vert 1 for horz
	private int hov;
	//cell location on the grid
	private int cellLocation;

	public Cell()
	{
		setText(" ");
		//intiziliaze the cell to false
		hov = -1;
		//no ship has been set
		shipPartNumber = 0;
		//
		ship = 0;
	}
	public Cell(int loco)
	{
		cellLocation = loco;
		
		//intiziliaze the cell to false
		
		
		//no ship has been set
		shipPartNumber = 0;
		//
		ship = 0;

	}
	public void setShip(int shipNumber, int orientation, int pNum){
		ship = shipNumber;
		hov = orientation;
		shipPartNumber = pNum;
		
	}
	
	public int getHv()
	{
		return hov;
	}
	
	public int getShipPartNumber()
	{
		return shipPartNumber;
	}
	
	public int getShip()
	{
		return ship;
	}
	public int getCellLocation()
	{
		return cellLocation;
	}
	
	
}
