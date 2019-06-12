package Framework.Algorithms;
import Framework.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MinCost implements Algorithm {
    int totalNodes = 0;

    public Solution search(Problem problem) {
        Node root = new Node(problem);
        PriorityQueue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                if (node1.pathCost < node2.pathCost){
                    return -1;
                }
                else if (node1.pathCost > node2.pathCost){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        });
        frontier.add(root);
        HashSet<State> visited = new HashSet<>();
        while(true){
            if (frontier.isEmpty()) {
                return null;
            }
            Node node = frontier.poll();
            if (problem.isGoalState(node.state)){
                node.state.prettyPrint();
                return new GlobalSearchSolution(node.extractSolution());
            }
            visited.add(node.state);
            this.totalNodes++;
            for(String action:problem.getPossibleActions(node.state)){
                Node child = new Node(problem,node,action);
                if (!visited.contains(child.state) && !frontier.contains(child)){
                    frontier.add(child);
;                }
                for (Node nodeInFrontier:frontier){
                    if (nodeInFrontier.state.equals(child.state) && nodeInFrontier.pathCost > child.pathCost){
                        frontier.remove(nodeInFrontier);
                        frontier.add(child);
                    }
                }
            }
        }
    }

    @Override
    public int getTotalNodes() {
        return this.totalNodes;
    }
}
