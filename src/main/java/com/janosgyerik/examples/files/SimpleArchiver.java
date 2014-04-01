package com.janosgyerik.examples.files;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleArchiver {

    private final File targetDir;

    public SimpleArchiver(File targetDir) {
        this.targetDir = targetDir;
    }

    public File getTargetWithDate(File source) {
        String datepart = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return new File(targetDir, datepart + "_" + source.getName());
    }

    private boolean prepareTargetDir() {
        if (!targetDir.isDirectory()) {
            if (!targetDir.mkdirs()) {
                return false;
            }
        }
        return true;
    }

    public boolean copyWithDate(File source) throws IOException {
        if (prepareTargetDir()) {
            File target = getTargetWithDate(source);
            return copy(source, target);
        }
        return false;
    }

    public boolean moveWithDate(File source) throws IOException {
        if (prepareTargetDir()) {
            File target = getTargetWithDate(source);
            return copy(source, target) && source.delete();
        }
        return false;
    }

    private boolean copy(File source, File target) throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(target);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
        return true;
    }
}
