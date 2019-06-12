package Framework.Instances;

import Framework.*;
import Framework.Lib.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SlidingPuzzle extends Problem {
    int n;
    SlidingPuzzleState initialState;

    Comparator<Node> heuristic = new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            SlidingPuzzleState state1 = (SlidingPuzzleState)node1.state;
            SlidingPuzzleState state2 = (SlidingPuzzleState)node2.state;
            return manhattanDistance(state1) - manhattanDistance(state2);
        }
    };

    @Override
    public Comparator<Node> getHeuristic(){
        Comparator<Node> heuristic = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                SlidingPuzzleState state1 = (SlidingPuzzleState)node1.state;
                SlidingPuzzleState state2 = (SlidingPuzzleState)node2.state;
                int manDist1 = manhattanDistance(state1);
                int manDist2 = manhattanDistance(state2);

                if (manDist1 < manDist2) {
                    return -1;
                }
                if (manDist1 > manDist2) {
                    return 1;
                }
                return 0;
            }
        };
        return heuristic;
    }

    public SlidingPuzzle(int n){
        this.n = n;
        this.initialState = (SlidingPuzzleState) createInitialState();
    }

    public State createInitialState() {
        assert (n % 2 == 1);
        while (true) {
            Position pos = new Position(0,0);
            ArrayList<Integer> possibleValues = new ArrayList<>();
            int[][] grid = new int[n][n];
            for (int i=0;i<n*n;i++){
                possibleValues.add(i);
            }
            Collections.shuffle(possibleValues);
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    grid[i][j] = possibleValues.remove(0);
                    if (grid[i][j] == 0) {
                        pos = new Position(i,j);
                    }
                }
            }
            State state = new SlidingPuzzleState(grid, pos);
            if (checkSolvable((SlidingPuzzleState)state)) {
                System.out.println("Initial state:");
                state.prettyPrint();
                System.out.println("\n");
                return state;
            }
        }
    }

    @Override
    public State getInitialState(){
        return this.initialState;
    }

    @Override
    public ArrayList<String> getPossibleActions(State state) {
        ArrayList<String> actions = new ArrayList<>();
        Position pos = ((SlidingPuzzleState) state).position;
        if (pos.i > 0){
            actions.add("N");
        }
        if (pos.i < n-1){
            actions.add("S");
        }
        if (pos.j > 0){
            actions.add("W");
        }
        if (pos.j < n-1){
            actions.add("E");
        }
        return actions;
    }

    @Override
    public int stepCost(State fatherState, String action, State state) {
        return 1;
    }

    @Override
    public State getResult(State fatherState, String action) {
        SlidingPuzzleState castedFatherState = (SlidingPuzzleState) fatherState;
        SlidingPuzzleState childState = new SlidingPuzzleState(castedFatherState);
        int old_i = castedFatherState.position.i;
        int old_j = castedFatherState.position.j;
        int new_i = old_i;
        int new_j = old_j;

        switch (action){
            case "N":
                new_i = old_i-1;
                break;
            case "S":
                new_i = old_i+1;
                break;
            case "W":
                new_j = old_j-1;
                break;
            case "E":
                new_j = old_j+1;
                break;
            default:
                break;
        }

        childState.position.i = new_i;
        childState.position.j = new_j;
        childState.grid[old_i][old_j] = childState.grid[new_i][new_j];
        childState.grid[new_i][new_j] = 0;

        return childState;
    }

    @Override
    public Solution solve(Algorithm algorithm) {
        return algorithm.search(this);
    }

    @Override
    public boolean isGoalState(State state) {
        SlidingPuzzleState castedState = (SlidingPuzzleState) state;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if (castedState.grid[i][j] != i*n+j){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkSolvable(SlidingPuzzleState state) {
        int[][] matrix = state.grid;
        int invCount = 0;
        int n = 3;

        int[] arr = new int[n*n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i*n + j] = matrix[i][j];
            }
        }

        for (int i = 0; i < n*n - 1; i++) {
            for (int j = i+1; j < n*n; j++) {
                if (arr[i] > arr[j] && arr[i] != 0 && arr[j] != 0) {
                    invCount++;
                }
            }
        }

        if(invCount%2 == 1){
            return false;
        }else{
            return true;
        }
    }


    public int manhattanDistance(SlidingPuzzleState state){
        int n = state.grid.length;
        int h = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int v = state.grid[i][j];
                int new_i = v/n;
                int new_j = v%n;
                h += (Math.abs(j-new_j)+ Math.abs(i - new_i));
            }
        }
        return h;
    }

    public int numberOfWrongTiles(SlidingPuzzleState state){
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if (state.grid[i][j] != i*n+j){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int getStateValue(State state){
        return numberOfWrongTiles((SlidingPuzzleState) state);
    }
}
