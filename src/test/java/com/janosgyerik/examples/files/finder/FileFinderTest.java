package com.janosgyerik.examples.files.finder;

import com.janosgyerik.examples.util.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class FileFinderTest {

    protected File tempDir;

    abstract FileFinder getFileFinder();

    protected abstract List<String> getMatchingNames(int num);

    protected abstract List<String> getNonMatchingNames(int num);

    @Before
    public void setUp() throws IOException {
        tempDir = FileUtils.createTempDir();
    }

    @After
    public void tearDown() throws IOException {
        FileUtils.deleteRecursively(tempDir);
        tempDir = null;
    }

    private File createTempFile(String filename) {
        return new File(tempDir, filename);
    }

    private void createTempFiles(List<String> filenames) throws IOException {
        for (String filename : filenames) {
            File file = new File(tempDir, filename);
            if (!file.createNewFile()) {
                throw new IOException("Could not create temp file: " + file);
            }
        }
    }

    @Test
    public void testNonexistentDir() {
        assertEquals(0, getFileFinder().listFiles(new File("nonexistent")).size());
    }

    @Test
    public void testInvalidPath() {
        assertEquals(0, getFileFinder().listFiles(new File("!@#$%^&*()")).size());
    }

    @Test
    public void testPathIsFile() throws IOException {
        assertEquals(0, getFileFinder().listFiles(createTempFile("hello")).size());
    }

    @Test
    public void testPathIsEmptyDir() {
        assertEquals(0, getFileFinder().listFiles(tempDir).size());
    }

    @Test
    public void testMatchOneOfMany() throws IOException {
        createTempFiles(getMatchingNames(1));
        createTempFiles(getNonMatchingNames(3));
        assertEquals(4, tempDir.listFiles().length);
        assertEquals(1, getFileFinder().listFiles(tempDir).size());
    }

    @Test
    public void testMatchManyOfMany() throws IOException {
        createTempFiles(getMatchingNames(2));
        createTempFiles(getNonMatchingNames(3));
        assertEquals(5, tempDir.listFiles().length);
        assertEquals(2, getFileFinder().listFiles(tempDir).size());
    }

    @Test
    public void testMatchNoneOfMany() throws IOException {
        createTempFiles(getNonMatchingNames(3));
        assertEquals(3, tempDir.listFiles().length);
        assertEquals(0, getFileFinder().listFiles(tempDir).size());
    }
}