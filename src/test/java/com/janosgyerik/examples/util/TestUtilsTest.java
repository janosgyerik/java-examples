package com.janosgyerik.examples.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TestUtilsTest {

    private File createTempFile() {
        try {
            return File.createTempFile("dummy", null);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Could not create temp file. Strange.");
        }
        return null;
    }

    private File createTempDir() {
        File tempfile = createTempFile();
        assertTrue(tempfile.delete());
        assertTrue(tempfile.mkdir());
        return tempfile;
    }

    @Test(expected = IOException.class)
    public void testSetupCleanDir_Throws_IfPathIsFile() throws IOException {
        File tempfile = createTempFile();
        assertTrue(tempfile.isFile());
        TestUtils.setupCleanDir(tempfile);
    }

    @Test(expected = IOException.class)
    public void testSetupCleanDir_Throws_IfPermissionDenied() throws IOException {
        TestUtils.setupCleanDir(new File("/nonexistent"));
    }

    @Test
    public void testSetupCleanDir_OK_IfPathIsDir() throws IOException {
        File tempdir = createTempDir();
        assertTrue(tempdir.isDirectory());
        TestUtils.setupCleanDir(tempdir);
        assertTrue(tempdir.isDirectory());
    }

    @Test
    public void testSetupCleanDir_WipesNonEmptyDir() throws IOException {
        File tempdir = createTempDir();
        assertTrue(tempdir.isDirectory());
        assertEquals(0, tempdir.list().length);
        assertTrue(new File(tempdir, "dummy").createNewFile());
        assertEquals(1, tempdir.list().length);
        TestUtils.setupCleanDir(tempdir);
        assertEquals(0, tempdir.list().length);
    }

    @Test
    public void testSetupCleanDir_CreatesDir() throws IOException {
        File tempdir = createTempDir();
        assertTrue(tempdir.delete());
        assertFalse(tempdir.exists());
        TestUtils.setupCleanDir(tempdir);
        assertTrue(tempdir.isDirectory());
    }

    @Test
    public void testCreateTestFiles() {

    }

    @Test
    public void testCreateTestDirs() {

    }
}
