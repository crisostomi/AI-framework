package Framework;

public class LocalSearchSolution implements Solution {
    State solution;

    public LocalSearchSolution(State solution){
        this.solution = solution;
    }

    @Override
    public void printSolution() {
        solution.prettyPrint();
    }
}
