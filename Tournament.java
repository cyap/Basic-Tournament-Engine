import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
Purpose: Represents an instance of a Tournament. Receives reports from UserInterface, a
*/


public class Tournament
{
	public Tournament(JFrame aFrame, InterfaceManager anIM, UserInterface aUI) 
	{	
		frame = aFrame;
		IM = anIM;
		UI = aUI;

		playerCount = 0;
		numberOfRounds = 0;
		playerTree = new ArrayList<Player>();
		roundList = new ArrayList<Round>();
		formatList = new TournamentFormat[] {new SingleElim(), new McMahon()};
		format = formatList[0];
		seeded = false;
		elimination = true;
		//format = new SingleElim();
		//format = new McMahon();


		
		String[] listOfPlayers;

	}
	
	public static void instantiateMainMenu() 
	{
		UI.clearListeners();
		frame.getContentPane().removeAll();
		// Draw main menu
		UI.createMainMenu(frame);

		// New
		ChangeListener newListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
			// Shift to Format Selection Menu
			instantiateFormatMenu();
			//System.out.println(UI.listeners);
			}
		};
		
		// Load
		ChangeListener loadListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
			// what to do when updated - how to distinguish between events
			}
		};
		
		// Exit
		ChangeListener exitListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
			// Exit the entire program
			frame.dispose();
			}
		};

		UI.addChangeListener(newListener);
		UI.addChangeListener(loadListener);
		UI.addChangeListener(exitListener);

		// Make visible
		IM.updateWindow(frame);
	}

	public static void instantiateFormatMenu() 
	{
		UI.clearListeners();
		frame.getContentPane().removeAll();

		// Draw Format Menu
		UI.createFormatMenu(frame);

	
		// Back
		ChangeListener backListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
			// Shift to Format Selection Menu
			//System.out.println(UI.listeners);
			instantiateMainMenu();
			}
		};
		
		// Next
		ChangeListener nextListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				// if single-elimination, shift to Player Entry Menu
				if (elimination == true)
				{
					instantiatePlayerEntryMenu(false);
				}
				
				else
				{
					instantiateOptionsMenu();
				}
			}	
		};

		// Exit
		ChangeListener exitListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
			// Shift to Format Selection Menu
			System.out.println(UI.listeners);
			instantiateMainMenu();
			}
		};
		UI.addChangeListener(backListener);
		UI.addChangeListener(nextListener);
		UI.addChangeListener(exitListener);

		// Format buttons
		for (int i = 0; i < formatList.length; i++)
		{
			final int formatNum = Integer.valueOf(i);
			ChangeListener formatListener = new ChangeListener()
			{
				public void stateChanged(ChangeEvent event)
				{
					System.out.println(elimination);
					format = formatList[formatNum];
					//format.setSeed(seeded);
					elimination = format.setElimination();
					
					System.out.println(formatNum);
				}
			};
			UI.addChangeListener(formatListener);
		}

		// Make visible
		IM.updateWindow(frame);
	}

	public static void instantiateOptionsMenu() 
	{
		UI.clearListeners();
		frame.getContentPane().removeAll();

		// Draw Format Menu
		UI.createOptionsMenu(frame);
		// Make visible
		IM.updateWindow(frame);

		// Back
		ChangeListener backListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
			// Shift to Format Selection Menu
			instantiateFormatMenu();
			}
		};
		
		// Next
		ChangeListener nextListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				// capture number
				String roundNum = UI.reportUserInput(frame).get(0);
				int numberOfRounds = Integer.valueOf(roundNum);

				// capture tiebreaker method

				// go to player entry menu
				instantiatePlayerEntryMenu(true);
			}
		};

		// Exit
		ChangeListener exitListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
			// Shift to Main Menu
			instantiateMainMenu();
			}
		};
		UI.addChangeListener(backListener);
		UI.addChangeListener(nextListener);
		UI.addChangeListener(exitListener);
	}

	public static void instantiatePlayerEntryMenu(boolean seed) 
	{

		UI.clearListeners();
		frame.getContentPane().removeAll();

		// Draw Format Menu
		UI.createPlayerEntryMenu(frame, seeded);
		// Make visible
		IM.updateWindow(frame);

		// Back
		ChangeListener backListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				if (elimination == true)
				// Shift back to Format Selection Menu
					instantiateFormatMenu();
				else
					instantiateOptionsMenu();
			}
		};
		
		// Next
		ChangeListener nextListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{

				// generate tournament
				generateTournament();

				UI.clearListeners();
				instantiateRoundMenu(1);

			}
		};

		// Exit
		ChangeListener exitListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				// Shift to Format Selection Menu
				System.out.println(UI.listeners);
				instantiateMainMenu();
			}
		};

		UI.addChangeListener(backListener);
		UI.addChangeListener(nextListener);
		UI.addChangeListener(exitListener);
	}

	public static void instantiateRoundMenu(int roundNumber)
	{
		UI.clearListeners();
		frame.getContentPane().removeAll();

		// Delineate Round
		int beginning = ((int) Math.pow(2,numberOfRounds+1))-2;
		for (int s = 1; s < roundNumber; s++)
			beginning = (beginning / 2) -1;
		beginning++;
		int end = beginning / 2 - 1;

		// Add Round navigation listeners
		for (int y = 0; y < numberOfRounds; y++)
		{
			final int index = Integer.valueOf(y+1);
			ChangeListener roundListener = new ChangeListener()
			{
				public void stateChanged(ChangeEvent event)
				{
					instantiateRoundMenu(index);
				}
			};
			UI.addChangeListener(roundListener);
		}

		// Add Results listener
		ChangeListener resultsListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				instantiateResultsMenu(numberOfRounds);		
			};
				

		};

		UI.addChangeListener(resultsListener);

		// Add pairing listeners
		for (int i = 0; i < playerTree.size(); i++)
		{
			final int index = Integer.valueOf(i);
			ChangeListener playerListener = new ChangeListener()
			{
				public void stateChanged(ChangeEvent event)
				{
					format.advancePlayer(index, playerTree);
				}
			};
		
			UI.addChangeListener(playerListener);
		}
	
		// Draw Round Menu
		UI.createRoundMenu(frame, numberOfRounds, beginning, end, playerTree, roundNumber);
		// Make visible
		IM.updateWindow(frame);

	}


	private static void instantiateResultsMenu(int resultsNum)
	{
		UI.clearListeners();
		frame.getContentPane().removeAll();
		
		String resultsText = format.generateResults(playerTree);

		// Add Round navigation listeners
		for (int y = 0; y < numberOfRounds; y++)
		{
			final int index = Integer.valueOf(y+1);
			ChangeListener roundListener = new ChangeListener()
			{
				public void stateChanged(ChangeEvent event)
				{
					instantiateRoundMenu(index);
				}
			};
			UI.addChangeListener(roundListener);
		}

		// Add Results listener
		ChangeListener resultsListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				instantiateResultsMenu(numberOfRounds);		
			};
				

		};
		UI.addChangeListener(resultsListener);
		System.out.println(resultsText);
		//ChangeListener resultsListener = new ChangeListener();
		UI.createResults(frame, numberOfRounds, numberOfRounds, resultsText);
		IM.updateWindow(frame);
	}

	public static void generateTournament()
	{
		// Gather user input
		String players = UI.reportUserInput(frame).get(0);
		
		// Seeds
		String seedList = "";
		if (seeded == true)
		{
			seedList = UI.reportUserInput(frame).get(1);
		}

		// Generate list of players
		listOfPlayers = format.generatePlayerList(players);

		// Pad with byes
		int byes = format.byePad(listOfPlayers, listOfPlayers.length);
		System.out.println(playerCount);

		// Determine playercount
		playerCount = listOfPlayers.length + byes;

		// Determine number of rounds
		numberOfRounds = format.determineNumberOfRounds(playerCount, numberOfRounds);
		
		// Generate initial list of players
		playerTree = format.generatePairings(listOfPlayers, playerCount, numberOfRounds, byes, seedList);
	}

	public static void main(String[] args)
	{
		// Initialize
		InterfaceManager tourIM = new InterfaceManager();
		UserInterface tourUI = new UserInterface();

		// Create mainFrame
		JFrame mainFrame = new JFrame();
		mainFrame.setMinimumSize(new Dimension(300,300));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Tournament tournament = new Tournament(mainFrame, tourIM, tourUI);
		
		// Main Menu
		tournament.instantiateMainMenu();
	
	}
	
	private static JFrame frame;
	private static InterfaceManager IM;
	private static UserInterface UI;
	
	private static int playerCount;
	private static int numberOfRounds;
	private static String[] listOfPlayers;
	private static ArrayList<Player> playerTree;
	
	private static TournamentFormat format;
	private static TournamentFormat[] formatList;
	
	private static ArrayList<Round> roundList;
	private static boolean seeded;
	private static boolean elimination;
	private boolean tiebreaker;
}