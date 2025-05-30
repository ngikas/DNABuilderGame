package DNABuilderGame1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * DNASequence stores target DNA sequences and maps them to specific animals.
 * Also includes utility methods for checking player input and selecting random targets.
 */
public class DNASequence {

    private static final Map<String, String> sequenceToAnimal = new HashMap<>();
    private static final Map<String, String> animalToImage = new HashMap<>();

    static {
        // Sample DNA sequences (should be 6 bases each)
        sequenceToAnimal.put("ATCGTA", "Tiger");
        sequenceToAnimal.put("CGTACG", "Parrot");
        sequenceToAnimal.put("TACGAT", "Shark");
        sequenceToAnimal.put("GGCATA", "Octopus");
        sequenceToAnimal.put("TTAGGC", "Lizard");

        // Match animal name to image path
        animalToImage.put("Tiger", Assets.TIGER_IMAGE);
        animalToImage.put("Parrot", Assets.PARROT_IMAGE);
        animalToImage.put("Shark", Assets.SHARK_IMAGE);
        animalToImage.put("Octopus", Assets.OCTOPUS_IMAGE);
        animalToImage.put("Lizard", Assets.LIZARD_IMAGE);
    }

    /**
     * Returns the animal associated with a given DNA sequence.
     * @param sequence the player's DNA input
     * @return the animal name or null if no match
     */
    public static String matchSequence(String sequence) {
        return sequenceToAnimal.get(sequence);
    }

    /**
     * Returns a random DNA sequence from the set of known animals.
     * @return a random DNA sequence
     */
    public static String getRandomTarget() {
        Object[] keys = sequenceToAnimal.keySet().toArray();
        return (String) keys[new Random().nextInt(keys.length)];
    }

    /**
     * Returns the image path for the given animal.
     * @param animal the animal name
     * @return the file path for the image
     */
   
        /**
         * Gets the animal name for a given DNA sequence.
         *
         * @param sequence the DNA sequence
         * @return the animal name, or null if not found
         */
        public static String getAnimalForSequence(String sequence) {
            return sequenceToAnimal.get(sequence);
        }
    }


