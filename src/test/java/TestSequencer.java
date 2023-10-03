import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSequencer {
    @Test
    public void convertToIntSequence_firstStringHasCommaAndHyphen() {
        String[] indexes = new String[] {"1,3-5", "2", "3-4"};
        SequenceConverter sc = new SequenceConverter();
        Integer[] convertingSequence = sc.convertToIntSequence(indexes);
        Integer[] comparedSequence = new Integer[]{1, 2, 3, 4, 5};
        assertArrayEquals(convertingSequence, comparedSequence);
    }

    @Test
    public void convertToGroups_firstStringHasCommaAndHyphen() {
        String[] indexes = new String[] {"1,3-5", "2", "3-4"};
        SequenceConverter sc = new SequenceConverter();
        Integer[][] convertingGroups = sc.convertToGroups(indexes);
        Integer[][] comparedGroups = new Integer[][]{{1, 2, 3}, {1, 2, 4}, {3, 2, 3}, {3, 2, 4}, {4, 2, 3}, {4, 2, 4}, {5, 2, 3},{5, 2, 4}};
        assertArrayEquals(convertingGroups, comparedGroups);
    }

    @Test
    public void convertToIntSequence_throwExceptions() throws IllegalArgumentException {
        String[] indexes_1 = new String[]{" "};
        SequenceConverter sc = new SequenceConverter();
        Throwable thrown_1 = assertThrows(IllegalArgumentException.class, () -> {
            sc.convertToIntSequence(indexes_1);
        });
        String expectedMessage_1 = "Строки не могут быть пустыми или содержать только пробелы";
        assertEquals(thrown_1.getMessage(), expectedMessage_1);

        String[] indexes_2 = new String[]{"A"};
        Throwable thrown_2 = assertThrows(IllegalArgumentException.class, () -> {
            sc.convertToIntSequence(indexes_2);
        });
        String expectedMessage_2 = "Строка должна содержать числа";
        assertEquals(thrown_2.getMessage(), expectedMessage_2);

        String[] indexes_3 = null;
        Throwable thrown_3 = assertThrows(NullPointerException.class, () -> {
            sc.convertToIntSequence(indexes_3);
        });
        String expectedMessage_3 = "Массив строк не может быть пустым";
        assertEquals(thrown_3.getMessage(), expectedMessage_3);
    }
}
