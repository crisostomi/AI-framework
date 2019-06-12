package Framework;

import java.util.ArrayList;
import java.util.Collections;

public class Node {
    public State state;
    public Node father = null;
    public String action = null;
    public int pathCost = 0;


    public Node(Problem problem){
        this.state = problem.getInitialState();
    }


    public Node(Problem problem, Node father, String action){
        this.state = problem.getResult(father.state,action);
        this.father = father;
        this.action = action;
        this.pathCost = father.pathCost + problem.stepCost(father.state,action,state);
    }

    public ArrayList<String> extractSolution(){
        ArrayList<String> solution = new ArrayList<>();
        solution.add(this.action);
        while(father.father != null){
            solution.add(father.action);
            father = father.father;
        }
        Collections.reverse(solution);
        return solution;
    }


}
