package Framework.Instances;

import Framework.Lib.Position;
import Framework.State;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;

public class SlidingPuzzleState extends State {
    public int[][] grid;
    public Position position;


    public SlidingPuzzleState(SlidingPuzzleState clone){
        this.grid = Arrays.stream(clone.grid).map(r -> r.clone()).toArray(int[][]::new);
        this.position = new Position(clone.position.i,clone.position.j);
    }

    public SlidingPuzzleState(int[][] grid, Position pos){
        this.grid = grid;
        this.position = pos;
    }


    public void prettyPrint() {
        System.out.println(Arrays.deepToString(this.grid).replace("], ","]\n"));
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SlidingPuzzleState)) {
            return false;
        }

        SlidingPuzzleState otherState = (SlidingPuzzleState) object;
        int n = otherState.grid.length;
        for(int i = 0; i < n; i++){
            for(int j = 0;j < n;j++){
                if (this.grid[i][j] != otherState.grid[i][j]){
                    return false;
                }
            }
        }
        return true;
    }



    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.grid);
    }


}
