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
        List<List<Integer>> forCombinator = intSequences.stream()
                .map(Arrays::asList)
                .collect(Collectors.toList());
        List<List<Integer>> combinations =  getAllCombinations(forCombinator);
        List<Integer[]> arrayCombinations = combinations.stream()
                .map(s -> s.toArray(Integer[]::new))
                .collect(Collectors.toList());
        return arrayCombinations.toArray(Integer[][]::new);
    }
    public static List<List<Integer>> getAllCombinations(List<List<Integer>> arrays) {
        List<List<Integer>> combinations = new ArrayList<>();
        getCombinations(arrays, new ArrayList<>(), 0, combinations);
        return combinations;
    }

    private static void getCombinations(List<List<Integer>> arrays, List<Integer> currentCombination, int currentIndex, List<List<Integer>> combinations) {
        if (currentIndex == arrays.size()) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        List<Integer> currentArray = arrays.get(currentIndex);
        for (int i = 0; i < currentArray.size(); i++) {
            currentCombination.add(currentArray.get(i));
            getCombinations(arrays, currentCombination, currentIndex + 1, combinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
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
