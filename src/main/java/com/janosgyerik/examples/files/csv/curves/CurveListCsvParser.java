package com.janosgyerik.examples.files.csv.curves;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

/**
 * parser for file format:
 *
 * header;months;foo;bar;baz
 * label;1M;1.1;2.1;3.1
 * label;2M;1.2;2.2;3.2
 * label;6M;1.6;2.6;3.6
 */
public class CurveListCsvParser {
    private static final int COL_MONTHS = 1;

    private static final Pattern RE_MONTHS = Pattern.compile("\\d+M");

    public Result parse(File file, Date curveDate, List<String> curveCodes) {
        try {
            Scanner scanner = new Scanner(new FileReader(file));
            Result result = parse(scanner, curveDate, curveCodes);
            scanner.close();
            return result;
        } catch (FileNotFoundException e) {
            return new Result();
            e.printStackTrace();
        }
    }

    public Result parse(Scanner scanner, Date curveDate, List<String> curveCodes) {
        Result.Builder resultBuilder = Result.builder(curveDate, curveCodes);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cols = line.split("\\s*;\\s*");
            if (cols.length <= COL_MONTHS || !RE_MONTHS.matcher(cols[COL_MONTHS]).matches()) {
                continue;
            }
            int months = Integer.parseInt(cols[COL_MONTHS].replaceAll("[^\\d]", ""));
            int index = 0;
            for (int i = COL_MONTHS + 1; i < cols.length && index < curveCodes.size(); ++i, ++index) {
                String strValue = cols[i].trim();
                if (strValue.isEmpty()) {
                    continue;
                }
                try {
                    double value = Double.parseDouble(strValue);
                    resultBuilder.addCurvePoint(index, new CurvePoint(curveDate, months, value));
                } catch (NumberFormatException e) {
                    resultBuilder.addError(new ParseError(cols[i], i, line));
                }
            }
        }
        return resultBuilder.build();
    }

    public static class Result {

        private final Collection<Curve> curves;
        private final Collection<ParseError> parseErrors;

        public Result(Collection<Curve> curves, Collection<ParseError> parseErrors) {
            this.curves = curves;
            this.parseErrors = parseErrors;
        }

        public static Builder builder(Date curveDate, List<String> curveCodes) {
            return new Builder(curveDate, curveCodes);
        }

        public Collection<Curve> getCurves() {
            return curves;
        }

        public Collection<ParseError> getParseErrors() {
            return parseErrors;
        }

        private static class Builder {
            private final List<Curve.Builder> curveBuilders = new ArrayList<>();
            private final List<ParseError> parseErrors = new ArrayList<>();

            private Builder(Date curveDate, List<String> curveCodes) {
                for (String code : curveCodes) {
                    curveBuilders.add(Curve.builder(code, curveDate));
                }
            }

            private void addError(ParseError parseError) {
                parseErrors.add(parseError);
            }

            private void addCurvePoint(int index, CurvePoint curvePoint) {
                curveBuilders.get(index).addCurvePoint(curvePoint);
            }

            Result build() {
                List<Curve> curves = new ArrayList<>();
                for (Curve.Builder builder : curveBuilders) {
                    if (!builder.isEmpty()) {
                        curves.add(builder.build());
                    }
                }
                return new Result(Collections.unmodifiableCollection(curves), Collections.unmodifiableCollection(parseErrors));
            }
        }
    }

    public static class ParseError {
        private final String message;

        public ParseError(String value, int index, String line) {
            message = String.format("Could not parse value: '%s' (column %d in line = '%s')", value, index, line);
        }

        @Override
        public String toString() {
            return message;
        }
    }
}
