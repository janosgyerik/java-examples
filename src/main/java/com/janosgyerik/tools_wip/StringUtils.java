package com.janosgyerik.tools_wip;

import java.util.*;
import java.util.stream.Stream;

public class StringUtils {

    public static final String ERR_NULL_PARAM = "none of the parameters should be null";
    public static final String ERR_PATTERNS_REPLACEMENTS_LENGTH_MISMATCH = "there must be the same number of patterns and replacements";
    public static final String ERR_NULL_OR_EMPTY_PATTERN = "there must be no null element or empty element in patterns";
    public static final String ERR_NULL_REPLACEMENT = "there must be no null element in replacements";
    public static final String ERR_DUPLICATE_PATTERNS = "patterns must be distinct";

    /**
     * Replace multiple patterns simultaneously
     *
     * @param text the source text
     * @param patterns patterns to replace
     * @param replacements texts to replace patterns
     * @return new text with patterns replaced
     */
    public static String replace(String text, String[] patterns, String[] replacements) {
        validateParams(text, patterns, replacements);

        StringBuilder builder = new StringBuilder();
        SegmentIterator iterator = new SegmentIterator(text, patterns, replacements);
        while (iterator.hasNext()) {
            builder.append(iterator.next());
        }
        return builder.toString();
    }

    private static class SegmentIterator implements Iterator<String> {

        private final String text;
        private final String[] patterns;
        private final String[] replacements;

        private int pos = 0;

        public SegmentIterator(String text, String[] patterns, String[] replacements) {
            assert patterns.length == replacements.length;
            this.text = text;
            this.patterns = patterns;
            this.replacements = replacements;
        }

        @Override
        public boolean hasNext() {
            return pos < text.length();
        }

        @Override
        public String next() {
            int start = pos;
            do {
                for (int index = 0; index < patterns.length; ++index) {
                    if (matches(patterns[index])) {
                        String segment = text.substring(start, pos) + replacements[index];
                        pos += patterns[index].length();
                        return segment;
                    }
                }
                ++pos;
            } while (pos < text.length());
            return text.substring(start);
        }

        private boolean matches(String pattern) {
            if (text.length() < pos + pattern.length()) {
                return false;
            }
            for (int i = 0; i < pattern.length(); ++i) {
                if (text.charAt(pos + i) != pattern.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static void validateParams(String text, String[] patterns, String[] replacements) {
        if (text == null || patterns == null || replacements == null) {
            throw new IllegalArgumentException(ERR_NULL_PARAM);
        }
        if (patterns.length != replacements.length) {
            throw new IllegalArgumentException(ERR_PATTERNS_REPLACEMENTS_LENGTH_MISMATCH);
        }
        if (patterns.length == 0) {
            return;
        }
        if (anyNullOrEmpty(patterns)) {
            throw new IllegalArgumentException(ERR_NULL_OR_EMPTY_PATTERN);
        }
        if (anyNull(replacements)) {
            throw new IllegalArgumentException(ERR_NULL_REPLACEMENT);
        }
        if (Stream.of(patterns).distinct().count() != patterns.length) {
            throw new IllegalArgumentException(ERR_DUPLICATE_PATTERNS);
        }
    }

    private static boolean anyNullOrEmpty(String[] strings) {
        return Stream.of(strings).allMatch(x -> x == null || x.isEmpty());
    }

    private static boolean anyNull(String[] strings) {
        return Stream.of(strings).allMatch(x -> x == null);
    }
}
