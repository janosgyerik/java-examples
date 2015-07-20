package com.janosgyerik.tools.files;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileComparatorTest {

    // TODO find a better way
    private File basedir = new File("src/test/resources/files/comparator");

    private File orig = new File(basedir, "orig.txt");
    private File same = new File(basedir, "same.txt");
    private File empty = new File(basedir, "empty.txt");
    private File nonexistent = new File("nonexistent");

    @Test
    public void testSameContent() throws IOException {
        assertTrue(new FileComparator().sameContent(orig, orig));
        assertTrue(new FileComparator().sameContent(orig, same));
    }

    @Test
    public void testDifferentFromEmpty() throws IOException {
        assertFalse(new FileComparator().sameContent(orig, empty));
    }

    @Test(expected = FileNotFoundException.class)
    public void testDifferentFromNonExistent() throws IOException {
        assertFalse(new FileComparator().sameContent(orig, nonexistent));
    }

    @Test(expected = FileNotFoundException.class)
    public void testDifferentBothNonExistent() throws IOException {
        assertFalse(new FileComparator().sameContent(nonexistent, nonexistent));
    }
}
