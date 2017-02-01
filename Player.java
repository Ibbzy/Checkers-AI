import java.util.*;

public class Player {
    public GameState nextState;
	private int nextMove;
    private int player;
    private int enemy;
    private int it;
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
        if(pState.getNextPlayer() == Constants.CELL_RED){
        	player=Constants.CELL_RED;
        	enemy=Constants.CELL_WHITE;	
        	}
        
        
        else {
        	player=Constants.CELL_WHITE;
        	enemy=Constants.CELL_RED;
        	}
        
        
        if (lNextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(pState, new Move());
        }
        else{
        return nextMove(pState,6);
        }
        
    }

        private GameState nextMove(GameState pState, int j) {
        	GameState nS;
        	it=0;
        	int a;
        	Vector<GameState> NextStates = new Vector<GameState>();
            pState.findPossibleMoves(NextStates);
            int b= minmax(NextStates.get(0),j,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
            nS = NextStates.get(0);
        	for(int i = 0;i<NextStates.size();i++){
        		a = minmax(NextStates.get(i),j,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
        		if(a>b){nS=NextStates.get(i);}
        	}
		return nS;
	}

	
        

		public int bestVal;
     	public int newEval;
        public int  minmax(GameState gs, int depth,boolean Maximizer,int alpha,int beta){
        it++;
        bestVal =0;
        if(depth==0 || it > 500000|| gs.isEOG()){return eval(gs);}
        

        Vector<GameState> lNextStates = new Vector<GameState>();
        gs.findPossibleMoves(lNextStates);
        
        
        if(Maximizer==true){
        	
        	bestVal = Integer.MIN_VALUE;
        	for(int i = 0;i<lNextStates.size();i++){
        		
        		newEval=minmax(lNextStates.get(i),depth-1,false,alpha,beta);
        		if(newEval>bestVal){bestVal=newEval;}
        		if(bestVal>alpha){alpha=bestVal;}
        		if(beta >= alpha){return alpha;}
        	}
            return bestVal;
        }
        if(Maximizer==false){
        	bestVal = Integer.MAX_VALUE;
            for(int i = 0;i<lNextStates.size();i++){

            	newEval=minmax(lNextStates.get(i),depth-1,true,alpha,beta);
            	if(newEval<bestVal){bestVal=newEval;}
            	if(bestVal<beta){beta=bestVal;}
            	if(beta <= alpha){return beta;}
            }
            return bestVal;            
        }

        
       
        return bestVal;
        //Random random = new Random();
        //return lNextStates.elementAt(random.nextInt(lNextStates.size()));
        //return nextState;
    }
    
    public int eval(GameState gs) {
      	 int val = 0;
      	 int multiplier;
      	 int[] placeVal = 
      		 		{7,7,7,7,
      			 	6,4,4,6,
      			 	4,2,2,4,
      			 	4,1,1,4,
      			 	4,1,1,4,
      			 	4,2,2,4,
      			 	6,4,4,6,
      			 	7,7,7,7};
      	 for (int i = 0; i < 32; i++){
      		 multiplier =1;
      		 if(gs.get(i)==Constants.CELL_KING){multiplier *=5;}
      		 if((4<i && i>28) && gs.get(i)==player && gs.get(i)==player){multiplier*=5;}
      		 if(4<i && i>28 && gs.get(i)==enemy && gs.get(i)==enemy){multiplier*=5;}
      		 if(gs.get(i)==player){val+=multiplier*placeVal[i];}
      		 if(gs.get(i)==enemy){val-=multiplier*placeVal[i];}
      	 	}
  		 if((gs.isRedWin() && player==Constants.CELL_RED ) || (gs.isWhiteWin() && player==Constants.CELL_WHITE) ){val=Integer.MAX_VALUE;}
  		 if((gs.isRedWin() && player==Constants.CELL_WHITE ) || (gs.isWhiteWin() && player==Constants.CELL_RED)){val=Integer.MIN_VALUE;}
      	 	return val;
       	}
}
