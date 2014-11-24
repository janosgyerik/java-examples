package com.janosgyerik.telnetserver.commands;

import com.janosgyerik.telnetserver.impl.commands.MkdirCommand;
import com.janosgyerik.telnetserver.util.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MkdirCommandTest extends BaseCommandTest {

    private List<String> mkdir(String... paths) {
        return new MkdirCommand(WORKDIR).execute(paths);
    }

    private List<String> mkdir(File file) {
        return new MkdirCommand(WORKDIR).execute(file.toString());
    }

    @Test
    public void testCannotCreateIfParentMissing() {
        File parent = new File(WORKDIR, "dir1");
        assertFalse(parent.isDirectory());

        File target = new File(parent, "dir2");
        mkdir(target);
        assertFalse(target.isDirectory());

        mkdir(parent);
        assertTrue(parent.isDirectory());
        mkdir(target);
        assertTrue(target.isDirectory());
    }

    @Test
    public void testCannotCreateIfFileExists() throws IOException {
        String filename = "file1.txt";
        FileUtils.createTestFiles(WORKDIR, filename);

        mkdir(filename);
        assertFalse(new File(WORKDIR, filename).isDirectory());

        String dirname = "dir1";
        FileUtils.createTestDirs(WORKDIR, dirname);

        mkdir(dirname);
        assertTrue(new File(WORKDIR, dirname).isDirectory());
    }

    @Test
    public void testCreateMultiple() {
        String[] dirnames = new String[]{"dir1", "dir2", "dir3"};
        mkdir(dirnames);
        for (String dirname : dirnames) {
            assertTrue(new File(WORKDIR, dirname).isDirectory());
        }
    }

    @Test
    public void testCreateWithRelpath() {
        String relpath = "dir1";
        File target = new File(WORKDIR, relpath);

        assertFalse(target.isDirectory());
        mkdir(relpath);
        assertTrue(target.isDirectory());
    }

    @Test
    public void testCreateWithAbsolutePath() {
        String relpath = "dir1";
        File target = new File(WORKDIR, relpath);

        assertFalse(target.isDirectory());
        mkdir(target.getAbsolutePath());
        assertTrue(target.isDirectory());
    }

    @Test
    public void testCreateMultipleWithPartialFailures() {
        String badPath = "nonexistent/dir1";
        String goodPath = "dir2";

        mkdir(badPath, goodPath);
        assertFalse(new File(WORKDIR, badPath).isDirectory());
        assertTrue(new File(WORKDIR, goodPath).isDirectory());
    }
}
