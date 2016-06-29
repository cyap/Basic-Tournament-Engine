/**
Purpose: 
*/

public class Player //implements Pairable
{
	/**
	Constructs the user interface
	@param
	*/
	
	public Player(String aName)
	{
		playerName = aName;
		
		/**
		
		*/
	}
	
	public String name()
	{
		return playerName;
	}
	
	public void rename(String newName)
	{
		playerName = newName;
	}

	public void rescore(int value)
	{
		seed = value;
	}

	public int score()
	{
		return seed;
	}


		
	private String playerName;
	private int seed;
	
}
	