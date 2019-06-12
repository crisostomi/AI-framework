package Framework.Instances;
import Framework.*;
import Framework.Algorithms.BestFirstGreedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class WaterJar extends Problem {
    public int[] jarCapacities;
    WaterJarState initialState;

    public WaterJar(int[] jarCapacities){
        int[] waterInJars = {0,0,0};
        WaterJarState state = new WaterJarState(waterInJars);
        this.initialState = state;
        this.jarCapacities = jarCapacities;
    }

    @Override
    public State getInitialState() {
        return this.initialState;
    }

    @Override
    public ArrayList<String> getPossibleActions(State state) {
        ArrayList<String> actions = new ArrayList<>();
        WaterJarState castedState = ((WaterJarState) state);
        for (int i=0;i<3;i++){
            if (castedState.waterInJars[i] != 0){
                actions.add("S"+i);
            }
            if (castedState.waterInJars[i] != this.jarCapacities[i]){
                actions.add("R"+i);
            }
            for (int j=0;j<3;j++){
                if (i != j && castedState.waterInJars[i] != 0 && castedState.waterInJars[j] != this.jarCapacities[j]){
                    actions.add("T"+i+""+j);
                }
            }
        }
        return actions;
    }

    @Override
    public int stepCost(State fatherState, String action, State state) {
        return 1;
    }

    @Override
    public State getResult(State fatherState, String action) {
        WaterJarState castedFatherState = (WaterJarState) fatherState;
        WaterJarState state = new WaterJarState(castedFatherState);
        char move = action.charAt(0);
        int i = action.charAt(1) - '0';
        if (move == 'S'){
            state.waterInJars[i] = 0;
        }
        if (move == 'R'){
            state.waterInJars[i] = this.jarCapacities[i];
        }
        if (move == 'T'){
            int j = action.charAt(2) - '0';
            if (state.waterInJars[i] + state.waterInJars[j] > this.jarCapacities[j]){
                state.waterInJars[i] = state.waterInJars[i] - (this.jarCapacities[j] - state.waterInJars[j]);
                state.waterInJars[j] = this.jarCapacities[j];
            }
            else {
                state.waterInJars[j] = state.waterInJars[j] + state.waterInJars[i];
                state.waterInJars[i] = 0;
            }
        }
        return state;
    }

    @Override
    public Solution solve(Algorithm algorithm) {
        return algorithm.search(this);
    }

    @Override
    public boolean isGoalState(State state) {
        WaterJarState castedState = (WaterJarState) state;
        int sum = IntStream.of(castedState.waterInJars).sum();
        return sum == 1;
    }

    @Override
    public Comparator<Node> getHeuristic(){
        Comparator<Node> comparator = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                long emptyJars1 = Arrays.stream(((WaterJarState) node1.state).waterInJars).filter(s -> s>0).count();
                long emptyJars2 = Arrays.stream(((WaterJarState) node2.state).waterInJars).filter(s -> s>0).count();
                if (emptyJars1 > emptyJars2){
                    return 1;
                }
                else if (emptyJars1 < emptyJars2){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        };
        return comparator;
    }

//
//    @Override
//    public Comparator<Node> getHeuristic(){
//        return new Comparator<Node>() {
//            @Override
//            public int compare(Node node, Node t1) {
//                return 0;
//            }
//        };
//    }
}
