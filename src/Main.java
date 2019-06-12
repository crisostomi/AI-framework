import Framework.Algorithm;
import Framework.Instances.*;
import Framework.Algorithms.*;
import Framework.Solution;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        SlidingPuzzle problem = new SlidingPuzzle(3);
//        int[] jarCapacities = {177,123,104};
//        WaterJar problem = new WaterJar(jarCapacities);
        ArrayList<Algorithm> algorithms = getAlgorithms();
        System.out.println("Problem: "+problem.getClass().getSimpleName());
        System.out.println("Initial state:");
        problem.getInitialState().prettyPrint();
        for(Algorithm algorithm:algorithms){
            System.out.println("\nSolving with algorithm: "+algorithm.getClass().getSimpleName());
            double startTime = System.currentTimeMillis();
            Solution solution = problem.solve(algorithm);
            System.out.println("Elapsed time: "+(System.currentTimeMillis()-startTime)+" ms");
            solution.printSolution();
            System.out.println("Nodes explored: "+algorithm.getTotalNodes());
            System.out.println("");
        }
    }

    public static ArrayList<Algorithm> getAlgorithms(){
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        BestFirstGreedy bfg = new BestFirstGreedy();
        DFS dfs = new DFS();
        BFS bfs = new BFS();
        IDS ids = new IDS();
        MinCost minCost = new MinCost();
        HillClimbing hillClimbing = new HillClimbing();
        algorithms.add(bfg);
//        algorithms.add(dfs);
//        algorithms.add(ids);
//        algorithms.add(minCost);
//        algorithms.add(bfs);
        algorithms.add(hillClimbing);
        return algorithms;
    }
}

//    int[] jarCapacities = {12,8,3};
//
//    WaterJar WJ = new WaterJar(jarCapacities);
//          WJ.algorithm = new DFS();
//                  System.out.print(WJ.solve());
