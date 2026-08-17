package com.reandroid.archive;

import com.reandroid.archive.writer.ApkFileWriter;
import com.reandroid.archive.writer.ZipAligner;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ZipAlign {
    public static void align(File file, File file2, ZipAligner zipAligner) throws IOException {
        if (file.equals(file2)) {
            r8g.a("Input and output are equal: ", file);
            return;
        }
        ApkFileWriter apkFileWriter = new ApkFileWriter(file2, new ArchiveFile(file).getInputSources());
        apkFileWriter.setZipAligner(zipAligner);
        apkFileWriter.write();
    }

    public static void alignApk(File file) throws IOException {
        if (!file.isFile()) {
            s8g.a("No such file: ", file);
            return;
        }
        File tmpFile = toTmpFile(file);
        try {
            alignApk(file, tmpFile);
            file.delete();
            tmpFile.renameTo(file);
        } catch (IOException e) {
            tmpFile.delete();
            throw e;
        }
    }

    private static File toTmpFile(File file) {
        String str = file.getName() + ".align.tmp";
        File parentFile = file.getParentFile();
        return parentFile == null ? new File(str) : new File(parentFile, str);
    }

    public static void alignApk(File file, File file2) throws IOException {
        align(file, file2, ZipAligner.apkAligner());
    }

    public static void align(File file, File file2, int i) throws IOException {
        ZipAligner zipAligner = new ZipAligner();
        zipAligner.setDefaultAlignment(i);
        align(file, file2, zipAligner);
    }
}
