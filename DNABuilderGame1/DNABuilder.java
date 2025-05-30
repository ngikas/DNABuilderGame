package DNABuilderGame1;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles DNA comparison logic between player input and correct sequences.
 */
public class DNABuilder {

    /**
     * Maps DNA sequences to animal names.
     */
    private static final Map<String, String> sequenceToAnimal = new HashMap<>();

    static {
        sequenceToAnimal.put(Assets.PARROT_SEQUENCE, "Parrot");
        sequenceToAnimal.put(Assets.TIGER_SEQUENCE, "Tiger");
        sequenceToAnimal.put(Assets.SHARK_SEQUENCE, "Shark");
        sequenceToAnimal.put(Assets.OCTOPUS_SEQUENCE, "Octopus");
        sequenceToAnimal.put(Assets.LIZARD_SEQUENCE, "Lizard");
    }

    /**
     * Compares a DNA string input by the player to the known sequences.
     *
     * @param playerSequence The DNA string typed by the player
     * @return The animal name if matched, otherwise null
     */
    public static String matchSequence(String playerSequence) {
        for (Map.Entry<String, String> entry : sequenceToAnimal.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(playerSequence)) {
                return entry.getValue();
            }
        }
        return null; // no match
    }
}
