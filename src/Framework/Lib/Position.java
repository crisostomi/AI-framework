package Framework.Lib;

public class Position {
    public int i;
    public int j;

    public Position(int i,int j){
        this.i = i;
        this.j = j;
    }

    public Position(Position position){
        this.i = position.i;
        this.j = position.j;
    }
}
