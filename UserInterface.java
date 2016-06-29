import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

/**
Purpose: Displays the user interface
*/

public class UserInterface
{
	/**
	Class for constructing the various user interfaces
	@param
	*/
	
	public UserInterface()
	{
		//listener = aListener;
		listeners = new ArrayList<ChangeListener>();
		userInputFields = new ArrayList<JTextArea>();
		userInput = new ArrayList<String>();
		/**
		
		*/
	}
	public void clearListeners()
	{
		listeners.clear();
		userInputFields.clear();
		userInput.clear();
	}

	public void addChangeListener(ChangeListener aListener)
	{
		listeners.add(aListener);
	}

	public void addChangeListener2(int index, ChangeListener aListener)
	{
		listeners.add(index, aListener);
	}

	public static void addTournamentListener(AbstractButton button, final int index)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				ChangeEvent updateEvent = new ChangeEvent(this);
				//System.out.println(listeners);
				listeners.get(index).stateChanged(updateEvent);
			}
		});
	}

	//public static void checkInput(JTextArea textArea)
	//{
	//	String returnvalue = textArea.getText();
	//	System.out.println(returnvalue);
	//}

	public static ArrayList<String> reportUserInput(JFrame frame)
	{
		for (int i = 0; i < userInputFields.size(); i++)
			//checkInput(userInputFields.get(i));
			userInput.add(userInputFields.get(i).getText());
			

		//checkInput(textTest);
		//JTextArea textArea = new JTextArea();
		//for (int i = 0; i < userInputFields.size(); i++) 
		//{
			//if (userInputFields.get(i).getClass().isInstance(textArea))
			//{
				// capture
			//	userInput.add(textArea.getText());
			//	System.out.println(userInput.get(0));
			//	System.out.println(textArea.getText());
			//}
		//}
		//System.out.println(userInput);				
		return userInput;
	}

	public static JPanel createNavigationPanel()
	{
		JPanel navigationPanel = new JPanel();
		navigationPanel.setLayout(new FlowLayout());
		
		JButton backButton = new JButton("Back");
		JButton nextButton = new JButton("Next");
		JButton quitButton = new JButton("Quit");

		// Add button listeners
		addTournamentListener(backButton, 0);
		addTournamentListener(nextButton, 1);
		addTournamentListener(quitButton, 2);
		
		navigationPanel.add(backButton);
		navigationPanel.add(nextButton);
		navigationPanel.add(quitButton);

		return navigationPanel;
	}


	public static void createMainMenu(final JFrame frame)
	{
		
		JButton newButton = new JButton("New");
		JButton loadButton = new JButton("Load");
		JButton exitButton = new JButton("Exit");
		
		frame.setLayout(new GridLayout(3,1));
		
		frame.add(newButton);
		frame.add(loadButton);
		frame.add(exitButton);
		
		// Button actions
		addTournamentListener(newButton, 0);
		addTournamentListener(loadButton, 1);
		addTournamentListener(exitButton, 2);
		//newButton.addActionListener(new ActionListener()
		//{
		//	public void actionPerformed(ActionEvent event)
		//	{
		//		ChangeEvent updateEvent = new ChangeEvent(this);
		//		listeners.get(0).stateChanged(updateEvent);
		//	}
		//});		
		
	}

	public static void createFormatMenu(JFrame frame)
	{

		/**
		Constructs the format interface
		@param
		*/
		
		// Main
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Title
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(new JLabel("Select format:"),BorderLayout.WEST);

		// Navigation
		JPanel navigationPanel = createNavigationPanel();

		// Format
		JPanel formatPanel = new JPanel();
		formatPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JRadioButton SEButton = new JRadioButton();
		JRadioButton DEButton = new JRadioButton();
		JRadioButton MMButton = new JRadioButton();
		JRadioButton SSButton = new JRadioButton();
		JRadioButton RRButton = new JRadioButton();

		// Button Grouping
		ButtonGroup formatGroup = new ButtonGroup();
		c.gridx = 0;
		for (int y = 0; y < 5; y++)
		{
			JRadioButton formatButton = new JRadioButton();
			if (y == 0)
				formatButton.setSelected(true);
			
			c.gridy = y;
			formatGroup.add(formatButton);
			formatPanel.add(formatButton,c);
			addTournamentListener(formatButton, y+3);
			c.gridy = y;
		}


		c.gridx = 1;
		c.gridy = 0;
		formatPanel.add(new JLabel("Single-elimination"),c);
		c.gridx = 1;
		c.gridy = 1;
		formatPanel.add(new JLabel("McMahon"),c);
		c.gridx = 1;
		c.gridy = 2;
		formatPanel.add(new JLabel("Double-Elimination"),c);
		c.gridx = 1;
		c.gridy = 3;
		formatPanel.add(new JLabel("Swiss"),c);
		c.gridx = 1;
		c.gridy = 4;
		formatPanel.add(new JLabel("Round-robin"),c);


		// Seeding
		JPanel seedPanel = new JPanel();
		seedPanel.setLayout(new BorderLayout());

		JPanel YNPanel = new JPanel();
		YNPanel.setLayout(new BoxLayout(YNPanel, BoxLayout.X_AXIS));
		
		JRadioButton YButton = new JRadioButton("Yes");
		JRadioButton NButton = new JRadioButton("No", true);

		YNPanel.add(YButton);
		YNPanel.add(NButton);

		ButtonGroup YNGroup = new ButtonGroup();
		YNGroup.add(YButton);
		YNGroup.add(NButton);

		seedPanel.add(new JLabel("Seeded?"), BorderLayout.NORTH);
		seedPanel.add(Box.createRigidArea(new Dimension(0,10)),BorderLayout.CENTER);
		seedPanel.add(YNPanel, BorderLayout.SOUTH);

		// Instantiation
		mainPanel.add(titlePanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainPanel.add(formatPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainPanel.add(seedPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainPanel.add(navigationPanel);
		
		frame.add(mainPanel);

	}

	public static void createOptionsMenu(JFrame frame) 
	{
		// Main
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel roundNumPanel = new JPanel();
		roundNumPanel.setLayout(new BoxLayout(roundNumPanel, BoxLayout.X_AXIS));
		
		roundNumPanel.add(new JLabel("Enter the number of rounds and select the tiebreaker method below: "));
		JTextArea numberField = new JTextArea("5");
		numberField.setSize(1,1);
		userInputFields.add(numberField);

		// Select tiebreaker method
		JPanel tiePanel = new JPanel();
		tiePanel.setLayout(new BoxLayout(tiePanel, BoxLayout.X_AXIS));
		
		JRadioButton H2HButton = new JRadioButton("Head-to-Head",true);
		JRadioButton RecButton = new JRadioButton("Opponent records");

		tiePanel.add(H2HButton);
		tiePanel.add(RecButton);

		ButtonGroup tieGroup = new ButtonGroup();
		tieGroup.add(H2HButton);
		tieGroup.add(RecButton);

		mainPanel.add(roundNumPanel);
		mainPanel.add(tiePanel);
		mainPanel.add(numberField);

		// Navigation Panel
		JPanel navigationPanel = createNavigationPanel();

		frame.add(mainPanel);
		frame.add(navigationPanel);
		
		
	}
	
	public static void createPlayerEntryMenu(JFrame frame, boolean seed) 
	{
		// Main
		JPanel mainPanel = new JPanel();
		//mainPanel.setLayout(new BorderLayout()); 
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Title
		//mainPanel.add(new JLabel("Enter the players below, each separated by a new line:"));


		// Entry
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
		JTextArea playerField = new JTextArea("Enter your list of players here.", 20,50);
		// 
		userInputFields.add(playerField);
		
		
		// Seeding 
		if (seed == true)
		{
			JTextArea seedField = new JTextArea("Enter seeds/ratings here.", 20, 1);
			userInputFields.add(seedField);
			textPanel.add(seedField);
			textPanel.add(Box.createRigidArea(new Dimension(20,10)));
		}
		textPanel.add(playerField);

		// Navigation
		JPanel navigationPanel = createNavigationPanel();
		mainPanel.add(textPanel);
		mainPanel.add(navigationPanel);

		//mainPanel.add(titlePanel);

		frame.add(mainPanel);

	}

	public static void createRoundMenu(JFrame frame, int numberOfRounds, int start, int end, ArrayList<Player> players, int roundNumber) {
		// Main
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel roundPanel = createRoundPanel(frame, numberOfRounds, roundNumber, players.size());

		// Pairings
		JPanel pairingPanel = new JPanel();
		pairingPanel.setLayout(new GridLayout(0,2));

		for (int i = 0; i < players.size(); i++)		
		{

			JButton playerButton = new JButton(players.get(i).name());
			addTournamentListener(playerButton, i+numberOfRounds+1);

			if (i < start && i > end)
			{
				
				pairingPanel.add(playerButton);
				//pairingGroup.add(playerButton);
				//if (i % 2 == 1)
				//{
				//	pairingGroup = new ButtonGroup();
				//}
				//System.out.println(players.get(i).name());
			}
		}
		
		
		mainPanel.add(roundPanel, BorderLayout.NORTH);
		mainPanel.add(pairingPanel, BorderLayout.CENTER);
		frame.add(mainPanel);


	}

	public static JPanel createRoundPanel(JFrame frame, int numberOfRounds, int roundNumber, int startIndex)
	{
		// debug
		startIndex = 0;

		// Round list
		ButtonGroup roundGroup = new ButtonGroup();
		JPanel roundPanel = new JPanel();
		roundPanel.setLayout(new BoxLayout(roundPanel, BoxLayout.X_AXIS));

		// Create each round button
		for (int i = 0; i < numberOfRounds; i++)
		{
			String roundName = String.valueOf(i+1);
			JToggleButton roundButton;

			// Selected button
			if (i == roundNumber-1)
			{
				boolean selectionState = true;
				roundButton = new JToggleButton("Round " + roundName, selectionState);
			}
			else
			{
				roundButton = new JToggleButton("Round " + roundName);
			}


			roundPanel.add(roundButton);
			roundGroup.add(roundButton);
			addTournamentListener(roundButton, startIndex+i);
		}

		// Results
		JToggleButton resultsButton = new JToggleButton("Results");
		roundGroup.add(resultsButton);

		addTournamentListener(resultsButton, startIndex+numberOfRounds);
		roundPanel.add(resultsButton);
		
		return roundPanel;

	}

	public static void createResults(JFrame frame, int numberOfRounds, int beginning, String results)
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel roundPanel = new JPanel();
		roundPanel = createRoundPanel(frame, numberOfRounds, numberOfRounds+1, 0);

		// Text
		JTextArea textResults = new JTextArea(results);
		textResults.setEditable(false);
		
		// Pairings
		//JPanel resultsPanel = new JPanel();
		//resultsPanel.setLayout(new GridLayout(0,2));

	
		mainPanel.add(roundPanel, BorderLayout.NORTH);
		mainPanel.add(textResults, BorderLayout.CENTER);
		frame.add(mainPanel);

	}
		

	private JFrame frame;
	public static ArrayList<ChangeListener> listeners;
	// change to private
	private static ArrayList<JTextArea> userInputFields;
	private static ArrayList<String> userInput;


	//private Player player;
	//private static ChangeListener listener;

}
	
