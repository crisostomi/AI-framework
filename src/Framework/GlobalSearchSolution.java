package Framework;

import Framework.Solution;

import java.util.ArrayList;

public class GlobalSearchSolution implements Solution {
    ArrayList<String> solution;

    public GlobalSearchSolution(ArrayList<String> solution){
        this.solution = solution;
    }

    @Override
    public void printSolution() {
        System.out.println("Solution length: "+solution.size());
        System.out.println(solution.toString());
    }
}
