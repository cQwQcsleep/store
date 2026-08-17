package net.jpountz.util;

import androidx.compose.compiler.plugins.kotlin.analysis.StabilityExternalClassNameMatchingKt;
import defpackage.c41;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import net.schmizz.sshj.sftp.PathHelper;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public enum Native {
    ;

    private static boolean loaded = false;

    public enum OS {
        WINDOWS("win32", "so"),
        LINUX("linux", "so"),
        MAC("darwin", "dylib"),
        SOLARIS("solaris", "so");

        public final String libExtension;
        public final String name;

        OS(String str, String str2) {
            this.name = str;
            this.libExtension = str2;
        }
    }

    private static String arch() {
        return System.getProperty("os.arch");
    }

    private static void cleanupOldTempLibs() {
        File[] fileArrListFiles = new File(new File(System.getProperty("java.io.tmpdir")).getAbsolutePath()).listFiles(new FilenameFilter() { // from class: net.jpountz.util.Native.1
            private final String searchPattern = "liblz4-java-";

            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.startsWith("liblz4-java-") && !str.endsWith(".lck");
            }
        });
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (!new File(file.getAbsolutePath() + ".lck").exists()) {
                    try {
                        file.delete();
                    } catch (SecurityException e) {
                        System.err.println("Failed to delete old temp lib" + e.getMessage());
                    }
                }
            }
        }
    }

    public static synchronized boolean isLoaded() {
        return loaded;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v6 */
    public static synchronized void load() {
        Throwable th;
        IOException e;
        File fileCreateTempFile = ".";
        synchronized (Native.class) {
            if (loaded) {
                return;
            }
            cleanupOldTempLibs();
            try {
                System.loadLibrary("lz4-java");
                loaded = true;
            } catch (UnsatisfiedLinkError unused) {
                File fileResourceName = resourceName();
                InputStream resourceAsStream = Native.class.getResourceAsStream(fileResourceName);
                if (resourceAsStream == null) {
                    throw new UnsupportedOperationException("Unsupported OS/arch, cannot find " + ((String) fileResourceName) + ". Please try building from source.");
                }
                try {
                    try {
                        fileCreateTempFile = File.createTempFile("liblz4-java-", "." + os().libExtension + ".lck");
                        try {
                            File file = new File(fileCreateTempFile.getAbsolutePath().replaceFirst(".lck$", HttpUrl.FRAGMENT_ENCODE_SET));
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                try {
                                    byte[] bArr = new byte[4096];
                                    while (true) {
                                        int i = resourceAsStream.read(bArr);
                                        if (i == -1) {
                                            fileOutputStream.close();
                                            System.load(file.getAbsolutePath());
                                            loaded = true;
                                            file.deleteOnExit();
                                            fileCreateTempFile.deleteOnExit();
                                            return;
                                        }
                                        fileOutputStream.write(bArr, 0, i);
                                    }
                                } catch (Throwable th2) {
                                    try {
                                        throw th2;
                                    } catch (Throwable th3) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (Throwable th4) {
                                            th2.addSuppressed(th4);
                                        }
                                        throw th3;
                                    }
                                }
                            } catch (IOException e2) {
                                e = e2;
                                throw new ExceptionInInitializerError("Cannot unpack liblz4-java: " + e);
                            }
                        } catch (IOException e3) {
                            e = e3;
                        } catch (Throwable th5) {
                            fileResourceName = 0;
                            th = th5;
                            if (loaded) {
                                fileResourceName.deleteOnExit();
                                fileCreateTempFile.deleteOnExit();
                            } else {
                                if (fileResourceName != 0 && fileResourceName.exists() && !fileResourceName.delete()) {
                                    throw new ExceptionInInitializerError("Cannot unpack liblz4-java / cannot delete a temporary native library " + fileResourceName);
                                }
                                if (fileCreateTempFile != 0 && fileCreateTempFile.exists() && !fileCreateTempFile.delete()) {
                                    throw new ExceptionInInitializerError("Cannot unpack liblz4-java / cannot delete a temporary lock file " + fileCreateTempFile);
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (IOException e4) {
                    e = e4;
                } catch (Throwable th7) {
                    fileResourceName = 0;
                    th = th7;
                    fileCreateTempFile = 0;
                }
            }
        }
    }

    private static OS os() {
        String property = System.getProperty("os.name");
        if (property.contains("Linux")) {
            return OS.LINUX;
        }
        if (property.contains("Mac")) {
            return OS.MAC;
        }
        if (property.contains("Windows")) {
            return OS.WINDOWS;
        }
        if (property.contains("Solaris") || property.contains("SunOS")) {
            return OS.SOLARIS;
        }
        c41.a("Unsupported operating system: ".concat(property));
        return null;
    }

    private static String resourceName() {
        OS os = os();
        return PathHelper.DEFAULT_PATH_SEPARATOR + Native.class.getPackage().getName().replace(StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR, '/') + PathHelper.DEFAULT_PATH_SEPARATOR + os.name + PathHelper.DEFAULT_PATH_SEPARATOR + arch() + "/liblz4-java." + os.libExtension;
    }
}
