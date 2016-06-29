import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class SingleElim implements TournamentFormat
{

	//public boolean setSeed()
	//{

	//}

	public boolean setElimination()
	{
		return true;
	}
	

	public String[] generatePlayerList(String players)
	{
		String[] playerlist;
		playerlist = players.split("\\n");
		int playerCount = playerlist.length;

		return playerlist;

	}

	public int byePad(String[] playerlist, int playerCount)
	{
		// Fill in byes for player counts that are not a power of 2		
		float f = (float) (Math.log(playerCount) / Math.log((int) 2));
		int g = (int) f;
		int byes = 0;

		for (int b = 1; f != g; b++)
		{
			byes++;
			f = (float) (Math.log(playerCount+b) / Math.log((int) 2));
			g = (int) f;

				
		}
		return byes;
	}

	public int determineNumberOfRounds(int playerCount, int numberOfRounds)
	{
		int numRDs = (int) (Math.log(playerCount) / Math.log((int) 2));
		return numRDs;
	}
	
	public ArrayList<Player> generatePairings(String[] playerlist, int playerCount, int numberOfRounds, int byes, String seeds)
	{

		// Randomize playerlist
		ArrayList<String> playerArrayList = new ArrayList<String>(Arrays.asList(playerlist));
		Collections.shuffle(playerArrayList);

		// Declare player tree
		ArrayList<Player> playerTree = new ArrayList<Player>();

		//playerCount = byes + playerCount;
		//int numberOfRounds = (int) (Math.log(playerCount) / Math.log((int) 2));

		// Allocate PlayerTree
		for (int i = 0; i < (2*playerCount-1); i++)
		{
			Player participant = new Player("Bye");
			playerTree.add(participant);
		}

		int offset = Integer.valueOf(byes);

		// Add players to tree
		for (int i = 0; i < playerArrayList.size(); i++)
		{
			int index = 2*playerCount-2-i-byes+offset;

			// Alternate byes to ensure no pairing is made up of two byes
			if (offset != 0)
			{
				offset--;
			}

			for (float j = index; j > 0; j = (int) Math.ceil(j/2 - 1))
			{					
				Player dummy = playerTree.get((int)j);
				dummy.rename(playerArrayList.get(i));
			}
		}

		// Generate list of rounds
		//for (int i = 0; i < numberOfRounds; i++)
		//{
		//	Round aRound = new Round();
		//	roundList.add(aRound);
		//}

		return playerTree;
			
	}


	public void advancePlayer(int position, ArrayList<Player> playerTree)
	{
		for (float z = position; z > -1; z = (int) Math.ceil(z/2) - 1)
		{
			playerTree.get((int)z).rename(playerTree.get(position).name());
		}
					//System.out.println(winner.name());
	}

	public String generateResults(ArrayList<Player> playerTree)
	{
		// Determine results
		ArrayList<String> results = new ArrayList<String>();
		for (Player player : playerTree)
		{
			if (player.name() != "Bye")
			{
				if (!results.contains(player.name()))
				{
					results.add(player.name());
				}

			}
		}

		//Convert to text
		String resultsText = "";
		resultsText += "1: ";
		resultsText += results.get(0);
		resultsText += "\n";

		// Determine placements
		int placement = 2;
		int tiesNum = 1;
		int tn = 0;
		for (int cell = 1; cell < results.size(); cell++)
		{
			resultsText += String.valueOf(placement);
			resultsText += ": ";
			resultsText += results.get(cell);
			resultsText += "\n";
			
			if (tn == 0)
			{
				placement += tiesNum;
				tiesNum *= 2;
				tn = Integer.valueOf(tiesNum);
			}
			tn--;
			

		}
		return resultsText;

	}
	



}