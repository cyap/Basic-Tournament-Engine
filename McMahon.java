import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class McMahon implements TournamentFormat
{

	public boolean setElimination()
	{
		return false;
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
		// Fill in byes for player counts that are not even
		if (playerCount % 2 == 0)
			return 0;
		return 1;
	}

	public int determineNumberOfRounds(int playerCount, int numberOfRounds)
	{
		return numberOfRounds;
	}
	

	public ArrayList<Player> generatePairings(String[] playerlist, int playerCount, int numberOfRounds, int byes, String seeds)
	{
		ArrayList<Player> playerTree = new ArrayList<Player>();
	/*
		String[] seedList;
		seedList = seeds.split("\\n");

		Player[] newPlayerList;
		for (int i = 0; i < newPlayerList.length; i++)
		{
			newPlayerList.add(new Player(playerList[i], seedList[i]));
		}
		Arrays.sort(newPlayerList,new Comparator<Player>() 
		{
			@Override
			public int compare(Player P1, Player P2) 
			{
				return ((Integer) P2.score()).compareTo(P1.score());
			}
		});

		// Declare player tree
		ArrayList<Player> playerTree = new ArrayList<Player>();

		// Allocate PlayerTree
		for (int i = 0; i < playerCount * numberOfRounds; i++)
		{
			Player participant = new Player("Bye", 0);
			playerTree.add(participant);
		}


		// Add players to tree
		for (int i = 0; i < newPlayerList.length; i++)
		{
			for (float j = i; j > 0; j = (int) Math.ceil(j/2 - 1))
			{					
				Player dummy = playerTree.get((int)j);
				dummy.rename(newPlayerList[i].name());
				dummy.rescore(newPlayerList[i].score());
			}
		}
		*/
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