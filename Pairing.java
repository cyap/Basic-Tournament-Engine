/**
Purpose:
*/


public class Pairing
{
	/**
	Constructs the user interface
	@param
	*/
	
	public Pairing(Player First, Player Second)
	{
		player1 = First;
		player2 = Second;
		
		/**
		
		*/
	}
	
	public String text()
	{
		return player1.name() + " VS. " + player2.name();
	}

	public String returnP1()
	{
		return player1.name();
	}
	
	public String returnP2()
	{
		return player2.name();
	}


		
	private Player player1;
	private Player player2;
	private String textPairing;

	
}