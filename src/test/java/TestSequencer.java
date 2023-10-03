import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestSequencer {
    @Test
    public void firstTest() {
        String[] indexes = new String[] {"1,3-5", "2", "3-4"};
        SequenceConverter sc = new SequenceConverter();
        Integer[] seq = sc.convertToSequence(indexes);
        System.out.println(Arrays.toString(seq));
    }

    @Test
    public void groupTest() {
        String[] indexes = new String[] {"1,3-5", "2", "3-4"};
        SequenceConverter sc = new SequenceConverter();
        Integer[][] groups = sc.convertToGroups(indexes);
        System.out.println(Arrays.deepToString(groups));
    }
}
