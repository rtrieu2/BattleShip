/* Main Method */

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import networking.BattleshipServer;
//import networking.Connection;

public class BattleshipGame extends JFrame
{
private static final long serialVersionUID = 1L;
	
	private static final String DefaultIP = "127.0.0.1";
	private static String serverIP = DefaultIP;
	//private static Client cp = null;

	private GridBagConstraints grid = new GridBagConstraints();
	private JPanel LoadScreen = new JPanel();
	private JButton BattleGame = null;
	private JButton ConnectGame = null;
	private JTextField Address = null;
	private JPanel GameOutput = new JPanel();

	//private volatile GameLayout GL = null;
	//private volatile Player P = null;
	
	public static void main(String[] args)
	{
		BattleshipGame b = new BattleshipGame();
		b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.setVisible(true);
	}
	
	public enum ScreenType 
	{
		MAIN,GAME
	}
	
	private void setCardPane(ScreenType cp)
	{
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), cp.toString());
	}
	
	
	
	
	public BattleshipGame() 
	{
		setTitle("Networked Battleship");
		getContentPane().setLayout(new CardLayout());
		getContentPane().add(LoadScreen, ScreenType.MAIN.toString());
		getContentPane().add(GameOutput, ScreenType.GAME.toString());

		MainScreen();
		pack();

		setCardPane(ScreenType.MAIN);
		
		setSize(289, 346);// the default size of the current layout
	}
	
	
	public void MainScreen()
	{
		LoadScreen.setLayout(new GridBagLayout());
		grid.gridx = 0;
		grid.gridy = 0;

		BattleGame = new JButton();
		
		BattleGame.setText("Welcome Battleship Game");
		BattleGame.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						// TODO:
					}
				});
		
		LoadScreen.add(BattleGame, grid);

		ConnectGame = new JButton();
		ConnectGame.setText("Start The Game");
		grid.gridy++;
		LoadScreen.add(ConnectGame, grid);
		ConnectGame.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{
						if(Address.getText().equals("")) 
						{
							Address.setText(DefaultIP);
						}
						serverIP = Address.getText();
						//startGame();
					}
				});

		Address = new JTextField(10);
		Address.setText(DefaultIP);
		grid.gridy++;
		LoadScreen.add(Address, grid);
	}
	
	
}