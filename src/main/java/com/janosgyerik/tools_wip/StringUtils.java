package com.janosgyerik.tools_wip;

import java.util.*;
import java.util.stream.Stream;

public class StringUtils {

    public static final String ERR_NULL_PARAM = "none of the parameters should be null";
    public static final String ERR_SEARCHSTRINGS_REPLACEMENTS_LENGTH_MISMATCH =
            "there must be the same number of search strings and replacements";
    public static final String ERR_NULL_OR_EMPTY_SEARCHSTRING = "there must be no null element or empty search string";
    public static final String ERR_NULL_REPLACEMENT = "there must be no null element in replacements";
    public static final String ERR_DUPLICATE_SEARCHSTRINGS = "search strings must be distinct";

    /**
     * Replace multiple search strings simultaneously
     *
     * @param text the source text
     * @param searchStrings search strings to replace
     * @param replacements texts to replace the corresponding search strings
     * @return new text with search strings replaced
     */
    public static String replace(String text, String[] searchStrings, String[] replacements) {
        validateParams(text, searchStrings, replacements);

        StringBuilder builder = new StringBuilder();
        ReplacementSegmentIterator replacementSegments = new ReplacementSegmentIterator(text, searchStrings, replacements);
        while (replacementSegments.hasNext()) {
            builder.append(replacementSegments.next());
        }
        return builder.toString();
    }

    private static class ReplacementSegmentIterator implements Iterator<String> {

        private final String text;
        private final String[] searchStrings;
        private final String[] replacements;

        private int pos = 0;

        public ReplacementSegmentIterator(String text, String[] searchStrings, String[] replacements) {
            assert searchStrings.length == replacements.length;

            this.text = text;
            this.searchStrings = searchStrings;
            this.replacements = replacements;
        }

        @Override
        public boolean hasNext() {
            return hasRemainingText();
        }

        @Override
        public String next() {
            int start = pos;
            do {
                for (int index = 0; index < searchStrings.length; ++index) {
                    if (matchesAtCurrentPos(searchStrings[index])) {
                        String segment = text.substring(start, pos) + replacements[index];
                        pos += searchStrings[index].length();
                        return segment;
                    }
                }
                ++pos;
            } while (hasRemainingText());
            return text.substring(start);
        }

        private boolean hasRemainingText() {
            return pos < text.length();
        }

        private boolean matchesAtCurrentPos(String searchString) {
            if (text.length() < pos + searchString.length()) {
                return false;
            }
            for (int i = 0; i < searchString.length(); ++i) {
                if (text.charAt(pos + i) != searchString.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static void validateParams(String text, String[] searchStrings, String[] replacements) {
        if (text == null || searchStrings == null || replacements == null) {
            throw new IllegalArgumentException(ERR_NULL_PARAM);
        }
        if (searchStrings.length != replacements.length) {
            throw new IllegalArgumentException(ERR_SEARCHSTRINGS_REPLACEMENTS_LENGTH_MISMATCH);
        }
        if (searchStrings.length == 0) {
            return;
        }
        if (anyNullOrEmpty(searchStrings)) {
            throw new IllegalArgumentException(ERR_NULL_OR_EMPTY_SEARCHSTRING);
        }
        if (anyNull(replacements)) {
            throw new IllegalArgumentException(ERR_NULL_REPLACEMENT);
        }
        if (containsDuplicates(searchStrings)) {
            throw new IllegalArgumentException(ERR_DUPLICATE_SEARCHSTRINGS);
        }
    }

    private static boolean anyNullOrEmpty(String[] strings) {
        return Stream.of(strings).allMatch(x -> x == null || x.isEmpty());
    }

    private static boolean anyNull(String[] strings) {
        return Stream.of(strings).allMatch(x -> x == null);
    }

    private static boolean containsDuplicates(String[] strings) {
        return Stream.of(strings).distinct().count() != strings.length;
    }
}
