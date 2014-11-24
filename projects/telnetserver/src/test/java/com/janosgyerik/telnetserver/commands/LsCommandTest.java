package com.janosgyerik.telnetserver.commands;

import com.janosgyerik.telnetserver.impl.commands.LsCommand;
import com.janosgyerik.telnetserver.util.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LsCommandTest extends BaseCommandTest {

    private List<String> ls() {
        return new LsCommand(WORKDIR).execute();
    }

    private List<String> ls(String... paths) {
        return new LsCommand(WORKDIR).execute(paths);
    }

    private List<String> ls(File file) {
        return new LsCommand(WORKDIR).execute(file.toString());
    }

    @Test
    public void testEmptyDir() {
        assertTrue(ls().isEmpty());
    }

    @Test
    public void testNonexistentDir() {
        assertTrue(ls(new File(WORKDIR, "nonexistent")).isEmpty());
    }

    @Test
    public void testDirWithFiles() throws IOException {
        String[] filenames = new String[]{"file1.txt", "file2.txt"};
        FileUtils.createTestFiles(WORKDIR, filenames);
        assertEquals(Arrays.asList(filenames), ls());
    }

    @Test
    public void testDirWithFilesAndDirs() throws IOException {
        String[] filenames = new String[]{"file1.txt", "file2.txt"};
        FileUtils.createTestFiles(WORKDIR, filenames);
        assertEquals(Arrays.asList(filenames), ls());

        String[] dirnames = new String[]{"dir1", "dir2"};
        FileUtils.createTestDirs(WORKDIR, dirnames);
        assertNotEquals(Arrays.asList(filenames), ls());

        List<String> names = new ArrayList<>();
        names.addAll(Arrays.asList(dirnames));
        names.addAll(Arrays.asList(filenames));
        assertEquals(names, ls());

        String[] filenames2 = new String[]{"file3.txt", "file4.txt"};
        FileUtils.createTestFiles(new File(WORKDIR, dirnames[0]), filenames2);
        assertEquals(Arrays.asList(filenames2), ls(new File(WORKDIR, dirnames[0])));
    }

    @Test
    public void testWithFileRelpath() throws IOException {
        String[] filenames = new String[]{"file1.txt", "file2.txt"};
        FileUtils.createTestFiles(WORKDIR, filenames);
        assertEquals(Arrays.asList(filenames[0]), ls(filenames[0]));
    }

    @Test
    public void testDirContentsWithRelpath() throws IOException {
        String dirname = "dir1";
        FileUtils.createTestDirs(WORKDIR, dirname);
        assertEquals(Arrays.asList(dirname), ls());

        String[] filenames = new String[]{"file1.txt", "file2.txt"};
        FileUtils.createTestFiles(new File(WORKDIR, dirname), filenames);
        assertEquals(Arrays.asList(filenames), ls(dirname));
    }

    @Test
    public void testWithFileAbsolutePath() throws IOException {
        String[] filenames = new String[]{"file1.txt", "file2.txt"};
        FileUtils.createTestFiles(WORKDIR, filenames);
        assertEquals(Arrays.asList(new File(WORKDIR, filenames[0]).toString()), ls(new File(WORKDIR, filenames[0])));
    }

    @Test
    public void testWithNoArgs() throws IOException {
        String[] filenames = new String[]{"file1.txt", "file2.txt"};
        FileUtils.createTestFiles(WORKDIR, filenames);
        assertEquals(Arrays.asList(filenames), ls());
    }

    @Test
    public void testWithManyArgs() throws IOException {
        String[] filenames = new String[]{"file1.txt"};
        FileUtils.createTestFiles(WORKDIR, filenames);
        String[] args = new String[]{filenames[0], filenames[0]};
        assertEquals(Arrays.asList(args), ls(args));
    }
}
