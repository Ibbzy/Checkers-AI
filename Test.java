
public class Test {

	public static void main(String[] args) {
		int x = 3;
		x=(Integer) null;
		
				
	}

}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Player {
    public GameState nextState;
	private int nextMove;
    

	/**
     * Performs a move
     *
     * @param pState
     *            the current state of the board
     * @param pDue
     *            time before which we must have returned
     * @return the next state the board is in after our move
     */
    public GameState play(final GameState pState, final Deadline pDue) {
    	Random random = new Random();
        Vector<GameState> lNextStates = new Vector<GameState>();
        pState.findPossibleMoves(lNextStates);
        
        
        
        if (lNextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(pState, new Move());
        }
        nextMove = minmax(pState, Constants.CELL_WHITE,7,pState);
        return nextMove;
        
    }

        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        
        ArrayList<Integer> valVector = new ArrayList<Integer>();
		public int bestVal;
     	
        public int  minmax(GameState gs, int nextp , int depth, GameState rootState){	
        if(depth==0 || gs.isEOG()){return eval(gs);}
        int bestVal = 0;
        Vector<GameState> lNextStates = new Vector<GameState>();
        gs.findPossibleMoves(lNextStates);
        nextp = gs.getNextPlayer();
        
        if(nextp==Constants.CELL_WHITE){
        	
        	bestVal = Integer.MIN_VALUE;
        	for(int i = 0;i<lNextStates.size();i++){
        	//valVector.add(eval(lNextStates.get(i)));
        	//valVector.add(minmax(lNextStates.get(i),nextp,depth-1));}
        	// bestVal=Integer.max(Collections.max(valVector),bestVal);
        	
        	if(eval(lNextStates.get(i))>bestVal){bestVal=}
            return bestVal;	
            //nextState = lNextStates.elementAt(random.nextInt(lNextStates.size()));
            //nextState = lNextStates.elementAt(valVector.indexOf(Collections.min(valVector)));
            
        }	
        
        if(nextp==Constants.CELL_RED){
        	
        	bestVal = Integer.MIN_VALUE;
        	for(int i = 0;i<lNextStates.size();i++){
        	valVector.add(minmax(lNextStates.get(i),nextp,depth-1));}
        	bestVal=Integer.max(Collections.max(valVector),bestVal);
        	
            return bestVal; 	
            //nextState = lNextStates.elementAt(random.nextInt(lNextStates.size()));
            //nextState = lNextStates.elementAt(valVector.indexOf(Collections.min(valVector)));
            
        }
   /* Gametree tree = new Gametree(pState);
    	tree.expand();
    	GameState best = tree.findBest();
    	
    	 return best
     */   
        //System.out.println(eval(pState));
        
        
       
        return bestVal;
        //Random random = new Random();
        //return lNextStates.elementAt(random.nextInt(lNextStates.size()));
        //return nextState;
    }
    
    public int eval(GameState gs) {
      	 int val = 0;
      	 int multiplier;
      	 for (int i = 0; i < 32; i++){
      		 multiplier = 1;
      		 if(gs.get(i)==Constants.CELL_KING){multiplier=3;}
      		 if(gs.isRedWin()){multiplier=1000000000;}
      		 if(gs.isWhiteWin()){multiplier=-1000000000;}
      		 if(gs.get(i)==Constants.CELL_RED){val+=multiplier;}
      		 if(gs.get(i)==Constants.CELL_WHITE){val-=multiplier;}
      	 	}
      	 
      	 	return val;
       	}
}