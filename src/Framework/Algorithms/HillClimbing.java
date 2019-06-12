package Framework.Algorithms;

import Framework.*;

import java.util.Random;

public class HillClimbing implements LocalSearchAlgorithm {
    public int totalNodes;
    public int lateralMoves;

    @Override
    public Solution search(Problem problem){
        Node node = new Node(problem);
        int currentMaxValue = 10;
        while(true) {
            Node highestValuedAdj = node;
            for (String action : problem.getPossibleActions(node.state)) {
                Node adj = new Node(problem, node, action);
                int adjValue = problem.getStateValue(adj.state);
                if (adjValue < currentMaxValue) {
                    currentMaxValue = adjValue;
                    highestValuedAdj = adj;
                }
                else if (adjValue == currentMaxValue){
                    int coinToss = new Random().nextInt(2);
                    if (coinToss==1){
                        highestValuedAdj = adj;
                    }
                }
            }
            int nodeValue = problem.getStateValue(node.state);
            if (currentMaxValue >= nodeValue){
                if (lateralMoves < 3000000){
                    lateralMoves++;
                }
                else{
                    System.out.println(currentMaxValue);
                    return new LocalSearchSolution(node.state);
                }
            }
                node = highestValuedAdj;
                this.totalNodes++;

        }
    }

    @Override
    public int getTotalNodes() {
        return this.totalNodes;
    }
}
