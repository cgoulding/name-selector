package ie.cgoulding.names;

import java.util.function.Predicate;

import lombok.Data;

@Data
public class NameFilter implements Predicate<Name> {

    private final Integer maxSyllables;
    private final Boolean startsWithConsonant;
    private final Boolean broad;

    @Override
    public boolean test(Name name) {
        return name.getSyllables() <= maxSyllables
            && (startsWithConsonant.equals(name.getStartsWithConsonant()))
            && (broad.equals(name.getBroadVowels() >= name.getNarrowVowels()));
    }
}
