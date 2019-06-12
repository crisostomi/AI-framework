package Framework.Lib;

import java.util.Random;

public class Functions {

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

}
