package com.sun.tools.javac;

import com.sun.nio.zipfs.ZipFileSystemProvider;
import java.nio.file.spi.FileSystemProvider;
import jdk.internal.jrtfs.JrtFileSystemProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ConfigProvider {
    public static String JAVA_HOME = "";
    public static FileSystemProvider jrtFsProvider = new JrtFileSystemProvider();
    public static FileSystemProvider zipFsProvider = new ZipFileSystemProvider();

    public static String getJavaHome() {
        return JAVA_HOME;
    }

    public static void setJavaHome(String str) {
        JAVA_HOME = str;
    }
}
