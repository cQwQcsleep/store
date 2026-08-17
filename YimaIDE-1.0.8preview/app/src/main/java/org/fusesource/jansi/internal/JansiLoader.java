package org.fusesource.jansi.internal;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JansiLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean loaded = false;
    private static String nativeLibraryPath;
    private static String nativeLibrarySourceUrl;

    public static void cleanup() {
        File[] fileArrListFiles = new File(getTempDir().getAbsolutePath()).listFiles(new FilenameFilter() { // from class: org.fusesource.jansi.internal.JansiLoader.1
            private final String searchPattern = "jansi-" + JansiLoader.getVersion();

            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.startsWith(this.searchPattern) && !str.endsWith(".lck");
            }
        });
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (!new File(file.getAbsolutePath() + ".lck").exists()) {
                    try {
                        file.delete();
                    } catch (SecurityException e) {
                        System.err.println("Failed to delete old native lib" + e.getMessage());
                    }
                }
            }
        }
    }

    private static String contentsEquals(InputStream inputStream, InputStream inputStream2) throws IOException {
        byte[] bArr = new byte[8192];
        byte[] bArr2 = new byte[8192];
        do {
            int nBytes = readNBytes(inputStream, bArr);
            int nBytes2 = readNBytes(inputStream2, bArr2);
            if (nBytes <= 0) {
                if (nBytes2 > 0) {
                    return "EOF on first stream but not second";
                }
                return null;
            }
            if (nBytes2 <= 0) {
                return "EOF on second stream but not first";
            }
            if (nBytes2 != nBytes) {
                return "Read size different (" + nBytes + " vs " + nBytes2 + ")";
            }
        } while (Arrays.equals(bArr, bArr2));
        return "Content differs";
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:72:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private static boolean extractAndLoadLibraryFile(String str, String str2, String str3) {
        String str4 = str + PsuedoNames.PSEUDONAME_ROOT + str2;
        String str5 = String.format("jansi-%s-%s-%s", getVersion(), randomUUID(), str2);
        String strConcat = str5.concat(".lck");
        File file = new File(str3, str5);
        File file2 = new File(str3, strConcat);
        try {
            try {
                InputStream resourceAsStream = JansiLoader.class.getResourceAsStream(str4);
                try {
                    if (!file2.exists()) {
                        new FileOutputStream(file2).close();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        copy(resourceAsStream, fileOutputStream);
                        fileOutputStream.close();
                        if (resourceAsStream != null) {
                            resourceAsStream.close();
                        }
                        file.deleteOnExit();
                        file2.deleteOnExit();
                        file.setReadable(true);
                        file.setWritable(true);
                        file.setExecutable(true);
                        InputStream resourceAsStream2 = JansiLoader.class.getResourceAsStream(str4);
                        try {
                            FileInputStream fileInputStream = new FileInputStream(file);
                            try {
                                String strContentsEquals = contentsEquals(resourceAsStream2, fileInputStream);
                                if (strContentsEquals != null) {
                                    throw new RuntimeException(String.format("Failed to write a native library file at %s because %s", file, strContentsEquals));
                                }
                                fileInputStream.close();
                                if (resourceAsStream2 != null) {
                                    resourceAsStream2.close();
                                }
                                if (!loadNativeLibrary(file)) {
                                    return false;
                                }
                                nativeLibrarySourceUrl = JansiLoader.class.getResource(str4).toExternalForm();
                                return true;
                            } catch (Throwable th) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            if (resourceAsStream2 != null) {
                                try {
                                    resourceAsStream2.close();
                                } catch (Throwable th4) {
                                    th3.addSuppressed(th4);
                                }
                            }
                            throw th3;
                        }
                        if (resourceAsStream2 != null) {
                            resourceAsStream2.close();
                        }
                        throw th3;
                    } catch (Throwable th5) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                        }
                        throw th5;
                    }
                } catch (Throwable th7) {
                    if (resourceAsStream != null) {
                        try {
                            resourceAsStream.close();
                        } catch (Throwable th8) {
                            th7.addSuppressed(th8);
                        }
                    }
                    throw th7;
                }
            } catch (Throwable th9) {
                file.deleteOnExit();
                file2.deleteOnExit();
                throw th9;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static int getMajorVersion() {
        String[] strArrSplit = getVersion().split("\\.");
        if (strArrSplit.length > 0) {
            return Integer.parseInt(strArrSplit[0]);
        }
        return 1;
    }

    public static int getMinorVersion() {
        String[] strArrSplit = getVersion().split("\\.");
        if (strArrSplit.length > 1) {
            return Integer.parseInt(strArrSplit[1]);
        }
        return 0;
    }

    public static String getNativeLibraryPath() {
        return nativeLibraryPath;
    }

    public static String getNativeLibrarySourceUrl() {
        return nativeLibrarySourceUrl;
    }

    private static File getTempDir() {
        return new File(System.getProperty("jansi.tmpdir", System.getProperty("java.io.tmpdir")));
    }

    public static String getVersion() {
        URL resource = JansiLoader.class.getResource("/org/fusesource/jansi/jansi.properties");
        String property = "unknown";
        if (resource != null) {
            try {
                Properties properties = new Properties();
                properties.load(resource.openStream());
                property = properties.getProperty("version", "unknown");
                return property.trim().replaceAll("[^0-9.]", "");
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        return property;
    }

    private static boolean hasResource(String str) {
        return JansiLoader.class.getResource(str) != null;
    }

    public static synchronized boolean initialize() {
        if (!loaded) {
            cleanup();
        }
        try {
            loadJansiNativeLibrary();
        } catch (Exception e) {
            if (!Boolean.parseBoolean(System.getProperty("jansi.graceful", "true"))) {
                throw new RuntimeException("Unable to load jansi native library. You may want set the `jansi.graceful` system property to true to be able to use Jansi on your platform", e);
            }
        }
        return loaded;
    }

    private static String join(List<String> list, String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str2 : list) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    private static void loadJansiNativeLibrary() throws Exception {
        if (loaded) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        String property = System.getProperty("library.jansi.path");
        String property2 = System.getProperty("library.jansi.name");
        if (property2 == null) {
            property2 = System.mapLibraryName("jansi");
            if (property2.endsWith(".dylib")) {
                property2 = property2.replace(".dylib", ".jnilib");
            }
        }
        if (property != null) {
            String str = property + PsuedoNames.PSEUDONAME_ROOT + OSInfo.getNativeLibFolderPathForCurrentOS();
            if (loadNativeLibrary(new File(str, property2))) {
                loaded = true;
                return;
            }
            linkedList.add(str);
            if (loadNativeLibrary(new File(property, property2))) {
                loaded = true;
                return;
            }
            linkedList.add(property);
        }
        String str2 = String.format("/%s/native/%s", JansiLoader.class.getPackage().getName().replace('.', '/'), OSInfo.getNativeLibFolderPathForCurrentOS());
        if (hasResource(str2 + PsuedoNames.PSEUDONAME_ROOT + property2)) {
            if (extractAndLoadLibraryFile(str2, property2, getTempDir().getAbsolutePath())) {
                loaded = true;
                return;
            }
            linkedList.add(str2);
        }
        for (String str3 : System.getProperty("java.library.path", "").split(File.pathSeparator)) {
            if (!str3.isEmpty()) {
                if (loadNativeLibrary(new File(str3, property2))) {
                    loaded = true;
                    return;
                }
                linkedList.add(str3);
            }
        }
        throw new Exception(String.format("No native library found for os.name=%s, os.arch=%s, paths=[%s]", OSInfo.getOSName(), OSInfo.getArchName(), join(linkedList, File.pathSeparator)));
    }

    private static boolean loadNativeLibrary(File file) {
        if (file.exists()) {
            try {
                String absolutePath = file.getAbsolutePath();
                System.load(absolutePath);
                nativeLibraryPath = absolutePath;
                return true;
            } catch (UnsatisfiedLinkError e) {
                if (file.canExecute()) {
                    System.err.printf("Failed to load native library:%s. osinfo: %s%n", file.getName(), OSInfo.getNativeLibFolderPathForCurrentOS());
                } else {
                    System.err.printf("Failed to load native library:%s. The native library file at %s is not executable, make sure that the directory is mounted on a partition without the noexec flag, or set the jansi.tmpdir system property to point to a proper location.  osinfo: %s%n", file.getName(), file, OSInfo.getNativeLibFolderPathForCurrentOS());
                }
                System.err.println(e);
            }
        }
        return false;
    }

    private static String randomUUID() {
        return Long.toHexString(new Random().nextLong());
    }

    private static int readNBytes(InputStream inputStream, byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = inputStream.read(bArr, i, length - i);
            if (i2 <= 0) {
                break;
            }
            i += i2;
        }
        return i;
    }
}
