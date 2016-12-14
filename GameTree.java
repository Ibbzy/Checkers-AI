import java.util.Vector;

public class GameTree {
	
	Vector<Node> game; 
	Vector<GameState> temp;
	
	void Gametree(GameState hello) {
		
		Node nd = new Node(hello);
		game.add(nd);
	}	
	
	void push_vector(Vector<GameState> vnd){
	    for(int i= 0;i<vnd.size();i++){
	    game.add(Node(vnd.get(i)));
	    		}
	    } 
	    
		void expand(){
			game.get(0).currentState.findPossibleMoves(temp);
			push_vector(temp);
			
			
			
		}
		
		
	}
	
}
