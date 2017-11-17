/*
 * File: BattleShipClient.java
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


public class BattleShipClient extends JFrame 
{  

	//network
	private JButton connectButton;
	Socket echoSocket;
	ObjectOutputStream out;
	ObjectInputStream in;

	private JLabel status;
	// Status of Game
	private boolean connected;
	private boolean shipPlaced;
	private boolean turn;
	private int totalClient;
	private int totalServer;

	private int totalHitC;
	private int totalHitS;
	
	private String port;
	private String ip;


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




	// set up GUI
	public BattleShipClient()
	{
		super( "BattleShip Client" );

		BoardShip bs = new BoardShip(); //bottom half gui
		BoardShot bo = new BoardShot(); //top half of gui

		shipPlaced = false; //status of game
		connected =false; //status between client and server
		turn = false;    //first turn will be server every game.
		//statistic
		totalClient = 0;
		totalServer =0;

		totalHitC = 0;
		totalHitS = 0;

		//gui intizilaztion 
		tiles = new Cell[100]; //setting up the buttons at bottom of gui buttons on the button wil be useless just visualization
		tiles = bs.getTiles();

		tilesTop = new Cell[100]; //seting up the buttons at top this is how the majority of the game is played
		tilesTop = bo.getTiles();
		
		tilesOpp = new Cell[100];
		setUpTilesTop();

		ButtonGroup bg = new ButtonGroup();  //setting up the gui panel for placing the ship
		ships = new Cell [5];
		v = new JRadioButton("Vertical",true);
		v.setPreferredSize(new Dimension(75,75));
		h = new JRadioButton("Horizontal");
		h.setPreferredSize(new Dimension(75, 75));
		pos = new JTextField("Enter Starting Position");
		pos.setPreferredSize(new Dimension(75, 75));
		bg.add(v);
		bg.add(h);



		// temp spot for connecting to server
		JPanel leftPanel  = new JPanel();
		shipPanel = new JPanel();

		

		status = new JLabel("<html> Status: Waiting for Server</html>"); //temp

		

		MenuClient M = new MenuClient(this);
		//set up the ships 5 ships the top row will be the panel that will determine where to place the ship
		shipPanel.setLayout(new GridLayout(6,1));
		initShip();
		
		bo.add(M, BorderLayout.NORTH);
		bs.add(shipPanel,BorderLayout.EAST);
		bo.add(leftPanel,BorderLayout.EAST);

		add(bs,BorderLayout.CENTER);
		add(bo,BorderLayout.NORTH);
		add(status,BorderLayout.SOUTH);
		setSize(850,850);
		setVisible(true);




	} 
	private void setUpTilesTop()
	{
		for(int i = 0; i < 100; i++)
		{
			tilesTop[i].addActionListener(new ButtonHandler2());
		}
	}
	private void initShip()
	{
		//set up how to add the ship to the grid
		JPanel temp1 = new JPanel(); //panel to be added to the shipPanel
		temp1.setLayout(new GridLayout(1,2)); 
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(2,1));
		temp.add(v); //two radio buttons
		temp.add(h); //add to one panel 

		JPanel temp3 = new JPanel();
		temp3.setLayout(new GridLayout(1,1));
		temp3.add(pos);

		temp1.add(temp3);
		temp1.add(temp);

		//add a listner to the text field
		pos.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText();

			}
		});


		shipPanel.add(temp1);


		//add action listeners to the button that will handle placing ship to the grid
		ac = new AircraftCarrier();	
		ships[0] = ac.getaccb();
		ships[0].addActionListener(new ButtonHandler());

		bs = new BattleShip();
		ships[1] = bs.getabs();
		ships[1].addActionListener(new ButtonHandler());

		d = new Destroyer();
		ships[2] = d.getds();
		ships[2].addActionListener(new ButtonHandler());

		sm = new SubMarine();
		ships[3] = sm.getsm();	
		ships[3].addActionListener(new ButtonHandler());

		pb = new PatrolBoat();
		ships[4] = pb.getpb();
		ships[4].addActionListener(new ButtonHandler());


		shipPanel.add(ac);
		shipPanel.add(bs);
		shipPanel.add(d);
		shipPanel.add(sm);
		shipPanel.add(pb);


	}

	private void checkStatus()
	{
		if(connected == true)
		{
			status.setText("<html> Status: Connected to Server. Please Place Ships</html>");
		}
		else if(!connected)
		{
			status.setText("<html> Status: Waiting for Server</html>");
		}
	}

	// handle button event
	



	
	//handles placing the ship onto the bottom grid
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event)
		{
			SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>(){

				@Override
				protected Void doInBackground() throws Exception {
					if(connected == true) // will only allow client to place ship if it is connected to server
					{
						int vertical = -1;
						if(v.isSelected()) //vertical
						{
							vertical = 0;
						}
						else if(h.isSelected()) // horizontal
						{
							vertical = 1;
						}


						Cell temp = (Cell)event.getSource();

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
						if(checkStatusOfPlacedShip()) //check if all boats are placed
						{
							shipPlaced = true; //update status of game
							try {
								Tuple red = (Tuple)in.readObject(); //get the grid information from server where all the pieces are
								tilesOpp =red.getTiles();

							} catch (ClassNotFoundException e) {} 
							catch (IOException e) {}

							Tuple temp1 =new Tuple();
							temp1.setTiles(tiles);
							try {
								out.writeObject(temp1); //write to server grid information
								out.flush();
							} catch (IOException e) {}
							//change status

							try {
								int loco = in.readInt(); //read in one value to keep loop going
								if(tiles[loco].getShip() >0) //server guessed correctely
								{
									tiles[loco].setIcon(null); //update icon
									tiles[loco].setBackground(Color.RED); //update icon
									tiles[loco].setShip(0,0,0); //set ship to 0 since it got destroyed used for win cond
									totalServer++;
									totalHitS++;
								}
								else
								{
									
									
									tiles[loco].setIcon(null);
									tiles[loco].setBackground(Color.GREEN);//missed updateIcon to destroy
									
									totalServer++;
								}
								turn = true;
								status.setText("<html> Status: Your Turn</html>");
							} catch (IOException e) {

							}

						}



					}

					return null;
				}

			};

			worker.execute();
		}//end buttonhandlerclass


	} 
	private boolean checkStatusOfPlacedShip()
	{
		if(ac.getStatusOfShip() && bs.getStatusOfShip() && d.getStatusOfShip() && pb.getStatusOfShip() && sm.getStatusOfShip() )
		{
			status.setText("<html> Status: Game Start-> Waiting for Server Turn </html>");
			return true;
		}
		return false;
	}
	private class ButtonHandler2 implements ActionListener{
		public void actionPerformed(ActionEvent event)
		{
			SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>(){

				@Override
				protected Void doInBackground() throws Exception { //majority of where the game will be played
					if(shipPlaced && turn)
					{
						Cell t = (Cell)event.getSource(); //get the tile information that was clicked
						t.setEnabled(false);
						int loco = t.getCellLocation();
						try {
							out.writeInt(loco); //write to server the tile location that was pressed
							out.flush();
						} catch (IOException e){}

						if(tilesOpp[loco].getShip()>0) //check with server information was hit
						{
							t.setIcon(null);
							t.setBackground(Color.RED);
							
							status.setText("<html> Status: HIT! Server Turn</html>");
							totalClient ++;
							totalHitC++;
							tilesOpp[loco].setShip(0,0,0);
						}
						else //miss 
						{
							
				   			
				   			t.setIcon(null);
							t.setBackground(Color.GREEN);
							status.setText("<html> Status: MISS! Client Turn</html>");
							totalClient++;

						}
						turn = !turn;


						if(checkWinServer() && shipPlaced)
						{
							//JOptionPane.showConfirmDialog(null, "You won the Game.\nWould you like to play another game?", "Game Continue", JOptionPane.YES_NO_OPTION);
							
							status.setText("<html> Status: Client Won</html>");
							
							int result = JOptionPane.showConfirmDialog(null, "You (Client) won the Game.\nWould you like to play another game?", "Game Continue", JOptionPane.YES_NO_OPTION);

							if(result == JOptionPane.YES_OPTION)
							{
																
								reset();
								
							}
							else
							{
								echoSocket.close();
								out.close();
								in.close();
								System.exit(1);

							}
						}
						else
						{

							try {
								int tempL = in.readInt();
								if(tiles[tempL].getShip() >0) //read in value from server server guessed correctly
								{
									tiles[tempL].setIcon(null);
									tiles[tempL].setBackground(Color.RED);
									tiles[tempL].setShip(0,0,0); //set ship to 0 since it got destroyed used for win cond
									totalServer++;
									totalHitS++;
									status.setText("<html> Status: SERVER HIT! Your Turn</html>");
								}
								else //missed
								{
									ImageIcon icon = new ImageIcon("images/img/batt102.gif");
									
									tiles[tempL].setIcon(null);
									tiles[tempL].setBackground(Color.GREEN);
									tiles[tempL].setShip(0,0,0);
									status.setText("<html> Status: SERVER MISSED! Your Turn</html>");
									totalServer++;
								}
								turn = true;
								status.setText("<html> Status: Your Turn</html>");
								//check if anyone has won

								if(checkWinSelf()&&shipPlaced)
								{
									//JOptionPane.showConfirmDialog(null, "Server won the Game.\nWould you like to play another game?", "Game Continue", JOptionPane.YES_NO_OPTION); //temp
									status.setText("<html> Status: Server Won</html>");
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
										echoSocket.close();
										out.close();
										in.close();
										System.exit(1);

									}
									
									
									//out.writeInt
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
	private boolean checkWinSelf()
	{
		for(int i = 0; i< 100; i++)
		{
			if(tiles[i].getShip()>0) //if none of the tiles have a ship value attached all ships have been destroyed.
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
	
	
	private void reset() throws IOException
	{
	
		dispose();
		echoSocket.close();
		out.close();
		in.close();
		BattleShipClient one = new BattleShipClient();
		while(!one.tempConnection(ip,port))
		{
			
		}
		
			
		
		one.checkStatus();
		//initShip();
	
	}
	public boolean tempConnection(String tip, String tport)
	{
		if (connected == false)
		{

			try {
				ip = tip;
				port = tport;
				
				int intPort = Integer.parseInt(port);
				echoSocket = new Socket(ip, intPort);
				out = new ObjectOutputStream( echoSocket.getOutputStream());
				in = new ObjectInputStream(echoSocket.getInputStream()); 

				connected = true;
				
				
				checkStatus();
				return true;
				

			} catch (NumberFormatException e) {
				System.out.println("1");
				return false;
			} catch (UnknownHostException e) {
				System.out.println("2");
				return false;
			} catch (IOException e) {
				System.out.println("3");
				return false;

			}

		}
		return false;
		



	}
	public void disconnectConnection() throws IOException
	{
		connected = false;
		echoSocket.close();
		out.close();
		in.close();
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
	

	
}// end class EchoServer3







