import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequenceConverter {
    public Integer[] convertToSequence(String[] indexes) {
        HashSet<Integer> sequence = new HashSet<>();
        List<Integer[]> intSequences = mapStringsToSequences(indexes);
        for (Integer[] intArray : intSequences) {
            sequence.addAll(List.of(intArray));
        }
        return sequence.toArray(Integer[]::new);
    }

    public Integer[][] convertToGroups(String[] indexes) {
        List<Integer[]> intSequences = mapStringsToSequences(indexes);
        Integer[][] integers = intSequences.toArray(Integer[][]::new);
        return getAllCombinations(integers);
    }

    public Integer[][] getAllCombinations(Integer[][] sequences) {
        int countOfCombinations = 1;
        for (int i = 0; i < sequences.length; i++) { //рассчитываем количество комбинаций
            countOfCombinations *= sequences[i].length;
        }
        Integer[][] groups = new Integer[countOfCombinations][1]; //массив с результатами комбинаций
        int count = 0; //счетчик для массива

        int[] pointers = new int[sequences.length]; //создаем "указатели" для выборки значений
        while (pointers[0] < sequences[0].length) { //цикл работает пока первый указатель не станет равен количеству значений в первомй последовательности
            Integer[] combination = new Integer[sequences.length]; //новая комбинация
            for (int i = 0; i < sequences.length; i++) {
                combination[i] = sequences[i][pointers[i]]; //берем с каждого массива по значению согласно указателям
            }
            groups[count] = combination; //добавляем готовую комбинацию в массив
            count++;

            //здесь и далее увеличиваем указатели с конца пока не будет максимум
            //затем увеличиваем указатель следуюий по порядку
            pointers[sequences.length - 1]++;
            for (int i = sequences.length - 1; i > 0; i--) {
                if (pointers[i] == sequences[i].length) {
                    pointers[i] = 0;
                    pointers[i-1]++;
                }
            }
        }
        return groups;
    }
    private List<Integer[]> mapStringsToSequences(String[] indexes) {
        List<Integer[]> sequences = new ArrayList<>();
        for (String index : indexes) {
            String[] strSequence = index.split(",");
            List<Integer> intSequence = new ArrayList<>();
            for (String str : strSequence) {
                if(str.contains("-")) {
                    String[] split = str.split("-");
                    int start = Integer.parseInt(split[0].trim());
                    int finish = Integer.parseInt(split[1].trim());
                    List<Integer> integers = IntStream.rangeClosed(start, finish).boxed().collect(Collectors.toList());
                    intSequence.addAll(integers);
                } else {
                    int i = Integer.parseInt(str);
                    intSequence.add(i);
                }
            }
            sequences.add(intSequence.toArray(Integer[]::new));
        }
        return sequences;
    }
}
