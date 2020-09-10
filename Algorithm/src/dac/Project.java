package dac;

import java.util.List;

public class Project {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        target(A.size(), A, B, C);
    }

    private void target(int num, List<Integer> A, List<Integer> B, List<Integer> C){
        if (num == 1){
            C.add(A.remove(A.size() - 1));
        } else {
            //A -> B
            target(num - 1, A, C, B);
            //A -> C
            C.add(A.remove(0));
            //B -> C
            target(num - 1,B, A, C);
        }
    }
}
