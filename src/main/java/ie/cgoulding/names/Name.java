package ie.cgoulding.names;

import lombok.Data;

@Data
public class Name {
    private final String name;
    private final Integer syllables;
    private final Integer broadVowels;
    private final Integer narrowVowels;
    private final Boolean startsWithConsonant;
}
