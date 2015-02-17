package com.janosgyerik.examples.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileUtilsTest {

    @Test
    public void testCreateTempFile() throws IOException {
        File tempFile = FileUtils.createTempFile();
        assertTrue(tempFile.isFile());
        FileUtils.deleteFile(tempFile);
    }

    @Test
    public void testDeleteFile() throws IOException {
        File tempFile = FileUtils.createTempFile();
        assertTrue(tempFile.isFile());
        FileUtils.deleteFile(tempFile);
        assertFalse(tempFile.exists());
    }

    @Test
    public void testDeleteRecursively_DeletesFile() throws IOException {
        File file = FileUtils.createTempFile();
        assertTrue(file.exists());
        FileUtils.deleteRecursively(file);
        assertFalse(file.exists());
    }

}