
import java.util.ArrayList;
public interface TournamentFormat
/**
	Interface to allow for the different ways in which a tournament can operate
*/

{	
	//public boolean setSeed();

	public boolean setElimination();

	public String[] generatePlayerList(String players);

	public int byePad(String[] playerlist, int playerCount);

	public int determineNumberOfRounds(int playerCount, int numberOfRounds);

	public ArrayList<Player> generatePairings(String[] playerlist, int playerCount, int numberOfRounds, int byes, String seeds);

	public void advancePlayer(int position, ArrayList<Player> playerTree);

	public String generateResults(ArrayList<Player> playerTree);
	
}