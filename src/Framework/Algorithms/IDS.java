package Framework.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import Framework.*;

public class IDS implements Algorithm {
    HashSet<State> visited;
    int totalNodes = 0;

    public Solution search(Problem problem){
        int limit = 0;
        while(true){
            visited = new HashSet<>();
            ArrayList<String> solution = limitedDFS(problem, limit);
            limit++;
            if (solution.get(0) != "cutoff"){
                return new GlobalSearchSolution(solution);
            }
        }
    }

    @Override
    public int getTotalNodes() {
        return this.totalNodes;
    }

    public ArrayList<String> limitedDFS(Problem problem, int limit){
        Node root = new Node(problem);
        return recursiveLimitedDFS(problem,root,limit);
    }

    public ArrayList<String> recursiveLimitedDFS(Problem problem, Node node, int limit) {
        ArrayList<String> cutoff = new ArrayList<>();
        cutoff.add("cutoff");
        ArrayList<String> result = null;
        visited.add(node.state);
        this.totalNodes++;
        boolean cutoffOccurred = false;
        if (problem.isGoalState(node.state)) {
            node.state.prettyPrint();
            return node.extractSolution();
        }
        else if (limit == 0){
            return cutoff;
        }
        else{
            for (String action : problem.getPossibleActions(node.state)) {
                Node child = new Node(problem, node, action);
                if (!visited.contains(child.state)) {
                    result = this.recursiveLimitedDFS(problem, child, limit - 1);
                    if ( result != null && result.get(0).equals(cutoff.get(0)) ) {
                        cutoffOccurred = true;
                    }
                    else if ( result != null ){
                        return result;
                    }
                }
            }
        }
        if (cutoffOccurred){
            return cutoff;
        }
        else{
            return null;
        }
    }
}
