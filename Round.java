import java.util.ArrayList;

/**
Purpose:
*/


public class Round
{
	/**
	@param
	*/
	
	public Round()
	{
		ArrayList<Pairing> pairingList = new ArrayList<Pairing>();
		ArrayList<String> textPairingList = new ArrayList<String>();
		/**
		
		*/
	}
	
	public void addPairings(ArrayList<Pairing> list)
	{
		//for (Pairing p : list)
			//Pairing newPairing
		pairingList = list;
	}

	public void addPlayer(String player)
	{
		//for (Pairing p : list)
			//Pairing newPairing
		textPairingList.add(player);
	}
	
	public void addPlayers(ArrayList<String> list)
	{
		//for (Pairing p : list)
			//Pairing newPairing
		textPairingList = list;
	}

	private void convertPairingList()
	{
		textPairingList.clear();
		for (Pairing entry : pairingList)
		{
			textPairingList.add(entry.returnP1());
			textPairingList.add(entry.returnP2());
		}
	}
	
	public static ArrayList<String> textPairings()
	{
		return textPairingList;
	}
	//public String text()
	//{
		//return player1.name() + " VS. " + player2.name();
//	}

		
	public ArrayList<Pairing> pairingList;
	public static ArrayList<String> textPairingList;
	
}