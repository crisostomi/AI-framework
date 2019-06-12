package Framework.Instances;

import Framework.Algorithm;
import Framework.Lib.Position;
import Framework.Problem;
import Framework.Solution;
import Framework.State;

import java.util.ArrayList;

public class ProteinFolding extends Problem {
    int n;
    String sequence;
    State initialState;

    public ProteinFolding(String sequence){
        this.n = sequence.length();
        this.sequence = sequence;
        ProteinFoldingState initialState = new ProteinFoldingState(n);
        initialState.grid[n-1][n-1] = sequence.charAt(0);
        initialState.position = new Position(n-1,n-1);
        initialState.lastAmmino = 0;
        this.initialState = initialState;
    }


    @Override
    public ArrayList<String> getPossibleActions(State state) {
        ArrayList actions = new ArrayList();
        ProteinFoldingState castedState = (ProteinFoldingState) state;
        int i =  castedState.position.i;
        int j =  castedState.position.j;
        if (castedState.lastAmmino == 0){
            actions.add("E");
            return actions;
        }
        if (castedState.grid[i][j+1] == 0){
            actions.add("E");
        }
        if (castedState.grid[i-1][j] == 0) {
            actions.add("N");
        }
        if (castedState.grid[i+1][j] == 0) {
            actions.add("S");
        }
        if (castedState.grid[i][j-1] == 0) {
            actions.add("W");
        }
        return actions;
    }

    @Override
    public int stepCost(State fatherState, String action, State state) {
        return 0;
    }

    @Override
    public State getResult(State fatherState, String action) {
        ProteinFoldingState newState  = new ProteinFoldingState((ProteinFoldingState)fatherState);
        int i = ((ProteinFoldingState) fatherState).position.i;
        int j = ((ProteinFoldingState) fatherState).position.j;
        int nextAmmino = ((ProteinFoldingState) fatherState).lastAmmino+1;
        if(action == "E"){
            newState.grid[i][j+1] = sequence.charAt(nextAmmino);
            newState.position.j = j+1;
        }
        if(action == "W"){
            newState.grid[i][j-1] = sequence.charAt(nextAmmino);
            newState.position.j = j-1;
        }
        if(action == "N"){
            newState.grid[i-1][j] = sequence.charAt(nextAmmino);
            newState.position.i = i-1;
        }
        if(action == "S"){
            newState.grid[i+1][j] = sequence.charAt(nextAmmino);
            newState.position.i = i+1;
        }
        newState.lastAmmino = nextAmmino;
        return newState;
    }

    public Solution solve(Algorithm algorithm){
        return(algorithm.search(this));
    }

    public State getInitialState(){
        return this.initialState;
    }

    @Override
    public boolean isGoalState(State state){
        return false;
    }
}
