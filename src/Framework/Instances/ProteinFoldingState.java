package Framework.Instances;

import Framework.Lib.Position;
import Framework.State;
import java.util.Arrays;

public class ProteinFoldingState extends State {
    public Position position;
    public char[][] grid;
    int lastAmmino = 0;

    public ProteinFoldingState(ProteinFoldingState clone){
        this.position = new Position(clone.position.i,clone.position.j);
        this.grid = Arrays.stream(clone.grid).map(r -> r.clone()).toArray(char[][]::new);
        this.lastAmmino = clone.lastAmmino;
    }

    public ProteinFoldingState(int n){
        int gridSize = (n-1)*2 + 1;
        grid = new char[gridSize][gridSize];
        for(int i=0;i<gridSize;i++){
            for(int j=0;j<gridSize;j++){
                this.grid[i][j] = 0;
            }
        }
    }

    @Override
    public void prettyPrint() {
        System.out.println(Arrays.deepToString(this.grid).replace("], ","\n"));
    }

    public boolean equals(Object object){
        return this == object;
    }

}
