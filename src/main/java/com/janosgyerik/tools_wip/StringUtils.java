package com.janosgyerik.tools_wip;

import java.util.*;

public class StringUtils {

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

        SearchTracker tracker = new SearchTracker(text, patterns, replacements);
        if (!tracker.hasNextMatch(0)) {
            return text;
        }

        StringBuilder buf = new StringBuilder(text.length() * 2);
        int start = 0;

        do {
            SearchTracker.MatchInfo matchInfo = tracker.matchInfo;
            int textIndex = matchInfo.textIndex;
            String pattern = matchInfo.pattern;
            String replacement = matchInfo.replacement;

            buf.append(text.substring(start, textIndex));
            buf.append(replacement);

            start = textIndex + pattern.length();
        } while (tracker.hasNextMatch(start));

        return buf.append(text.substring(start)).toString();
    }

    private static class SearchTracker {

        private final String text;

        private final Map<String, String> patternToReplacement = new HashMap<>();
        private final Set<String> pendingPatterns = new HashSet<>();

        private MatchInfo matchInfo = null;

        private static class MatchInfo {
            private final String pattern;
            private final String replacement;
            private final int textIndex;

            private MatchInfo(String pattern, String replacement, int textIndex) {
                this.pattern = pattern;
                this.replacement = replacement;
                this.textIndex = textIndex;
            }
        }

        private SearchTracker(String text, String[] searchList, String[] replacementList) {
            this.text = text;
            for (int i = 0; i < searchList.length; ++i) {
                String pattern = searchList[i];
                patternToReplacement.put(pattern, replacementList[i]);
                pendingPatterns.add(pattern);
            }
        }

        boolean hasNextMatch(int start) {
            int textIndex = -1;
            String nextPattern = null;

            for (String pattern : new ArrayList<>(pendingPatterns)) {
                int matchIndex = text.indexOf(pattern, start);
                if (matchIndex == -1) {
                    pendingPatterns.remove(pattern);
                } else {
                    if (textIndex == -1 || matchIndex < textIndex) {
                        textIndex = matchIndex;
                        nextPattern = pattern;
                    }
                }
            }

            if (nextPattern != null) {
                matchInfo = new MatchInfo(nextPattern, patternToReplacement.get(nextPattern), textIndex);
                return true;
            }
            return false;
        }
    }

    private static void validateParams(String text, String[] patterns, String[] replacements) {
        if (text == null || patterns == null || replacements == null) {
            throw new IllegalArgumentException("None of the parameters should be null");
        }
        if (patterns.length != replacements.length) {
            throw new IllegalArgumentException("There must be the same number of patterns and replacements");
        }
        // TODO searchList should not have null or empty elements
        // TODO replacementList should not have null elements
        // TODO searchList should not contain duplicates
    }
}
