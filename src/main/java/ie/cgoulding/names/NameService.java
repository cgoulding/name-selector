package ie.cgoulding.names;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NameService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final NameParser nameParser = new NameParser();

    public List<String> loadNames(String gender, NameFilter filter) {
        return Stream.of(2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009,
                        2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018)
            .map(year -> fileName(year, gender))
            .map(this::content)
            .flatMap(names -> names.getNames().stream())
            .collect(Collectors.toSet())
            .stream()
            .map(this::parse)
            .filter(filter)
            .sorted(new NameComparator())
            .map(Name::getName)
            .collect(Collectors.toList());
    }

    private Name parse(String name) {
        return nameParser.parse(name);
    }

    private String fileName(Integer year, String gender) {
        return String.format("popular-baby-names/%s/%s_names_%s.json", year, gender, year);
    }

    private Names content(String fileName) {
        try {
            return mapper.readValue(this.getClass().getClassLoader().getResourceAsStream(fileName), Names.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class NameComparator implements Comparator<Name> {

        @Override
        public int compare(Name name1, Name name2) {
            return name1.getName().compareTo(name2.getName());
        }
    }
}
