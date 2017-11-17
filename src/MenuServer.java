/*
 * File: MyMenuBar.java
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

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



//
// Create Menu bar for JFrame
//
public class MenuServer extends JMenuBar //implements MenuListener
{
	// Create my font
	private static Font myFont = new Font("Tahoma", Font.BOLD, 15);
	//private BoardShip Gui;
	private BattleShipServer GuiS;
	
	private String msg;
	
	public MenuServer(BoardShip Gui1, BattleShipServer bss1)
	{
		super();
		
		// Set default font
		UIManager.put("Menu.font", myFont);
		UIManager.put("MenuItem.font", myFont);
		UIManager.put("OptionPane.messageFont", myFont);
		
		
		//this.Gui = Gui1;
		this.GuiS = bss1;
		
		
		// File and Sub Menus
		// File Menu'
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
	    // About message
		String msgAbout = "Author(s):\n" +
						  "         Name            Netid\n" +
						  "     Ronald Trieu   -  rtrieu2\n" +
						  "     Hend Khalil    -  hkhali2  \n" +
						  "     Priyank Patel  -  ppate313\n\n" +
						  "CS-342   : Program # 4 Battleship Game\n" +
						  "Version  : 1.0.0";
		
		// About item 
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic('A');
		fileMenu.add(aboutItem);
		aboutItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					
					JOptionPane.showMessageDialog(null, msgAbout, "About", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		);

			
		// Exit Item
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('x');
		fileMenu.add(exitItem);
		exitItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					System.exit(0);
				}
			});
		
	    // Game Menu
		JMenu gameMenu = new JMenu("Help");
		gameMenu.setMnemonic('H');
		
		// Help Item
		// How to play Battleship Game message
		String msgConn = "First click on Clinet button.\n" + 
						 "Run the game again.\n" +
				         "On Server Game click connect.\n" +
						 "On Client Game click connect.\n";
		
		// 	How to Connect to The Client
		JMenuItem connItem = new JMenuItem("How To Connect");
		connItem.setMnemonic('C');
		gameMenu.add(connItem);
		connItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JOptionPane.showMessageDialog(null, msgConn, "How To Connect to The Client", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		);
		
		String msgRule = "Place all the ships on both Server and Client side.\n" + 
				         "Server will start and will randomly guess a ship location Click on top grid to guess.\n" +
						 "Then client turn likewise one by one.\n";
		
//	 	How to Connect to The Client
		JMenuItem ruleItem = new JMenuItem("How To Play");
		ruleItem.setMnemonic('C');
		gameMenu.add(ruleItem);
		ruleItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JOptionPane.showMessageDialog(null, msgRule, "Rules of the Game", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		);
		
		
		// Connect to the Client
		/*JMenuItem clientItem = new JMenuItem("Connect to the Client ");
		clientItem.setMnemonic('C');
		gameMenu.add(clientItem);
		clientItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					// TODO:
					System.exit(0);
				}
			});*/
		
		
		// Dis-Connect from the Client
		/*JMenuItem disConnectItem = new JMenuItem("Disconnect From the Client ");
		disConnectItem.setMnemonic('D');
		gameMenu.add(disConnectItem);
		disConnectItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					// TODO:
					System.exit(0);
				}
			});*/
		
		
		
		// Statitics Menu
		JMenu statMenu = new JMenu("Statistic");
		statMenu.setMnemonic('T');
		
		// Show the Game Statitics box
		JMenuItem statItem = new JMenuItem("Statistic of The Game ");
		statItem.setMnemonic('a');
		statMenu.add(statItem);
		statItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						// TODO:
						msg = bss1.ServerStat();

						//System.out.println(msg);
						JOptionPane.showMessageDialog(null,  msg, "Statitic of Server", JOptionPane.INFORMATION_MESSAGE);
						//BattleShipServer s = new BattleShipServer();

						//BattleShipClient bc = new BattleShipClient();


					}
				});
		
		
		// Statitics Menu
		JMenu connMenu = new JMenu("Connection");
		connMenu.setMnemonic('o');
		
		String machineAddress;
		int portAdd = 10007;
		
		
		try
		{  
			InetAddress addr = InetAddress.getLocalHost();
			machineAddress = addr.getHostAddress();
		}
		catch (UnknownHostException e)
		{
			machineAddress = "127.0.0.1";
		}
		
		String ipMsg = "IP Address : " + machineAddress + "\n" +
					   "Port : " + portAdd;
		
		JMenuItem ipItem = new JMenuItem("IP Address and Port");
		ipItem.setMnemonic('p');
		connMenu.add(ipItem);
		ipItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						JOptionPane.showMessageDialog(null,  ipMsg, "IP and Port", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}); 
		
		JMenuItem clientItem = new JMenuItem("Connect to Client");
		connMenu.add(clientItem);
		clientItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						bss1.setUpServer();
					}
				});
		JMenuItem client = new JMenuItem("Client");
		client.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						bss1.setClient();
					}
				});
		
		

		this.add(fileMenu);
		this.add(gameMenu);
		this.add(statMenu);
		this.add(connMenu);
		this.add(client);
		this.add(Box.createHorizontalGlue());
		
		
	}
	
}
