package com.janosgyerik.examples.files.finder;

import com.janosgyerik.examples.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DateMatchingFileFinderTest extends FileFinderTest {

    @Override
    FileFinder getFileFinder() {
        return new DateMatchingFileFinder("'fund_'yyyyMMdd", DateUtils.create(2015, 2, 17));
    }

    @Override
    protected List<String> getMatchingNames(int num) {
        List<String> names = new ArrayList<>(num);
        for (int i = 0; i < num; ++i) {
            names.add("fund_20150217-" + i + ".csv");
        }
        return names;
    }

    @Override
    protected List<String> getNonMatchingNames(int num) {
        List<String> names = new ArrayList<>(num);
        for (int i = 0; i < num; ++i) {
            names.add("hello" + i + ".csv");
        }
        return names;
    }

}