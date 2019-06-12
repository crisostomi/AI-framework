package Framework.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import Framework.*;


public class BFS implements Algorithm {
    int totalNodes;

    @Override
    public int getTotalNodes() {
        return this.totalNodes;
    }

    public Solution search(Problem problem){
        Node root = new Node(problem);
        if (problem.isGoalState(root.state)){
            return new GlobalSearchSolution((root.extractSolution()));
        }
        LinkedList<Node> frontier = new LinkedList<>();
        HashSet<State> visited = new HashSet<>();
        frontier.add(root);
        visited.add(root.state);
        while(true){
            if (frontier.isEmpty()){
                return null;
            }
            Node node = frontier.removeFirst();
            visited.add(node.state);
            this.totalNodes++;
            for(String action:problem.getPossibleActions(node.state)){
                Node son = new Node(problem,node,action);
                if (!visited.contains(son.state) && !frontier.contains(son)){
                    if (problem.isGoalState(son.state)){
                        son.state.prettyPrint();
                        return new GlobalSearchSolution(son.extractSolution());
                    }
                    frontier.addLast(son);
                }

            }
        }
    }

}
