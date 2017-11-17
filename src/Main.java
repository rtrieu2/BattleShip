/*
 * File: Main.java
 * 		 This file contains the Main method.
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

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.border.*;
public class Main extends JFrame
{
	

	public static void main(String[] args)
	{
  
		BattleShipServer application =new BattleShipServer();
		application. setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}
