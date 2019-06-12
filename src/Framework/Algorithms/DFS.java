package Framework.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import Framework.*;

public class DFS implements Algorithm {
    int totalNodes = 0;
    HashSet<State> visited = new HashSet<>();

    public Solution search(Problem problem){
        Node root = new Node(problem);
        ArrayList<String> solution = recursive_DFS(problem,root,0);
        return new GlobalSearchSolution(solution);
    }

    @Override
    public int getTotalNodes() {
        return this.totalNodes;
    }

    public ArrayList<String> recursive_DFS(Problem problem, Node node, int depth) {
        ArrayList<String> result = null;
        visited.add(node.state);
        this.totalNodes++;
        if (problem.isGoalState(node.state)) {
            node.state.prettyPrint();
            return node.extractSolution();
        }
        //node.state.prettyPrint();
        for (String action : problem.getPossibleActions(node.state)) {
            Node child = new Node(problem, node, action);
            if (!visited.contains(child.state)) {
                result = this.recursive_DFS(problem, child, depth + 1);
                if (result != null){
                    return result;
                }
            }
        }
        return null;
    }
}
