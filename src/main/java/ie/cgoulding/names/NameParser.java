package ie.cgoulding.names;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.StringUtils;

import eu.crydee.syllablecounter.SyllableCounter;

public class NameParser {

    private final SyllableCounter counter = new SyllableCounter();

    private final Set<String> vowels = new HashSet<>(
        Arrays.asList("a", "e", "i", "o", "u", "y")
    );
    private final Set<String> broadVowels = new HashSet<>(
        Arrays.asList("a", "o", "u")
    );
    private final Set<String> narrowVowels = new HashSet<>(
        Arrays.asList("e", "i", "y")
    );

    public Name parse(String name) {
        return new Name(
            name,
            counter.count(name),
            this.broadVowels.stream()
                .mapToInt(vowel -> StringUtils.countOccurrencesOf(name.toLowerCase(), vowel)).sum(),
            this.narrowVowels.stream()
                .mapToInt(vowel -> StringUtils.countOccurrencesOf(name.toLowerCase(), vowel)).sum(),
            vowels.stream().noneMatch(vowel -> StringUtils.startsWithIgnoreCase(name, vowel))
        );
    }
}
