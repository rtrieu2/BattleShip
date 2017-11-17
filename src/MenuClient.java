/*
 
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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

//
// Create Menu bar for JFrame
//
public class MenuClient extends JMenuBar //implements MenuListener
{
	// Create my font
	private static Font myFont = new Font("Tahoma", Font.BOLD, 15);
	//private BoardShip Gui;
	//private BattleShipClient GuiS;
	
	private String msg;
	private String ip;
	private String port;
	private Boolean connected;
	
	public MenuClient(BattleShipClient bsc1)
	{
		super();
		connected = false;
		// Set default font
		UIManager.put("Menu.font", myFont);
		UIManager.put("MenuItem.font", myFont);
		UIManager.put("OptionPane.messageFont", myFont);
		
		
		//this.Gui = Gui1;
		//this.GuiS = bsc1;
		
		
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
		JMenuItem ruleItem = new JMenuItem("How to Play");
		ruleItem.setMnemonic('C');
		gameMenu.add(ruleItem);
		ruleItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JOptionPane.showMessageDialog(null, msgRule, "Rules for plays the Game", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		);
		
		
		// Statitics Menu
		JMenu statMenu = new JMenu("Statitics");
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
						msg = bsc1.ServerStat();

						JOptionPane.showMessageDialog(null,  msg, "Statitic of Cleint", JOptionPane.INFORMATION_MESSAGE);
						
					}
				});
		
		
		
		// Connection Menu
		JMenu connMenu = new JMenu("Connection");
		connMenu.setMnemonic('o');

		
		JMenuItem clientItem = new JMenuItem("Connect to Server");
		connMenu.add(clientItem);
		clientItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						if (connected == false)
						{
							if(bsc1.tempConnection(ip,port) == true)
								clientItem.setText("Disconnect From Server");
						}
						else
						{
							connected = false;
							try {
								bsc1.disconnectConnection();
							} catch (IOException e) {
								
							}
						}
					}
				});
		
		JMenuItem infoForClientItem = new JMenuItem("Enter IP and Port");
		connMenu.add(infoForClientItem);
		infoForClientItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{	
						
						 	JTextField ipField = new JTextField(5);
						    JTextField portField = new JTextField(5);

						    JPanel myPanel = new JPanel();
						    
						    myPanel.add(new JLabel("IP:"));
						    myPanel.add(ipField);
						    myPanel.add(Box.createVerticalStrut(15)); // a spacer
						    myPanel.add(new JLabel("Port:"));
						    myPanel.add(portField);

						    
						    int result = JOptionPane.showConfirmDialog(null, myPanel,
						        "Please Ip and Port", JOptionPane.OK_OPTION);
						    if (result == JOptionPane.OK_OPTION) {
						      System.out.println("x value: " + ipField.getText());
						      System.out.println("y value: " + portField.getText());
						    }
						    ip = ipField.getText();
						    port = portField.getText();
						    
						    
					}
						
					
				});

		
		

		this.add(fileMenu);
		this.add(gameMenu);
		this.add(statMenu);
		this.add(connMenu);
		this.add(Box.createHorizontalGlue());
		
		
	}
	
}
