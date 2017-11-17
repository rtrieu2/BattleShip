/*
 * File: BattleShipServer.java
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

import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class BattleShipServer extends JFrame {

	// GUI items
	
	private boolean running;

	// Network Items
	boolean serverContinue;
	ServerSocket serverSocket;
	Socket clientSocket;
	ObjectOutputStream out;
	ObjectInputStream in;


	// Status of Game
	private JLabel status; //stages of game
	private boolean connected; //connected to client
	private boolean shipPlaced; // all ships are placed
	private boolean turn; //whos turn
	//statistic of the game
	private int totalClient;
	private int totalServer;

	private int totalHitC;
	private int totalHitS;

	//GUI items
	private Cell [] tiles;
	private Cell [] tilesTop;
	private Cell [] tilesOpp;
	private JRadioButton v;
	private JRadioButton h;
	private JTextField pos;
	private Cell[] ships;
	private JPanel shipPanel;
	private AircraftCarrier ac;
	private Destroyer	d;
	private PatrolBoat pb;
	private SubMarine sm;
	private BattleShip bs;

	public BattleShipServer()
	{

		super("BattleShip Server");


		BoardShip bs = new BoardShip(); 
		BoardShip bo = new BoardShip();



		shipPlaced = false; //status of game
		connected =false;
		turn = true;    //first turn will be server every game.
		//statistic
		totalClient = 0;
		totalServer = 0;

		totalHitC = 0;
		totalHitS = 0;


		//gui intizilaztion 
		tiles = new Cell[100]; //bottom board
		tiles = bs.getTiles();

		tilesTop = new Cell[100]; //top board
		tilesTop = bo.getTiles();
		
		tilesOpp = new Cell[100];//representation of opponent ships location

		
		//gui stuff to add ship to the grid
		ButtonGroup bg = new ButtonGroup();
		ships = new Cell [5];
		v = new JRadioButton("Vertical",true);
		v.setPreferredSize(new Dimension(75,75));
		h = new JRadioButton("Horizontal");
		h.setPreferredSize(new Dimension(75, 75));

		pos = new JTextField("Enter Starting Position");
		pos.setPreferredSize(new Dimension(75, 75));
		bg.add(v);
		bg.add(h);



		
		shipPanel = new JPanel();
		
		
		
		setUpTilesTop();

		shipPanel.setLayout(new GridLayout(6,1));
		initShip();

		MenuServer M = new MenuServer(bo,this);
		
		bo.add(M, BorderLayout.NORTH);

		bs.add(shipPanel,BorderLayout.EAST);
		
		add(bs,BorderLayout.CENTER);
		add(bo,BorderLayout.NORTH);
		status = new JLabel("<html> Status: Waiting for Client</html>");
		add(status,BorderLayout.SOUTH);

		setSize(850,850);
		setVisible(true);

	} 


	
	
	public void setClient() {
		
			dispose(); //exit out of this frame and call the client frame
			BattleShipClient application =new BattleShipClient();
			application. setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		
	}
	
	public void setUpServer()
	{
		serverSocket = null;
		clientSocket = null;
		try {
			serverSocket = new ServerSocket(10007); //set up a server with port 10007
			connected =true;
			

		}
		catch (IOException e) 
		{ 
			System.err.println("Could not listen on port: 10007."); 
			System.exit(1); 
		} 

		try { 
			
			status.setText("<html>Status: Connected to Client. Please place ships </html>");
			clientSocket = serverSocket.accept(); 
			out = new ObjectOutputStream( clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream()); 
		} 
		catch (IOException e) 
		{ 
			System.err.println("Accept failed."); 
			System.exit(1); 
		} 
		
		





	}
	private void setUpTilesTop() // add action listener to the top grid
	{
		for(int i = 0; i < 100; i++)
		{
			tilesTop[i].addActionListener(new ButtonHandler2());
		}
	}
	private void initShip()
	{
		//intizilaze ships
		JPanel temp1 = new JPanel();
		temp1.setLayout(new GridLayout(1,2));
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(2,1));
		temp.add(v);
		temp.add(h);
		JPanel temp3 = new JPanel();
		temp3.setLayout(new GridLayout(1,1));
		temp3.add(pos);

		temp1.add(temp3);
		temp1.add(temp);

		pos.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText();

			}
		});


		shipPanel.add(temp1);



		ac = new AircraftCarrier();	
		ships[0] = ac.getaccb();
		ships[0].addActionListener(new ButtonHandler1());

		bs = new BattleShip();
		ships[1] = bs.getabs();
		ships[1].addActionListener(new ButtonHandler1());

		d = new Destroyer();
		ships[2] = d.getds();
		ships[2].addActionListener(new ButtonHandler1());

		sm = new SubMarine();
		ships[3] = sm.getsm();	
		ships[3].addActionListener(new ButtonHandler1());

		pb = new PatrolBoat();
		ships[4] = pb.getpb();
		ships[4].addActionListener(new ButtonHandler1());


		shipPanel.add(ac);
		shipPanel.add(bs);
		shipPanel.add(d);
		shipPanel.add(sm);
		shipPanel.add(pb);


	}
	private class ButtonHandler1 implements ActionListener{
		public void actionPerformed(ActionEvent event)
		{
			SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>(){

				@Override
				protected Void doInBackground() throws Exception {
					if(connected == true)
					{
						//check if vertical or horizontal
						int vertical = -1;
						if(v.isSelected())
						{
							vertical = 0;
						}
						else if(h.isSelected())
						{
							vertical = 1;
						}

						Cell temp = (Cell)event.getSource();

						//check which ship number it is to call right method
						if(temp.getShip() == 1)
						{
							if(ac.addacclis(pos.getText(), vertical, tiles))
								temp.setEnabled(false);

						}
						else if(temp.getShip()== 2)
						{
							if(bs.addacclis(pos.getText(), vertical, tiles))
								temp.setEnabled(false);


						}
						else if(temp.getShip() ==3)
						{
							if(d.addacclis(pos.getText(), vertical, tiles))
								temp.setEnabled(false);

						}
						else if(temp.getShip() ==4)
						{
							if(sm.addacclis(pos.getText(), vertical, tiles))
								temp.setEnabled(false);

						}
						else if(temp.getShip() ==5)
						{
							if(pb.addacclis(pos.getText(), vertical, tiles))
								temp.setEnabled(false);


						}
						//if all ships are placed go on to next stage
						if(checkStatusOfPlacedShip()) 
						{
							//set the status to true
							shipPlaced = true;
							Tuple temp1 =new Tuple();
							temp1.setTiles(tiles);
							try {
								out.writeObject(temp1); //send out respresentaion of ship grid
								out.flush();
							} catch (IOException e) {

							}
							try {
								Tuple red = (Tuple)in.readObject(); //read in the representation of ship grid from opponent
								tilesOpp =red.getTiles();
								System.out.println(tilesTop[0].getCellLocation());

							} catch (ClassNotFoundException e) {

							} catch (IOException e) {

							}

						}
					}
					return null;

				}


			};
			worker.execute();
		}

	}
	private class ButtonHandler2 implements ActionListener{
		public void actionPerformed(ActionEvent event)
		{
			SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>(){

				@Override
				protected Void doInBackground() throws Exception {
					if(shipPlaced && turn) // if button is pressed and if it is in the correct stage of the game
					{
						Cell t = (Cell)event.getSource();
						t.setEnabled(false);
						int loco = t.getCellLocation();
						try {
							out.writeInt(loco); //send out this button location so opponent cna update grid
							out.flush();
						} catch (IOException e){}

						if(tilesOpp[loco].getShip()>0)
						{
							
				   			
				   			t.setIcon(null); //if hit color is red
							t.setBackground(Color.RED);
							
							status.setText("<html> Status: HIT! Client Turn</html>");
							totalServer ++;
							totalHitS++;
							tilesOpp[loco].setShip(0,0,0);
						}
						else
						{
							
							t.setIcon(null); //miss set color to green
							t.setBackground(Color.GREEN);
							status.setText("<html> Status: MISS! Client Turn</html>");
							totalServer++;
							tilesOpp[loco].setShip(0,0,0);
						}
						turn = !turn;

						if(checkWinServer())
						{
							//JOptionPane.showConfirmDialog(null, "You(Server) won the Game.\nWould you like to play another game?", "Game Continue", JOptionPane.YES_NO_OPTION);
							status.setText("<html> Status: Server Won</html>");

							int result = JOptionPane.showConfirmDialog(null, "You(Server) won the Game.\nWould you like to play another game?", "Game Continue", JOptionPane.YES_NO_OPTION);

							if(result == JOptionPane.YES_OPTION)
							{
								
								System.out.println("in yes");
								reset();
							}
							else
							{
								clientSocket.close();
								serverSocket.close();
								out.close();
								in.close();
								System.exit(1);

							}

						}
						else
						{


							try {
								int tempL = in.readInt();
								if(tiles[tempL].getShip() >0)
								{
									
									tiles[tempL].setIcon(null);
									tiles[tempL].setBackground(Color.RED);//update icon
									tiles[tempL].setShip(0,0,0); //set ship to 0 since it got destroyed used for win cond

									status.setText("<html> Status: CLIENT HIT! Your Turn</html>");
									totalClient++;
									totalHitC++;
								}
								else
								{
									
						   			
									tiles[tempL].setIcon(null);
									tiles[tempL].setBackground(Color.GREEN);
									tiles[tempL].setShip(0,0,0);//set ship to 0 since it got destroyed used for win cond
									status.setText("<html> Status: CLIENT MISSED! Your Turn</html>");
									totalClient++;
								}

								turn = true;
								status.setText("<html> Status: Your Turn</html>");


								if(checkWinSelf()&&shipPlaced)
								{
								
									status.setText("<html> Status: Client Won</html>");
									int result = JOptionPane.showConfirmDialog(null, "Server won the Game.\nWould you like to play another game?", "Game Continue", JOptionPane.YES_NO_OPTION);
									if(result == JOptionPane.YES_OPTION)
									{
										/*try {
											out.writeInt(100);
											out.flush();
										} catch (IOException e){}
										// va in.readInt();
										*/
										reset();
									}
									else
									{
										clientSocket.close();
										serverSocket.close();
										out.close();
										in.close();
										System.exit(1);

									}
								}




							}catch (IOException e){}
						}
					}
					return null;
				}


			};
			worker.execute();

		}
	}


	private boolean checkStatusOfPlacedShip() 
	{
		if(ac.getStatusOfShip() && bs.getStatusOfShip() && d.getStatusOfShip() && pb.getStatusOfShip() && sm.getStatusOfShip() )
		{ 
			status.setText("<html> Status:Game Start-> Your Turn </html>");
			return true; //check all ships if all ships return true then ships are placed.

		}
		return false;
	}
	private boolean checkWinSelf()
	{
		for(int i = 0; i< 100; i++)
		{
			if(tiles[i].getShip()>0) //check to see if all values inside are 0 if all 0 every ship is destroyed
				return false;
		}
		return true;
	}
	private boolean checkWinServer()
	{
		for(int i = 0; i< 100; i++)
		{
			if(tilesOpp[i].getShip()>0)
				return false;
		}
		return true;
	}

	public String ServerStat()
	{
		String msg = "Client Current Statistics\n" +
				     "\tTotal Hit(s)    : " + totalHitC + "\n" +
				     "\tTotal Miss(s)   : " + (totalClient - totalHitC) + "\n" + 
				     "\tTotal Attack(s) : " + totalClient +
				     "Server Current Statistics\n" +
				     "\tTotal Hit(s)    : " + totalHitS + "\n" +
				     "\tTotal Miss(s)   : " + (totalServer - totalHitS) + "\n" + 
				     "\tTotal Attack(s) : " + totalServer;
		return msg;
	}
	
	private void reset() throws IOException
	{
	
		//close the current frame
		//close sockets
		dispose();
		clientSocket.close();
		serverSocket.close();
		out.close();
		in.close();
		BattleShipServer one = new BattleShipServer();
		one.setUpServer(); //connect to client again
		
			
		
		//initShip();
	
	}
	
	

} // end class EchoServer3










