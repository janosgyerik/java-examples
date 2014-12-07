package com.janosgyerik.examples.multicolumn;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CurveMatrixParserTest {

    @Test
    public void testWithTwoColumns() {
        String data = "id,date,col1,col2\n" +
                "1,2014-12-07,27,28\n" +
                "2,2014-12-08,25,29\n";
        ScannerRecordProvider provider = new ScannerRecordProvider(new Scanner(data), 1);
        ColumnSplitter splitter = ColumnSplitters.simpleCsvSplitter();
        IntegerColumnParser col1Parser = new IntegerColumnParser(3);
        IntegerColumnParser col2Parser = new IntegerColumnParser(4);

        MultiColumnParser parser = new MultiColumnParserImpl();
        List<ParsedColumn<?>> result = parser.parse(provider, splitter, Arrays.asList(col1Parser, col2Parser));

        ParsedColumn<?> col1 = result.get(0);
        assertEquals(0, col1.getParseErrors().size());
        assertEquals(2, col1.getValues().size());
        assertEquals(Arrays.asList(27, 25), col1.getValues());

        ParsedColumn<?> col2 = result.get(1);
        assertEquals(0, col2.getParseErrors().size());
        assertEquals(2, col2.getValues().size());
        assertEquals(Arrays.asList(28, 29), col2.getValues());
    }

    @Test
    public void testWithEmptyCells() {
        String data = "id,date,col1,col2\n" +
                "1,2014-12-07,,28\n" +
                "2,2014-12-08,21,29\n";
        ScannerRecordProvider provider = new ScannerRecordProvider(new Scanner(data), 1);
        ColumnSplitter splitter = ColumnSplitters.simpleCsvSplitter();
        IntegerColumnParser col1Parser = new IntegerColumnParser(3);
        IntegerColumnParser col2Parser = new IntegerColumnParser(4);

        MultiColumnParser parser = new MultiColumnParserImpl();
        List<ParsedColumn<?>> result = parser.parse(provider, splitter, Arrays.asList(col1Parser, col2Parser));

        ParsedColumn<?> col1 = result.get(0);
        assertEquals(0, col1.getParseErrors().size());
        assertEquals(1, col1.getValues().size());
        assertEquals(Arrays.asList(21), col1.getValues());

        ParsedColumn<?> col2 = result.get(1);
        assertEquals(0, col2.getParseErrors().size());
        assertEquals(2, col2.getValues().size());
        assertEquals(Arrays.asList(28, 29), col2.getValues());
    }

    @Test
    public void testWithInvalidCells() {
        String data = "id,date,col1,col2\n" +
                "1,2014-12-07,x,28\n" +
                "2,2014-12-08,21,29\n";
        ScannerRecordProvider provider = new ScannerRecordProvider(new Scanner(data), 1);
        ColumnSplitter splitter = ColumnSplitters.simpleCsvSplitter();
        IntegerColumnParser col1Parser = new IntegerColumnParser(3);
        IntegerColumnParser col2Parser = new IntegerColumnParser(4);

        MultiColumnParser parser = new MultiColumnParserImpl();
        List<ParsedColumn<?>> result = parser.parse(provider, splitter, Arrays.asList(col1Parser, col2Parser));

        ParsedColumn<?> col1 = result.get(0);
        assertEquals(1, col1.getParseErrors().size());
        assertEquals(1, col1.getValues().size());
        assertEquals(Arrays.asList(21), col1.getValues());

        ParsedColumn<?> col2 = result.get(1);
        assertEquals(0, col2.getParseErrors().size());
        assertEquals(2, col2.getValues().size());
        assertEquals(Arrays.asList(28, 29), col2.getValues());
    }

    @Test
    public void testWithCustomObject() {
        String data = "id,date,col1,col2\n" +
                "1,2014-12-07,x,28\n" +
                "2,2014-12-08,21,29\n";
        ScannerRecordProvider provider = new ScannerRecordProvider(new Scanner(data), 1);
        ColumnSplitter splitter = ColumnSplitters.simpleCsvSplitter();

        class DataPoint {
            private final Integer id;
            private final Integer num;

            public DataPoint(Integer id, Integer num) {
                this.id = id;
                this.num = num;
            }

            @Override
            public String toString() {
                return String.format("%d: %d", id, num);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }

                DataPoint dataPoint = (DataPoint) o;

                if (!id.equals(dataPoint.id)) {
                    return false;
                }
                if (!num.equals(dataPoint.num)) {
                    return false;
                }

                return true;
            }

            @Override
            public int hashCode() {
                int result = id.hashCode();
                result = 31 * result + num.hashCode();
                return result;
            }
        }

         class DataPointParser extends AbstractColumnParser<DataPoint> {
             public DataPointParser(int index) {
                 super(index);
             }

             @Override
            public void parseSafeColumns(List<String> columnList, int index, String value) {
                Integer id, num;
                try {
                    id = Integer.parseInt(columnList.get(0));
                    num = Integer.parseInt(columnList.get(index));
                    getValues().add(new DataPoint(id, num));
                } catch (NumberFormatException e) {
                    getErrors().add(new ParseError(index, value));
                }
            }
        }
        ColumnParser<DataPoint> col1Parser = new DataPointParser(3);
        ColumnParser<DataPoint> col2Parser = new DataPointParser(4);

        MultiColumnParser parser = new MultiColumnParserImpl();
        List<ParsedColumn<?>> result = parser.parse(provider, splitter, Arrays.asList(col1Parser, col2Parser));

        ParsedColumn<?> col1 = result.get(0);
        assertEquals(1, col1.getParseErrors().size());
        assertEquals(1, col1.getValues().size());
        assertArrayEquals(Arrays.asList(new DataPoint(2, 21)).toArray(), col1.getValues().toArray());

        ParsedColumn<?> col2 = result.get(1);
        assertEquals(0, col2.getParseErrors().size());
        assertEquals(2, col2.getValues().size());
        assertArrayEquals(Arrays.asList(new DataPoint(1, 28), new DataPoint(2, 29)).toArray(), col2.getValues().toArray());
    }
}