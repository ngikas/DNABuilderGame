package DNA;

import java.util.*;

public class DNALogic {
    private static final Map<String, String> animalSequences = new HashMap<>();
    private Map<String, Integer> builtCounts = new HashMap<>();
    private String currentAnimal;
    private String currentSequence;

    static {
        animalSequences.put("Tiger", "ATCGTA");
        animalSequences.put("Shark", "CGTACG");
        animalSequences.put("Lizard", "TACGAT");
        animalSequences.put("Parrot", "GATTAC");
        animalSequences.put("Octopus", "CAGTAC");
    }

    public DNALogic() {
        for (String animal : animalSequences.keySet()) {
            builtCounts.put(animal, 0);
        }
        generateNewSequence();
    }

    public void generateNewSequence() {
        List<String> animals = new ArrayList<>(animalSequences.keySet());
        Collections.shuffle(animals);
        currentAnimal = animals.get(0);
        currentSequence = animalSequences.get(currentAnimal);
    }

    public String getCurrentAnimal() {
        return currentAnimal;
    }

    public String getCurrentSequence() {
        return currentSequence;
    }

    public boolean checkSequence(String input) {
        boolean correct = input.equalsIgnoreCase(currentSequence);
        if (correct) {
            builtCounts.put(currentAnimal, builtCounts.get(currentAnimal) + 1);
        }
        return correct;
    }

    public Map<String, Integer> getBuiltCounts() {
        return builtCounts;
    }
}
