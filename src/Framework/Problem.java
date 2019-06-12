package Framework;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Problem {

    public abstract State getInitialState();

    public abstract ArrayList<String> getPossibleActions(State state);

    public abstract int stepCost(State fatherState, String action, State state);

    public abstract State getResult(State fatherState, String action);

    public abstract Solution solve(Algorithm algorithm);

    public abstract boolean isGoalState(State state);

    public Comparator<Node> getHeuristic(){
        return null;
    }

    public int getStateValue(State state){
        return 1;
    }

}



