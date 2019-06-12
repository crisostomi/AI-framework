package Framework;

import java.util.ArrayList;

public interface LocalSearchAlgorithm extends Algorithm {

    Solution search(Problem problem);

    int getTotalNodes();

}
