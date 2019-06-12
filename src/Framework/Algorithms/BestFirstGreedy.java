package Framework.Algorithms;

import Framework.*;
import Framework.Instances.SlidingPuzzle;
import Framework.Instances.SlidingPuzzleState;

import java.util.*;

public class BestFirstGreedy implements Algorithm {
    int totalNodes;

    public Solution search(Problem problem) {
        Node root = new Node(problem);
        PriorityQueue<Node> frontier = new PriorityQueue<>(problem.getHeuristic());
        frontier.add(root);
        HashSet<State> visited = new HashSet<>();
        while(true){
            if (frontier.isEmpty()) {
                return null;
            }
            Node node = frontier.poll();
            if (problem.isGoalState(node.state)){
                System.out.println("Goal state:");
                node.state.prettyPrint();
                return new GlobalSearchSolution((node.extractSolution()));
            }
            visited.add(node.state);
            this.totalNodes++;
            for(String action:problem.getPossibleActions(node.state)){
                Node child = new Node(problem,node,action);
                if (!visited.contains(child.state) && !frontier.contains(child)){
                    frontier.add(child);
                }
//                for (Node nodeInFrontier:frontier){
//                    if (nodeInFrontier.state.equals(child.state) && nodeInFrontier.pathCost > child.pathCost){
//                        frontier.remove(nodeInFrontier);
//                        frontier.add(child);
//                    }
//                }
            }
        }
    }

    @Override
    public int getTotalNodes() {
        return this.totalNodes;
    }
}
