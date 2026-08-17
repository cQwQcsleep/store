package com.reandroid.utils.io;

import com.reandroid.arsc.ARSCLib;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.io.FileUtil;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FileUtil {
    private static String def_prefix;
    private static final Map<String, File> TEMP_DIRS = new HashMap();
    private static final String[] FILE_SIZE_UNITS = {" bytes", " Kb", " Mb", " Gb", " Tb", " Pb"};

    public static String combineFilePath(String str, String str2) {
        return combinePath(File.separatorChar, str, str2);
    }

    public static String combinePath(char c, String str, String str2) {
        if (StringsUtil.isEmpty(str)) {
            return str2;
        }
        if (StringsUtil.isEmpty(str2)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length() + str2.length() + 1);
        sb.append(str);
        if (str.charAt(str.length() - 1) != c) {
            sb.append(c);
        }
        sb.append(str2);
        return sb.toString();
    }

    public static String combineUnixPath(String str, String str2) {
        return combinePath('/', str, str2);
    }

    public static void createNewFile(File file) throws IOException {
        ensureParentDirectory(file);
        if (file.isFile()) {
            file.delete();
        }
        file.createNewFile();
    }

    public static void deleteDirectory(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                file.delete();
                return;
            }
            for (File file2 : fileArrListFiles) {
                deleteDirectory(file2);
            }
            file.delete();
        }
    }

    public static void deleteEmptyDirectory(File file) {
        if (file == null || !file.isDirectory()) {
            return;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            file.delete();
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (!file2.isDirectory()) {
                return;
            }
            deleteEmptyDirectory(file2);
        }
        deleteIfEmptyDirectory(file);
    }

    private static void deleteIfEmptyDirectory(File file) {
        if (file == null || !file.isDirectory()) {
            return;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            file.delete();
        }
    }

    public static void ensureParentDirectory(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null || parentFile.exists()) {
            return;
        }
        parentFile.mkdirs();
    }

    public static int getClassesDexNumber(String str) {
        if (str == null) {
            return -1;
        }
        if (str.equals("classes.dex")) {
            return 0;
        }
        if (str.startsWith("classes") && str.endsWith(".dex")) {
            try {
                int i = Integer.parseInt(str.substring(7, str.length() - 4));
                if (i < 2) {
                    return -1;
                }
                return i;
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    private static String getDefRootName() {
        if (def_prefix == null) {
            def_prefix = "tmp_" + ARSCLib.getName() + "-" + ARSCLib.getVersion();
        }
        return def_prefix;
    }

    public static String getExtension(File file) {
        return getExtensionForSimpleName(file.getName());
    }

    private static String getExtensionForSimpleName(String str) {
        if (str.endsWith(".9.png")) {
            return ".9.png";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf < 0 ? StringsUtil.EMPTY : str.substring(iLastIndexOf);
    }

    public static String getFileName(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = str.lastIndexOf(92);
        }
        return iLastIndexOf >= 0 ? str.substring(iLastIndexOf + 1) : str;
    }

    public static String getNameWoExtension(File file) {
        return getNameWoExtensionForSimpleName(file.getName());
    }

    private static String getNameWoExtensionForSimpleName(String str) {
        if (str.endsWith(".9.png")) {
            return str.substring(0, str.length() - 6);
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf < 0 ? str : str.substring(0, iLastIndexOf);
    }

    public static String getParent(String str) {
        if (StringsUtil.isEmpty(str)) {
            return StringsUtil.EMPTY;
        }
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = str.lastIndexOf(92);
        }
        return iLastIndexOf <= 0 ? StringsUtil.EMPTY : str.substring(0, iLastIndexOf + 1);
    }

    public static File getTempDir(String str) {
        File writableTempDir;
        synchronized (FileUtil.class) {
            if (str == null) {
                try {
                    str = getDefRootName();
                } catch (Throwable th) {
                    throw th;
                }
            }
            Map<String, File> map = TEMP_DIRS;
            writableTempDir = map.get(str);
            if (writableTempDir == null) {
                writableTempDir = getWritableTempDir(str);
                if (writableTempDir != null) {
                    writableTempDir.deleteOnExit();
                    map.put(str, writableTempDir);
                }
            } else if (!writableTempDir.exists()) {
                writableTempDir.mkdir();
                writableTempDir.deleteOnExit();
            }
        }
        return writableTempDir;
    }

    private static File getWritableTempDir(File file, String str) {
        File file2 = new File(file, str);
        if (!file2.isDirectory() && !file2.mkdirs()) {
            return null;
        }
        String str2 = "test_" + System.currentTimeMillis() + "-";
        for (int i = 0; i < 9999; i++) {
            File file3 = new File(file2, str2 + i);
            if (!file3.exists()) {
                try {
                    if (!file3.createNewFile() || !file3.delete()) {
                        break;
                    }
                    return file2;
                } catch (IOException unused) {
                }
            }
        }
        return null;
    }

    public static InputStream inputStream(File file) throws IOException {
        if (file.isFile()) {
            return new FileInputStream(file);
        }
        s8g.a("No such file: ", file);
        return null;
    }

    public static List<File> listClassesDex(File file) {
        ArrayCollection arrayCollection = new ArrayCollection();
        if (file != null && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    if (file2.isFile() && getClassesDexNumber(file2) != -1) {
                        arrayCollection.add(file2);
                    }
                }
            }
            arrayCollection.sort(new Comparator() { // from class: es4
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return CompareUtil.compare(FileUtil.getClassesDexNumber((File) obj), FileUtil.getClassesDexNumber((File) obj2));
                }
            });
        }
        return arrayCollection;
    }

    public static FileChannel openReadChannel(File file) throws IOException {
        if (file.isFile()) {
            return new FileInputStream(file).getChannel();
        }
        s8g.a("No such file: ", file);
        return null;
    }

    public static FileChannel openWriteChannel(File file) throws IOException {
        if (file.isDirectory()) {
            r8g.a("File is directory: ", file);
            return null;
        }
        ensureParentDirectory(file);
        if (!file.exists() || file.delete()) {
            file.createNewFile();
        }
        return new FileOutputStream(file).getChannel();
    }

    public static OutputStream outputStream(File file) throws IOException {
        ensureParentDirectory(file);
        return new FileOutputStream(file);
    }

    public static void setDefaultTempPrefix(String str) {
        synchronized (FileUtil.class) {
            try {
                if (ObjectsUtil.equals(str, def_prefix)) {
                    return;
                }
                String str2 = def_prefix;
                if (str2 != null) {
                    TEMP_DIRS.remove(str2);
                }
                def_prefix = str;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String shortPath(File file, int i) {
        File file2 = file;
        while (i > 0) {
            File parentFile = file2.getParentFile();
            if (parentFile == null) {
                break;
            }
            i--;
            file2 = parentFile;
        }
        if (file == file2) {
            return file.getName();
        }
        return file.getAbsolutePath().substring(file2.getAbsolutePath().length() + 1);
    }

    public static String toReadableFileSize(long j) {
        if (j < 0) {
            return Long.toString(j);
        }
        String[] strArr = FILE_SIZE_UNITS;
        String str = "";
        int i = 0;
        long j2 = 0;
        while (i < strArr.length) {
            long j3 = i == 0 ? 1024L : 1000L;
            str = strArr[i];
            long j4 = j / j3;
            if (j4 == 0) {
                break;
            }
            j2 = j - (j3 * j4);
            i++;
            j = j4;
        }
        if (j2 == 0) {
            return j + str;
        }
        return j + Constants.ATTRVAL_THIS + j2 + str;
    }

    public static File toTmpName(File file) {
        File parentFile = file.getParentFile();
        String str = file.getName() + ".tmp";
        return parentFile == null ? new File(str) : new File(parentFile, str);
    }

    @Deprecated
    public static void writeUtf8(File file, String str) throws IOException {
        IOUtil.writeUtf8(str, file);
    }

    public static String getExtension(String str) {
        return getExtensionForSimpleName(getFileName(str));
    }

    public static String getNameWoExtension(String str) {
        return getNameWoExtensionForSimpleName(getFileName(str));
    }

    public static File getTempDir() {
        return getTempDir(null);
    }

    public static int getClassesDexNumber(File file) {
        if (file != null) {
            return getClassesDexNumber(file.getName());
        }
        return -1;
    }

    private static File getWritableTempDir(String str, String str2) {
        if (str == null) {
            return null;
        }
        return getWritableTempDir(new File(str), str2);
    }

    private static File getWritableTempDir(String str) {
        File writableTempDir = getWritableTempDir(System.getProperty("java.io.tmpdir", null), str);
        if (writableTempDir == null) {
            writableTempDir = getWritableTempDir(System.getProperty("user.home", null), str);
        }
        if (writableTempDir != null) {
            return writableTempDir;
        }
        File file = new File(new File("tmp").getAbsolutePath());
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            file = parentFile;
        }
        return getWritableTempDir(file.getAbsolutePath(), str);
    }
}
