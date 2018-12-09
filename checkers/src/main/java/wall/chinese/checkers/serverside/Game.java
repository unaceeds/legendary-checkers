package wall.chinese.checkers.serverside;

public class Game 
{
	private InsideBoard insideBoard;
	private int maxCountOfPlayers;
	private int countOfConnectedPlayers = 0;
	private Player[] players;
	
	
	public Game(int maxCountOfPlayers)
	{
		this.maxCountOfPlayers = maxCountOfPlayers;
		players = new Player[maxCountOfPlayers];
		
	}
	
	public void addPlayer(Player player)
	{
		if(countOfConnectedPlayers < maxCountOfPlayers)
		{
			players[countOfConnectedPlayers] = player;
			countOfConnectedPlayers++;
		}
	}
}
