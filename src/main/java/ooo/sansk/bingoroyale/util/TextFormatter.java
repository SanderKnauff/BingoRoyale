package ooo.sansk.bingoroyale.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFormatter {

    public static String enumNameToFancyString(String enumName) {
        return Stream.of(enumName.split("_")).map(TextFormatter::capitalizeWord).collect(Collectors.joining(" "));
    }

    public static String capitalizeWord(String source) {
        String lowerCase = source.toLowerCase();
        return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
    }
}
