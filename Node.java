


import java.util.Vector;


	
	public class Node {
		
		GameState parentState;
		GameState currentState;
		Vector<GameState> temp;
		
		
		Node(GameState gsr){
			parentState = null;
			currentState =gsr;
		}
		
		Node(GameState gsk, GameState gsp){
			parentState = gsp;
			currentState= gsk;
		}
		

	}

