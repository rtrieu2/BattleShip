/*
 * File: Tuple.java
 * 		 his file used to get the index of cell.
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

import java.io.Serializable;

public class Tuple implements Serializable {
	
	private Cell[] tiles;
	
	public Tuple()
	{
		
	}
	public void setTiles(Cell[] temp)
	{
		tiles = temp;
	}
	public Cell[] getTiles()
	{
		return tiles;
	}

}
