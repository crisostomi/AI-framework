package Framework.Instances;

import Framework.State;

import java.util.Arrays;

public class WaterJarState extends State {
    public int[] waterInJars;
    
    @Override
    public void prettyPrint() {
        System.out.println("Prima brocca "+waterInJars[0]+" seconda brocca: "+waterInJars[1]+" terza brocca: "+waterInJars[2]);
    }

    public WaterJarState(int[] waterInJars){
        this.waterInJars = waterInJars;
    }

    public WaterJarState(WaterJarState clone){
        this.waterInJars = Arrays.copyOf(clone.waterInJars,clone.waterInJars.length);
    }

    @Override
    public boolean equals(Object object) {
        return Arrays.equals(this.waterInJars,((WaterJarState) object).waterInJars);
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(waterInJars);
    }
}
